package com.example.application.views;

import com.example.application.tasks.translator.Translator;
import com.example.application.tasks.translator.exceptions.FileReadException;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


@Route("translator")
public class TranslatorView extends VerticalLayout {

    private TextArea input;

    private TextArea output;

    private Translator translator;

    public TranslatorView(File dictionaryFile) {
        translator = new Translator(dictionaryFile);
        setUpInputArea();
        setUploadFileArea();
        setUpOutputArea();
    }

    private void setUpInputArea() {
        input = new TextArea();
        input.setWidthFull();
        input.setLabel("Text to translate");
        add(input);
    }

    private void setUploadFileArea() {
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload uploadField = new Upload(buffer);
        uploadField.setMaxFiles(100);
        uploadField.setMaxFileSize(1 * 1024 * 1024);
        uploadField.setDropLabel(new Label("Drop file here (max 1MB)"));

        Span errorField = new Span();
        errorField.setVisible(false);
        errorField.getStyle().set("color", "red");

        uploadField.addFailedListener(e -> showErrorMessage(e.getReason().getMessage(), errorField));
        uploadField.addFileRejectedListener(e -> showErrorMessage(e.getErrorMessage(), errorField));
        uploadField.addSucceededListener(event -> {
            try {
                String fileName = event.getFileName();
                InputStream inputStream = buffer.getInputStream(fileName);

                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + System.lineSeparator());
                }
                input.setValue(sb.toString());
                output.setValue(translator.translate(sb.toString()));
            } catch (IOException e1) {
                e1.printStackTrace();
                createError(e1.getMessage());
            }
        });

        add(uploadField, errorField);
    }


    private void showErrorMessage(String message, Span errorField) {
        errorField.setVisible(true);
        errorField.setText(message);
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
