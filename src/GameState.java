import java.util.LinkedList;

/**
 * This class implements a structure to keep track of a Missile Command game's state.
 * Care must be taken to ensure that multiple threads do not modify the same
 * state instance at the same time.
 *
 * @author Tobias Salem, alias Vassago
 * @version 1.0
 */
public class GameState {

   /**
    * A list of all currently active missiles in the game.
    * Each element of this list should be a {@link Missile} instance.
    */
   public LinkedList missiles;

   /**
    * A list of all currently active explosions in the game.
    * Each element of this list should be an {@link Explosion} instance.
    */
   public LinkedList explosions;

   /**
    * A list of all currently active (alive) buildings in the game.
    * Each element of this list should be a {@link Building} instance.
    */
   public LinkedList buildings;

   /**
    * A list of all unprocessed clicks by the player.  Each element of this
    * list should be a {@link Vector2D} instance.  The coordinates are
    * relative to the users view of the game, i.e., (0,0) is in the lower
    * left and positive x and y coordinates go the right and left,
    * respectively.
    */
   public LinkedList playerClicks;

   /** The player's current score. **/
   public int score;

   /**
    * The player's current power level.
    * Should be between 0 and 1, inclusive.
    */
   public float power;

   /** The width of the game world (in pixels). */
   public int worldWidth;

   /** The height of the game world (in pixels). */
   public int worldHeight;

   /**
    * Creates a new instance in which all the public fields are initialized
    * to default values, except for {@link #worldWidth} and {@link
    * #worldHeight}.
    */
   public GameState() {
      missiles = new LinkedList();
      explosions = new LinkedList();
      buildings = new LinkedList();
      playerClicks = new LinkedList();
      score = 0;
      power = 0.0F;
   }

}
