package utils


data class Coordinate(val x: Int, val y: Int)

operator fun Coordinate.plus(coordinate: Coordinate) = Coordinate(x+coordinate.x, y+coordinate.y)

fun Coordinate.step(direction: Direction) : Coordinate = when(direction) {
    Direction.UP -> Coordinate(x, y+1)
    Direction.LEFT -> Coordinate(x-1, y)
    Direction.RIGHT -> Coordinate(x+1, y)
    Direction.DOWN -> Coordinate(x, y-1)
}