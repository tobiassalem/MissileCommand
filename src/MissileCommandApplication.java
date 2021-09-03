import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class implements the missile command game as a stand-alone application.
 *
 * @author Tobias Salem, alias Vassago
 * @version 1.0
 */
public class MissileCommandApplication extends JFrame {

   /**
    * Creates and shows a new missile command game display.  When the
    * display (frame) is closed, the JVM will exit.
    */
   public MissileCommandApplication() {
      super("Missile Command by Vassago");
      JPanel screenContents = new JPanel(new BorderLayout());
      GameDisplay display = new GameDisplay(640, 480);
      Scoreboard scoreboard = new Scoreboard();

      screenContents.add(scoreboard, BorderLayout.NORTH);
      screenContents.add(display, BorderLayout.CENTER);
      getContentPane().add(screenContents);

      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);

      GameLogic logic = new GameLogic();
      GameController control = new GameController(display, scoreboard, logic);
      control.startGame();
   }

   /**
    * Runs the missile command application
    * @param args command line arguments, which are ignored
    */
   public static void main(String[] args) {
      MissileCommandApplication ignored = new MissileCommandApplication();
   }

}
