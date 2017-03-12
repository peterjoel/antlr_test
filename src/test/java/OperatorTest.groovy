import comparer.model.Operator
import comparer.model.Value
import org.junit.Test

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is

class OperatorTest {


    private boolean checkOp(Operator op, int v1, int v2){
        return op.apply(new Value(v1), new Value(v2));
    }

    @Test
    public void testEqual(){
        assertThat(checkOp(Operator.EQ, 12, 10), is(false));
        assertThat(checkOp(Operator.EQ, 2, 10), is(false));
        assertThat(checkOp(Operator.EQ, 5, 5), is(true));
    }

    @Test
    public void testNotEqual(){
        assertThat(checkOp(Operator.NEQ, 12, 10), is(true));
        assertThat(checkOp(Operator.NEQ, 2, 10), is(true));
        assertThat(checkOp(Operator.NEQ, 2, 2), is(false));
    }

    @Test
    public void testLessThan(){
        assertThat(checkOp(Operator.LT, 2, 10), is(true));
        assertThat(checkOp(Operator.LT, 2, 2), is(false));
        assertThat(checkOp(Operator.LT, 2, 1), is(false));
    }

    @Test
    public void testGreaterThan(){
        assertThat(checkOp(Operator.GT, 2, 10), is(false));
        assertThat(checkOp(Operator.GT, 2, 2), is(false));
        assertThat(checkOp(Operator.GT, 2, 1), is(true));
    }

    @Test
    public void testLessThanOrEqual(){
        assertThat(checkOp(Operator.LTEQ, 2, 10), is(true));
        assertThat(checkOp(Operator.LTEQ, 2, 2), is(true));
        assertThat(checkOp(Operator.LTEQ, 2, 1), is(false));
    }

    @Test
    public void testGreaterThanOrEqual(){
        assertThat(checkOp(Operator.GTEQ, 2, 10), is(false));
        assertThat(checkOp(Operator.GTEQ, 2, 2), is(true));
        assertThat(checkOp(Operator.GTEQ, 2, 1), is(true));
    }
}
