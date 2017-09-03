package tb;

public class Level {

    private final String price;
    private final int size;

    public Level(String price, int size) {
        this.price = price;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Level level = (Level) o;

        if (size != level.size) return false;
        return price.equals(level.price);
    }

    @Override
    public int hashCode() {
        int result = price.hashCode();
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString() {
        return "Level{" +
                "price='" + price + '\'' +
                ", size=" + size +
                '}';
    }
}
