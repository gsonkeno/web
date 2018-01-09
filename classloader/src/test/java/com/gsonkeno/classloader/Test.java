package com.gsonkeno.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by gaosong on 2018-01-01
 */
public class Test {
    public static void main(String[] args) {
        try{
            System.out.println("current Thread classLoader is " + Thread.currentThread().getContextClassLoader());
            URL url = new URL("file:E:/ideaProjects2017/java-training1/jdk-classloader/target/jdk-classloader-1.0-SNAPSHOT.jar");

            System.out.println( "java.class.path" + System.getProperty("java.class.path"));
            URLClassLoader myClassLoader = new URLClassLoader(new URL[]{url});
           // Thread.currentThread().setContextClassLoader(myClassLoader);
//            Class myClass = myClassLoader.loadClass("TestMyCl");
//            myClass.newInstance();

            System.out.println(myClassLoader);
            Thread.currentThread().setContextClassLoader(myClassLoader);
            Class myClass1 = myClassLoader.loadClass("TestMyCl");
            myClass1.newInstance();



        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
