//Object
public class Character {
    private String name;
    private String description;
    private int health;
    private int attack;
    private int defense;

    /**
     * Displays the character's name, health, attack, defense, and description to
     * the console.
     */
    public void displayStats() {
        System.out.println("--- " + name + " ---");
        System.out.println("Health:      " + health);
        System.out.println("Attack:      " + attack);
        System.out.println("Defense:    " + defense);
        System.out.println("Description: " + description);

    }

    /**
     * Constructor for the Character class. Initializes a character with name,
     * health, attack, defense stats, and a description.
     * 
     * @param name        The character's name
     * @param health      The character's maximum health
     * @param attack      The character's attack damage
     * @param defense     The character's defense rating
     * @param description A story description of the character
     */
    public Character(String name, int health, int attack, int defense, String description) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.description = description;
    }

    // GETTERS
    /**
     * Returns the character's name.
     * 
     * @return The name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the character's maximum health.
     * 
     * @return The character's health value
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the character's attack damage.
     * 
     * @return The character's attack value
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Returns the character's defense rating.
     * 
     * @return The character's defense value
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Returns the character's story description.
     * 
     * @return The character's description
     */
    public String getDescription() {
        return description;
    }

}