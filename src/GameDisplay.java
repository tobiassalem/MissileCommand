import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

/**
 * This class implements the main display area for the Missile Command game.
 * The display is stateless in that it does not keep track of the game state.
 * Thus, the state must be passed to the display in order to update its display.
 * 
 * @author Tobias Salem, alias Vassago
 * @version 1.0
 */
public class GameDisplay extends JComponent {

   /**
    * The rate at which the display is faded.  This creates the illusion of
    * missile streaks rather easily.  The value is in the range 0 to 255.
    */
   public final static int FADE_RATE = 1<<5;

   /** The width of the display (in pixels). */
   private int width;

   /** The height of the display (in pixels). */
   private int height;

   /**
    * The display's image is first drawn to this buffer, in effect giving us
    * double buffering and reducing flicker. Should never be null.
    */
   private BufferedImage buffer;

   /**
    * Creates a new instance of GameDisplay with the given width and height
    * @param width the width (in pixels) of the GameDisplay
    * @param height the height (in pixels) of the GameDisplay
    */
   public GameDisplay(int width, int height) {
      this.width = width;
      this.height = height;
      setPreferredSize(new Dimension(width, height));
      setOpaque(true);
      setDoubleBuffered(false);

      buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics g = buffer.getGraphics();
      g.setColor(Color.BLACK);
      g.fillRect(0, 0, width, height);
   }

   /**
    * Returns the dimensions of the display, in pixels.
    * @return the dimensions of the display in pixels
    */
   public Dimension getSize() { return new Dimension(width,height); }

   /**
    * Returns the width of the display, in pixels.
    * @return the width of the display, in pixels.
    */
   public int getWidth() { return width; }

   /**
    * Returns the height of the display, in pixels.
    * @return Returns the height of the display, in pixels.
    */
   public int getHeight() { return height; }

   /**
    * Paints the display using the specified Graphics object.
    * @param g the graphics object on which to pain the display
    */
   public void paintComponent(Graphics g) {
      // Need to make sure we don't modify g in any way.
      Graphics graphics = g.create();
      synchronized (buffer) {
         // Only one thread should access the buffer's Graphics object...
         graphics.drawImage(buffer, 0, 0, this);
      }
   }

   /**
    * Updates the display by using the provided (non-null) state object to
    * draw the current set of buildings, explosions, and missiles.
    * @param state the current state of the game
    */
   public void update(GameState state) {
      Graphics graphics = null;
      synchronized (buffer) {
         // Only one thread should access the buffer's Graphics object...
         graphics = buffer.getGraphics();
      }

      paintBuildings(state.buildings.iterator(),  graphics);
      paintMissiles(state.missiles.iterator(), graphics);
      paintExplosions(state.explosions.iterator(), graphics);
      fadeBuffer(graphics);
   }

   /**
    * Draws a semitransparent black box the size of this display with the
    * provided Graphics object.  The top left of the box is at (0,0) with
    * respect to the graphics coordinate system.
    * @param g the graphics object on which to draw
    */
   private void fadeBuffer(Graphics g) {
      g.setColor(new Color(0,0,0,FADE_RATE));
      g.fillRect(0,0,width,height);
   }

   /**
    * Pain all the building using the provided Graphics object.
    * Assumes that every object returned by the Iterator is a {@link Building}.
    * @param iter an iterator over buildings
    * @param graphics the graphics object on which to paint
    */
   private void paintBuildings(Iterator iter, Graphics graphics) {

      Vector2D topLeftPoint, bottomRightPoint;
      int topLeftXPix, topLeftYPix, bottomRightXPix, bottomRightYPix;
      int buildingWidth, buildingHeight;
      Color buildingColor;

      while(iter.hasNext()) {
         Building b = (Building)iter.next();

         buildingColor = new Color(1.0F-b.getHealth(), b.getHealth(), 0.0F);
         graphics.setColor(buildingColor);

         topLeftPoint = b.getTopLeft();
         topLeftXPix = topLeftPoint.getIComp();
         topLeftYPix = height - topLeftPoint.getJComp();

         bottomRightPoint = b.getBottomRight();
         bottomRightXPix = bottomRightPoint.getIComp();
         bottomRightYPix = height - bottomRightPoint.getJComp();

         buildingWidth = bottomRightXPix - topLeftXPix;
         buildingHeight = bottomRightYPix - topLeftYPix;

         graphics.fillRect
            (topLeftXPix, topLeftYPix, buildingWidth, buildingHeight);
      }
   }

   /**
    * Paint all the missiles using the provided Graphics object.
    * Assumes that every object returned by the Iterator is a {@link Missile}.
    * @param iter an iterator over missiles
    * @param graphics the graphics object on which to paint
    */
   private void paintMissiles(Iterator iter, Graphics graphics) {
      Vector2D location;
      int x, y, size;

      while(iter.hasNext()) {
         Missile m = (Missile)iter.next();
         location = m.getLocation();
         size = m.getSize();
         x = location.getIComp();
         y = height - location.getJComp();

         float hue =
            (float)(m.getExplosionSize()) /
            (Missile.MAX_EXPLOSION_SIZE + Missile.MIN_EXPLOSION_SIZE);

         graphics.setColor(new Color(1.0F, 1.0F - hue, 0.0F));
         graphics.fillOval(x, y, size, size);
      }
   }

   /**
    * Paint all the explosions using the provided Graphics object. Assumes
    * Assumes that every object returned by the Iterator is an {@link Explosion}.
    * @param iter an iterator over explosions
    * @param graphics the graphics object on which to paint
    */
   private void paintExplosions(Iterator iter, Graphics graphics) {
      int currentRadius;
      Vector2D location;
      Color explosionColor;

      while(iter.hasNext()) {
         Explosion e = (Explosion) iter.next();

         float hue = ((float)e.getCurrentRadius()) / e.getMaxRadius();
         graphics.setColor(new Color(1.0F, hue, 0.0F));

         currentRadius = e.getCurrentRadius();
         location = e.getLocation();

         graphics.fillOval
            (location.getIComp() - currentRadius,
             height - location.getJComp() - currentRadius,
             currentRadius * 2,
             currentRadius * 2);
      }
   }

}
