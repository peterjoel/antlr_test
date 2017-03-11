package computer;


import computer.model.*;
import org.antlr.v4.runtime.*;

import java.util.List;
import java.util.stream.Collectors;

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
            Computation computation;
            ComputationParser.NegationContext negation = ctx.negation();
            if(negation != null ){
                computation = ctx.negation().accept(new NegationVisitor());
            }
            else {
                computation = ctx.comparison().accept(new ComparisonVisitor());
            }
            return computation;
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
            OperatorVisitor opVisitor = new OperatorVisitor();
            ValueVisitor valueVisitor = new ValueVisitor();
            List<Value> values = ctx.value()
                                    .stream()
                                    .map(value -> value.accept(valueVisitor))
                                    .collect(Collectors.toList());
            Operator op = ctx.operator().accept(opVisitor);
            return new Comparison(values.get(0), op, values.get(1));
        }

    }

    private static class OperatorVisitor extends ComputationBaseVisitor<Operator> {
        @Override
        public Operator visitOperator(ComputationParser.OperatorContext ctx) {
            switch(ctx.getText()){
                case "=": return Operator.EQ;
                case "!=": return Operator.NEQ;
                case "<": return Operator.LT;
                case "<=": return Operator.LTEQ;
                case ">": return Operator.GT;
                case ">=": return Operator.GTEQ;
            }
            // no es possible
            return Operator.EQ;
        }
    }

    private static class ValueVisitor extends ComputationBaseVisitor<Value> {
        @Override
        public Value visitValue(ComputationParser.ValueContext ctx) {
            return new Value(Integer.parseInt(ctx.getText()));
        }

    }
}
