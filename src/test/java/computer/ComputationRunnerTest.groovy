package computer

import org.junit.Test

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is

/**
 * Created by peter on 11/03/2017.
 */
class ComputationRunnerTest {


    @Test
    void testOperators() {

        ComputationRunner runner = new ComputationRunner();

        assertThat(runner.run("3 > 2"), is(true));
        assertThat(runner.run("3 >= 2"), is(true));
        assertThat(runner.run("3 < 2"), is(false));
        assertThat(runner.run("3 <= 2"), is(false));
        assertThat(runner.run("1 != 4"), is(true));

    }

    @Test
    void testNegate() {

        ComputationRunner runner = new ComputationRunner();

        assertThat(runner.run("!(3 > 2)"), is(false));
        assertThat(runner.run("!( 3 >= 2)"), is(false));
        assertThat(runner.run("!(3 < 2)"), is(true));

    }

    @Test
    void testNegateAlternativeSyntax() {

        ComputationRunner runner = new ComputationRunner();

        assertThat(runner.run("NOT(1 != 4)"), is(false));

    }

    @Test
    void testNestedNegate() {

        ComputationRunner runner = new ComputationRunner();

        assertThat(runner.run("!(!(1 != 4))"), is(true));
        assertThat(runner.run("NOT(!(1 != 4))"), is(true));
        assertThat(runner.run("NOT( !(!(6 != 6)))"), is(true));
        assertThat(runner.run("NOT(NOT(!(6 != 6)))"), is(true));

    }
}
