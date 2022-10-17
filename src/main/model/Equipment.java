package model;

// Equipment class that stores equipment data (flames) as well as equipment name
public class Equipment {
    private int strength;
    private int intelligence;
    private int luck;
    private int dexterity;
    private final String name;

    public Equipment(String name) {
        int min = 10;
        int max = 150;
        strength = (int) Math.floor(Math.random() * (max - min + 1) + min);
        intelligence = (int) Math.floor(Math.random() * (max - min + 1) + min);
        luck = (int) Math.floor(Math.random() * (max - min + 1) + min);
        dexterity = (int) Math.floor(Math.random() * (max - min + 1) + min);
        this.name = name;
    }

    // Effects: returns intelligence value
    public int getIntelligence() {
        return intelligence;
    }

    // Effects: returns luck value
    public int getLuck() {
        return luck;
    }

    // Effects: returns dexterity value
    public int getDexterity() {
        return dexterity;
    }

    // Effects: returns strength value
    public int getStrength() {
        return strength;
    }

    // Effects: returns equipment name
    public String getName() {
        return name;
    }

    // Effects: prints equipment dex, strength, int, luk value
    public void printEquipment() {
        System.out.println("Dex = " + getDexterity());
        System.out.println("Str = " + getStrength());
        System.out.println("Int = " + getIntelligence());
        System.out.println("Luk = " + getLuck());
    }

    // Requires: equipment
    // Modifies: equipment
    // Effects: reroll the stats (flames) of the equipment with new randomly generated ones within the min and max
    public void rerollFlame() {
        int min = 10;
        int max = 150;
        strength = (int) Math.floor(Math.random() * (max - min + 1) + min);
        intelligence = (int) Math.floor(Math.random() * (max - min + 1) + min);
        luck = (int) Math.floor(Math.random() * (max - min + 1) + min);
        dexterity = (int) Math.floor(Math.random() * (max - min + 1) + min);

    }

}
