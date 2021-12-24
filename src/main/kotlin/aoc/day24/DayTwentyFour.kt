package aoc.day24

import aoc.Day
import aoc.cons
import aoc.mapSecond
import aoc.splitLines
import kotlinx.coroutines.*
import java.io.File

class DayTwentyFour : Day(24) {

    override fun test() {
        testOutput()
//        check(computeAluState(parse("inp x\nmul x -1"), "5").x == -5)
//        check(computeAluState(parse("inp z\ninp x\nmul z 3\neql z x"), "39").z == 1)
//        check(computeAluState(parse("inp z\ninp x\nmul z 3\neql z x"), "38").z == 0)
//        check(computeAluState(parse("inp z\ninp x\nmul z 3\neql z x"), "27").z == 0)
    }

    override fun solvePartOne(input: String) = helper(parse(input))

    override fun solvePartTwo(input: String): Long {
        TODO("Not yet implemented")
    }
}

private fun serialNumberGenerator() = sequence {
    for (n in 99999999999999L downTo 0) {
        if (!n.toString().contains('0')) {
            yield(n)
        }
    }
}

private fun serialNumberGeneratorRRR() = sequence {
    for (n in 99999999999999L downTo 0) {
        if (!n.toString().contains('0')) {
            yield(n)
        }
    }
}

private fun randomSerialNumberGenerator() = sequence {
    while (true) {
        val n = (0..99999999999999L).random()
        if (!n.toString().padStart(14, '0').contains('0')) {
            yield(n)
        }
    }
}

private fun toFile(instructions: List<Instruction>, input: String) {
    val (_, debug) = computeAluState(instructions, input, AluState.empty())
    var file = 0
    for (line in debug) {
        if (line.startsWith("Inp")) {
            file++
        }
        File("debug/groups/$file-$input.txt").appendText(line + "\n")
    }
    File("debug/$input.txt").writeText(debug.joinToString("\n"))
}


private fun helper(instructions: List<Instruction>): Long {
    println(calcZ(MyInput("11111111111111")) == computeAluState(instructions, "11111111111111").first.z)
    println(calcZ(MyInput("15495271165446")) == computeAluState(instructions, "15495271165446").first.z)
    println(calcZ(MyInput("13579246899999")) == computeAluState(instructions, "13579246899999").first.z)
    // toFile(instructions, "15495271165446")
    // toFile(instructions, "13579246899999")

    return -1L
}

class MyInput(s: String) : Input() {
    val iterator by lazy { s.asSequence().map { it.digitToInt().toLong() }.iterator() }
    override fun next() = iterator.next()
}

private fun solve(instructions: List<Instruction>): Long {
    val nrGen = serialNumberGenerator()
    var result: Long? = null

    return runBlocking {
        val jobs = (1..9)
            .map { prefix ->
                async(Dispatchers.IO) {

                    nrGen.map { num ->
                        val temp = calcZ(MyInput(prefix.toString() + num.toString()))

                        if (temp == 0L) {
                            println("$num $temp")
                        }
                        num to temp
                    }.filter { it.second == 0L }.first().first
                }
            }.awaitAll()

        jobs.maxOf { it }
    }
}

private fun computeAluState(instructions: List<Instruction>, input: String, debug: Boolean = true) =
    computeAluState(instructions, input, AluState.empty(), debug)

private fun compile(instructions: List<Instruction>, input: String): List<String> {
    val (instruction, nextInstructions) = instructions.cons()

    val (inputVar, nextInput) = if (instruction is Inp) {
        input.first().digitToInt() to input.drop(1)
    } else {
        // NOOP
        0 to input
    }

    val line = compile(instruction, inputVar)

    return listOf(line) + if (nextInstructions.isEmpty()) {
        emptyList()
    } else {
        compile(nextInstructions, nextInput)
    }
}

private fun computeAluState(
    instructions: List<Instruction>,
    input: String,
    aluState: AluState,
    debug: Boolean = true
): Pair<AluState, List<String>> {
    val (instruction, nextInstructions) = instructions.cons()

    val (inputVar, nextInput) = if (instruction is Inp) {
        input.first().digitToInt() to input.drop(1)
    } else {
        // NOOP
        0 to input
    }

    val newState = compute(instruction, inputVar, aluState)

    val debugString = if (debug) listOf("${instruction.toString().padEnd(10, ' ')} $newState") else emptyList()

    return if (nextInstructions.isEmpty()) {
        newState to debugString
    } else {
        computeAluState(nextInstructions, nextInput, newState, debug).mapSecond { debugString + it }
    }
}

