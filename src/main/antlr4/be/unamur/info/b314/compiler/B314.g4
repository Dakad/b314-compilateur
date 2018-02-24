grammar B314;

import B314Words;

/** Parser règles (B314.g4) commence avec des minuscules*/
/** Lexer règles (B314Words.g4) sont tjrs en MAJUSCULES */


/** The start rule; begin parsing here. */
root: program | anyRules ;


// This rules is only for testing a specific rules.
// TODO Must be removed on final release.
anyRules : (type | varDecl | impDecl | instr | action
         | exprD | exprD | exprG | fctDecl | instr
         | clauseDefault | clauseWhen )
         ;


/** Variable */

type    : scalar | array;
scalar  : BOOL_TYPE | INT_TYPE | SQR_TYPE;
array   : scalar LBRACK NUMBER (COMMA NUMBER)? RBRACK ;       // boolean[2]  or square[2,3]


  // Variable declaration
varDecl : ID AS type;                                        // nomVar as integer, boolean[2]


/** Import */

impDecl :  IMPORT fileDecl;                                  // import inputFile.wld
fileDecl: ID IMPORT_EXT;                                     // inputFile.wld


/** Actions */

action  : MOVE  (NORTH | SOUTH | EAST | WEST)
        | SHOOT (NORTH | SOUTH | EAST | WEST)
        | USE (MAP | RADIO | FRUITS | SODA)
        | DO NOTHING
        ;


/*' Expression Droite */

  /* Expressions entières : int, variable de l’environnement
   *                        (lat, long, grid size) ou int + int
   */

exprD : INTEGER                                         // 2, 13, -4,
      | LAT | LONGT | GRID SIZE
      | (MAP | RADIO | AMMO | FRUITS |SODA) COUNT
      | LIFE
      | exprD (ADD | MULT | DIV | MOD) exprD

  /* Expressions booléennes */
      | TRUE | FALSE
      | ENNEMI IS (NORTH | SOUTH | EAST | WEST)
      | GRAAL  IS (NORTH | SOUTH | EAST | WEST)
      | exprD (AND | OR) exprD
      | NOT exprD
      | exprD (LT | GT | EQ | LE | GE) exprD

  /* Expressions sur les types de cases */
      | (DIRT | ROCK | VINES | ZOMBIE | PLAYER | ENNEMI | MAP | RADIO | AMMO)
      | (FRUITS | SODA | GRAAL)
      | NEARBY LBRACK exprD COMMA exprD RBRACK

      | exprG
      | ID LPAR (exprD (COMMA exprD)*)? RPAR
      ;


/*' Expression Gauche */

exprG : ID
      | ID LBRACK exprD (COMMA exprD)? RBRACK
      ;


/* Fonction */

fctDecl : ID AS FUNCTION LPAR (varDecl (COMMA varDecl)*)* RPAR COLON (scalar | VOID)
          (DECLARE LOCAL (varDecl SEMI)+)?
          DO (instr)+
          RETURN ID SEMI
          DONE
        ;


/* Instructions */

instr : SKP
      | IF exprD THEN (instr)+ DONE
      | IF exprD THEN (instr)+ ELSE (instr)+ DONE
      | WHILE exprD DO (instr)+ DONE
      | SET exprG TO exprD
      | COMPUTE exprD
      | NEXT action
      ;


/* Program */

program : DECLARE AND RETAIN
            (varDecl COMMA | fctDecl)*
            instr*
            clauseDefault
        | DECLARE AND RETAIN
            (varDecl SEMI | fctDecl | impDecl)*
            WHEN YOUR TURN
            clauseWhen*
            clauseDefault
        ;


/* Clause Default */

clauseDefault : BY DEFAULT
                (DECLARE LOCAL (varDecl SEMI)+)?
                DO instr+ DONE
              ;


/* Clause When */

clauseWhen : WHEN exprD
            (DECLARE LOCAL (varDecl SEMI)+)?
             DO instr+ DONE
           ;

