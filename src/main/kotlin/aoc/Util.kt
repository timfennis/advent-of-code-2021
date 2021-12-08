package aoc

fun <A, B> Map<A, B>.flip() = this.entries.associateBy({ it.value }) { it.key }