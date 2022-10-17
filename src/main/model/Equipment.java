package model;

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

    public int getIntelligence() {
        return intelligence;
    }

    public int getLuck() {
        return luck;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getStrength() {
        return strength;
    }

    public String getName() {
        return name;
    }

    public void printEquipment() {
        System.out.println("Dex = " + getDexterity());
        System.out.println("Str = " + getStrength());
        System.out.println("Int = " + getIntelligence());
        System.out.println("Luk = " + getLuck());
    }

    public void rerollFlame() {
        int min = 10;
        int max = 150;
        strength = (int) Math.floor(Math.random() * (max - min + 1) + min);
        intelligence = (int) Math.floor(Math.random() * (max - min + 1) + min);
        luck = (int) Math.floor(Math.random() * (max - min + 1) + min);
        dexterity = (int) Math.floor(Math.random() * (max - min + 1) + min);

    }

}
