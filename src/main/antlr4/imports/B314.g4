grammar B314;

import B314Words;

root: ID;

//Expressions droites
exprD: exprEnt
       | exprBool
       | exprCase
       | exprG
       | ID LPAR (exprD (COMMA exprD)*)? RPAR;

//Expressions entières
exprEnt: INTEGER
         | LATITUDE | LONGITUDE | GRID SIZE
         | (MAP | RADIO | AMMO | FRUITS |SODA) COUNT
         | LIFE
         | exprD (PLUS|MINUS) exprD
         | exprD (MULT|DIV|MOD) exprD;

//Expressions booléennes
exprBool: TRUE | FALSE
          | ENNEMI IS (NORTH | SOUTH | EAST | WEST)
          | GRAAL IS (NORTH | SOUTH | EAST | WEST)
          | exprD (AND|OR) exprD
          | NOT exprD
          | exprD (LESSTO|SUPTO|EGAL) exprD;

//Expressions sur les types de cases
exprCase: DIRT | ROCK | VINES | ZOMBIE | PLAYER | ENNEMI | MAP | RADIO | AMMO
        | FRUITS | SODA | GRAAL | NEARBY LBRACKET exprD COMMA exprD RBRACKET;

exprG: ID
    |ID LBRACKET exprD (COMMA exprD)? RBRACKET;