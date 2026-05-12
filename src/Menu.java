import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Menu {
    public static class PlayerLoadout {
        int bonusDamage;
        int armorBonus;
        int smallHealthPacks;
        int mediumHealthPacks;
        int largeHealthPacks;
        int grenadeCount;
        int lockpickCount;
        String weaponName;

        public int consumeBestHealthPack() {
            if (largeHealthPacks > 0) {
                largeHealthPacks--;
                return 50;
            }
            if (mediumHealthPacks > 0) {
                mediumHealthPacks--;
                return 30;
            }
            if (smallHealthPacks > 0) {
                smallHealthPacks--;
                return 10;
            }
            return 0;
        }

        public int getTotalHealthPacks() {
            return smallHealthPacks + mediumHealthPacks + largeHealthPacks;
        }
    }

    public enum LootType {
        SMALL_HEAL,
        MEDIUM_HEAL,
        LARGE_HEAL,
        WEAPON_BUFF,
        ARMOR_BUFF,
    }

    public static class LootItem {
        String name;
        LootType type;
        int value;

        public LootItem(String name, LootType type, int value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }
    }

    public static final Random RNG = new Random();

    public static final LootItem[] SEARCH_TABLE = {
            new LootItem("Small Health Pack", LootType.SMALL_HEAL, 1),
            new LootItem("Medium Health Pack", LootType.MEDIUM_HEAL, 1),
            new LootItem("Large Health Pack", LootType.LARGE_HEAL, 1),
            new LootItem("Weapon Mod Kit", LootType.WEAPON_BUFF, 3),
            new LootItem("Reinforced Armor Plate", LootType.ARMOR_BUFF, 2),

            // added duplicate entries for higher chances of being picked

            new LootItem("Small Health Pack", LootType.SMALL_HEAL, 1),
            new LootItem("Weapon Mod Kit", LootType.WEAPON_BUFF, 3)
    };

    /**
     * Allows the player to search for loot after winning a combat encounter.
     * Randomly selects an item from the loot table and applies it to the player's
     * inventory.
     * 
     * @param loadout The player's inventory to be updated with found items
     */
    public static void searchAfterEncounter(PlayerLoadout loadout) {
        System.out.println("\nYou search the area for useful supplies...");
        LootItem found = SEARCH_TABLE[RNG.nextInt(SEARCH_TABLE.length)];

        System.out.println("You found " + found.name);

        switch (found.type) {
            case SMALL_HEAL:
                loadout.smallHealthPacks += found.value;
                System.out.println("+1 Small Health Pack");
                break;

            case MEDIUM_HEAL:
                loadout.mediumHealthPacks += found.value;
                System.out.println("+1 Health Pack");
                break;

            case LARGE_HEAL:
                loadout.largeHealthPacks += found.value;
                System.out.println("+1 Large Health Pack");
                break;

            case WEAPON_BUFF:
                loadout.bonusDamage += found.value;
                System.out.println("Weapon damage has increased by +" + found.value);
                break;

            case ARMOR_BUFF:
                loadout.armorBonus += found.value;
                System.out.println("Armor has increased by +" + found.value);
                break;
        }

        System.out.println("Current bonus damage: +" + loadout.bonusDamage);
        System.out.println("Current bonus armor +" + loadout.armorBonus);

    }

    /**
     * Main entry point for the game. Initializes the menu, handles character
     * selection,
     * and runs the game scenarios in sequence.
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        showMainMenu();
        int menuChoice = scanner.nextInt();

        if (menuChoice == 1) {
            ArrayList<Character> roles = createRoles();
            Character selected = selectRole(scanner, roles);
            PlayerLoadout loadout = createLoadoutForClass(selected);

            System.out.println("\nYou selected:");
            selected.displayStats();
            displayLoadout(loadout, selected);
            System.out.println("\nGame starting...");

            runFirstScenario(scanner, selected, loadout);

        } else if (menuChoice == 2) {
            System.out.println("Goodbye.");
        } else {
            System.out.println("Invalid menu choice.");
        }

        scanner.close();
    }

    /**
     * Displays the main menu with game options to the player.
     */
    public static void showMainMenu() {
        System.out.println("=== Welcome to The Ascent, A Text-Based RPG set in the far future! ===");
        System.out.println("1. Start Game");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Creates and returns an ArrayList containing the three available character
     * classes:
     * Guardsman, Hacker, and Medic with their stats and descriptions.
     * 
     * @return ArrayList of Character objects representing available roles
     */
    public static ArrayList<Character> createRoles() {
        ArrayList<Character> roles = new ArrayList<>();

        roles.add(new Character(
                "Guardsman",
                120,
                25,
                15,
                "Before The Sealspire went dark, you were a guardsman- protector of the Spire and keeper of its laws. Trained in every manner of weaponry, you stood against threats from both within and beyond its walls. Now the Sealspire has fallen. The force that kept the horrors beneath the earth asleep is gone, and your home has decended into chaos. What was once a sanctuary is now a livning nightmare. Fight. Survive. Escape."));

        roles.add(new Character(
                "Hacker",
                85,
                35,
                8,
                "You are skilled in the hight tech of the Sealspire, You're familiar with the towers inner workings and it's saffisticated sysstems. Since the tower went dark you have been survivng by staying hidden in areas that would normally be declined to you. YOu must use your skills to escape the Spire."

        ));

        roles.add(new Character(
                "Medic",
                100,
                18,
                20,
                "You have treated many injuries in your field of work. You've treated civilians, and Guardsmen alike. Use your skills to protect yourself and to help others if neccesary."));

        return roles;

    }

    /**
     * Prompts the player to select a character role from the available options.
     * Validates input and repeats until a valid choice is made.
     * 
     * @param scanner Scanner object for user input
     * @param roles   ArrayList of available Character roles
     * @return The selected Character object
     */
    public static Character selectRole(Scanner scanner, ArrayList<Character> roles) {
        int choice = -1;

        while (choice < 1 || choice > roles.size()) {
            System.out.println("\nChoose your role:");
            for (int i = 0; i < roles.size(); i++) {
                System.out.println((i + 1) + ". " + roles.get(i).getName());
            }
            System.out.print("Enter role number: ");
            choice = scanner.nextInt();

            if (choice < 1 || choice > roles.size()) {
                System.out.println("Invalid role. Try again.");
            }
        }
        return roles.get(choice - 1);
    }

    /**
     * Creates a class-specific starting loadout with weapons and items
     * tailored to each character type (Guardsman, Hacker, or Medic).
     * 
     * @param player The Character for which to create the loadout
     * @return PlayerLoadout object with starting items and bonuses
     */
    public static PlayerLoadout createLoadoutForClass(Character player) {
        PlayerLoadout loadout = new PlayerLoadout();

        if (player.getName().equalsIgnoreCase("Guardsman")) {
            loadout.weaponName = "Pistol";
            loadout.bonusDamage = 5;
            loadout.mediumHealthPacks = 1;
            loadout.grenadeCount = 1;
        } else if (player.getName().equalsIgnoreCase("Hacker")) {
            loadout.weaponName = "Knife";
            loadout.bonusDamage = 3;
            loadout.smallHealthPacks = 1;
            loadout.lockpickCount = 1;
        } else {
            loadout.weaponName = "Shotgun";
            loadout.bonusDamage = 15;
            loadout.largeHealthPacks = 2;
        }

        return loadout;
    }

    /**
     * Prints the player's starting items and equipment to the console.
     * 
     * @param loadout The PlayerLoadout to display
     * @param player  The Character whose loadout is being displayed
     */
    public static void displayLoadout(PlayerLoadout loadout, Character player) {
        System.out.println("\n--- Starting Items ---");
        System.out.println("Weapon: " + loadout.weaponName + " (Damage +" + loadout.bonusDamage + ")");
        if (loadout.mediumHealthPacks > 0) {
            System.out.println("Health Pack x" + loadout.mediumHealthPacks + " (Heals 30)");
        }
        if (loadout.smallHealthPacks > 0) {
            System.out.println("Small Health Pack x" + loadout.smallHealthPacks + " (Heals 10)");
        }
        if (loadout.largeHealthPacks > 0) {
            System.out.println("Large Health Pack x" + loadout.largeHealthPacks + " (Heals 50)");
        }
        if (loadout.grenadeCount > 0) {
            System.out.println("Grenade x" + loadout.grenadeCount + " (Deals 60 damage)");
        }
        if (loadout.lockpickCount > 0) {
            System.out.println("Lockpick x" + loadout.lockpickCount + " (Can skip one non-boss scenario)");
        }
    }

    /**
     * Runs Scenario 1: The Deep Dark — the player encounters an enemy in tunnels,
     * optionally uses a lockpick to skip to the boss, or proceeds to the factory
     * section.
     * 
     * @param scanner Scanner for user input
     * @param player  The player's Character
     * @param loadout The player's inventory and equipment
     */
    public static void runFirstScenario(Scanner scanner, Character player, PlayerLoadout loadout) {
        System.out.println("\n--- SCENARIO 1: The Deep Dark ---");
        System.out.println("You wake in a maintenance tunnel beneath the Spire.");
        System.out.println(player.getName() + " checks their gear and listens for movement.");
        System.out.println("A hostile presence appears ahead...\n");

        Enemy firstEnemy;

        if (player.getName().equalsIgnoreCase("Guardsman")) {
            firstEnemy = new MediumEnemy("Wounded Enforcer");
        } else if (player.getName().equalsIgnoreCase("Hacker")) {
            firstEnemy = new SmallEnemy("Damaged Security Drone");
        } else {
            firstEnemy = new SmallEnemy("Tunnel Crawler");
        }

        System.out.println("Enemy encountered:");
        firstEnemy.displayStats();

        int playerHp = runCombatEncounter(scanner, player, loadout, firstEnemy, player.getHealth());

        if (playerHp > 0) {
            playerHp = applyScenarioRewards(player, loadout, playerHp, "Scenario 1");
            searchAfterEncounter(loadout);
            System.out.println("\nYou survived the tunnel encounter and find a maintenance lift.");
            System.out.println("The lift grinds upward into a ruined factory section of the Spire.");

            if (loadout.lockpickCount > 0 && player.getName().equalsIgnoreCase("Hacker")) {
                System.out.println("\nUse lockpick to bypass the factory section? (1 = Yes, 2 = No)");
                int skipChoice = scanner.nextInt();
                if (skipChoice == 1) {
                    loadout.lockpickCount--;
                    System.out.println("You bypass security doors and skip this scenario.");
                    System.out.println("You emerge near the summit. Boss encounters cannot be skipped.");

                    int bossHp = runFinalBossScenario(scanner, player, loadout, playerHp);
                    if (bossHp > 0) {
                        System.out.println("\nYou defeat the Warden and reclaim the upper Spire.");
                        System.out.println("VICTORY");
                    } else {
                        System.out.println("\nThe summit falls silent as the Warden stands victorious.");
                        System.out.println("GAME OVER");
                    }
                    return;
                }
            }

            int finalHp = runFactorySectionEncounter(scanner, player, loadout, playerHp);
            if (finalHp > 0) {
                finalHp = applyScenarioRewards(player, loadout, finalHp, "Scenario 2");
                searchAfterEncounter(loadout);
                System.out.println("\nYou carve a path through the factory and keep climbing.");

                int bossHp = runFinalBossScenario(scanner, player, loadout, finalHp);
                if (bossHp > 0) {
                    System.out.println("\nYou defeat the Warden and reclaim the upper Spire.");
                    System.out.println("VICTORY");
                } else {
                    System.out.println("\nThe summit falls silent as the Warden stands victorious.");
                    System.out.println("GAME OVER");
                }
            } else {
                System.out.println("\nThe factory floor overwhelms you.");
                System.out.println("GAME OVER");
            }
        } else {
            System.out.println("\nYou were defeated in the tunnels.");
            System.out.println("GAME OVER");
        }

    }

    /**
     * Runs a single turn-based combat encounter between the player and an enemy.
     * Handles player actions (attack, defend, heal, grenade) and enemy
     * counterattacks.
     * Returns the player's remaining HP after combat ends.
     * 
     * @param scanner  Scanner for user input
     * @param player   The player's Character
     * @param loadout  The player's inventory with weapons and items
     * @param enemy    The Enemy to fight
     * @param playerHp The player's current health at the start of combat
     * @return The player's remaining HP after combat (0 if defeated)
     */
    public static int runCombatEncounter(Scanner scanner, Character player, PlayerLoadout loadout, Enemy enemy,
            int playerHp) {
        int maxHp = player.getHealth();

        while (playerHp > 0 && enemy.isAlive()) {
            boolean defending = false;
            boolean usedHealthPack = false;

            System.out.println("\n--- Combat Turn ---");
            System.out.println(player.getName() + " HP: " + playerHp);
            System.out.println(enemy.getName() + " HP: " + enemy.getHealth());
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Use Health Pack");
            if (loadout.lockpickCount > 0 && player.getName().equalsIgnoreCase("Hacker")) {
                System.out.println("4. Use Lockpick");
            }
            if (loadout.grenadeCount > 0) {
                System.out.println("5. Use Grenade");
            }
            System.out.print("Choose action: ");

            int action = scanner.nextInt();

            if (action == 1) {
                enemy.takeDamage(player.getAttack() + loadout.bonusDamage);
            } else if (action == 2) {
                defending = true;
                System.out.println("You brace for impact.");
            } else if (action == 3) {
                int healAmount = loadout.consumeBestHealthPack();
                if (healAmount > 0) {
                    playerHp = Math.min(maxHp, playerHp + healAmount);
                    usedHealthPack = true;
                    System.out.println("You heal for " + healAmount + ". Current HP: " + playerHp);
                } else {
                    System.out.println("No health packs left.");
                }
            } else if (action == 4 && loadout.lockpickCount > 0 && player.getName().equalsIgnoreCase("Hacker")) {
                loadout.lockpickCount--;
                System.out.println("You pick the lock and slip past the " + enemy.getName() + "!");
                System.out.println("You escape the encounter.");
                return playerHp;
            } else if (action == 5 && loadout.grenadeCount > 0) {
                loadout.grenadeCount--;
                enemy.takeDamage(60);
                System.out.println("Grenades left: " + loadout.grenadeCount);
            } else {
                System.out.println("Invalid action. You lose your turn.");
            }

            if (enemy.isAlive() && !usedHealthPack) {
                int enemyDamage = Math.max(0, enemy.getAttack() - (player.getDefense() + loadout.armorBonus));

                if (defending) {
                    enemyDamage = enemyDamage / 2;
                }

                playerHp -= enemyDamage;
                if (playerHp < 0) {
                    playerHp = 0;
                }

                System.out.println(enemy.getName() + " attacks for " + enemyDamage + " damage.");
            }
        }

        return playerHp;

    }

    /**
     * Runs Scenario 2: Factory Climb — two consecutive enemy encounters
     * (a Medium and a Large enemy) that the player must defeat.
     * 
     * @param scanner  Scanner for user input
     * @param player   The player's Character
     * @param loadout  The player's inventory and equipment
     * @param playerHp The player's current health at the start of the scenario
     * @return The player's remaining HP after defeating both enemies (0 if
     *         defeated)
     */
    public static int runFactorySectionEncounter(Scanner scanner, Character player, PlayerLoadout loadout,
            int playerHp) {
        System.out.println("\n--- SCENARIO 2: Factory Climb ---");
        System.out.println("Conveyors, smoke, and shattered catwalks block your way up the Spire.");
        System.out.println("Two hostiles move to intercept you: one medium, one large.");

        Enemy mediumEnemy = new MediumEnemy("Forge Sentinel");
        Enemy largeEnemy = new LargeEnemy("Hammer Brute");

        System.out.println("\nFirst wave:");
        mediumEnemy.displayStats();
        playerHp = runCombatEncounter(scanner, player, loadout, mediumEnemy, playerHp);

        if (playerHp <= 0) {
            return playerHp;
        }

        System.out.println("\nYou climb to the next catwalk. A larger threat blocks the ascent.");
        largeEnemy.displayStats();
        playerHp = runCombatEncounter(scanner, player, loadout, largeEnemy, playerHp);

        return playerHp;
    }

    /**
     * Runs the Final Scenario: The Summit Core — boss fight against The Iron
     * Warden.
     * The boss uses a special attack every 4th turn. Combat continues until player
     * or boss is defeated.
     * 
     * @param scanner  Scanner for user input
     * @param player   The player's Character
     * @param loadout  The player's inventory and equipment
     * @param playerHp The player's current health at the start of the boss fight
     * @return The player's remaining HP after the boss fight (0 if defeated)
     */
    public static int runFinalBossScenario(Scanner scanner, Character player, PlayerLoadout loadout, int playerHp) {
        System.out.println("\n--- FINAL SCENARIO: The Summit Core ---");
        System.out.println("You reach the crown of the Spire where the core chamber burns with unstable energy.");
        System.out.println("The final guardian steps forward: The Iron Warden.");

        BossEnemy boss = new BossEnemy("The Iron Warden", "Seismic Slam");
        boss.displayStats();

        int maxHp = player.getHealth();
        int turnCount = 1;

        while (playerHp > 0 && boss.isAlive()) {
            boolean defending = false;
            boolean usedHealthPack = false;

            System.out.println("\n--- Boss Turn " + turnCount + " ---");
            System.out.println(player.getName() + " HP: " + playerHp);
            System.out.println(boss.getName() + " HP: " + boss.getHealth());
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Use Health Pack");
            if (loadout.grenadeCount > 0) {
                System.out.println("4. Use Grenade");
            }
            System.out.print("Choose action: ");

            int action = scanner.nextInt();

            if (action == 1) {
                boss.takeDamage(player.getAttack() + loadout.bonusDamage);
            } else if (action == 2) {
                defending = true;
                System.out.println("You brace for impact.");
            } else if (action == 3) {
                int healAmount = loadout.consumeBestHealthPack();
                if (healAmount > 0) {
                    playerHp = Math.min(maxHp, playerHp + healAmount);
                    usedHealthPack = true;
                    System.out.println("You heal for " + healAmount + ". Current HP: " + playerHp);
                } else {
                    System.out.println("No health packs left.");
                }
            } else if (action == 4 && loadout.grenadeCount > 0) {
                loadout.grenadeCount--;
                boss.takeDamage(60);
                System.out.println("Grenades left: " + loadout.grenadeCount);
            } else {
                System.out.println("Invalid action. You lose your turn.");
            }

            if (boss.isAlive() && !usedHealthPack) {
                int enemyDamage;

                if (turnCount % 4 == 0) {
                    boss.useSpecialAbility();
                    enemyDamage = Math.max(0, boss.getBonusAttack() - (player.getDefense() + loadout.armorBonus));
                } else {
                    enemyDamage = Math.max(0, boss.getAttack() - (player.getDefense() + loadout.armorBonus));
                }

                if (defending) {
                    enemyDamage = enemyDamage / 2;
                }

                playerHp -= enemyDamage;
                if (playerHp < 0) {
                    playerHp = 0;
                }

                System.out.println(boss.getName() + " attacks for " + enemyDamage + " damage.");
            }

            turnCount++;
        }

        return playerHp;
    }

    /**
     * Applies rewards for completing a scenario: fully heals the player and
     * increases their weapon bonus damage by 8.
     * 
     * @param player       The player's Character
     * @param loadout      The player's inventory to be updated with new bonuses
     * @param currentHp    The player's current HP before healing
     * @param scenarioName The name of the completed scenario (for display)
     * @return The player's max health after full healing
     */
    public static int applyScenarioRewards(Character player, PlayerLoadout loadout, int currentHp,
            String scenarioName) {
        int healedHp = player.getHealth();
        int healedAmount = healedHp - currentHp;
        loadout.bonusDamage += 8;

        System.out.println("\n--- " + scenarioName + " Complete ---");
        System.out.println("You are fully healed for the next challenge (" + healedAmount + " HP restored).");
        System.out.println("Damage bonus increased by +8.");
        System.out.println("Current weapon bonus damage: +" + loadout.bonusDamage);

        return healedHp;
    }

}