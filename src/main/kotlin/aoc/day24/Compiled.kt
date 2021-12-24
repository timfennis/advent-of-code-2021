package aoc.day24

abstract class Input {
    abstract fun next(): Long
}

fun calcZ(input: Input): Long {
    var z = 0L
    z = f(input.next(), z, 1, 13, 14)
    z = f(input.next(), z, 1, 12, 8)
    z = f(input.next(), z, 1, 11, 5)
    z = f(input.next(), z, 26, 0, 4)
    z = f(input.next(), z, 1, 15, 10)
    z = f(input.next(), z, 26, -13, 13)
    z = f(input.next(), z, 1, 10, 16)
    z = f(input.next(), z, 26, -9, 5)
    z = f(input.next(), z, 1, 11, 6)
    z = f(input.next(), z, 1, 13, 13)
    z = f(input.next(), z, 26, -14, 6)
    z = f(input.next(), z, 26, -3, 7)
    z = f(input.next(), z, 26, -2, 13)
    z = f(input.next(), z, 26, -14, 3)

    return z
}

private fun f(win: Long, z: Long, p1: Int, p2: Int, p3: Int): Long {

    val mod = (z % 26) + p2
    val bool = if (mod != win) 1 else 0

    return ((z / p1) * ((25 * bool) + 1)) + ((p3 + win) * bool)
}

