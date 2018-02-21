grammar B314;

import B314Words;

//root: type*;

// Variables
type : scalar | array
     ;

scalar : BOOLEAN | INTEGER | SQUARE
       ;
array : scalar LBRACKET NUMBER (COMMA NUMBER)? RBRACKET     // boolean[2]  or square[2,3]
      ;
