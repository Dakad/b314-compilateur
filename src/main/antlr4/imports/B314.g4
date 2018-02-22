grammar B314;

import B314Words;

/** The start rule; begin parsing here. */
root: impDecl;

// parser rules start with lowercase letters,
// lexer rules with uppercase
impDecl: IMPORT filedecl;
filedecl: FILENAME IMPORT_EXT;
