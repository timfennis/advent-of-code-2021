package aoc.day18

import aoc.Day
import aoc.splitLines
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.system.exitProcess

class DayEighteenTheBrokenVersion : Day(18) {

    override val exampleSolution: List<Long> = listOf(4140, -1)

    @Suppress("UNREACHABLE_CODE")
    override fun test() {

        // Check example addition
        val result = parse("[1,2]") + parse("[[3,4],5]")
        println("Adding [1,2] to [[3,4],5]")
        check(result.toString() == "[[1,2],[[3,4],5]]")

        // Check reduction
        println("Reducing: [[[[[9,8],1],2],3],4]")
        check(parse("[[[[[9,8],1],2],3],4]").reduce() == parse("[[[[0,9],2],3],4]"))

        println("Reducing: [7,[6,[5,[4,[3,2]]]]]")
        check(parse("[7,[6,[5,[4,[3,2]]]]]").reduce() == parse("[7,[6,[5,[7,0]]]]"))

        println("Reducing: [[6,[5,[4,[3,2]]]],1]")
        check(parse("[[6,[5,[4,[3,2]]]],1]").reduce() == parse("[[6,[5,[7,0]]],3]"))

        println("Reducing: [[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]")
        check(parse("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]").reduce() == parse("[[3,[2,[8,0]]],[9,[5,[7,0]]]]"))

        // Check addition with reduction and splitting
        println("Adding: [[[[4,3],4],4],[7,[[8,4],9]]] + [1,1]")
        check(parse("[[[[4,3],4],4],[7,[[8,4],9]]]") + parse("[1,1]") == parse("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"))

        // Testenx
        val sumExample1 = sumList(listOf("[1,1]", "[2,2]", "[3,3]", "[4,4]"))
        println("Sum example 1: $sumExample1")
        check(sumExample1 == parse("[[[[1,1],[2,2]],[3,3]],[4,4]]"))

        val sumExample2 = sumList(listOf("[1,1]", "[2,2]", "[3,3]", "[4,4]", "[5,5]"))
        println("Sum example 2: $sumExample2")
        check(sumExample2 == parse("[[[[3,0],[5,3]],[4,4]],[5,5]]"))


        val sumExample3 = sumList(listOf("[1,1]", "[2,2]", "[3,3]", "[4,4]", "[5,5]", "[6,6]"))
        println("Sum example 3: $sumExample3")
        check(sumExample3 == parse("[[[[5,0],[7,4]],[5,5]],[6,6]]"))

        // Steps before big
        println("-----------------------------------------------")
        val a = parse("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]")
        val b = parse("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]")
        val c = a + b
        println("$a + $b = $c")
        check(c == parse("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"))

        // Big example
        val stringers = listOf(
            "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
            "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
            "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
            "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
            "[7,[5,[[3,8],[1,4]]]]",
            "[[2,[2,2]],[8,[8,1]]]",
            "[2,9]",
            "[1,[[[9,3],9],[[9,0],[0,7]]]]",
            "[[[5,[7,4]],7],1]",
            "[[[[4,2],2],6],[8,7]]",
        )

        println("---- LARGER EXAMPLE ----")
        val final = stringers.map { parse(it) }.reduce { acc, num -> println("Current acc: $acc + $num"); acc + num }
        println("Final $final")
        println("Magnitude: ${final.magnitude()}")
    }

    override fun solvePartOne(input: String) = input.splitLines()
        .map { parse(it) }
        .reduce { acc, snailNum -> acc + snailNum }
        .also { println("Final sum: $it") }
        .magnitude()
        .toLong()

    override fun solvePartTwo(input: String) = -1L
}

fun sumList(list: List<String>) = list.map { parse(it) }.reduce { acc, num -> acc + num }

sealed class SnailNum {
    abstract fun reduce(): SnailNum
    abstract fun splitOnce(): Split
    abstract fun explodeOnce(depth: Int = 1): Explosion
    abstract fun withLeftCarry(carry: Int): SnailNum
    abstract fun withRightCarry(carry: Int): SnailNum
    abstract operator fun plus(other: SnailNum): SnailNum
    abstract fun magnitude(): Int
    abstract fun toInt(): Int
}

data class SnailReal(val v: Int) : SnailNum() {
    companion object {
        val Zero = SnailReal(0)
    }

    override fun reduce() = this

    override fun splitOnce() = when {
        v > 9 -> Split(SnailPair(SnailReal(floor(v / 2.0).toInt()), SnailReal(ceil(v / 2.0).toInt())), true)
        else -> Split(this, false)
    }

    override fun explodeOnce(depth: Int) = Explosion(this, 0, 0, false)

    override fun withLeftCarry(carry: Int) = SnailReal(v + carry)
    override fun withRightCarry(carry: Int) = SnailReal(v + carry)

    override fun plus(other: SnailNum) = when (other) {
        is SnailReal -> SnailReal(this.v + other.v)
        else -> throw RuntimeException("Invalid operator")
    }

