
/**
 * This class represent the explosion from a missile on the Missile Command game
 *
 * @author Tobias Salem, alias Vassago
 * @version 1.0
 */
public class MissileExplosion extends Explosion {

    /**
     * Constructs a MissileExplosion with its default values.
     */
    MissileExplosion() {
        super();
    }

    /**
     * Constructs an Explosion with a current radius of 0 centered at the
     * {@link Vector2D} location, with the specified maximum radius (in
     * pixels) and rate of growth (in pixels per time step).
     * @param location the location of the explosion
     * @param maxRadius the maximum radius of the explosion
     * @param growthSpeed the speed with which the explosion grows to maxRadius
     */
    public MissileExplosion(Vector2D location, int maxRadius, int growthSpeed) {
       super(location,maxRadius,growthSpeed);
       this.damage = 0.1F;
    }    

}
