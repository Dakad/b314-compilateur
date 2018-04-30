package be.unamur.info.b314.compiler.semantics.exception;

public class MissingRpar extends RuntimeException {

  private static final String msg = "A Rpar was missing in compute : ";

  public MissingRpar (String name) { super (msg + name);}

  public MissingRpar() { this ("undefined");}

}
