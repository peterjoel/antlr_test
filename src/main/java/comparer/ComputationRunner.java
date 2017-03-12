package comparer;


import comparer.model.*;
import org.antlr.v4.runtime.*;

import java.util.Optional;

/**
 * Created by peter on 11/03/2017.
 */
public class ComputationRunner {


    public boolean run(String input) {

        CharStream charStream = new ANTLRInputStream(input);
        ComputationLexer lexer = new ComputationLexer(charStream);
        TokenStream tokens = new CommonTokenStream(lexer);
        ComputationParser parser = new ComputationParser(tokens);
        ComputationVisitor visitor = new ComputationVisitor();
        Computation comparison = visitor.visit(parser.computation());
        return comparison.compute();

    }


    private static class ComputationVisitor extends ComputationBaseVisitor<Computation> {
        @Override
        public Computation visitComputation(ComputationParser.ComputationContext ctx) {
            return Optional.ofNullable(ctx.comparison())
                    .map(comparison -> comparison.accept(new ComparisonVisitor()))
                    .orElseGet(() -> ctx.negation().accept(new NegationVisitor()));
        }
    }


    private static class NegationVisitor extends ComputationBaseVisitor<Computation> {
        @Override
        public Computation visitNegation(ComputationParser.NegationContext ctx) {
            ComputationVisitor computationVisitor = new ComputationVisitor();
            Computation computation = ctx.computation().accept(computationVisitor);
            return new Negation(computation);
        }
    }


    private static class ComparisonVisitor extends ComputationBaseVisitor<Computation> {
        @Override
        public Comparison visitComparison(ComputationParser.ComparisonContext ctx) {
            Operator op = ctx.operator().op;
            ValueVisitor valueVisitor = new ValueVisitor();
            Value val1 = ctx.value(0).accept(valueVisitor);
            Value val2 = ctx.value(1).accept(valueVisitor);
            return new Comparison(val1, op, val2);
        }
    }


    private static class ValueVisitor extends ComputationBaseVisitor<Value> {
        @Override
        public Value visitValue(ComputationParser.ValueContext ctx) {
            return new Value(Integer.parseInt(ctx.getText()));
        }
    }
}
