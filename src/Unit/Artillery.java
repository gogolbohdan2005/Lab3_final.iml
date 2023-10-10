package Unit;

// Клас для артилерії
public class Artillery extends MilitaryUnit {
    private int calibre;
    private int range;

    public Artillery(String name, int health, int calibre, int damage) {
        super(name, health, damage);
        this.calibre = calibre;
        this.range = range;
    }

    public int getCalibre() {
        return calibre;
    }

    public void setCalibre(int calibre) {
        this.calibre = calibre;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
