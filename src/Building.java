/**
 * This class represents a simple two-dimensional building 
 * in the Missile Command game world.
 *
 * @author Tobias Salem, alias Vassago
 * @version 1.0
 */
public class Building {

   /**
    * The top left coordinate of the building, from the user's perspective.
    * Missile command uses Quadrant 1 of the two-dimensional Cartesian
    * coordinate system.  Therefore the origin is at the bottom left corner
    * of the window.  The default value is null.
    */
   private Vector2D topLeft;

   /**
    * The bottom right coordinate of the building, from the user's
    * perspective.  Missile command uses Quadrant 1 of the two-dimensional
    * Cartesian coordinate system.  Therefore the origin is at the bottom
    * left corner of the window.  The default values are null.
    */
   private Vector2D bottomRight;

   /**
    * Describes the health of the building and ranges from 0 to 1 inclusive.
    * Default value is 1.0F.
    */
   private float health = 1.0F;

   /**
    * Describes the repair rate of the building and ranges from 0 to 1
    * inclusive. Default value is 0.2F.
    */
   private float repairRate = 0.2F;

   /**
    * Creates a new Building instance with everything set to their default
    * values.
    */
   public Building() { }

   /**
    * Creates a new instance of the building with everything set to their
    * default value except the vectors describing the top left and bottom right corners.
    * These vectors are given the valyes passed as arguments.
    * @param topLeft vector representing the top left of the building
    * @param bottomRight vector representing the bottow right of the building
    */
   public Building(Vector2D topLeft, Vector2D bottomRight) {
      this.topLeft = topLeft;
      this.bottomRight = bottomRight;
   }

   /**
    * Get the object that defines the top left corner of the building.
    * @return vector representing the top left corner of the building
    */
   public Vector2D getTopLeft() { return this.topLeft; }

   /**
    * Set the the location of the top left corner of the building to the
    * argument without duplicating the argument.  The coordinate should be
    * specified from the user's perspective, i.e., under the assumption that
    * the game window is Quadrant 1 in the usual Cartesian plane with the
    * origin at the lower left corner.
    * @param topLeft vector which should represent the top left corner of the building
    */
   public void setTopLeft(Vector2D topLeft) { this.topLeft = topLeft; }

   /**
    * Returns the point object that defines the bottom right corner of the
    * building
    * @return vector representing the bottom right corner of the building
    */
   public Vector2D getBottomRight() { return bottomRight; }

   /**
    * Set the location of the bottom right corner of the building to the
    * argument without duplicating the argument. The coordinate should be specified from the
    * user's perspective, i.e., under the assumption that the game window is
    * Quadrant 1 in the usual Cartesian plane with the origin at the lower
    * left corner.
    * @param bottomRight vector which should represent the bottom right corner
    * of the building
    */
   public void setBottomRight(Vector2D bottomRight) {
      this.bottomRight = bottomRight;
   }

   /**
    * Returns the current health of this building. The value will be between
    * 0 and 1, inclusive. A value of 0 indicates that the building is destroyed.
    * @return floating point value representing the health of the building
    */
   public float getHealth() { return this.health; }

   /**
    * Sets the current health value of the building. The health value must
    * be between 0 and 1, inclusive. If an invalid value is passed the health
    * will automatically be assigned the value of 0.
    * @param health the health value which should be assigned to the building
    */
   public void setHealth(float health) {
      if ((0.0F <= health) && (health <= 1.0F)) {
         this.health = health;
      } else {
         this.health = 0.0F;
      }
   }

   /**
    * Returns the repair rate of this building.  The repair rate will be
    * between 0 and 1, inclusive.
    * @return the repair rate of this building.
    */
   public float getRepairRate() { return this.repairRate; }

   /**
    * Sets the repair rate of this building.  The repair rate must be
    * between 0 and 1, inclusive.  Everytime the building is repaired, the
    * health is increased by the repair rate.  If an invalid repair rate is
    * given then the repair rate is set to 0.
    * @param repairRate the new repair rate of this building
    */
   public void setRepairRate(float repairRate) {
      if ((0.0F <= repairRate) && (repairRate <= 1.0F)) {
         this.repairRate = repairRate;
      } else {
         this.repairRate = 0.0F;
      }
   }

   /**
    * Damages this building by the given amount.
    * If the building's health falls below 0, then it is set at 0.
    * @param damageAfflicted the damage afflicted to this building
    */
   public void damage(float damageAfflicted) {
    health -= damageAfflicted;
    if (health < 0.0F) {
        health = 0.0F;
    }
   }

   /**
    * Checks if the building is destroyed
    * @return true if the health of the building is equal or below 0, false otherwise
    */
   public boolean isDestroyed() {
      // Just for safety using floating points we test that the health is
      // less than or equal to 0.0.
      return (health <= 0.0F);
   }

   /**
    * Repairs the building by increasing the health of the
    * building by the repair rate. If the health exceeds 1 it is set to 1.
    */
   public void repair() {
      health += repairRate;
      if (health > 1.0F) {
         health = 1.0F;
      }
   }

   /**
    * Checks if the given vector is interior to the building.
    * @param v a non null vector to check against the building
    * @return true if the given vector is interior to the building, false otherwise
    */
   public boolean isInterior(Vector2D v) {
      return (v.getJComp() <= topLeft.getJComp()) &&
             (topLeft.getIComp() <= v.getIComp()) &&
             (v.getIComp() <= bottomRight.getIComp());
   }

}
