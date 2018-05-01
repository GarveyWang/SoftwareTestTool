package com.softwaretest.tool.service;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.List;


public class ClassUtil {

    private static JavaCompiler compiler;
    static{
        compiler = ToolProvider.getSystemJavaCompiler();
    }

    /**
     * 获取java文件路径
     * @param file
     * @return
     */
    private static String getFilePath(String file){
        int last1 = file.lastIndexOf('/');
        int last2 = file.lastIndexOf('\\');
        return file.substring(0, last1>last2?last1:last2)+File.separatorChar;
    }

    /**
     * 编译java文件
     * @param ops 编译参数
     * @param files 编译文件
     */
    private static void javac(List<String> ops,String... files){
        StandardJavaFileManager manager = null;
        try{
            manager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> it = manager.getJavaFileObjects(files);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, ops, null, it);
            task.call();
            System.out.println("Compile Java File:" + files);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(manager!=null){
                try {
                    manager.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 加载类
     * @param name 类名
     * @return
     */
    private static Class<?> load(String name){
        Class<?> cls = null;
        URLClassLoader classLoader = null;
        try{
            //classLoader = ClassUtil.class.getClassLoader();
            String path = "tempClasspath";
            File classpath=new File(path);
            URL[]urls = new URL[1];

            String repository =(new URL("file", null, classpath.getCanonicalPath() + File.separator)).toString() ;
            System.out.println(repository);
            URLStreamHandler streamHandler = null;
            urls[0] = new URL(null, repository, streamHandler);
            classLoader = new URLClassLoader(urls);

            cls = classLoader.loadClass(name);
            System.out.println("Load Class["+name+"] by "+classLoader);
        }catch(Exception e){
            e.printStackTrace();
        }
        return cls;
    }

    /**
     * 编译代码并加载类
     * @param filePath java代码路径
     * @param clsName 类名
     * @return
     */
    public static Class<?> loadClass(String filePath, String clsName){
        try {
            List<String> ops = new ArrayList<>();
            ops.add("-Xlint:unchecked");
            ops.add("-d");
            ops.add("G:\\code\\SoftwareTestTool\\tempClasspath");
            javac(ops,filePath);
            return load(clsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 调用类方法
     * @param cls 类
     * @param methodName 方法名
     * @param paramsCls 方法参数类型
     * @param params 方法参数
     * @return
     */
    public static Object invoke(Class<?> cls,String methodName,Class<?>[] paramsCls,Object[] params){
        Object result = null;
        try {
            Method method = cls.getDeclaredMethod(methodName, paramsCls);
            Object obj = cls.newInstance();
            result = method.invoke(obj, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}