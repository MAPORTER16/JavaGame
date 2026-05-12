/**
 * LargeEnemy represents a large-sized enemy variant with high stats.
 * Inherits all combat mechanics from the Enemy base class.
 */
public class LargeEnemy extends Enemy {
    /**
     * Constructor for LargeEnemy. Creates a large enemy with predefined stats.
     * 
     * @param name The enemy's name
     */
    public LargeEnemy(String name) {
        super(name, 110, 30, 18, "Large");
    }

}
