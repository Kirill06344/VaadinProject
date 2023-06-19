package com.example.application.views;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.example.application.tasks.streams.EmptyCollectionException;
import com.example.application.tasks.streams.StreamMethods;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route("stream api")
public class StreamView extends VerticalLayout {

    private StreamMethods streamApi;

    private RadioButtonGroup<String> radioGroup;

    private TextArea input;

    private TextArea result;

    private Map<Integer, String> functions = new HashMap<>();

    public StreamView(StreamMethods streamApi) {
        this.streamApi = streamApi;
        fillStreamFunctions();
        setUpInput();
        setUpRadioGroup();
        setUpResultField();
    }

    private void setUpInput() {
        input = new TextArea();
        input.setWidthFull();
        input.setLabel("Elements of collection");
        setAlignSelf(Alignment.START, input);
        add(input);
    }

    private void setUpRadioGroup() {
        HorizontalLayout container = new HorizontalLayout();
        radioGroup = new RadioButtonGroup<>();
        radioGroup.setLabel("Stream functions:");
        radioGroup.setItems(functions.values());
        radioGroup.setValue("Average value");
        Button convertButton = new Button("Get result!");
        convertButton.addClassName("chapter-button");
        container.add(radioGroup, convertButton);
        container.setAlignItems(Alignment.BASELINE);

        convertButton.addClickListener(e -> {
            System.out.println(radioGroup.getValue());
            String [] els = input.getValue().split(" ");
            try {
                switch (radioGroup.getValue()) {
                    case "Average value" -> {
                        double avg = streamApi.getAverageValue(Arrays.stream(els)
                                .mapToInt(Integer::parseInt)
                                .boxed()
                                .toList()
                        );
                        result.setValue(String.valueOf(avg));
                    }
                    case "Transform strings" -> {
                        var lStrs = streamApi.transformListOfStrings(Arrays.stream(els).toList());
                        result.setValue(lStrs.toString());
                    }
                    case "List of squares" -> {
                        var squares = streamApi.getListOfSquares(Arrays.stream(els)
                                .mapToInt(Integer::parseInt)
                                .boxed()
                                .toList()
                        );
                        result.setValue(squares.toString());
                    }
                    case "Last element" -> {
                        try {
                            var last = streamApi.getLastElement(Arrays.stream(els).toList());
                            result.setValue(last);
                        } catch (EmptyCollectionException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case "Map from list" -> {
                        var map = streamApi.createMapFromList(Arrays.stream(els).toList());
                        result.setValue(map.toString());
                    }
                    case "Start letter" -> {
                        var start = streamApi.getStringsStartingWithLetter(Arrays.stream(els).toList(), 'a');
                        result.setValue(start.toString());
                    }
                    case "Sum of even" -> {
                        int sum = streamApi.getSumOfEvenElements(
                                Arrays.stream(els)
                                        .mapToInt(Integer::parseInt)
                                        .toArray()
                        );
                        result.setValue(String.valueOf(sum));
                    }
                }
            } catch (NumberFormatException ex) {
                createError(ex.getMessage());
            }
        });

        add(container);
    }


    private void createError(String error) {
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        Div text = new Div(new Text(error));

        Button closeButton = new Button(new Icon("lumo", "cross"));
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });

        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(Alignment.CENTER);

        notification.add(layout);
        notification.open();
    }

    private void setUpResultField() {
        result = new TextArea();
        result.setWidthFull();
        result.setLabel("Result");
        setAlignSelf(Alignment.START, result);
        add(result);
    }

    private void fillStreamFunctions() {
        functions.put(1, "Average value");
        functions.put(2, "Transform strings");
        functions.put(3, "List of squares");
        functions.put(4, "Last element");
        functions.put(5, "Start letter");
        functions.put(6, "Sum of even" );
        functions.put(7, "Map from list");
    }

}
