package be.unamur.info.b314.compiler.main;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B314actionSyntaxTest {

    private static final Logger LOG = LoggerFactory.getLogger(B314actionSyntaxTest.class);

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

    //
    // Serie action OK
    //
    @Test
    public void testaction_use_soda_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/use_soda.b314", testFolder.newFile(), true, "action: use_soda");
    }

    @Test
    public void testaction_shoot_north_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/shoot_north.b314", testFolder.newFile(), true, "action: shoot_north");
    }

    @Test
    public void testaction_use_map_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/use_map.b314", testFolder.newFile(), true, "action: use_map");
    }

    @Test
    public void testaction_shoot_south_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/shoot_south.b314", testFolder.newFile(), true, "action: shoot_south");
    }

    @Test
    public void testaction_use_radio_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/use_radio.b314", testFolder.newFile(), true, "action: use_radio");
    }

    @Test
    public void testaction_use_fruits_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/use_fruits.b314", testFolder.newFile(), true, "action: use_fruits");
    }

    @Test
    public void testaction_shoot_west_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/shoot_west.b314", testFolder.newFile(), true, "action: shoot_west");
    }

    @Test
    public void testaction_move_east_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/move_east.b314", testFolder.newFile(), true, "action: move_east");
    }

    @Test
    public void testaction_do_nothing_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/do_nothing.b314", testFolder.newFile(), true, "action: do_nothing");
    }

    @Test
    public void testaction_move_west_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/move_west.b314", testFolder.newFile(), true, "action: move_west");
    }

    @Test
    public void testaction_shoot_east_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/shoot_east.b314", testFolder.newFile(), true, "action: shoot_east");
    }

    @Test
    public void testaction_move_north_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/move_north.b314", testFolder.newFile(), true, "action: move_north");
    }

    @Test
    public void testaction_move_south_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/move_south.b314", testFolder.newFile(), true, "action: move_south");
    }

    @Test
    public void testaction_use_soda_ok() throws Exception{
        CompilerTestHelper.launchCompilation("/syntax/action/ok/use_soda.b314", testFolder.newFile(), true, "action: use_soda");
    }

    //
    // Serie action KO
    //
    @Test
    public void testaction_shoot_nothing_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/action/ko/shoot_nothing.b314", testFolder.newFile(), false, "action: shoot_nothing");
    }

    @Test
    public void testaction_use_arena_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/action/ko/use_arena.b314", testFolder.newFile(), false, "action: use_arena");
    }

    @Test
    public void testaction_move_arena_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/action/ko/move_arena.b314", testFolder.newFile(), false, "action: move_arena");
    }

    @Test
    public void testaction_shoot_soda_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/action/ko/shoot_soda.b314", testFolder.newFile(), false, "action: shoot_soda");
    }

    @Test
    public void testaction_move_radio_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/action/ko/move_radio.b314", testFolder.newFile(), false, "action: move_radio");
    }

    @Test
    public void testaction_use_south_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/action/ko/use_south.b314", testFolder.newFile(), false, "action: use_south");
    }

    @Test
    public void testaction_use_nothing_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/action/ko/use_nothing.b314", testFolder.newFile(), false, "action: use_nothing");
    }

    @Test
    public void testaction_move_nothing_ko() throws Exception {
        CompilerTestHelper.launchCompilation("/syntax/action/ko/move_nothing.b314", testFolder.newFile(), false, "action: move_nothing");
    }

}