private fun compile(instruction: Instruction, input: Int): String {
    return when (instruction) {
        is Inp -> "${instruction.p1} = input.next()"
        is Mul -> "${instruction.p1} = mul(${instruction.p1}, ${instruction.p2})"
        is Div -> "${instruction.p1} = div(${instruction.p1}, ${instruction.p2})"
        is Mod -> "${instruction.p1} = mod(${instruction.p1}, ${instruction.p2})"
        is Add -> "${instruction.p1} = add(${instruction.p1}, ${instruction.p2})"
        is Eql -> "${instruction.p1} = eql(${instruction.p1}, ${instruction.p2})"
    }
}

private fun compute(instruction: Instruction, input: Int, state: AluState): AluState {
    val p1d = state[instruction.p1]

    return when (instruction) {
        is Inp -> {
            state.with(instruction.p1, input.toLong())
        }
        is Mul -> {
            val p2d = if (instruction.p2.toLongOrNull() == null) state[instruction.p2] else instruction.p2.toLong()
            state.with(instruction.p1, p1d * p2d)
        }
        is Div -> {
            val p2d = if (instruction.p2.toLongOrNull() == null) state[instruction.p2] else instruction.p2.toLong()
            state.with(instruction.p1, p1d / p2d)
        }
        is Add -> {
            val p2d = if (instruction.p2.toLongOrNull() == null) state[instruction.p2] else instruction.p2.toLong()
            state.with(instruction.p1, p1d + p2d)
        }
        is Mod -> {
            val p2d = if (instruction.p2.toLongOrNull() == null) state[instruction.p2] else instruction.p2.toLong()
            state.with(instruction.p1, p1d % p2d)
        }
        is Eql -> {
            val p2d = if (instruction.p2.toLongOrNull() == null) state[instruction.p2] else instruction.p2.toLong()
            state.with(instruction.p1, if (p1d == p2d) 1 else 0)
        }
    }
}

private fun parse(input: String) = input.splitLines().map { parseInstruction(it) }
private fun parseInstruction(line: String): Instruction {
    val (ins, p1, p2) = line.split(" ") + listOf("NOOP")
    if (p1.length > 1) throw RuntimeException("Invalid parameter")

    return when (ins) {
        "inp" -> Inp(p1)
        "mul" -> Mul(p1, p2)
        "add" -> Add(p1, p2)
        "div" -> Div(p1, p2)
        "mod" -> Mod(p1, p2)
        "eql" -> Eql(p1, p2)
        else -> throw RuntimeException("Unknown instruction $ins")
    }
}

data class AluState(val w: Long, val x: Long, val y: Long, val z: Long) {
    operator fun get(register: String): Long = when (register) {
        "w" -> w
        "x" -> x
        "y" -> y
        "z" -> z
        else -> throw RuntimeException("Invalid register $register")
    }

    fun with(register: String, value: Long): AluState = when (register) {
        "w" -> AluState(value, this.x, this.y, this.z)
        "x" -> AluState(this.w, value, this.y, this.z)
        "y" -> AluState(this.w, this.x, value, this.z)
        "z" -> AluState(this.w, this.x, this.y, value)
        else -> throw RuntimeException("Invalid register $register")
    }

    companion object {
        fun empty() = AluState(0, 0, 0, 0)
    }
}

internal sealed class Instruction(val p1: String)
internal class Inp(register: String) : Instruction(register) {
    override fun toString() = "Inp $p1"
}

internal class Mul(register: String, val p2: String) : Instruction(register) {
    override fun toString() = "Mul $p1 $p2"
}

internal class Add(register: String, val p2: String) : Instruction(register) {
    override fun toString() = "Add $p1 $p2"
}

internal class Div(register: String, val p2: String) : Instruction(register) {
    override fun toString() = "Div $p1 $p2"
}

internal class Mod(register: String, val p2: String) : Instruction(register) {
    override fun toString() = "Mod $p1 $p2"
}

internal class Eql(register: String, val p2: String) : Instruction(register) {
    override fun toString() = "Eql $p1 $p2"
}
