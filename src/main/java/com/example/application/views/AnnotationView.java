package com.example.application.views;

import com.example.application.tasks.annotation.DemoClass;
import com.example.application.tasks.annotation.MyAnnotation;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.ThreadLocalRandom;

@Route("annotation")
public class AnnotationView extends VerticalLayout {
    private TextArea task;

    private Button button;

    private VerticalLayout container = new VerticalLayout();


    private final String taskText = """
            Работа написанной аннотации. Чтобы посмотреть результат работы, нажмите на кнопку.
            Аннотация вызывает из другого класса все аннотированные защищенные и приватные методы столько раз, сколько указано в параметре аннотации.
            """;

    public AnnotationView() {
        setUpTaskArea();
        add(container);
    }

    private void setUpTaskArea() {
        HorizontalLayout area = new HorizontalLayout();
        area.setWidthFull();
        area.setAlignItems(Alignment.CENTER);
        setUpTask();
        setUpButton();
        area.add(task, button);
        add(area);
    }

    private void setUpTask() {
        task = new TextArea();
        task.setLabel("Annotation");
        task.setWidthFull();
        task.setReadOnly(true);
        task.setValue(taskText);
    }

    private void setUpButton() {
        button = new Button("Work");
        button.addClassName("chapter-button");
        button.addClickListener(event -> {
            Class<DemoClass> demoClass = DemoClass.class;
            Method[] methods = demoClass.getDeclaredMethods();
            invokeMethods(methods);
        });
    }


    public void invokeMethods(Method[] methods) {
        for (Method method : methods) {
            TextArea tmpText = new TextArea();
            tmpText.setLabel("Result");
            tmpText.setMinWidth(25, Unit.PERCENTAGE);
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                boolean isAccessibleDefault = true;
                try {
                    if (!Modifier.isPublic(method.getModifiers())) {
                        method.setAccessible(true);
                        isAccessibleDefault = false;
                    }
                    int count = method.getAnnotation(MyAnnotation.class).count();
                    tmpText.setValue("Calling method with name: " + method.getName()
                            + "\nThis method has got @MyAnnotation"
                    + "\nCall method: " + method.getName() + " for " + count + " times");
                    while (count-- > 0) {
                        Object[] parameters = getParametersForMethod(method);
                        method.invoke(null, parameters);
                    }
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                } catch (InvocationTargetException e) {
                    Throwable x = e.getCause();
                    System.out.println("invocation of method: " + method.getName() + " failed because of " + x.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Illegal argument for method: " + method.getName());
                } finally {
                    if (!isAccessibleDefault) {
                        method.setAccessible(false);
                    }
                }
            }
            if (container.getUI().isPresent()) {
                container.getUI().get().access(() -> container.add(tmpText));
            }
        }
    }

    public static Object[] getParametersForMethod(Method method) {
        Object[] parametres = new Object[method.getParameterCount()];
        for (int i = 0; i < parametres.length; ++i) {
            if (method.getParameters()[i].getType().equals(int.class)) {
                parametres[i] = ThreadLocalRandom.current().nextInt();
            } else if (method.getParameters()[i].getType().equals(double.class)) {
                parametres[i] = ThreadLocalRandom.current().nextDouble();
            } else if (method.getParameters()[i].getType().equals(boolean.class)) {
                parametres[i] = ThreadLocalRandom.current().nextBoolean();
            } else if (method.getParameters()[i].getType().equals(char.class)) {
                parametres[i] = (char) (ThreadLocalRandom.current().nextInt(0, 200));
            } else if (method.getParameters()[i].getType().equals(float.class)) {
                parametres[i] = ThreadLocalRandom.current().nextFloat();
            } else if (method.getParameters()[i].getType().equals(byte.class)) {
                parametres[i] = (byte) ThreadLocalRandom.current().nextInt(-128, 127);
            } else {
                parametres[i] = "Random string!";
            }
        }
        return parametres;
    }


}
