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

    // Requires:
    // Modifies:
    // Effects:
    public int getIntelligence() {
        return intelligence;
    }

    // Requires:
    // Modifies:
    // Effects:
    public int getLuck() {
        return luck;
    }

    // Requires:
    // Modifies:
    // Effects:
    public int getDexterity() {
        return dexterity;
    }

    // Requires:
    // Modifies:
    // Effects:
    public int getStrength() {
        return strength;
    }

    // Requires:
    // Modifies:
    // Effects:
    public String getName() {
        return name;
    }

    // Requires:
    // Modifies:
    // Effects:
    public void printEquipment() {
        System.out.println("Dex = " + getDexterity());
        System.out.println("Str = " + getStrength());
        System.out.println("Int = " + getIntelligence());
        System.out.println("Luk = " + getLuck());
    }

    // Requires:
    // Modifies:
    // Effects:
    public void rerollFlame() {
        int min = 10;
        int max = 150;
        strength = (int) Math.floor(Math.random() * (max - min + 1) + min);
        intelligence = (int) Math.floor(Math.random() * (max - min + 1) + min);
        luck = (int) Math.floor(Math.random() * (max - min + 1) + min);
        dexterity = (int) Math.floor(Math.random() * (max - min + 1) + min);

    }

}
