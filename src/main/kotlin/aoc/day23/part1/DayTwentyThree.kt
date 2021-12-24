package aoc.day23.part1

import arrow.core.memoize


fun solve(input: String): Long {
    return solveImp(State.from(input)).toLong()
}

private fun solveImp(initialState: State): Int {
    // clear cache
    STATE_CACHE.clear()

    val mapping = mutableMapOf(initialState.hash to initialState)
    var iterations = 0;

    do {
        // Print progress
        // println("Iteration: ${iterations++}, Size: ${mapping.size}")
        if (mapping.containsKey(desiredState.hash)) {
            // Not sure why but once we find a solution it tends to be the correct one
            return mapping[desiredState.hash]!!.cost
        }

        for ((_, state) in mapping.toMap()) {
            for (next in state.nextPossibleStates()) {
                if (!mapping.containsKey(next.hash)) {
                    mapping[next.hash] = next
                } else {
                    if (mapping[next.hash]!!.cost > next.cost) {
                        mapping[next.hash] = next
                    }
                }
            }
        }

    } while (true)
}


private fun Content.cost() = when((this as? Amphipod)?.type) {
    'A' -> 1
    'B' -> 10
    'C' -> 100
    'D' -> 1000
    else -> throw RuntimeException("Whot are ye duin")
}

private val nodes = listOf(
    Node("bl2"),
    Node("bl1"),

    Node("h12"),
    Node("h23"),
    Node("h34"),

    Node("r1.1"), Node("r1.2"),
    Node("r2.1"), Node("r2.2"),
    Node("r3.1"), Node("r3.2"),
    Node("r4.1"), Node("r4.2"),

    Node("br1"),
    Node("br2"),
).associateBy { it.name }

private fun node(name: String) = nodes[name]!!

private val vertices = listOf(
    1 to listOf("bl2" to "bl1", "br1" to "br2"),
    2 to listOf("bl1", "h12", "h23", "h34", "br1").zipWithNext(),
    // hallway to rooms
    2 to listOf(
        "bl1" to "r1.1",
        "h12" to "r1.1",
        "h12" to "r2.1",
        "h23" to "r2.1",
        "h23" to "r3.1",
        "h34" to "r3.1",
        "h34" to "r4.1",
        "br1" to "r4.1",
    ),
    1 to listOf("r1.1", "r1.2").zipWithNext(),
    1 to listOf("r2.1", "r2.2").zipWithNext(),
    1 to listOf("r3.1", "r3.2").zipWithNext(),
    1 to listOf("r4.1", "r4.2").zipWithNext()
).flatMap { (cost, b) ->
    b.flatMap { (a, b) ->
        listOf(
            Vertex(node(a), node(b), cost),
            Vertex(node(b), node(a), cost)
        )
    }
}

private val verticesByNode = vertices.groupBy { it.start }

private val desiredState = State(
    mapOf(
        node("bl2") to Empty,
        node("bl1") to Empty,
        node("h12") to Empty,
        node("h23") to Empty,
        node("h34") to Empty,
        node("br1") to Empty,
        node("br2") to Empty,
        node("r1.1") to Amphipod('A'),
        node("r1.2") to Amphipod('A'),
        node("r2.1") to Amphipod('B'),
        node("r2.2") to Amphipod('B'),
        node("r3.1") to Amphipod('C'),
        node("r3.2") to Amphipod('C'),
        node("r4.1") to Amphipod('D'),
        node("r4.2") to Amphipod('D'),
    ), 0
)

private fun Map<Node, Content>.isEmpty(node: Node) = this[node]!! == Empty

internal sealed class Content
internal data class Amphipod(val type: Char) : Content() {
    override fun toString() = "$type"
}

internal object Empty : Content() {
    override fun toString() = "."
}

internal data class State(val map: Map<Node, Content>, val cost: Int) {
    override fun toString() = """
        Expended: $cost
        #############
        #${map[node("bl2")]}${map[node("bl1")]}.${map[node("h12")]}.${map[node("h23")]}.${map[node("h34")]}.${map[node("br1")]}${
        map[node(
            "br2"
        )]
    }#
        ###${map[node("r1.1")]}#${map[node("r2.1")]}#${map[node("r3.1")]}#${map[node("r4.1")]}###
        ###${map[node("r1.2")]}#${map[node("r2.2")]}#${map[node("r3.2")]}#${map[node("r4.2")]}###
          ######### 
    """.trimIndent()

    val hash by lazy { map.values.joinToString("") }

