package cartilhadejava;

public class Counter {
    protected int count;

    Counter() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count ++;
    }

    public void decrementCount() {
        count --;
    }

}
