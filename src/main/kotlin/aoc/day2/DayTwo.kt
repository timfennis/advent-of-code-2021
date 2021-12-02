package aoc.day2

fun main() {
    // The same process input function uses different modifier functions to get to the result
    println("Part one answer: " + processInput(dayTwoInput, ::modifyCoordinates, Pair(0,0)).multiply())
    println("Part two answer: " + processInput(dayTwoInput, ::modifyCoordinatesPartTwo, Triple(0,0,0)).multiply())
}

/**
 * Takes a list of instructions and folds over it with a coordinate modifier until one coordinate remains
 */
fun <T> processInput(input: List<Instruction>, modifier: (T, Instruction) -> T, initial: T): T
    = input.fold(initial) { acc, inst -> modifier(acc, inst) }

/**
 * Modifies coordinates (c) with an instruction according to the rules in part one
 */
fun modifyCoordinates(c: Pair<Int, Int>, instruction: Instruction) = when(instruction.dir) {
    Direction.FORWARD -> Pair(c.first + instruction.distance, c.second)
    Direction.DOWN -> Pair(c.first, c.second + instruction.distance)
    Direction.UP -> Pair(c.first, c.second - instruction.distance)
}

/**
 * Modify coordinates (c) with an instruction according got the rules in part two
 */
fun modifyCoordinatesPartTwo(c: Triple<Int, Int, Int>, instruction: Instruction) = when(instruction.dir) {
    Direction.FORWARD -> Triple(c.first + instruction.distance, c.second + (c.third * instruction.distance), c.third)
    Direction.DOWN -> Triple(c.first, c.second, c.third + instruction.distance)
    Direction.UP -> Triple(c.first, c.second, c.third -  instruction.distance)
}

/**
 * Simple extension functions that calculate the final number from the coordinates
 */
fun Triple<Int, Int, Int>.multiply() = this.first * this.second
fun Pair<Int, Int>.multiply() = this.first * this.second