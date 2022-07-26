package gamesense;

import java.util.ArrayList;
import java.util.List;
import shapesandunits.Collidable;
import shapesandunits.Point;
import shapesandunits.Line;

/**
 * @author Dekel Eli 313162422
 * gameEnviroment - holds a list of collidables in the game
 * and can check if a moving collidable is going to hit another collidble.
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    /**
     * Constructor.
     * @param collidables the list of collidables in a game.
     */
    public GameEnvironment(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * default constructor.
     * creates a new list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    //
    /**
     * add the given collidable to the environment.
     * @param c the new collidable.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * @return the list of collidables.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }


    /**
     * getClosestCollision - searches on the list of collidables
     * the collidables the goes through the trajectory.
     * @param trajectory the tested trajectory.
     * @return the closest collision to the start point of the trajectory
     *         null if there are no collisions.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> collisions = new ArrayList<>();
        // collects all of the possible collisions on the trajectory
        for (Collidable collidable : collidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (p != null) {
                collisions.add(new CollisionInfo(p, collidable));
            }
        }
        // if there are collisions, checks who is the closest
        if (collisions.size() != 0) {
            double min = collisions.get(0).collisionPoint().distance(trajectory.start());
            CollisionInfo closest = collisions.get(0);
            double check;
            for (int i = 1; i < collisions.size(); i++) {
                check = collisions.get(i).collisionPoint().distance(trajectory.start());
                if (check < min) {
                    closest = collisions.get(i);
                }
            }
            return closest;
        }
        // passed all objects
        return null;
    }
}