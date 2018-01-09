package com.gsonkeno.classloader;

/**
 * Created by gaosong on 2017-12-31
 */
public class TestAbstractCl {

    public TestAbstractCl() {
        System.out.println("TestAbstractCl constructor initialized "
                + "is " + this.getClass().getClassLoader() + "\n"
                +  "hashcode is " + this.getClass().getClassLoader().hashCode()

        );
    }

    public void sayHi(){
        System.out.println("TestAbstractCl sayHi "
                + "is " + this.getClass().getClassLoader() + "\n"
                +  "hashcode is " + this.getClass().getClassLoader().hashCode()
        );
    }
}
