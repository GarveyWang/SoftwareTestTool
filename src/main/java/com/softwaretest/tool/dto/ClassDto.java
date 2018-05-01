package com.softwaretest.tool.dto;

import java.lang.reflect.Method;

public class ClassDto {
    private String clsName;
    private String[] methodNames;
    private String[] paras;
    private String[] returnTypes;

    public ClassDto(Class cls) {
        clsName = cls.getName();
        Method[] methods = cls.getDeclaredMethods();
        methodNames = new String[methods.length];
        paras = new String[methods.length];
        returnTypes = new String[methods.length];
        for (int i=0;i<methods.length;++i){
            methodNames[i]=methods[i].getName();
            Class[] paraTypes =methods[i].getParameterTypes();
            paras[i]="";
            for (int j=0;j<paraTypes.length;++j){
                paras[i]+=(paraTypes[j].getSimpleName());
                if (j!=paraTypes.length-1){
                    paras[i]+=" ";
                }
            }

            returnTypes[i] = methods[i].getReturnType().getSimpleName();
        }
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public String[] getMethodNames() {
        return methodNames;
    }

    public void setMethodNames(String[] methodNames) {
        this.methodNames = methodNames;
    }

    public String[] getParas() {
        return paras;
    }

    public void setParas(String[] paras) {
        this.paras = paras;
    }

    public String[] getReturnTypes() {
        return returnTypes;
    }

    public void setReturnTypes(String[] returnTypes) {
        this.returnTypes = returnTypes;
    }
}
