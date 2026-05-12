/**
 * MediumEnemy represents a medium-sized enemy variant with moderate stats.
 * Inherits all combat mechanics from the Enemy base class.
 */
public class MediumEnemy extends Enemy {
    /**
     * Constructor for MediumEnemy. Creates a medium enemy with predefined stats.
     * 
     * @param name The enemy's name
     */
    public MediumEnemy(String name) {
        super(name, 60, 20, 8, "Medium");
    }

}
