package recursiva.Utilities;

public class Utilities {
    private int count;
    private String name;
    private int hihger;
    private int less;
    private float prom;

    public Utilities(int count, String name, int hihger, int less, float prom) {
        this.count = count;
        this.name = name;
        this.hihger = hihger;
        this.less = less;
        this.prom = prom;
    }

    public Utilities(int count, String name) {
        this.count = count;
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHihger() {
        return hihger;
    }

    public void setHihger(int hihger) {
        this.hihger = hihger;
    }

    public int getLess() {
        return less;
    }

    public void setLess(int less) {
        this.less = less;
    }

    public float getProm() {
        return prom;
    }

    public void setProm(float prom) {
        this.prom = prom;
    }
}
