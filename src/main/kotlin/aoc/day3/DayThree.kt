package aoc.day3


fun main () {
    runOnInput(dayThreeExampleInput)
    println("\n-------------------\n")
    runOnInput(dayThreeInput)
}

fun runOnInput(input: Input)
{
    println("Power consumption is: " + calculateGammaAndEpsilon(input).toPowerConsumption())
    println("Most common at 0: " + mostCommonAt(input, 0))
    println("Oxygen rating: " + findOxygenGeneratorRating(input) + " " + findOxygenGeneratorRating(input).toLong(2))
    println("CO2 rating: " + findCO2ScrubberRating(input) + " " + findCO2ScrubberRating(input).toLong(2))
    println("Life support rating: " + calculateLifeSupportRating(findOxygenGeneratorRating(input),findCO2ScrubberRating(input)))
}

fun bitsAt(input: Input, offset: Int): Collection<Char> = input.map { it[offset] }

fun mostCommonAt(input: Input, offset: Int) =
    if (bitsAt(input, offset).count { it == '1' } >= (input.size / 2.0)) '1' else '0'

fun leastCommonAt(input: Input, offset: Int) =
    if (bitsAt(input, offset).count { it == '0' } <= (input.size / 2.0)) '0' else '1'

fun findOxygenGeneratorRating(input: Input, offset: Int = 0): String = if (input.size == 1) {
    input.first()
} else {
    val filtered = filterOnPosition(input, offset, mostCommonAt(input, offset))
    findOxygenGeneratorRating(filtered, offset + 1)
}

fun findCO2ScrubberRating(input: Input, offset: Int = 0): String = if (input.size == 1) {
    input.first()
} else {
    findCO2ScrubberRating(filterOnPosition(input, offset, leastCommonAt(input, offset)), offset + 1)
}

fun filterOnPosition(input: Input, offset: Int, filter: Char) = input.filter { it[offset] == filter  }


fun calculateGammaAndEpsilon(input: Input) = Pair(
    input.first().mapIndexed { index, _ -> mostCommonAt(input, index) }.joinToString("").toLong(2),
    input.first().mapIndexed { index, _ -> leastCommonAt(input, index) }.joinToString("").toLong(2)
)

fun calculateLifeSupportRating(a: String, b: String) = a.toLong(2) * b.toLong(2)

fun Pair<Long, Long>.toPowerConsumption() = this.first * this.second

fun List<Char>.join() = this.joinToString("")