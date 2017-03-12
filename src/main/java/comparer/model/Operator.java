package comparer.model;

import java.util.function.BiFunction;

public enum Operator {

    LT  ((v1, v2) -> v1.getVal() < v2.getVal()),
    GT  ((v1, v2) -> v1.getVal() > v2.getVal()),
    LTEQ((v1, v2) -> v1.getVal() <= v2.getVal()),
    GTEQ((v1, v2) -> v1.getVal() >= v2.getVal()),
    EQ  ((v1, v2) -> v1.getVal() == v2.getVal()),
    NEQ ((v1, v2) -> v1.getVal() != v2.getVal());

    public boolean apply(Value v1, Value v2) {
        return op.apply(v1, v2);
    }

    private BiFunction<Value, Value, Boolean> op;

    Operator(BiFunction<Value, Value, Boolean> op) {
        this.op = op;
    }
}
