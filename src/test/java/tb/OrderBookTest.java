package tb;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderBookTest {

    @Test
    public void testExample1() {
        OrderBook orderBook = new OrderBook();
        orderBook.onOrder(Side.BUY, 2, "1.00");
        orderBook.onOrder(Side.BUY, 3, "2.00");
        orderBook.onOrder(Side.BUY, 4, "1.00");
        Assert.assertEquals(expected1(), orderBook.getBids());
        Assert.assertEquals(0, orderBook.getAsks().size());
    }

    @Test
    public void testExample2() {
        OrderBook orderBook = new OrderBook();
        orderBook.onOrder(Side.SELL, 2, "1.00");
        orderBook.onOrder(Side.SELL, 3, "2.00");
        orderBook.onOrder(Side.SELL, 4, "1.00");
        Assert.assertEquals(expected2(), orderBook.getAsks());
        Assert.assertEquals(0, orderBook.getBids().size());
    }

    @Test
    public void testExample3() {
        OrderBook orderBook = new OrderBook();
        orderBook.onOrder(Side.BUY, 10, "12.23");
        orderBook.onOrder(Side.BUY, 20, "12.31");
        orderBook.onOrder(Side.SELL, 5, "13.55");
        orderBook.onOrder(Side.BUY, 5, "12.23");

        orderBook.onOrder(Side.BUY, 15, "12.25");
        orderBook.onOrder(Side.SELL, 5, "13.31");
        orderBook.onOrder(Side.BUY, 30, "12.25");
        orderBook.onOrder(Side.SELL, 5, "13.31");
        

        Assert.assertEquals(expectedBids(), orderBook.getBids());
        Assert.assertEquals(expectedAsks(), orderBook.getAsks());
    }


    private List<Level> expectedBids() {
        List<Level> levels = new ArrayList<Level>(2);
        levels.add(new Level("12.31", 20));
        levels.add(new Level("12.25", 45));
        levels.add(new Level("12.23", 15));
        return levels;
    }



    private List<Level> expectedAsks() {
        List<Level> levels = new ArrayList<Level>(2);
        levels.add(new Level("13.31", 10));
        levels.add(new Level("13.55", 5));
        return levels;
    }

    private List<Level> expected1() {
        List<Level> levels = new ArrayList<Level>(2);
        levels.add(new Level("2.00", 3));
        levels.add(new Level("1.00", 6));
        return levels;
    }

    private List<Level> expected2() {
        List<Level> levels = new ArrayList<Level>(2);
        levels.add(new Level("1.00", 6));
        levels.add(new Level("2.00", 3));
        return levels;
    }


}
