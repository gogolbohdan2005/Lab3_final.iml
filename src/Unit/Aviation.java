package Unit;


// Клас для авіації
public class Aviation extends MilitaryUnit {
    private String aircraftType;
    private int speed;

    public Aviation(String name, int health, String aircraftType, int speed, int damage) {
        super(name, health, damage);
        this.aircraftType = aircraftType;
        this.speed = speed;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

// Аналогічні класи можна створити для артилерії та піхоти.


