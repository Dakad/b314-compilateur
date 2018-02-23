lexer grammar B314Words;

// Word
DECLARE: 'declare';
AND: 'and';
RETAIN: 'retain';
AS: 'as';
INTEGER: 'integer';
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
LESSTO: '<';
SUPTO: '>';
PLUS: '+';
COMMA: ',';
SEMICOLON: ';';
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
BOOLEAN: 'boolean';
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
LATITUDE: 'latitude';
LONGITUDE: 'longitude';
SHOOT: 'shoot';
NOTHING: 'nothing';
SKP : 'skip';

MINUS: '-';
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