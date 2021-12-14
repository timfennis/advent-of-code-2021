@file:Suppress("unused")

package aoc

import kotlin.math.round

fun <A, B> Map<A, B>.flip() = this.entries.associateBy({ it.value }) { it.key }

fun String.splitLines() = this.split("\n")

fun List<Int>.median() =
    this.sorted().let { sorted ->
        if (sorted.size % 2 == 0) {
            round((sorted[(sorted.size / 2)] + sorted[(sorted.size / 2) + 1]) / 2.0).toInt()
        } else {
            sorted[(sorted.size / 2)]
        }
    }


fun List<Double>.median() =
    this.sorted().let { sorted ->
        if (sorted.size % 2 == 0) {
            (sorted[(sorted.size / 2)] + sorted[(sorted.size / 2) + 1]) / 2.0
        } else {
            sorted[(sorted.size / 2)]
        }
    }


fun List<Long>.median(): Long =
    this.sorted().let { sorted ->
        if (sorted.size % 2 == 0) {
            round((sorted[(sorted.size / 2)] + sorted[(sorted.size / 2) + 1]) / 2.0).toLong()
        } else {
            sorted[(sorted.size / 2)]
        }
    }

fun <T> List<T>.updated(index: Int, value: T) = this.mapIndexed { currentIndex, currentValue -> if(currentIndex == index) value else currentValue  }
fun <K, V> Map<K, V>.updated(key: K, value: V) = mapOf(key to value) + this.filterNot { (lk, _) -> lk == key }

fun <T> List<T>.cons() = this.first() to this.drop(1)