package org.example.threadlocalex;

public class SetInitValue {

    public static void main(String[] args) {

        // use supplier + lambda
        ThreadLocal<String> threadLocal1 = ThreadLocal.withInitial(() -> "Hello by anonymous class");

        // use the subclass
        ThreadLocal<String> threadLocal = new ThreadLocal<>(){
            @Override
            protected String initialValue(){
                return "Hello by anonymous class";
            }
        };

        System.out.println(threadLocal.get());
    }

}
