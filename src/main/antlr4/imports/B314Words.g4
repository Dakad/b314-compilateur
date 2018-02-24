lexer grammar B314Words;

/* World */

DECLARE:      'declare';
RETAIN:       'retain';
AS:           'as';
INT_TYPE:     'integer';
ARENA:        'arena';
BY:           'by';
DEFAULT:      'default';
DO:           'do';
SET:          'set';
TO:           'to';
DONE:         'done';
VINES:        'vines';
WHILE:        'while';
PLAYER:       'player';
ENNEMI:       'ennemi';
ZOMBIE:       'zombie';
ROCK:         'rock';
DIRT:         'dirt';
FRUITS:       'fruits';
MAP:          'map';
RADIO:        'radio';
GRAAL:        'graal';
SODA:         'soda';
SQR_TYPE:     'square';
VOID:         'void';
GRID:         'grid';
SIZE:         'size';
COUNT:        'count';
NEARBY:       'nearby';


 /* Stategie */

IMPORT:       'import';
IMPORT_EXT:   '.wld';
FUNCTION:     'function';
RETURN:       'return';
IF:           'if';
THEN:         'then';
ELSE:         'else';
WHEN:         'when';
TURN:         'turn';
LIFE:         'life';
NEXT:         'next';
USE:          'use';
LOCAL:        'local';
MOVE:         'move';
EAST:         'east';
NORTH:        'north';
SOUTH:        'south';
WEST:         'west';
LAT:          'latitude';
LONGT:        'longitude';
SHOOT:        'shoot';
NOTHING:      'nothing';
SKP:          'skip';
YOUR:         'your';
AMMO:         'ammo';
COMPUTE:      'compute';



// Expr. Bool

BOOL_TYPE:    'boolean';
FALSE:        'false';
TRUE:         'true';
IS:           'is';
NOT:          'not';
AND:          'and';
OR:           'or';


// Separators

LBRACK:       '[';
RBRACK:       ']';
LPAR:         '(';
RPAR:         ')';
COMMA:        ',';
SEMI:         ';';
COLON:        ':';


// Operators

ADD:          '+';
SUB:          '-';
MULT:         '*';
DIV:          '/';
MOD:          '%';
EQ:           '=';
LT:           '<';
GT:           '>';
LE:           '<=';
GE:           '>=';


//idenfifiant
ID: LETTER (LETTER | DIGIT)* ;

INTEGER: '-'? NUMBER;
NUMBER: (DIGIT)+;

fragment LETTER: 'A'..'Z' | 'a'..'z' ;
fragment DIGIT: '0'..'9' ;

// Comments -> ignored

COMMENT:      '/*' .*? '*/' -> skip;

// Whitespaces  -> ignored
// NewLine      -> ignored

NEWLINE:      '\r'? '\n'  -> skip ;
WS:           [ \t]+ -> skip ;