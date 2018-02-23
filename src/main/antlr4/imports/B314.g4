grammar B314;

import B314Words;

root: ID;

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