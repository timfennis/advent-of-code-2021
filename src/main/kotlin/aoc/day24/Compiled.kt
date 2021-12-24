package aoc.day24

abstract class Input {
    abstract fun next(): Long
}

fun testOutput () {
    for (i in 1..9L) {
        for (j in 1..9L) {
            println("$i, $j: " + f(j, f(i, 0, 1, 13, 14), 1, 12, 8))
        }

    }
}
fun calcZ(input: Input): Long {
    var z = 0L

    z = f(input.next(), z, 1, 13, 14) //  It just adds 1 to 14
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

private fun f(numIn: Long, z: Long, div: Int, p2: Int, p3: Int): Long {
    val mod = ((z % 26) + p2)
    return if (mod == numIn) {
        (z / div)
    } else {
        ((z / div) * 26) + (p3 + numIn)
    }
}

