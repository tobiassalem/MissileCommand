import java.util.*;

/**
 * This class implements all the game logic for Missile Command game.
 *
 * @author Tobias Salem, alias Vassago
 * @version 1.0
 */
public class GameLogic {

   /** Power regeneration rate. */
   public final static float POWER_REGEN = .005F;

   /** Power loss rate (per click). */
   public final static float POWER_HIT = .01F;

   /** The space to put between buildings, in pixels. */
   public final static int BUILDING_SPACING = 30;

  /** The width of each building, in pixels. */
  public final static int BUILDING_WIDTH = 60;

  /** The maximum height to make each building, in pixels. */
  public final static int MAX_HEIGHT = 50;

  /** The nr of buildings in the game */
  public final static int NR_OF_BUILDINGS = 5;

  /** The maximum nr of missiles in the game */
  public final static int MAX_NR_OF_MISSILES = 10;

  /** Used to determine the velocity of newly created missiles. */
  public final static int MAX_VEL = 10;
  
  /**
   * Random number generator.
   * Defaults to a generator seeded with the current time.
   */
  private Random gen = new Random();

  /**
   * Creates a new instance of GameLogic with all fields set to their
   * default values.
   */
  public GameLogic() { }

   /** Runs the game for one time step. **/
   public void updateCycle(GameState state) {
      createMissiles(state);
      updateMissiles(state);
      updatePlayerInput(state);
      updateExplosions(state);

      if(state.power < 1.0) {
         state.power += POWER_REGEN;
      }
   }

   /**
    * Update the state of the missile by one time step by moving them
    * appropriately and checking for collisions with buildings
    * @param state the current GameState
    */
   private void updateMissiles(GameState state) {
      Iterator missiles = state.missiles.iterator();
      Iterator buildings;
      boolean buildingHit;

      // Iterate over each currently active missile.
      while(missiles.hasNext()) {
         Missile m = (Missile)missiles.next();

         // Move the missile and get its new location.
         m.move();
         Vector2D location = m.getLocation();

         // If missile went of the left or right edge of the screen, remove it
         if (location.getIComp() < 0 ||
             location.getIComp() > state.worldWidth)
         {
            missiles.remove();
         }
         // If missile hit the ground, remove it, flag building hit and create an explosion
         else if (location.getJComp() < 0)
         {
            location.setJComp(0);
            missiles.remove();
            buildingHit = true;
            state.explosions.add(m.explode());
         }
         else
         {
            // Loop over all the buildings to see if we hit any of them.
            buildings = state.buildings.iterator();
            buildingHit = false;
            while(buildings.hasNext() && !buildingHit) {
               Building b = (Building)buildings.next();
               if(b.isInterior(m.getLocation())) {
                  missiles.remove();
                  buildingHit = true;
                  state.explosions.add(m.explode());
               }
            } // end while(buildings.hasNext() && !buildingHit)
         } // end else
      } // end while(missiles.hasNext())

   } // end method updateMissiles(GameState state)


   /**
    * Adds missiles to the game (as defined by the state object)
    * @param state the current GameState
    */
   private void createMissiles(GameState state) {

       // Adds up to 2 missiles if there are
       // two few missiles currently alive.
      if(state.missiles.size() < gen.nextInt(5)) {
         int max = gen.nextInt(3);
         for (int i = 0; i <= max; i++) {
            int x = gen.nextInt(state.worldWidth);
            int y = state.worldHeight;
            int dx = gen.nextInt(MAX_VEL*2) - MAX_VEL;
            int dy = -2 - gen.nextInt(MAX_VEL);
            Missile m = new Missile(new Vector2D(x, y), new Vector2D(dx, dy));
            m.setExplosionSize(gen.nextInt(Missile.MAX_EXPLOSION_SIZE));
            m.setSize(5 + gen.nextInt(5));
            state.missiles.add(m);
         }
      }
   }

