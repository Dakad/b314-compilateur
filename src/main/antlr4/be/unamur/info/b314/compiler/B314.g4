grammar B314;

import B314Words;

// parser rules start with lowercase letters,
// lexer rules with uppercase

/** The start rule; begin parsing here. */
root: (type | varDel | impDecl)*;

// Variables
type : scalar | array
     ;
scalar : BOOLEAN | INTEGER | SQUARE
       ;
array : scalar LBRACKET NUMBER (COMMA NUMBER)? RBRACKET     // boolean[2]  or square[2,3]
      ;

// Variable declaration
varDel: ID AS type;


// import
impDecl: IMPORT filedecl;
filedecl: ID IMPORT_EXT;

//Right expressions
exprD: exprEnt
    | exprBool
    | exprCase
    | exprG
    | ID LPAR (exprD ( COMMA exprD)*)? RPAR
    | LPAR exprD RPAR ;

//Int expressions
exprEnt: ENTIER
    | LATITUDE | LONGITUDE | GRID SIZE
    | (MAP | RADIO | AMMO | FRUITS | SODA) COUNT
    | LIFE
    | exprD (PLUS|MINUS) exprD
    | exprD (MULT|DIV|MOD) exprD ;

//Bool expression
exprBool: TRUE | FALSE
    | ENNEMI IS (NORTH | SOUTH | EAST | WEST)
    | GRAAL IS (NORTH | SOUTH | EAST | WEST)
    | exprD (AND|OR) exprD
    | NOT exprD
    | exprD (LESSTO|SUPTO|EQ) exprD ;

//Case expression
exprCase: DIRT
    | ROCK
    | VINES
    | ZOMBIE
    | PLAYER
    | ENNEMI
    | MAP
    | RADIO
    | AMMO
    | FRUITS
    | SODA
    | GRAAL
    | NEARBY LBRACKET exprD COMMA exprD RBRACKET ;

//Lefth expression
exprG: ID
    | ID LBRACKET exprD (COMMA exprD)? RBRACKET ;

