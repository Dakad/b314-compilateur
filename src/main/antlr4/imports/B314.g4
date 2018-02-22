grammar B314;

import B314Words;

/** Parser rules starts with lowercase letters */
/** Lexer rules (B314Words) is only with UPPERCASE */

/** The start rule; begin parsing here. */
root: (type | varDecl | impDecl | instr | action)*;


// Variables
type : scalar | array;
scalar : BOOLEAN | INTEGER | SQUARE;
array : scalar LBRACKET NUMBER (COMMA NUMBER)? RBRACKET ;     // boolean[2]  or square[2,3]

// Variable declaration
varDecl: ID AS type;


// import
impDecl: IMPORT fileDecl;
fileDecl: ID IMPORT_EXT;


// Instructions
instr : SKP
      | IF 'expD' THEN (instr)+ DONE
      | IF 'expD' THEN (instr)+ ELSE (instr)+ DONE
      | WHILE 'exprD' DO (instr)+ DONE
      | SET 'exprG' TO 'expreD'
      | COMPUTE 'exprD'
      | NEXT action
      ;

// Actions
action  : MOVE (NORTH | SOUTH| EAST | WEST)
        | SHOOT (NORTH | SOUTH| EAST | WEST)
        | USE (MAP | RADIO | FRUITS | SODA)
        | DO NOTHING
        ;