   /**
    * Processes all the clicks by the user since this method was last called.
    * @param state the current GameSate
    */
   private void updatePlayerInput(GameState state){
      Iterator clicks = state.playerClicks.iterator();
      Iterator buildings;
      boolean isExplosionClick = true;

      // Loop over all the clicks.
      while (clicks.hasNext()) {
         Vector2D c = (Vector2D) clicks.next();
         clicks.remove();

         // Used to indicate if c should create an explosion or not.
         isExplosionClick = true;

         // Loop over all the buildings to see if the player clicked on one.
         buildings = state.buildings.iterator();
         while (buildings.hasNext()) {
            Building b = (Building)buildings.next();
            if (b.isInterior(c)) {
               // Clicked inside a building,
               // repair the building and deduct points
               b.repair();
               state.score -= 10;

               // Don't create an explosion by clicking on a building.
               isExplosionClick = false;
            }
         }

         // Check to see if we need to create an explosion.
         if (isExplosionClick && state.power > 0.0F) {
            // Decrease the power so that future explosions are smaller.
            state.power -= POWER_HIT;

            // Create an explosion. The maximum size depends on the power.
            // A user click results in a DefenderExplosion
            state.explosions.add(new DefenderExplosion(c, (int)(50*state.power), 5));
         }
      } // end of looping over user clicks
   }

   /**
    * Check to see if explosions have destroyed missiles or buildings.
    * @param state the current GameState
    */
   private void updateExplosions(GameState state) {
      Iterator explosions = state.explosions.iterator();

      // Used to keep track of any new explosions, such as ones
      // created when an existing explosion destroys a missile.
      LinkedList newExplosions = new LinkedList();

      // Loop over the current set of explosions.
      while (explosions.hasNext()) {
         Explosion e = (Explosion) explosions.next();

         if (e.explode()) {
            // The explosion has reached its maximum size.
            explosions.remove();
         } else {
            // Check if the explosion has destroyed a missile.
            Iterator missiles = state.missiles.iterator();
            while (missiles.hasNext()) {
               Missile m = (Missile) missiles.next();
               if (e.intersects(m)) {
                  // Explosion intersects a missile,
                  // adds a new explosion and give the player some points.
                  newExplosions.add(m.explode());
                  missiles.remove();
                  state.score += 1000;                  
               }
            }

            // Check if the explosion hit a building.
            Iterator buildings = state.buildings.iterator();
            while (buildings.hasNext()) {
               Building b = (Building) buildings.next();
               if (e.intersects(b)) {
                  // Explosion intersects a building,
                  // code damages the building and then
                  // removes it if it is destroyed.
                  b.damage(e.getDamage());
                  if (b.isDestroyed()) {
                     buildings.remove();
                  }
                  
               } // end if explosion intersects building
            } // end of looping over buildings
         } // end else block (explosion has not reached is maximum size)
      } // end of looping over explosions

      // Add all the new explosions.
      state.explosions.addAll(newExplosions);
   }

   /**
    * Initializes the game state by setting the power and adding buildings.
    * @param state the GameState to initialize
    */
   public void initializeGameState(GameState state) {
      initializeSkyLine(state);
      state.power = 1.0F;
   }

   /**
    * Adds buildings to the game (as defined by the state object)
    * @param state the current GameState
    */
   private void initializeSkyLine(GameState state) {

      // creates a skyline
      for (int point = 0; point < state.worldWidth; /* increment in the body */ ) {
         int incr = 15 + gen.nextInt(BUILDING_WIDTH);

         Vector2D topLeft = new Vector2D(point, gen.nextInt(MAX_HEIGHT) + MAX_HEIGHT);
         Vector2D bottomRight = new Vector2D(point + incr, 0);
         state.buildings.add(new Building(topLeft, bottomRight));

         // Increment the loop index here
         point += incr + 5 + gen.nextInt(BUILDING_SPACING);
      }
  }

   /**
    * Checks if the game is over
    * @param state the current GameState
    * @return true if game is considered to be over, false otherwise
    */
   public boolean isGameOver(GameState state) {
      // The game is over when there are no buildings left
      if (state.buildings.size() < 1)
        return true;
      else
        return false;
   }

}
