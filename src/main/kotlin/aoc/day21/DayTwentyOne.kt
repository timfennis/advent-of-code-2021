package aoc.day21

import arrow.core.memoize


var die = sequence() {
    var value = 0
    while (true) {
        ++value
        yield(value)
        if (value == 100) {
            value = 0
        }
    }
}

fun main() {
    //partOne()
    partTwo()
}

private fun partTwo() {
    val player1 = Player(1, 4, 0)
    val player2 = Player(2, 8, 0)
    var game = Game(player1, player2, player1, 0, 21)

    val (a, b) = simulateRecursiveMemoized(game, null)

    println("Player 1 wins in $a and player 2 wins in $b")
}

private fun partOne() {
    val player1 = Player(1, 5, 0)
    val player2 = Player(2, 10, 0)
    var game = Game(player1, player2, player1, 0, 1000)

    while (!game.hasVictoriousPlayer()) {
        game = game.advance(die.take(3).sum(), 3)
        die = die.drop(3)
    }

    val winner = game.winner()!!
    val loser = game.loser()!!

    println("Player ${winner.index} wins other player had ${loser.score} points dice was rolled ${game.dieRolls} times SCORE: ${game.finalScore()}")
}


data class Player(val index: Int, val position: Int, val score: Int) {
    fun advance(dieRoll: Int): Player {
        var newPos = ((position + dieRoll) % 10)
        if (newPos == 0) newPos = 10
        return Player(index, newPos, this.score + newPos)
    }
}

private val simulateRecursiveMemoized = ::simulateRecursive.memoize()
private fun simulateRecursive(game: Game, dieRoll: Int? = null): Pair<Long, Long> {

    val advancedGame = if (dieRoll != null) {
        game.advance(dieRoll)
    } else {
        game
    }

    if (advancedGame.hasVictoriousPlayer()) {
        return if (advancedGame.winner() == game.player1) {
            1L to 0L
        } else {
            0L to 1L
        }
    }

    return outcomes.map { outcome ->
        simulateRecursiveMemoized(advancedGame, outcome)
    }.fold(0L to 0L) { (x, y), (xx, yy) -> x + xx to y + yy }
}

data class Game(
    val player1: Player,
    val player2: Player,
    val currentPlayer: Player,
    val dieRolls: Int = 0,
    val victoryScore: Int = 1000
) {
    fun advance(dieRoll: Int, rollCount: Int = 3): Game {
        return if (currentPlayer == player1) {
            Game(
                player1 = player1.advance(dieRoll),
                player2 = player2,
                currentPlayer = player2,
                dieRolls = dieRolls + rollCount,
                victoryScore = victoryScore
            )
        } else {
            Game(
                player1 = player1,
                player2 = player2.advance(dieRoll),
                currentPlayer = player1,
                dieRolls = dieRolls + rollCount,
                victoryScore = victoryScore
            )
        }
    }

    fun hasVictoriousPlayer() = player1.score >= victoryScore || player2.score >= victoryScore
    fun player1Score() = player2.score * dieRolls * if (player1 == winner()) 1 else 0
    fun player2Score() = player1.score * dieRolls * if (player2 == winner()) 1 else 0

    fun finalScore() = loser()!!.score * dieRolls

    fun winner(): Player? = when {
        player1.score >= victoryScore -> player1
        player2.score >= victoryScore -> player2
        else -> null
    }

    fun loser(): Player? = when {
        player1.score >= victoryScore -> player2
        player2.score >= victoryScore -> player1
        else -> null
    }
}

private val outcomes = listOf(1,2,3).flatMap { x ->
    listOf(1,2,3).flatMap { y ->
        listOf(1,2,3).map { z ->
            listOf(x,y,z).sum()
        }
    }
}