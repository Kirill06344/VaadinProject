package com.example.application.tasks.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
//        Class<DemoClass> demoClass = DemoClass.class;
//        Method[] methods = demoClass.getDeclaredMethods();
//        invokeMethods(methods);
    }

//    public static void invokeMethods(Method[] methods) {
//        for (Method method : methods) {
//            if (method.isAnnotationPresent(MyAnnotation.class)) {
//                System.out.println("Calling method with name: " + method.getName());
//                System.out.println("This method has got @MyAnnotation");
//                boolean isAccessibleDefault = true;
//                try {
//                    if (!Modifier.isPublic(method.getModifiers())) {
//                        method.setAccessible(true);
//                        isAccessibleDefault = false;
//                    }
//                    int count = method.getAnnotation(MyAnnotation.class).count();
//                    System.out.println("Call method: " + method.getName() + " for " + count + " times");
//                    while (count-- > 0) {
//                        Object[] parameters = getParametersForMethod(method);
//                        method.invoke(null, parameters);
//                    }
//                } catch (IllegalAccessException e) {
//                    System.out.println(e.getMessage());
//                } catch (InvocationTargetException e) {
//                    Throwable x = e.getCause();
//                    System.out.println("invocation of method: " + method.getName() + " failed because of " + x.getMessage());
//                } catch (IllegalArgumentException e) {
//                    System.out.println("Illegal argument for method: " + method.getName());
//                } finally {
//                    if (!isAccessibleDefault) {
//                        method.setAccessible(false);
//                    }
//                }
//                System.out.println("-----------------------------------");
//            }
//        }
//    }
//
//    public static Object[] getParametersForMethod(Method method) {
//        Object[] parametres = new Object[method.getParameterCount()];
//        for (int i = 0; i < parametres.length; ++i) {
//            if (method.getParameters()[i].getType().equals(int.class)) {
//                parametres[i] = ThreadLocalRandom.current().nextInt();
//            } else if (method.getParameters()[i].getType().equals(double.class)) {
//                parametres[i] = ThreadLocalRandom.current().nextDouble();
//            } else if (method.getParameters()[i].getType().equals(boolean.class)) {
//                parametres[i] = ThreadLocalRandom.current().nextBoolean();
//            } else if (method.getParameters()[i].getType().equals(char.class)) {
//                parametres[i] = (char) (ThreadLocalRandom.current().nextInt(0, 200));
//            } else if (method.getParameters()[i].getType().equals(float.class)) {
//                parametres[i] = ThreadLocalRandom.current().nextFloat();
//            } else if (method.getParameters()[i].getType().equals(byte.class)) {
//                parametres[i] = (byte) ThreadLocalRandom.current().nextInt(-128, 127);
//            } else {
//                parametres[i] = "Random string!";
//            }
//        }
//        return parametres;
//    }
}
