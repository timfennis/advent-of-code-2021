# Advent of Code 2021

## Additional rules

To make it a little harder for myself I set some rules.

1. Only pure functions, so no side effects!
2. Every function must be declared as an expression (except for main because who cares)


```kotlin
fun notLikeThis(a: Int, b: Int): Int {
    val c = a + b
    
    return c
}

fun butLikeThis(a: Int, b: Int) = a + b
```