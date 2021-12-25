package aoc.day24

import aoc.cons
import arrow.core.*

abstract class Input {
    abstract fun next(): Long
}

typealias PFun = (Long, Long) -> Long

fun testOutput() {

    for (n10 in 1..9L) {
        for (n11 in 1..9L) {
            try {
                println("$n10 $n11: " + f11(n11, f10(n10, 0)))
            } catch(t: Throwable) {
                println("$n10 $n11: Failed")
            }
        }
    }
//    for (n1 in 1..9L) {
//        for (n2 in 1..9L) {
//            try {
//                println("$n1 $n2: " + f14(n1, f13(n2, f2(n2 , f1(n1, 0)))))
//            } catch (t: Throwable) {
//                println("$n1 $n2 failed")
//            }
//        }
//    }
    // 13499629698991
    // println(calcZ(MyInput("11111111111111")))
//    for (i in 1..9L) {
//        println("$i: " + f13(i, f2(i, 0)))
//    }
//    println(f14(4, f1(5, 0)))
//    println(f13(5, f2(5, 0)))
//    println(calcZ(MyInput("93499629698999")))
//    for (i in 1..9L) {
//        for (j in 1..9L) {
//            println("$i, $j: " + f(j, f(i, 0, 1, 13, 14), 1, 12, 8))
//        }
//    }
//    for (i in 1..9L) {
//        for (z in 0..10000L) {
//            val res = f(i, z, 26, -14, 3)
//            if (res == 0L) {
//                println("$i // $z = $res")
//            }
//        }
//    }

//    val solution = searchMax(0L, functions.reversed())
//    println("Solution: $solution")

//    val rev = searchMin(0L, functions.reversed())
//    println("Solution: $rev")

    // 164118121421
//    val foo = searchMin(0L, functions.reversed().take(14));
//    println(foo)
}

fun searchMax(search: Long, functions: List<PFun>): Option<String> {
    val (ff, fnext) = functions.cons()

    for (i in 9 downTo 1L) {
        for (z in 0..1_000_000L) {
            val ans = ff(i, z)

            if (ans == search) {
                if (fnext.isEmpty()) {
                    return Some("$i")
                } else {

                    val next = searchMax(z, fnext).map { it + i }

                    if (next is Some) {
                        return next
                    }
                }

            }
        }
    }

    println("Failing at ${functions.size}")

    return None
}

//13499629698991
//13499629698991

fun searchMin(search: Long, functions: List<PFun>, iter: Long= 10000): Option<String> {
    val (ff, fnext) = functions.cons()

    for (i in 1..9L) {
        for (z in 0.. iter) {
            val ans = ff(i, z)

            if (ans == search) {
                if (fnext.isEmpty()) {
                    return Some("$i")
                } else {

                    val next = searchMin(z, fnext).map { it + i }

                    if (next is Some) {
                        return next
                    }
                }
            }
        }
    }

    return None
}

val f1 = ::f.partially3(1).partially3(13).partially3(14)   // push
    val f2 = ::f.partially3(1).partially3(12).partially3(8)    // push
        val f3 = ::f.partially3(1).partially3(11).partially3(5)    // push
        val f4 = ::f.partially3(26).partially3(0).partially3(4)    // pop
        val f5 = ::f.partially3(1).partially3(15).partially3(10)   // push
        val f6 = ::f.partially3(26).partially3(-13).partially3(13) // pop
        val f7 = ::f.partially3(1).partially3(10).partially3(16)   // push
        val f8 = ::f.partially3(26).partially3(-9).partially3(5)   // pop
        val f9 = ::f.partially3(1).partially3(11).partially3(6)    // push
            val f10 = ::f.partially3(1).partially3(13).partially3(13)  // push
            val f11 = ::f.partially3(26).partially3(-14).partially3(6) // pop
        val f12 = ::f.partially3(26).partially3(-3).partially3(7)  // pop
    val f13 = ::f.partially3(26).partially3(-2).partially3(13) // pop
val f14 = ::f.partially3(26).partially3(-14).partially3(3) // pop

private val functions = listOf(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14)
fun calcZ(input: Input): Long {
    var z = 0L

    z = f1(input.next(), z) //  It just adds 1 to 14
    z = f2(input.next(), z)
    z = f3(input.next(), z)
    z = f4(input.next(), z)
    z = f5(input.next(), z)
    z = f6(input.next(), z)
    z = f7(input.next(), z)
    z = f8(input.next(), z)
    z = f9(input.next(), z)
    z = f10(input.next(), z)
    z = f11(input.next(), z)
    z = f12(input.next(), z)
    z = f13(input.next(), z)
    z = f14(input.next(), z)

    return z
}

private fun push(numIn: Long, z: Long, check: Int, offset: Int): Long {
    assert (((z % 26) + check) != numIn)
    return (z * 26) + (offset + numIn)
}

private fun pop(numIn: Long,z: Long, check: Int, offset: Int): Long {
    assert (((z % 26) + check) == numIn)
    return z / 26
}

private fun f(numIn: Long, z: Long, div: Int, check: Int, offset: Int): Long {
    val mod = ((z % 26) + check)
    return if (mod == numIn) {
        if (div != 26) throw RuntimeException("Pop while push expected")
        (z / div)
    } else {
        if (div != 1) throw RuntimeException("Push while pop expected")
        ((z / div) * 26) + (offset + numIn)
    }
}

