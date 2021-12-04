package aoc.day3

fun main() {
    val gammaAndEpsilon = calculateGammaAndEpsilon(dayThreeInput)
    val oxygenGeneratorRating = findOxygenGeneratorRating(dayThreeInput)
    val cO2ScrubberRating = findCO2ScrubberRating(dayThreeInput)
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

// Support functions

fun mostCommonBitAtOffset(input: Input, offset: Int) =
    input.groupingBy { it[offset] }.eachCount().maxWithOrNull(comparator)!!.key

fun leastCommonBitAtOffset(input: Input, offset: Int) =
    input.groupingBy { it[offset] }.eachCount().minWithOrNull(comparator)!!.key

tailrec fun findRating(input: Input, filterForOffset: (Input, Int) -> Char, offset: Int = 0): String = if (input.size == 1) {
    input.first()
} else {
    // Filter the values that have the bit at the given position and tail recursively repeat for the next position
    findRating(input.filter { it[offset] == filterForOffset(input, offset) }, filterForOffset, offset + 1)
}

// Helper extension functions

private val comparator: Comparator<Map.Entry<Char, Int>> = compareBy({ it.value }, { it.key })

fun String.binaryStringToLong() = this.toLong(2)
fun Pair<Long, Long>.toPowerConsumption() = this.first * this.second
fun List<Char>.join() = this.joinToString("")

