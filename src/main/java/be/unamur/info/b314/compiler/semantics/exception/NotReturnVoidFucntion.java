package be.unamur.info.b314.compiler.semantics.exception;

public class NotReturnVoidFucntion extends RuntimeException {

  private static final String msg = "Function has not return VOID : ";

  public NotReturnVoidFucntion (String name) { super (msg + name);}

  public NotReturnVoidFucntion () {this ("undefined");}

}
