package Unit;

// Клас для піхоти
public class Infantry extends MilitaryUnit {
    private String weaponType;

    public Infantry(String name, int health, String weaponType, int damage) {
        super(name, health, damage);
        this.weaponType = weaponType;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }
}