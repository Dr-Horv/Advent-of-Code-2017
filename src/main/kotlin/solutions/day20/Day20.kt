
package solutions.day20

import solutions.Solver

data class Vector3D(val x: Int, val y: Int, val z: Int)

operator fun Vector3D.plus(v2: Vector3D) = Vector3D(x+v2.x, y+v2.y, z+v2.z)

class Particle(var position: Vector3D, var velocity: Vector3D, val acceleration: Vector3D) {

    companion object {
        var nbr = 0
        fun getIdentity(): Int {
            return nbr++
        }
    }

    public val identity = getIdentity()
    fun accelerate() {
        velocity += acceleration
    }

    fun move() {
        position += velocity
    }
}

fun Particle.manhattanDistance(): Int = Math.abs(position.x) + Math.abs(position.y) + Math.abs(position.z)

class Day20: Solver {
    override fun solve(input: List<String>, partTwo: Boolean): String {
        val particles = input.map(this::parseParticle).toMutableList()
        var sortedByDistance = particles.sortedBy(Particle::manhattanDistance)

        while (true) {
            for (i in (1..10)) {
                particles.forEach {
                    it.accelerate()
                    it.move()
                }

                val toRemove = mutableListOf<Particle>()
                for (p in particles) {
                    for (p2 in particles) {
                        if(p != p2 && p.position == p2.position) {
                            toRemove.add(p)
                            toRemove.add(p2)
                        }
                    }
                }

                particles.removeAll(toRemove)
            }



            val newSortedByDistance = particles.sortedBy(Particle::manhattanDistance)

            if(sortedByDistance.map(Particle::identity) == newSortedByDistance.map(Particle::identity)) {
                return if(!partTwo) {
                    sortedByDistance.first().identity.toString()
                } else {
                    particles.size.toString()
                }
            }

            sortedByDistance = newSortedByDistance
        }

    }

    private fun parseParticle(particleDefinition: String): Particle {
        println(particleDefinition)
        val (position, velocity, acceleration) = particleDefinition.split(", ").map(String::trim)
        val p= position.toVector3D()
        val v = velocity.toVector3D()
        val a = acceleration.toVector3D()

        return Particle(p, v, a)
    }
}

private fun String.toVector3D(): Vector3D {
    val stripped = this.substring(3, this.lastIndex)
    val (x,y,z) = stripped.split(",").map(String::trim).map(String::toInt)
    return Vector3D(x,y,z)
}
