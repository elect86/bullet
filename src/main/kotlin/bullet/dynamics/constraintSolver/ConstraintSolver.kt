/*
Bullet Continuous Collision Detection and Physics Library
Copyright (c) 2003-2006 Erwin Coumans  http://continuousphysics.com/Bullet/

This software is provided 'as-is', without any express or implied warranty.
In no event will the authors be held liable for any damages arising from the use of this software.
Permission is granted to anyone to use this software for any purpose,
including commercial applications, and to alter it and redistribute it freely,
subject to the following restrictions:

1. The origin of this software must not be misrepresented; you must not claim that you wrote the original software. If you use this software in a product, an acknowledgment in the product documentation would be appreciated but is not required.
2. Altered source versions must be plainly marked as such, and must not be misrepresented as being the original software.
3. This notice may not be removed or altered from any source distribution.
*/

package bullet.dynamics.constraintSolver

import bullet.collision.collisionDispatch.CollisionObject
import bullet.collision.narrowPhaseCollision.PersistentManifold
import bullet.linearMath.DebugDraw
import sun.plugin.com.Dispatcher

enum class ConstraintSolverType { SEQUENTIAL_IMPULSE_SOLVER, MLCP_SOLVER, NNCG_SOLVER;

    val i = 1 shl ordinal
}

interface ConstraintSolver {

    fun prepareSolve(numBodies: Int, numManifolds: Int)

    /** solve a group of constraints    */
    fun solveGroup(bodies: ArrayList<CollisionObject>, numBodies: Int,
                   manifold: ArrayList<PersistentManifold>, numManifolds: Int,
                   constraints: ArrayList<TypedConstraint>, numConstraints: Int,
                   info: ContactSolverInfo, debugDrawer: DebugDraw, dispatcher: Dispatcher): Float

    fun allSolved(info: ContactSolverInfo, debugDrawer: DebugDraw)

    /** clear internal cached data and reset random seed    */
    fun reset()

    val solverType: ConstraintSolverType
}