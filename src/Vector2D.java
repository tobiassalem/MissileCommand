
/**
 * This class represents a two dimensional vector.
 *
 * @author Tobias Salem, alias Vassago
 * @version 1.0
 */
public class Vector2D 
{

  private int i;
  private int j;

  /**
   * Default constructor creating a Vector with zero magnitude
   */
  public Vector2D() 
  {
  }

  /**
   * Creates a vector with i and j values given by the arguments.
   * @param i the i component of the vector
   * @param j the j component of the vector
   */
  public Vector2D(int i, int j)
  {
    this.i = i;
    this.j = j;
  }


  /**
   * Copies the input vector, producing a new duplicate vector.
   * @param v the vector to copy and construct a new one
   */
  public Vector2D(Vector2D v) {
    i = v.i;
    j = v.j;
  }

  /**
   * Adds the given vector v to this vector and stores the result in this vector.
   * @param v the vector which to add to this vector
   * @return the resulting vector (this)
   */
  public Vector2D add(Vector2D v)
  {
    this.i += v.getIComp();
    this.j += v.getJComp();
    return this;
  }

  /**
   * Substracts the given vector v from this vector and stores the result in this vector.
   * @param v the vector which to subtract from this vector
   * @return the resulting vector (this)
   */
  public Vector2D subtract(Vector2D v)
  {
    this.i -= v.getIComp();
    this.j -= v.getJComp();
    return this;
  }

  /**
   * Returns the dot product between this vector and the given vector c
   * @param v the vector which to dot product with this vector
   * @return the dot product
   */
  public int dot(Vector2D v)
  {
    return (this.i * v.i) + (this.j * v.j);
  }

  /**
   * Returns the distance between this vector and the given vector v.
   * @param v the vector which to calculate the distance to
   * @return the distance between this vector and the given vector v
   */
  public double distanceTo(Vector2D v)
  {
    int deltaI = this.i - v.i;
    int deltaJ = this.j - v.j;
    return Math.sqrt((deltaI * deltaI) + (deltaJ * deltaJ));    
  }

  /**
   * Returns the magnitude of this vector
   * @return the magnitue of this vector
   */
  public double magnitude()
  {
    return Math.sqrt((i * i) + (j * j)); 
  }
  

  /**
   * Returns the i component of this vector
   * @return the i component of this vector
   */
  public int getIComp()
  {
    return this.i;
  }
  
  /**
   * Returns the j component of this vector
   * @return the j component of this vector
   */
  public int getJComp()
  {
    return this.j;
  }

  /**
   * Sets the i component of this vector
   * @param i the new value for the i component of this vector
   */
  public void setIComp(int i)
  {
    this.i = i;
  }

  /**
   * Sets the j component of this vector
   * @param j the new value for the j component of this vector
   */
  public void setJComp(int j)
  {
    this.j = j;
  }

  /**
   * Returns a string representation of this vector
   * @return a string representation of this vector
   */
  public String toString()
  {
    String s = "Vector < " +i+ "," +j+ ">";
    return s;
  }

   /**
    * Main method for testing this class.
    * The arguments are ignored
    * @param args command line arguments, which are ignored
    */
   public static void main(String args[]) {
      // Construct two vectors
      Vector2D a = new Vector2D(1, 2);
      Vector2D b = new Vector2D(3, 4);
      System.out.println("Vector A: " + a);
      System.out.println("Vector B: " + b);

      // Add and subtract the two vectors (This does not destroy a or b)
      System.out.println("a + b = " + (new Vector2D(a)).add(b) );
      System.out.println("a - b = " + (new Vector2D(a)).subtract(b));

      // Compute the dot product of two points
      System.out.println("a . b = " + a.dot(b));
   }
}