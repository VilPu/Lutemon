package lutemon.main;


import androidx.annotation.NonNull;

public class Lutemon {
    private final String name;
    private final String color;
    private int attack;
    private int defense;
    private int experience;
    private int health;
    private int maxHealth;
    private final int id;
    private static int idCounter = 0;

    public Lutemon(String name, String color, int attack, int defense, int maxHealth) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.id = idCounter;
        idCounter++;

    }

    public int defend() {
        return (int) (Math.random() * defense);
    }

    public void attack(Lutemon lutemon) {
        int damageDealt = attack - lutemon.defend();
        if (damageDealt <= 0) {
            return;
        }

        lutemon.health = lutemon.health - damageDealt;

    }

    //Getters & Setters
    public int getDefense() {
        return defense;
    }

    public int getExperience() {
        return experience;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAttack() {
        return attack;
    }

    public int getId() {
        return id;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
