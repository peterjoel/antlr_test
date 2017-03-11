package computer.model;

/**
 * Created by peter on 11/03/2017.
 */
public class Value {


    private int val;

    public Value(int val) {
        this.val = val;
    }

    public int getVal(){
        return val;
    }

    public String toString(){
        return "[Value = " + val + "]";
    }
}
