grammar Computation;

@header {
    import comparer.model.Operator;
}

computation : comparison | negation ;

negation : (NOT ('(') computation ')');

comparison : value operator value ;

value : DIGITS ;

operator returns [Operator op]
         : '<'  { $op = Operator.LT;   }
         | '<=' { $op = Operator.LTEQ; }
         | '>'  { $op = Operator.GT;   }
         | '>=' { $op = Operator.GTEQ; }
         | '='  { $op = Operator.EQ;   }
         | '!=' { $op = Operator.NEQ;  };

NOT : 'NOT' | '!' ;

DIGITS : ('0'..'9')+ ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;







