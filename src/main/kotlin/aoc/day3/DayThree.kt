package aoc.day3

fun main() {
    runOnInput(dayThreeExampleInput)
    println("\n-------------------\n")
    runOnInput(dayThreeInput)
}

fun runOnInput(input: Input) {
    val gammaAndEpsilon = calculateGammaAndEpsilon(input)
    val oxygenGeneratorRating = findOxygenGeneratorRating(input)
    val cO2ScrubberRating = findCO2ScrubberRating(input)
    val lifeSupportRating = calculateLifeSupportRating(oxygenGeneratorRating, cO2ScrubberRating)

    println("Gamma and Epsilon: $gammaAndEpsilon")
    println("Power consumption is: " + gammaAndEpsilon.toPowerConsumption())
    println("Oxygen rating: $oxygenGeneratorRating [${oxygenGeneratorRating.binaryStringToLong()}]")
    println("CO2 rating: $cO2ScrubberRating ${cO2ScrubberRating.binaryStringToLong()}")
    println("Life support rating: $lifeSupportRating")
}

fun findOxygenGeneratorRating(input: Input)  = findRating(input, ::mostCommonBitAtOffset)
fun findCO2ScrubberRating(input: Input) = findRating(input, ::leastCommonBitAtOffset)

fun calculateGammaAndEpsilon(input: Input) = Pair(
    input.first().mapIndexed { index, _ -> mostCommonBitAtOffset(input, index) }.join().binaryStringToLong(),
    input.first().mapIndexed { index, _ -> leastCommonBitAtOffset(input, index) }.join().binaryStringToLong()
)

fun calculateLifeSupportRating(a: String, b: String) = a.binaryStringToLong() * b.binaryStringToLong()

//
// Support functions
//

fun bitsAt(input: Input, offset: Int): Collection<Char> = input.map { it[offset] }

fun mostCommonBitAtOffset(input: Input, offset: Int) =
    if (bitsAt(input, offset).count { it == '1' } >= (input.size / 2.0)) '1' else '0'

fun leastCommonBitAtOffset(input: Input, offset: Int) =
    if (bitsAt(input, offset).count { it == '0' } <= (input.size / 2.0)) '0' else '1'

fun findRating(input: Input, filter: (Input, Int) -> Char, offset: Int = 0): String = if (input.size == 1) {
    input.first()
} else {
    findRating(filterOnPosition(input, offset, filter(input, offset)), filter, offset + 1)
}

fun filterOnPosition(input: Input, offset: Int, filter: Char) = input.filter { it[offset] == filter }

//
// Helper extension functions
//

fun String.binaryStringToLong() = this.toLong(2)
fun Pair<Long, Long>.toPowerConsumption() = this.first * this.second
fun List<Char>.join() = this.joinToString("")