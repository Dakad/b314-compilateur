package be.unamur.info.b314.compiler.semantics.exception;

public class BadWritteCompute extends RuntimeException {

  private static final String msg = "Wrong way to right compute : ";

  public BadWritteCompute (String name) { super (msg + name);}

  public BadWritteCompute() { this ("undefined");}

}