    override fun magnitude() = v

    operator fun plus(int: Int) = SnailReal(this.v + int)

    override fun toInt(): Int = v

    override fun toString() = "$v"
}

data class SnailPair(val l: SnailNum, val r: SnailNum) : SnailNum() {
    override fun toString() = "[$l,$r]"

    override operator fun plus(other: SnailNum) = when (other) {
        is SnailPair -> SnailPair(this, other).reduce()
        else -> throw RuntimeException("Invalid operator")
    }

    override fun magnitude() = (l.magnitude() * 3) + (r.magnitude() * 2)

    override fun toInt() = throw RuntimeException("Cannot convert SnailPair to Int")

    override fun reduce(): SnailNum {
        val (exploded, _, _, hasExploded) = this.explodeOnce()

        if (hasExploded) {
            return exploded.reduce()
        }

        val (split, hasSplit) = this.splitOnce()

        if(hasSplit) {
            return split.reduce()
        }

        return split
    }

    override fun splitOnce(): Split {
        val (newLeft, leftHasSplit) = l.splitOnce()

        if (leftHasSplit) {
            return Split(SnailPair(newLeft, r), true);
        }

        val (newRight, rightHasSplit) = r.splitOnce()

        return Split(SnailPair(l, newRight), rightHasSplit)
    }


    override fun withLeftCarry(carry: Int) = when {
        r is SnailReal -> SnailPair(l, r + carry)
        else -> SnailPair(l, r.withLeftCarry(carry))
    }

    override fun withRightCarry(carry: Int) = when {
        l is SnailReal -> SnailPair(l + carry, r)
        else -> SnailPair(l.withRightCarry(carry), r)
    }

    override fun explodeOnce(depth: Int) = when {
        depth == 5 && l is SnailReal && r is SnailReal -> {
            Explosion(SnailReal.Zero, l.v, r.v, true)
        }
        depth <= 4 -> {
            val (leftNum, leftCarryFromLeft, rightCarryFromLeft, leftHasExploded) = this.l.explodeOnce(depth + 1)

            // If left has been reduced abort the right side and just return nothing
            val (rightNum, leftCarryFromRight, rightCarryFromRight, rightHasExploded) = if (leftCarryFromLeft > 0 || rightCarryFromLeft > 0) {
                Explosion(this.r, 0, 0, true)
            } else {
                this.r.explodeOnce(depth + 1)
            }

            Explosion(
                SnailPair(leftNum.withLeftCarry(leftCarryFromRight), rightNum.withRightCarry(rightCarryFromLeft)),
                leftCarryFromLeft,
                rightCarryFromRight,
                leftHasExploded or rightHasExploded
            )
        }
        else -> throw RuntimeException("What?!??!?")
    }
}

class IncompleteSnailPair(val l: SnailNum) : SnailNum() {
    override fun reduce() = throw RuntimeException("Cannot reduce IncompleteSnailPair")
    override fun splitOnce() = throw RuntimeException("Cannot splitOnce IncompleteSnailPair")
    override fun magnitude() = throw RuntimeException("Cannot magnitude IncompleteSnailPair")
    override fun explodeOnce(depth: Int) = throw RuntimeException("Cannot reduceWithCarry IncompleteSnailPair")

    override fun withLeftCarry(carry: Int): SnailNum =
        throw RuntimeException("Cannot withLeftCarry IncompleteSnailPair")

    override fun withRightCarry(carry: Int): SnailNum =
        throw RuntimeException("Cannot withRightCarry IncompleteSnailPair")

    override fun plus(other: SnailNum) = throw RuntimeException("Cannot add IncompleteSnailPair")
    override fun toInt() = throw RuntimeException("Cannot toInt IncompleteSnailPair")
}

object EmptySnailPair : SnailNum() {
    override fun reduce() = throw RuntimeException("Cannot reduce EmptySnailPair")
    override fun splitOnce() = throw RuntimeException("Cannot splitOnce IncompleteSnailPair")
    override fun magnitude() = throw RuntimeException("Cannot magnitude IncompleteSnailPair")
    override fun explodeOnce(depth: Int) = throw RuntimeException("Cannot reduceWithCarry EmptySnailPair")

    override fun withLeftCarry(carry: Int): SnailNum = throw RuntimeException("Cannot withLeftCarry EmptySnailPair")
    override fun withRightCarry(carry: Int): SnailNum = throw RuntimeException("Cannot withRightCarry EmptySnailPair")
    override fun plus(other: SnailNum) = throw RuntimeException("Cannot add EmptySnailPair")
    override fun toInt() = throw RuntimeException("Cannot toInt EmptySnailPair")
}

data class Explosion(val num: SnailNum, val leftCarry: Int = 0, val rightCarry: Int = 0, val hasExploded: Boolean = false)
data class Split(val nem: SnailNum, val hasSplit: Boolean = false)