package com.example.application.views;

import com.example.application.tasks.translator.Translator;
import com.example.application.tasks.translator.exceptions.FileReadException;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Route("translator")
public class TranslatorView extends VerticalLayout {

    private TextArea input;

    private TextArea output;

    private Translator translator;

    public TranslatorView(File dictionaryFile) {
        translator = new Translator(dictionaryFile);
        setUpInputArea();
        createTranslateButton();
        setUpOutputArea();
    }

    private void setUpInputArea() {
        input = new TextArea();
        input.setWidthFull();
        input.setLabel("Text to translate");
        add(input);
    }

    private void createTranslateButton() {
        Button button = new Button("Translate");
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        button.addClickListener(event -> {
            try {
                if (translator.getDictionary() == null) {
                    throw new FileReadException("Dictionary didn't load, sorry.");
                }
                File tmp = File.createTempFile("tmp", "txt", Path.of("src/main/resources").toFile());
                try (FileWriter fw = new FileWriter(tmp);
                     BufferedWriter bw = new BufferedWriter(fw))
                {
                    bw.write(input.getValue());
                }
                String text = readFile(tmp.toString(), StandardCharsets.UTF_8);
                output.setValue(translator.translate(text));
                tmp.deleteOnExit();
            } catch (FileReadException | IOException e) {
                createError(e.getMessage());
            }
        });
        add(button);
    }

    private void setUpOutputArea() {
        output = new TextArea();
        output.setWidthFull();
        output.setReadOnly(true);
        output.setLabel("Output");
        add(output);
    }

    public static String readFile(String path, Charset encoding) throws FileReadException {
        try {
            return Files.readString(Paths.get(path), encoding);
        } catch (IOException e) {
            throw new FileReadException(e.getMessage());
        }
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

}
