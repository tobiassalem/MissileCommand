/**
 * This class implements a simple two-dimensional missile
 * in the Missile Command game world.
 *
 * @author Tobias Salem
 * @version 1.0
 */
public class Missile {

   /** The maximum size of an explosion that a missile can have. */
   public final static int MAX_EXPLOSION_SIZE= 50;

   /** The minimum size of an explosion that a missile can have. */
   public final static int MIN_EXPLOSION_SIZE= 5;

   /** The location of the missile.  Defaults to null. */
   private Vector2D location;

   /** The velocity of the missile.  Defaults to null. */
   private Vector2D velocity;

   /** The size of the missile (its diameter) in pixels.  Defaults to 6. */
   private int size = 6;

   /** The size of the explosion stored in the missile.  Defaults to 20. */
   private int explosionSize = 20;

   /**
    * The speed at which the explosion contained in the missile grows
    * Defaults to 5.
    */
   private int explosionSpeed = 5 ;

   /**
    * Creates a new Missile with everything set to its default value
    * except the location and velocity which are specified by the arguments
    * @param location the location of the missile
    * @param velocity the velocity of the missile
    */
   public Missile(Vector2D location, Vector2D velocity) {
      this.location = location;
      this.velocity = velocity;
   }

   /**
    * Returns the location of the missile.
    * @return the location of the missile
    */
   public Vector2D getLocation() { return this.location; }

   /**
    * Sets the location of the missile.
    * @param location the location of the vector
    */
   public void setLocation(Vector2D location) { this.location = location; }

   /**
    * Returns the velocity of the missile.
    * @return the velocity of the missile
    */
   public Vector2D getVelocity() { return this.velocity; }

   /**
    * Sets the velocity of the missile.
    * @param velocity the velocity of the missile
    */
   public void setVelocity(Vector2D velocity) { this.velocity = velocity; }

   /**
    * Returns the size of the missile
    * @return the size of the missile
    */
   public int getSize() { return this.size; }

   /**
    * Sets the size of the missile
    * @param size of the missile
    */
   public void setSize(int size) { this.size = size; }

   /**
    * Returns the explosion size of the missile.
    * @return the explosion size of the missile.
    */
   public int getExplosionSize() { return this.explosionSize; }

   /**
    * Sets the explosion size, making sure it is in the appropriate range.
    * @param explosionSize the explosion size for this missile
    */
   public void setExplosionSize(int explosionSize) {
      if(explosionSize > MAX_EXPLOSION_SIZE)
         explosionSize = MAX_EXPLOSION_SIZE;
      else if(explosionSize < MIN_EXPLOSION_SIZE)
         explosionSize = MIN_EXPLOSION_SIZE;
      else this.explosionSize = explosionSize;
   }

   /** Move the location of the missile by its velocity vector. */
   public void move() { location.add(velocity); }

   /**
    * Explodes the missile by returning an explosion object centered at the
    * current location of the missile
    * @return explosion object
    */
   public Explosion explode() {
      // the explosion resulting from a missile is a missile explosion
      return new MissileExplosion(location, explosionSize, explosionSpeed);
   }

}
