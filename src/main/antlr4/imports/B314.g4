grammar B314;

import B314Words;

root: ID;

impDecl: IMPORT FileDec;
FileDec: FileName'.wdl';
FileName: LETTER (NUMBER | LETTER)*;