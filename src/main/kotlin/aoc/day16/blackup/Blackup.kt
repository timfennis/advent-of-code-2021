package aoc.day16.blackup

import aoc.Day
import aoc.binaryStringToInt
import aoc.binaryStringToLong
import aoc.mapFirst
import java.lang.RuntimeException

class DaySixteen : Day(16) {
    override val exampleSolution = listOf(20, 1L)

    override fun solvePartOne(input: String) = decodePacket(hexToBin(input))
        .also {
            check(decodePacket(hexToBin("C200B40A82")).first.value() == 3L)
            check(decodePacket(hexToBin("04005AC33890")).first.value() == 54L)
            check(decodePacket(hexToBin("880086C3E88112")).first.value() == 7L)
            check(decodePacket(hexToBin("CE00C43D881120")).first.value() == 9L)
            check(decodePacket(hexToBin("D8005AC2A8F0")).first.value() == 1L)
            check(decodePacket(hexToBin("F600BC2D8F")).first.value() == 0L)
            check(decodePacket(hexToBin("9C0141080250320F1802104A08")).first.value() == 1L)
        }
        .let { (packet, _) -> packet.versionSum() }
        .toLong()

    override fun solvePartTwo(input: String) = decodePacket(hexToBin(input))
        .let { (packet, _) -> packet.value() }
}

private fun hexToBin(input: String) = input
    .map { Integer.toBinaryString(it.digitToInt(16)).padStart(4, '0') }
    .joinToString("") { it }

private fun decodePackets(input: String): List<Packet> {
    val (packet, next) = decodePacket(input)

    return if (next.all { it == '0' }) {
        listOf(packet)
    } else {
        listOf(packet) + decodePackets(next)
    }
}

private fun decodePacket(input: String): Pair<Packet, String> {
    val (version, typeRemainder) = input.readBitsWith(3, String::binaryStringToInt)
    val (type, remainingInput) = typeRemainder.readBitsWith(3, String::binaryStringToInt)

    return if (type == 4) {
        val (nextInput, decimal) = readDecimal(remainingInput)
        LiteralPacket(version, decimal) to nextInput
    } else {
        val (operator, nextInput) = readOperator(version, type, remainingInput)
        operator to nextInput
    }
}


sealed class Packet(val version: Int, val type: Int) {
    open fun versionSum() = version
    abstract fun value(): Long

    override fun toString(): String {
        return "Packet(version=$version, type=$type)"
    }

}

class LiteralPacket(version: Int, private val decimal: Long) : Packet(version, 4) {
    override fun value() = decimal

    override fun toString(): String {
        return "LiteralPacket(version=$version, type=$type, decimal=$decimal)"
    }
}

class OperatorPacket(version: Int, type: Int, private val subPackets: List<Packet>) : Packet(version, type) {
    override fun versionSum() = version + subPackets.sumOf { it.versionSum() }
    override fun value(): Long = when (type) {
        0 -> subPackets.sumOf { it.value() }
        1 -> subPackets.fold(1L) { acc, packet -> acc * packet.value() }
        2 -> subPackets.minOf { it.value() }
        3 -> subPackets.maxOf { it.value() }
        5 -> if (subPackets.first().value() > subPackets.drop(1).first().value()) 1 else 0
        6 -> if (subPackets.first().value() < subPackets.drop(1).first().value()) 1 else 0
        7 -> if (subPackets.first().value() == subPackets.drop(1).first().value()) 1 else 0
        else -> throw RuntimeException("Invalid operator packet $type")
    }

    override fun toString(): String {
        return "OperatorPacket(version=$version, type=$type, subPackets=$subPackets)"
    }

}

private tailrec fun readDecimal(input: String, acc: String = ""): Pair<String, Long> =
    if (input.first() == '1') {
        readDecimal(input.drop(5), acc + input.drop(1).take(4))
    } else {
        input.drop(5) to input.drop(1).take(4).binaryStringToLong()
    }

private fun readOperator(version: Int, type: Int, input: String): Pair<OperatorPacket, String> {
    val (first, remainder) = input.readBits(1)
    return if (first == "0") {
        val (subPackLen, subPackRemainder) = remainder.readBitsWith(15, String::binaryStringToInt)

        subPackRemainder.readBitsWith(subPackLen) { OperatorPacket(version, type, decodePackets(it)) }
    } else {
        val (subPackCount, subPacketsData) = remainder.readBitsWith(11, String::binaryStringToInt)

        (0 until subPackCount)
            .fold(Pair(emptyList<Packet>(), subPacketsData)) { (packets, next), _ ->
                decodePacket(next).mapFirst { packets + listOf(it) }
            }
            .mapFirst { OperatorPacket(version, type, it) }
    }
}

private fun String.readBits(amount: Int) = this.take(amount) to this.drop(amount)
private fun <T> String.readBitsWith(amount: Int, mapper: (String) -> T): Pair<T, String> =
    mapper(this.take(amount)) to this.drop(amount)
