/**
 * This class represents a defender explosion, an explosion made by the
 * player in the Missile Command game world. This type of explosion does not
 * harm buildings.
 *
 * @author Tobias Salem, alias Vassago
 * @version 1.0
 */
public class DefenderExplosion extends Explosion {

    /**
     * Constructs a DefenderExplosion with its default values.
     */
    public DefenderExplosion() {
        super();
        this.damage = 0F;
    }

    /**
     * Constructs a DefenderExplosion with a current radius of 0 centered at the
     * {@link Vector2D} location, with the specified maximum radius (in
     * pixels) and rate of growth (in pixels per time step).
     * @param location the location of the explosion
     * @param maxRadius the maximum radius of the explosion
     * @param growthSpeed the speed with which the explosion grows to maxRadius
     */
    public DefenderExplosion(Vector2D location, int maxRadius, int growthSpeed) {
        super(location, maxRadius, growthSpeed);
        this.damage = 0F;
    }

}
