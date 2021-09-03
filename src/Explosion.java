/**
 * This class represents a simple two-dimensional explosion
 * in the Missile Command game world.
 * 
 * @author Tobias Salem, alias Vassago
 * @version 1.0
 */
public abstract class Explosion {

   /** The location of the center of the explosion. */
   protected Vector2D location;

   /** The radius of the explosion measured in pixels.  Defaults to 0. */
   protected int currentRadius = 0;

   /**
    * The maximum radius of the explosion measured in pixels.
    * The default value is 40 pixels.
    */
   protected int maxRadius = 40;

   /**
    * The growth speed measured in radius pixels per time step.
    * The default value is 5 radius pixels per time step.
    */
   protected int growthSpeed = 5;

   /** The damage of the explosion, floating point value between 0 and 1 */
   protected float damage = 0.1F;

   /**
    * Creates a new instance of Explosion, with everything set to its
    * default value.
    */
   public Explosion() { }

   /**
    * Constructs an Explosion with a current radius of 0 centered at the
    * {@link Vector2D} location, with the specified maximum radius (in
    * pixels) and rate of growth (in pixels per time step).
    * @param location the location of the explosion
    * @param maxRadius the maximum radius of the explosion
    * @param growthSpeed the growth speed of the explosion
    */
   public Explosion(Vector2D location, int maxRadius, int growthSpeed) {
      this.location = location;
      this.maxRadius = maxRadius;
      this.growthSpeed = growthSpeed;
   }

   /**
    * Returns the location of the center of the explosion
    * @return vector representing the center of the explosion
    */
   public Vector2D getLocation() { return this.location; }

   /**
    * Sets the location of the center of the epxlosion
    * @param location vector representing the center of the explosion
    */
   public void setLocation(Vector2D location) { this.location = location; }

   /**
    * Returns the maximum radius of the explosion
    * @return the maxium radius of the explosion
    */
   public int getMaxRadius() { return this.maxRadius; }


   /**
    * Sets the maximum radius of the explosion
    * @param maxRadius the maxium radius of the explosion
    */
   public void setMaxRadius(int maxRadius) { this.maxRadius = maxRadius; }

   /**
    * Returns the rate of growth (in pixels per time step) of the
    * explosion
    * @return the growth speed of the explosion
    */
   public int getGrowthSpeed() { return this.growthSpeed; }

   /**
    * Sets how many pixels the radius of the explosion will grow every
    * time step.
    * @param growthSpeed the growth speed of the explosion
    */
   public void setGrowthSpeed(int growthSpeed) {
      this.growthSpeed = growthSpeed;
   }

   /**
    * Returns the current radius of the explosion.
    * @return the current radius of the explosion
    */
   public int getCurrentRadius() { return currentRadius; }

   /**
    * Set the current radius of the explosion. If this is greater than the
    * maximum radius then the maximum radius is used.
    * @param currentRadius the radius to be set for the exploion
    */
   public void setCurrentRadius(int currentRadius ) {
      this.currentRadius =
         currentRadius > maxRadius ? maxRadius : currentRadius;
   }

   /**
    * Returns the damage of this explosion
    * @return damage of this explosion
    */
   public float getDamage() {
       return this.damage;
   }

   /**
    * Sets the damage of this explosion
    * @param dmg the damage value of this explosion
    */
   public void setDamage(float dmg) {
       this.damage = dmg;
   }

   /**
    * Increase the explosion radius by the rate of growth for one time step.
    * This method is synchronized to protect the progression of the explosion.
    * @return true if the explosion has reached its maximum radius, false otherwise
    */
   public synchronized boolean explode() {
      return (currentRadius += growthSpeed) > maxRadius;
   }


   /**
    * Checks if this explosion intersects the given missile
    * @param missile which to chek for intersection
    * @return true if the missile intersects this explosion, false otherwise
    */
   public boolean intersects(Missile missile) {
      return location.distanceTo(missile.getLocation()) <= currentRadius;
   }


   /**
    * Checks if this explosion interescts the given building
    * @param building which to check for intersection
    * @return true if the building intersects this explosion, false otherwise
    */
   public boolean intersects(Building building) {
      // cache all variables:
      int left, right, top, myX, myY;
      left = building.getTopLeft().getIComp();
      right = building.getBottomRight().getIComp();
      top = building.getTopLeft().getJComp();
      myX = location.getIComp();
      myY = location.getJComp();

      boolean inX, inY;
      inX = ((myX < right) && (myX > left));
      inY = ((myY < top));

      // Case 1: above top of building, to sides:
      if (!inX && !inY) { // only check 2 top corners
         return
            (location.distanceTo(building.getTopLeft()) < currentRadius) ||
            (location.distanceTo(new Vector2D(right, top)) < currentRadius);
      }

      // Case 2: directly on top of building.
      if (inX && !inY) {
         return ((myY - top) < currentRadius);
      }

      // Case 3: on either side of building.
      if (!inX && inY) {
         return
            ((myX > right) && ((myX - right) < currentRadius)) ||
            ((myX < left) && ((left - myX) < currentRadius));
      }

      // Case 4: if none of the above then explosion must be inside.
      return true;
   }

}
