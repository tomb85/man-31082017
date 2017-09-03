package tb;

import java.util.*;
import java.util.function.Supplier;

public class OrderBook {


    private SortedMap<Integer, Integer> bids = new TreeMap<>(Comparator.<Integer>reverseOrder());
    private SortedMap<Integer, Integer> asks = new TreeMap<>();

    public List<Level> getAsks() {
        return getLevels(() -> asks);
    }


    public List<Level> getBids() {
        return getLevels(() -> bids);
    }

    private List<Level> getLevels(Supplier<SortedMap<Integer, Integer>> supplier) {
        SortedMap<Integer, Integer> data = supplier.get();
        List<Level> levels = new ArrayList<>(data.size());
        for (Map.Entry<Integer, Integer> entry : data.entrySet()) {
            String price = convert(entry.getKey());
            levels.add(new Level(price, entry.getValue()));
        }
        return levels;
    }

    private String convert(Integer value) {
        float price = value / 100.0f;
        return String.format("%.2f", price);
    }

    public void onOrder(Side side, int size, String price) {
        int p, current;
        switch (side) {
            case BUY:
                p = Math.round(Float.parseFloat(price) * 100);
                bids.putIfAbsent(p, 0);
                current = bids.get(p);
                bids.put(p, current + size);
                break;
            case SELL:
                p = Math.round(Float.parseFloat(price) * 100);
                asks.putIfAbsent(p, 0);
                current = asks.get(p);
                asks.put(p, current + size);
                break;
        }
    }


}
