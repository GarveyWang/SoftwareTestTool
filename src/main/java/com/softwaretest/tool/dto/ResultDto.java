package com.softwaretest.tool.dto;


import java.util.List;

public class ResultDto {
    private Object[] inputs;
    private Object[] expects;
    private Object[] outputs;
    private Object[] results;
    private int passed;
    private int failed;

    public ResultDto(List<String> inputs, List<Object> outputs, List<String> expects, List<Boolean> results) {
        this.inputs =inputs.toArray();
        this.expects=expects.toArray();
        this.outputs=outputs.toArray();
        this.results=results.toArray();
        passed=0;
        failed=0;
        for (int i=0;i<this.results.length;++i){
            if (this.results[i].toString().equals("true")){
                ++passed;
            }else {
                ++failed;
            }
        }
    }

    public Object[] getInputs() {
        return inputs;
    }

    public void setInputs(Object[] inputs) {
        this.inputs = inputs;
    }

    public Object[] getExpects() {
        return expects;
    }

    public void setExpects(Object[] expects) {
        this.expects = expects;
    }

    public Object[] getOutputs() {
        return outputs;
    }

    public void setOutputs(Object[] outputs) {
        this.outputs = outputs;
    }

    public Object[] getResults() {
        return results;
    }

    public void setResults(Object[] results) {
        this.results = results;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }
}
