public class BossEnemy extends Enemy {
    private String specialAbility;

    /**
     * Constructor for the BossEnemy class. Creates a boss-level enemy with high
     * stats and a special ability.
     * 
     * @param name           The boss's name
     * @param specialAbility The name of the boss's special attack ability
     */
    public BossEnemy(String name, String specialAbility) {
        super(name, 170, 34, 18, "Boss");
        this.specialAbility = specialAbility;

    }

    // Boss hits harder every other turn
    /**
     * Returns the boss's bonus attack damage (base attack + 10).
     * Used on special attack turns.
     * 
     * @return The boss's increased attack value
     */
    public int getBonusAttack() {
        return attack + 10;
    }

    /**
     * Displays the boss's special ability activation.
     */
    public void useSpecialAbility() {
        System.out.println(name + " uses " + specialAbility + "!");
    }

    /**
     * Returns the boss's special ability name.
     * 
     * @return The special ability description
     */
    public String getSpecialAbility() {
        return specialAbility;
    }

    @Override
    public void displayStats() {
        super.displayStats();
        System.out.println("Special: " + specialAbility);
    }
}