    companion object {
        fun from(input: String): State {
            val c = input.filter { it.isLetter() && it.isUpperCase() }
            return State(
                mapOf(
                    node("bl2") to Empty,
                    node("bl1") to Empty,
                    node("h12") to Empty,
                    node("h23") to Empty,
                    node("h34") to Empty,
                    node("br1") to Empty,
                    node("br2") to Empty,
                    node("r1.1") to Amphipod(c[0]),
                    node("r1.2") to Amphipod(c[4]),
                    node("r2.1") to Amphipod(c[1]),
                    node("r2.2") to Amphipod(c[5]),
                    node("r3.1") to Amphipod(c[2]),
                    node("r3.2") to Amphipod(c[6]),
                    node("r4.1") to Amphipod(c[3]),
                    node("r4.2") to Amphipod(c[7]),
                ), 0
            )
        }
    }
}

internal data class Node(val name: String) {
    val type: NodeType = if (name[0] == 'r') NodeType.ROOM else NodeType.HALL
}

internal data class Vertex(val start: Node, val end: Node, val cost: Int)
internal enum class NodeType { ROOM, HALL }

private val STATE_CACHE = mutableMapOf<String, Set<State>>()

private fun State.nextPossibleStates(): Set<State> {
    val currentState = this@nextPossibleStates
    val outputSet = mutableSetOf<State>()

    if (STATE_CACHE.containsKey(currentState.hash)) {
        return STATE_CACHE[currentState.hash]!!
    }

    for ((currentLocation, amphipod) in map.filterValues { it is Amphipod }) {

        if (currentLocation.type == NodeType.ROOM && currentLocation.backingRoomList()
                .all { desiredState.map[it]!! == currentState.map[it] }
        ) {
            continue
        }

        val destinations = nodes
            // Filter nodes that are occupied
            .filterValues { map.isEmpty(it) }
            // Filter nodes that have empty backing nodes
            .filterNot { (_, node) -> hasEmptyBackingNodes(node, map) }
            // Only allow travel from room to corridor and corridor to room
            .filterValues { it.type != currentLocation.type }
            // Filter nodes that are unreachable
            .filterValues { destination ->
                shortestPath(currentLocation, destination).drop(1).all { map.isEmpty(it) }
            }
            // Filter nodes that are illegal because they belong to other Amphipods
            .filterNot { (_, destination) -> destination.type == NodeType.ROOM && desiredState.map[destination]!! != amphipod }
            // Filter nodes that are illegal because other Amphipods are still inside
            .filterNot { (_, destination) ->
                destination.type == NodeType.ROOM && desiredState.map[destination]!! == amphipod && destination.backingRoomList()
                    .any { node ->
                        val nc = map[node]!!; nc is Amphipod && nc.type != (desiredState.map[node]!! as Amphipod).type
                    }
            }

        // Yield all possible states
        for ((_, destination) in destinations) {
            outputSet.add(
                currentState.copy(
                    map = map.swap(currentLocation, destination),
                    cost = cost + (shortestPath(currentLocation, destination).calculateCost() * amphipod.cost())
                )
            )
        }
    }

    STATE_CACHE[currentState.hash] = outputSet

    return outputSet
}

private fun hasEmptyBackingNodes(node: Node, map: Map<Node, Content>): Boolean {
    if (node.type == NodeType.HALL) return false
    val backingNode = node.backingRoomList().drop(1).firstOrNull() ?: return false

    return map[backingNode]!! is Empty
}

private fun Node.backingRoomList() = sequence {
    val room = this@backingRoomList
    if (room.type != NodeType.ROOM) throw RuntimeException("You're fucking it up Jimmy")
    val prefix = room.name.take(3)
    for (number in room.roomNumber..2) {
        yield(node("$prefix$number"))
    }
}

private val Node.roomNumber
    get() = this.name.last().digitToInt()

private fun Map<Node, Content>.swap(from: Node, to: Node) = this.mapValues { (key, _) ->
    when (key) {
        from -> this[to]!!
        to -> this[from]!!
        else -> this[key]!!
    }
}
//
//= this.copy(map =
//}, cost = this.cost + ((room to connectedRoom).cost() * this.map[room]!!.cost()))


// Shortest path hackery!

private fun List<Node>.calculateCost() =
    this.zipWithNext().map { (f, t) -> vertices.first { v -> v.start == f && v.end == t } }.sumOf { v -> v.cost }

private fun shortestPath(begin: Node, end: Node) = shortestPathMemoized(begin, end, listOf(listOf(begin)))

private val shortestPathMemoized = ::shortestPathInternal.memoize()

private fun shortestPathInternal(begin: Node, end: Node, paths: List<List<Node>>): List<Node> {
    val newPaths: List<List<Node>> = paths.flatMap { path ->

        if (path.last() == end) {
            listOf(path)
        } else {
            val interesting = verticesByNode[path.last()]!!.filterNot { it.end in path }
            interesting.map { newNode ->
                path + listOf(newNode.end)
            }
        }
    }

    return if (paths == newPaths) {
        paths.minByOrNull { path -> path.calculateCost() }!!
    } else {
        shortestPathMemoized(begin, end, newPaths)
    }
}

