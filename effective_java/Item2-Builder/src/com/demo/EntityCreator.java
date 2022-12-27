package com.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class EntityCreator<T> {

    private Class<T> classInstance;
    private T entityObject;


    public EntityCreator(Class<T> classInstance, Object... initParams) throws Exception {
        this.classInstance = classInstance;
        Class<?>[] paramTypes = new Class<?>[initParams.length];
        for (int index = 0 ; index < initParams.length; index++) {
            String checkStr = initParams[index].getClass().getSimpleName();
            if (checkStr.contains("Integer")) {
                paramTypes[index] = int.class;
            }
            if (checkStr.contains("Double")) {
                paramTypes[index] = double.class;
            }
            if (checkStr.contains("Boolean")) {
                paramTypes[index] = boolean.class;
            }
            if (checkStr.contains("String")) {
                paramTypes[index] = initParams[index].getClass();
            }
        }
        Constructor<T> constructor = classInstance.getDeclaredConstructor(paramTypes);
        constructor.setAccessible(true);
        this.entityObject = constructor.newInstance(initParams);
    }

    public EntityCreator<T> setValue(String paramName, Object paramValue) throws Exception {
        Field field = classInstance.getDeclaredField(paramName);
        field.setAccessible(true);
        field.set(entityObject, paramValue);
        return this;
    }

    public T build() {
        return entityObject;
    }


}
