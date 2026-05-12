/**
 * SmallEnemy represents a small-sized enemy variant with low stats.
 * Inherits all combat mechanics from the Enemy base class.
 */
public class SmallEnemy extends Enemy {
    /**
     * Constructor for SmallEnemy. Creates a small enemy with predefined stats.
     * 
     * @param name The enemy's name
     */
    public SmallEnemy(String name) {
        super(name, 30, 12, 3, "Small");
    }

}
