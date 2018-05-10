package be.unamur.info.b314.compiler.semantics.exception;

public class NotReturnVoidFucntion extends RuntimeException {

  public NotReturnVoidFucntion (String msgError) { super (msgError);}

  public NotReturnVoidFucntion () {this ("undefined");}

}
