// Base Enemy class
public abstract class Enemy {
    protected String name;
    protected int health;
    protected int attack;
    protected int defense;
    protected String size;

    /**
     * Constructor for the Enemy base class. Initializes an enemy with name, health,
     * attack, defense stats, and a size descriptor.
     * 
     * @param name    The enemy's name
     * @param health  The enemy's maximum health
     * @param attack  The enemy's attack damage
     * @param defense The enemy's defense rating
     * @param size    The enemy's size classification (Small, Medium, Large, Boss)
     */
    public Enemy(String name, int health, int attack, int defense, String size) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.size = size;
    }

    // Methods
    /**
     * Applies damage to the enemy, subtracting defense before health is reduced.
     * Prints damage information to the console.
     * 
     * @param damage The raw damage amount before defense reduction
     */
    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defense);
        health -= actualDamage;
        System.out.println(name + " takes " + actualDamage + " damage! HP remaining: " + health);
    }

    /**
     * Checks if the enemy is still alive.
     * 
     * @return true if health is greater than 0, false otherwise
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Displays the enemy's name, size, health, attack, and defense stats to the
     * console.
     */
    public void displayStats() {
        System.out.println("--- " + name + " [" + size + "] ---");
        System.out.println("Health:  " + health);
        System.out.println("Attack:  " + attack);
        System.out.println("Defense: " + defense);
    }

    // Getters
    /**
     * Returns the enemy's name.
     * 
     * @return The name of the enemy
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the enemy's current health.
     * 
     * @return The enemy's health value
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the enemy's attack damage.
     * 
     * @return The enemy's attack value
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Returns the enemy's defense rating.
     * 
     * @return The enemy's defense value
     */
    public int getDefense() {
        return defense;
    }

    public String getSize() {
        return size;
    }
}
