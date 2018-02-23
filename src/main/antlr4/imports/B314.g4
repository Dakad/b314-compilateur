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


//Expressions droites : Se trouve

  //Expressions entières :  int, une variable de l’environnement
                           // (lat, long, grid size) ou int + int
exprD : INTEGER
      | LATITUDE | LONGITUDE | GRID SIZE
      | (MAP | RADIO | AMMO | FRUITS |SODA) COUNT
      | LIFE
      | exprD (PLUS | |MULT | DIV | MOD) exprD

  //Expressions booléennes
      | TRUE | FALSE
      | ENNEMI IS (NORTH | SOUTH | EAST | WEST)
      | GRAAL IS (NORTH | SOUTH | EAST | WEST)
      | exprD (AND | OR) exprD
      | NOT exprD
      | exprD (LESSTO | SUPTO | EGAL) exprD

  //Expressions sur les types de cases
      | (DIRT | ROCK | VINES | ZOMBIE | PLAYER | ENNEMI | MAP | RADIO | AMMO)
      | (FRUITS | SODA | GRAAL)
      | NEARBY LBRACKET exprD COMMA exprD RBRACKET

      | exprG
      | ID LPAR (exprD (COMMA exprD)*)? RPAR;

exprG : ID
      |ID LBRACKET exprD (COMMA exprD)? RBRACKET;


// Fonction
fctDecl : ID AS FUNCTION LPAR (varDel (COMMA varDel)*)* RPAR COLON (scalar | VOID)
          //(declare local (VarDecl;)+)?
          DO (instruction)+ RETURN ID DONE;