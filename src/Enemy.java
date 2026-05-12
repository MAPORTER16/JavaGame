// Base Enemy class
public abstract class Enemy {
    protected String name;
    protected int health;
    protected int attack;
    protected int defense;
    protected String size;

    // Constructor
    public Enemy(String name, int health, int attack, int defense, String size) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.size = size;
    }

    // Methods
    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defense);
        health -= actualDamage;
        System.out.println(name + " takes " + actualDamage + " damage! HP remaining: " + health);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void displayStats() {
        System.out.println("--- " + name + " [" + size + "] ---");
        System.out.println("Health:  " + health);
        System.out.println("Attack:  " + attack);
        System.out.println("Defense: " + defense);
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getSize() {
        return size;
    }
}
