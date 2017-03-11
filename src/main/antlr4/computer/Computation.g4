grammar Computation;


computation : comparison | negation ;

negation : (NOT ('(') computation ')');

comparison : value operator value ;

value : DIGITS ;

NOT : 'NOT' | '!' ;

operator : '<' | '<=' | '>' | '>=' | '=' | '!=' ;

DIGITS : ('0'..'9')+ ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;







