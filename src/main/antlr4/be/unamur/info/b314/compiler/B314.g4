grammar B314;

import B314Words;

/** Parser règles (B314.g4) commence avec des minuscules*/
/** Lexer règles (B314Words.g4) sont tjrs en MAJUSCULES */


/** The start rule; begin parsing here. */
root: program ;



/** Variable */
// nbVal   : NUMBER;
type    : scalar | array;
scalar  : BOOL_TYPE | INT_TYPE | SQR_TYPE;
array   : scalar LBRACK intVal (COMMA intVal)? RBRACK ;       // boolean[2]  or square[2,3]



  // Plateau de jeu declaration
board   : ARENA AS SQR_TYPE LBRACK intVal COMMA intVal RBRACK; // arena as square [9, 9]

  // Variable declaration
varDecl : ID AS type;                                        // nomVar as integer, boolean[2]


/** Import */

impDecl :  IMPORT fileDecl;                                  // import inputFile.wld
fileDecl:  ID IMPORT_EXT;                                   // inputFile.wld


/** Actions */

action  : MOVE  (NORTH | SOUTH | EAST | WEST)
        | SHOOT (NORTH | SOUTH | EAST | WEST)
        | USE (MAP | RADIO | FRUITS | SODA)
        | DO NOTHING
        ;

 /* Expression Droite */

intVal  : INTEGER;
opInt   : (ADD | SUB | MULT | DIV | MOD);
boolVal : (TRUE | FALSE);
opBool  : (AND | OR);

exprDFct : ID LPAR (exprD (COMMA exprD)*)? RPAR;

    /* Expressions entières */
exprD : LPAR exprD RPAR
      | exprInt
      | exprD opInt exprD                // int + int, map count * 3

    /* Expressions booléennes */
      | exprBool
      | exprD opBool exprD
      | exprD (LT | GT | EQ | LE | GE) exprD

    /* Expressions sur les types de cases */
      | exprCase

      | exprG
      | exprDFct
      ;


    /* Var env. entières */
exprInt : intVal                                              // 2, 13, -4,
        | LAT | LONGT | GRID SIZE                             // (lat, long, grid size)
        | (MAP | RADIO | AMMO | FRUITS |SODA) COUNT
        | LIFE
        ;

    /* Var. env. booléennes */
exprBool : boolVal
         | ENNEMI IS (NORTH | SOUTH | EAST | WEST)
         | GRAAL  IS (NORTH | SOUTH | EAST | WEST)
         | NOT exprD
         ;

    /* Var. env. case */
exprCase : (DIRT | ROCK | VINES | ZOMBIE | PLAYER | ENNEMI | MAP | RADIO | AMMO)
         | (FRUITS | SODA | GRAAL)
         | NEARBY LBRACK exprD COMMA exprD RBRACK
         ;



/* Expression Gauche */

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

program : DECLARE AND RETAIN (programMonde | programStrat) ;

    /* Program pour fichier MONDE.b314 */

programMonde : ( programMondeGlobalDecl* (board SEMI)? programMondeGlobalDecl* )
               instr*
               clauseDefault
             ;
programMondeGlobalDecl : (varDecl SEMI | fctDecl);


    /* Program pour fichier STRATEGIE.b314 */

programStrat : programStratGlobalDecl*
               WHEN YOUR TURN
                clauseWhen*
                clauseDefault
             ;
programStratGlobalDecl : (varDecl SEMI | fctDecl | impDecl);



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

