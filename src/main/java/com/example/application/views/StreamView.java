package com.example.application.views;


import java.util.HashMap;
import java.util.Map;

import com.example.application.tasks.streams.StreamMethods;
import com.vaadin.flow.component.button.Button;
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
        add(container);
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
