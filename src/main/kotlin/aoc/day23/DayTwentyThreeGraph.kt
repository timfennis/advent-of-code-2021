package aoc.day23

private val input = """
    #############
    #...........#
    ###D#C#A#B###
      #B#C#D#A#
      #########
""".trimIndent()

fun main() {
    val graph = Graph(Node("LL").connectTo(
        Node("LR").apply {
            connectTo(cost = 2, node = Node("R1F").connectTo(Node("R1B")))
            connectTo(cost = 2, node = Node("R12H").apply {
                connectTo(cost = 2, node = Node("R2F").connectTo(Node("R2B")))
                connectTo(cost = 2, node = Node("R23H").apply {
                    connectTo(cost = 2, node = Node("R3F").connectTo(Node("R3B")))
                    connectTo(cost = 2, node = Node("R34H").apply {
                        connectTo(cost = 2, node = Node("R4F").connectTo(Node("R4B")))
                        connectTo(cost = 2, node = Node("RL").connectTo(Node("RR")))
                    })
                })
            })
        }
    ))

    println(graph.nodes)
}

class Graph(node: Node) {
    val nodes = node.getNodes()

    fun getNode(name: String) = nodes.first { it.name == name }
}

class Node(val name: String, ) {
    private var occupant: Ambipod? = null
    private val vertices = mutableListOf<Vertex>()

    fun connectTo(node: Node, cost: Int = 1): Node {
        vertices.add(Vertex(this, node, cost))
        node.vertices.add(Vertex(node, this, cost))
        return node
    }

    fun getNodes(seen: Set<Node> = emptySet()): Set<Node> {
        val newNodes = vertices.flatMap { listOf(it.start, it.end) }.toSet()
            .subtract(seen)

        val intersection = seen.union(newNodes)
        return intersection.union(newNodes.flatMap { it.getNodes(intersection) }.toSet())
    }

    override fun toString(): String {
        return "Node(name='$name', occupant=$occupant)"
    }
}

class Vertex(val start: Node, val end: Node, val cost: Int) {
    override fun toString(): String {
        return "Vertex(start=$start, end=${end}, cost=$cost)"
    }
}

data class Ambipod(val char: Char)

