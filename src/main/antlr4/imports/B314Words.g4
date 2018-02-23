lexer grammar B314Words;

// Word
DECLARE: 'declare';
AND: 'and';
RETAIN: 'retain';
AS: 'as';
INT_TYPE: 'integer';
ARENA: 'arena';
LBRACKET : '[';
RBRACKET : ']';
BY: 'by';
DEFAULT: 'default';
DO: 'do';
SET: 'set';
TO: 'to';
DONE: 'done';
VINES: 'vines';
WHILE: 'while';
LT: '<';
GT: '>';
ADD: '+';
COMMA: ',';
SEMI: ';';
COLON: ':';
PLAYER: 'player';
ENNEMI: 'ennemi';
ZOMBIE: 'zombie';
ROCK: 'rock';
DIRT: 'dirt';
FRUITS: 'fruits';
MAP: 'map';
RADIO: 'radio';
GRAAL: 'graal';
SODA: 'soda';
SQUARE: 'square';
VOID: 'void';
GRID: 'grid';
SIZE: 'size';
COUNT: 'count';
NOT: 'not';
FALSE: 'false';
NEARBY: 'nearby';

//stategie
BOOL_TYPE: 'boolean';
FUNCTION : 'function';
TRUE: 'true';
IS : 'is';
RETURN: 'return';
IF: 'if';
ELSE: 'else';
OR: 'or';
EQ: '=';
THEN: 'then';
MULT: '*';
LPAR: '(';
RPAR: ')';
WHEN: 'when';
TURN: 'turn';
LIFE: 'life';
NEXT: 'next';
USE: 'use';
LOCAL: 'local';
MOVE: 'move';
EAST: 'east';
NORTH: 'north';
SOUTH: 'south';
WEST: 'west';
LAT: 'latitude';
LONGT: 'longitude';
SHOOT: 'shoot';
NOTHING: 'nothing';
SKP : 'skip';

SUB: '-';
DIV: '/';
MOD: '%';

COMPUTE: 'compute';
IMPORT: 'import';
AMMO : 'ammo';

IMPORT_EXT : '.wld';

//idenfifiant
ID: LETTER (LETTER | DIGIT)* ;



NUMBER: (DIGIT)+;

fragment LETTER: 'A'..'Z' | 'a'..'z' ;
fragment DIGIT: '0'..'9' ;

// Comments -> ignored

COMMENT: '/*' .*? '*/' -> skip;

// Whitespaces -> ignored

NEWLINE: '\r'? '\n'  -> skip ;
WS: [ \t]+ -> skip ;

WHITESPACE: ' ' -> skip;