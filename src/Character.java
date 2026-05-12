//Object
public class Character {
    private String name;
    private String description;
    private int health;
    private int attack;
    private int defense;

    //Stats method
    public void displayStats() {
        System.out.println("--- " + name + " ---");
        System.out.println("Health:      " + health);
        System.out.println("Attack:      " + attack);
        System.out.println("Defense:    " + defense);
        System.out.println("Description: " + description);

        
    }

    //Constructor
    public Character(String name, int health, int attack, int defense, String description) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.description = description;
    }


    //GETTERS
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

    public String getDescription() {
        return description;
    }

}