public class BossEnemy extends Enemy {
    private String specialAbility;

    public BossEnemy(String name, String specialAbility) {
        super(name, 170, 34, 18, "Boss");
        this.specialAbility = specialAbility;

    }

    // Boss hits harder every other turn
    public int getBonusAttack() {
        return attack + 10;
    }

    public void useSpecialAbility() {
        System.out.println(name + " uses " + specialAbility + "!");
    }

    public String getSpecialAbility() {
        return specialAbility;
    }

    @Override
    public void displayStats() {
        super.displayStats();
        System.out.println("Special: " + specialAbility);
    }
}
