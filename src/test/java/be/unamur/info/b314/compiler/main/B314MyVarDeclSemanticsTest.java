package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.semantics.SymTableFiller;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ignore
public class B314MyVarDeclSemanticsTest {

  private static final Logger LOG = LoggerFactory.getLogger(B314MyVarDeclSemanticsTest.class);

  @Rule
  public TemporaryFolder testFolder = new TemporaryFolder(); // Create a temporary folder for outputs deleted after tests

  @Rule
  public TestRule watcher = new TestWatcher() { // Prints message on logger before each test
    @Override
    protected void starting(Description description) {
      LOG.info(String.format("Starting test: %s()...",
          description.getMethodName()));
    }

    ;
  };
  private SymTableFiller symTable;

  @Test
  @Ignore
  public void test_my_var_decl() throws Exception {
//    this.symTable = CompilerTestHelper.getSymTable("/semantics/varDecl/array_in_all_shapes.b314");
//
//    LOG.debug("Symb TAble is: {{}", symTable.getSymTable());
//
//
//    LOG.debug("Check Number of Global Variables");
//    assertThat("Wrong count of variables", symTable.countVariables(), equalTo(6));
//
//    LOG.debug("Check Global Variables Symbols");
//    assertThat("Wrong number of global variable", symTable.getSymTable().keySet(), hasSize(6));
//
//    LOG.debug("Check if contains myVar1");
//    assertThat(symTable.getSymTable().keySet(), contains("myVar1"));
//    assertThat(symTable.getSymTable().get("myVar1"), notNullValue());
  }

}