lexer grammar B314Words;

// Word
DECLARE: 'declare';
AND: 'and';
RETAIN: 'retain';
AS: 'as';
INTEGER: 'integer';
ARENA: 'arena';
LBRACKET : 'lbracket';
RBRACKET : 'rbracket';
BY: 'by';
DEFAULT: 'default';
DO: 'do';
SET: 'set';
TO: 'to';
DONE: 'done';
VINES: 'vines';
WHILE: 'while';
LESSTO: '<';
PLUS: '+';
COMMA: ',';
PLAYER: 'player';
ENNEMI: 'ennemi';
ZOMBIE: 'zombie';
ROCK: 'rock';
DIRT: 'dirt';
FRUITS: 'fruits';
MAP: 'map';
RADIO: 'radio';
GRALL: 'graal';
SODA: 'soda';

//stategie
BOOLEAN: 'boolean';
FUNCTION : 'function';






// Identifiers

ID: LETTER (LETTER | DIGIT)* ;

NUMBER: (DIGIT)+;

fragment LETTER: 'A'..'Z' | 'a'..'z' ;
fragment DIGIT: '0'..'9' ;

// Comments -> ignored

COMMENT: '/*' .*? '*/' -> skip;

// Whitespaces -> ignored

NEWLINE: '\r'? '\n'  -> skip ;
WS: [ \t]+ -> skip ;