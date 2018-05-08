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
array   : scalar LBRACK one=intVal (COMMA second=intVal)? RBRACK ;       // boolean[2]  or square[2,3]



  // Plateau de jeu declaration
board   : ARENA AS SQR_TYPE LBRACK one=intVal COMMA second=intVal RBRACK; // arena as square [9, 9]

  // Variable declaration
varDecl : name=ID AS type;                                        // nomVar as integer, boolean[2]


/** Import */

impDecl :  IMPORT file=fileDecl;                                  // import inputFile.wld
fileDecl:  name=ID ext=IMPORT_EXT;                                   // inputFile.wld


/** Actions */

action  : MOVE direction=(NORTH | SOUTH | EAST | WEST)      # Move
        | SHOOT direction=(NORTH | SOUTH | EAST | WEST)     # Shoot
        | USE item=(MAP | RADIO | FRUITS | SODA)            # Use
        | DO NOTHING                                        # Nothing
        ;

 /* Expression Droite */

intVal  : INTEGER;
opInt   : (ADD | SUB | MULT | DIV | MOD);
boolVal : (TRUE | FALSE);

    /* Expressions entières */
exprD : exprInt                                 #ExprDInt
      | left=exprD opInt right=exprD            #ExprDOpInt

    /* Expressions booléennes */
      | exprBool                                #ExprDBool
      | left=exprD
        op=(AND | OR | LT | GT | EQ | LE | GE)
        right=exprD                             #ExprDOpBool

    /* Expressions sur les types de cases */
      | exprCase                                #ExprDCase

      | exprG                                   #ExprDG

    /* Expressions avec les fonctions */
      | exprFct                                 #ExprDFct

    /* Expressions avec parenthèse */
      | LPAR expr=exprD RPAR                    #ExprDPar
      ;


    /* Var env. entières */
exprInt : intVal                                              // 2, 13, -4,
        | LAT | LONGT | GRID SIZE                             // (lat, long, grid size)
        | (MAP | RADIO | AMMO | FRUITS |SODA) COUNT
        | LIFE
        ;

    /* Var. env. booléennes */
exprBool : boolVal
         | ENNEMI IS direction=(NORTH | SOUTH | EAST | WEST)
         | GRAAL  IS direction=(NORTH | SOUTH | EAST | WEST)
         | NOT exprD
         ;

    /* Var. env. case */
exprCase : (DIRT | ROCK | VINES | ZOMBIE | PLAYER | ENNEMI | MAP | RADIO | AMMO)  # EnvCase
         | (FRUITS | SODA | GRAAL)                                                # EnvCase
         | NEARBY LBRACK elt+=exprD COMMA elt+=exprD RBRACK                       # EnvCase
         ;

    /* Appel de function */
exprFct  : name=ID
            LPAR
              (param+=exprD (COMMA param+=exprD)*)?
            RPAR
         ;

/* Expression Gauche */

exprG : name=ID                                                 # Var
      | name=ID LBRACK one=exprD (COMMA second=exprD)? RBRACK   # ArrayElt
      ;


/* Fonction */

fctDecl : name=ID AS FUNCTION
              paramDecl
              fctTypeDecl
            localVarDecl?
          DO
            (instr)+
            fctReturnDecl
          DONE
        ;

paramDecl : LPAR
              (param+=varDecl
              (COMMA param+=varDecl)*)*
            RPAR;

fctTypeDecl : COLON (fctType=scalar | VOID);

fctReturnDecl : RETURN (returnVal=exprD | VOID);

/* Instructions */

instr : SKP                                                   # Skip
      | IF condition=exprD
        THEN (instr)+
        (ELSE (instr)+)?
        DONE                                                  # IfThenElse
      | WHILE condition=exprD DO (instr)+ DONE                # While
      | SET var=exprG TO value=exprD                          # SetTo
      | COMPUTE fct=exprD                                     # Compute
      | NEXT action                                           # Next
      ;


/* Program */

program : DECLARE AND RETAIN (programMonde | programStrat) ;

    /* Program pour fichier MONDE.b314 */

programMonde : ( programMondeGlobalDecl* (arenaDecl=board SEMI)? programMondeGlobalDecl* )
               instr*
               clauseDefault
             ;
programMondeGlobalDecl : (globalVarDecl+=varDecl SEMI | globalFctDecl+=fctDecl);


    /* Program pour fichier STRATEGIE.b314 */

programStrat : programStratGlobalDecl*
               WHEN YOUR TURN
                clauseWhen*
                clauseDefault
             ;
programStratGlobalDecl : (globalVarDecl+=varDecl SEMI | globalFctDecl+=fctDecl | impDecl);



/* Clause Default */

clauseDefault : BY DEFAULT
                  localVarDecl?
                  DO instr+ DONE
              ;

localVarDecl : DECLARE LOCAL (localVars+=varDecl SEMI)+;

/* Clause When */

clauseWhen : WHEN condition=exprD
              localVarDecl?
              DO instr+ DONE
           ;

