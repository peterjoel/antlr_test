package comparer.model;

/**
 * Created by peter on 11/03/2017.
 */
public class Comparison implements Computation {
    private Operator op;
    private Value v1;
    private Value v2;

    public Comparison(Value v1, Operator op, Value v2) {
        this.op = op;
        this.v1 = v1;
        this.v2 = v2;
    }

    public boolean compute() {
        return op.apply(v1, v2);
    }
}
