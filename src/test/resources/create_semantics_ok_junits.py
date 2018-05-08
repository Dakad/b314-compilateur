#!/usr/bin/env python2
#encoding: UTF-8

# Creates Java test files from existing directories and files. 
# Test files must be in src/test/resources/semantics/testseriename/ok/testfile.b314
# Script must be launched from src/test/resources folder

import os

filePath=os.path.dirname(os.path.realpath(__file__))
semanticsDir=os.path.join(filePath, 'semantics') 

for serie in [x for x in os.listdir(semanticsDir) if not x.startswith('.')]:
    junitName = 'B314{}SemanticOkTest'.format(serie)
    with open('{}/../java/be/unamur/info/b314/compiler/main/{}.java'.format(filePath, junitName),'w') as fout:
        okDir=os.path.join(semanticsDir, serie, 'ok')
        fout.write('package be.unamur.info.b314.compiler.main;\n\n')
        fout.write('import org.junit.Test;\n')
        fout.write('import org.junit.Rule;\n')
        fout.write('import org.junit.rules.TemporaryFolder;\n')
        fout.write('import org.junit.rules.TestRule;\n')
        fout.write('import org.junit.rules.TestWatcher;\n')
        fout.write('import org.junit.runner.Description;\n')
        fout.write('import org.slf4j.Logger;\n')
        fout.write('import org.slf4j.LoggerFactory;\n')
        fout.write('\n')
        fout.write('public class {} {{\n'.format(junitName))
        fout.write('\n')
        fout.write('    private static final Logger LOG = LoggerFactory.getLogger({}.class);\n'.format(junitName))
        fout.write('\n')
        fout.write('    @Rule\n')
        fout.write('    public TemporaryFolder testFolder = new TemporaryFolder(); // Create a temporary folder for outputs deleted after tests\n')
        fout.write('\n')
        fout.write('    @Rule\n')
        fout.write('    public TestRule watcher = new TestWatcher() { // Prints message on logger before each test\n')
        fout.write('        @Override\n')
        fout.write('        protected void starting(Description description) {\n')
        fout.write('            LOG.info(String.format("Starting test: %s()...",\n')
        fout.write('                    description.getMethodName()));\n')
        fout.write('        }\n')
        fout.write('    ;\n')
        fout.write('    };\n')
        fout.write('\n')
        fout.write('    //\n')
        fout.write('    // Serie {} OK\n'.format(serie))
        fout.write('    //\n')
        for inputFile in [x for x in os.listdir(okDir) if not x.startswith('.')]:
            fout.write('    @Test\n')
            fout.write('    public void test{}_{}_ok() throws Exception{{\n'.format(serie, inputFile.replace('.b314', '')))
            fout.write('        CompilerTestHelper.launchCompilation("/semantics/{}/ok/{}", testFolder.newFile(), true, "{}: {}");\n'.format(serie, inputFile, serie, inputFile.replace('.b314', '')))
            fout.write('    }\n\n')
        fout.write('}')

