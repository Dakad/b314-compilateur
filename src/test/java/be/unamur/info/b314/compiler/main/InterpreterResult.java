
package be.unamur.info.b314.compiler.main;

import com.google.common.collect.Lists;
import java.util.List;

/**
 *
 * @author Xavier Devroey - xavier.devroey@unamur.be
 */
public class InterpreterResult {
    
    private int exitStatus;
    private final List<String> errLines = Lists.newArrayList();
    private final List<String> outLines = Lists.newArrayList();

    public InterpreterResult() {
    }
    
    public InterpreterResult(int exitStatus) {
        this.exitStatus = exitStatus;
    }

    public void setExitStatus(int exitStatus) {
        this.exitStatus = exitStatus;
    }

    public Integer getExitStatus() {
        return exitStatus;
    }
    
    public void addErrLine(String line){
        errLines.add(line);
    }
    
    public void addOutLine(String line){
        outLines.add(line);
    }

    public List<String> getErrLines() {
        return errLines;
    }

    public List<String> getOutLines() {
        return outLines;
    }
    
}
