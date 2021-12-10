@file:Suppress("unused")

package aoc

fun <A, B> Map<A, B>.flip() = this.entries.associateBy({ it.value }) { it.key }

fun String.splitLines() = this.split("\n")

fun List<Int>.median() =
    this.sorted().let { sorted ->
        if (sorted.size % 2 == 0) {
            sorted[(sorted.size / 2)] + sorted[(sorted.size / 2) + 1]
        } else {
            sorted[(sorted.size / 2)]
        }
    }


fun List<Double>.median() =
    this.sorted().let { sorted ->
        if (sorted.size % 2 == 0) {
            sorted[(sorted.size / 2)] + sorted[(sorted.size / 2) + 1]
        } else {
            sorted[(sorted.size / 2)]
        }
    }


fun List<Long>.median(): Long =
    this.sorted().let { sorted ->
        if (sorted.size % 2 == 0) {
            sorted[(sorted.size / 2)] + sorted[(sorted.size / 2) + 1]
        } else {
            sorted[(sorted.size / 2)]
        }
    }
