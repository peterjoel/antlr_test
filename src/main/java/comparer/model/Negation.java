package comparer.model;

/**
 * Created by peter on 11/03/2017.
 */
public class Negation implements Computation {

    private Computation child;


    public Negation(Computation child) {
        this.child = child;
    }


    public boolean compute() {
        return !child.compute();
    }
}
