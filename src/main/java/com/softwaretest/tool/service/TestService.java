package com.softwaretest.tool.service;

import com.csvreader.CsvReader;
import com.softwaretest.tool.ToolApplication;
import com.softwaretest.tool.dto.ResultDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service("TestService")
public class TestService {
    public ResultDto test(String signature){
        Class cls = ToolApplication.currentClass;
        CsvReader csvReader;
        Class[] paraTypes=getParamTypes(signature);
        Object[] objs=new Object[paraTypes.length];
        List<String> inputs=new ArrayList<>();
        List<String>expects=new ArrayList<>();
        List<Boolean>results=new ArrayList<>();
        List<Object>outputs=new ArrayList<>();

        try {
            csvReader=new CsvReader(ToolApplication.csv.getPath(),',', Charset.forName("UTF-8"));
            csvReader.readHeaders();
            while (csvReader.readRecord()){
                String input ="";
                for (int i=0;i<csvReader.getColumnCount()-1;++i){
                    input+=csvReader.get(i);
                    input+=" ";

                    if (paraTypes[i]==int.class){
                        objs[i]=Integer.valueOf(csvReader.get(i));
                    }else if (paraTypes[i]==String.class){
                        objs[i]=csvReader.get(i);
                    }else if (paraTypes[i]==double.class){
                        objs[i]=Double.valueOf(csvReader.get(i));
                    }else if (paraTypes[i]==boolean.class){
                        objs[i]=Boolean.valueOf(csvReader.get(i));
                    }else if (paraTypes[i]==float.class){
                        objs[i]=Float.valueOf(csvReader.get(i));
                    }else if (paraTypes[i]==char.class){
                        objs[i]=csvReader.get(i).charAt(0);
                    }else if (paraTypes[i]==long.class){
                        objs[i]=Long.valueOf(csvReader.get(i));
                    }
                }
                inputs.add(input);
                Object output = ClassUtil.invoke(cls,getMethodName(signature),getParamTypes(signature),objs);
                outputs.add(output);
                String expect=csvReader.get(csvReader.getColumnCount()-1);
                expects.add(expect);
                results.add(expect.equals(output.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultDto(inputs,outputs,expects,results);
    }

    private String getMethodName(String signature){
        String[] split = signature.split(" ");
        return split[0];
    }
    private Class[] getParamTypes(String signature){
        String[] split = signature.split(" ");
        Class[] classes = new Class[split.length-1];
        for (int i=1;i<split.length;++i){
            switch (split[i]){
                case "int":
                    classes[i-1]=int.class;
                    break;
                case "double":
                    classes[i-1]=double.class;
                    break;
                case "String":
                    classes[i-1]=String.class;
                    break;
                case "boolean":
                    classes[i-1]=boolean.class;
                    break;
                case "float":
                    classes[i-1]=float.class;
                    break;
                case "char":
                    classes[i-1]=char.class;
                    break;
                case "long":
                    classes[i-1]=long.class;
                    break;
                default:
                    classes[i-1]=null;
                    break;
            }
        }
        return classes;
    }
}
