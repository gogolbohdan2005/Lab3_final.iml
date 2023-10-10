package Unit;

// Базовий клас для військових одиниць
public class MilitaryUnit {
    protected String name;
    protected int health;
    protected int damage;


    public MilitaryUnit(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public int getFirepower() {
        return damage;
    }
    public boolean receiveDamage(int damage) {
        health -= damage;

        if (health <= 0) {
            health = 0;
            System.out.println(name + " has been destroyed!");
            return true;
        } else {
            System.out.println(name + " receives " + damage + " damage. Remaining health: " + health);
            return false;
        }
    }


    public boolean isDestroyed() {
        return health <= 0;
    }
    @Override
    public String toString() {
        return "\nUnit:\n" +
                "Name='" + name + '\'' +
                ", Health=" + health +
                ", Damage=" + damage;
    }
}
