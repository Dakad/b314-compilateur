grammar B314;

import B314Words;

// parser rules start with lowercase letters,
// lexer rules with uppercase

/** The start rule; begin parsing here. */
root: (type | varDecl | impDecl)*;


// Variables
type : scalar | array;
scalar : BOOLEAN | INTEGER | SQUARE;
array : scalar LBRACKET NUMBER (COMMA NUMBER)? RBRACKET ;     // boolean[2]  or square[2,3]

// Variable declaration
varDecl: ID AS type;


// import
impDecl: IMPORT fileDecl;
fileDecl: ID IMPORT_EXT;
