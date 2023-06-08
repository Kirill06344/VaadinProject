package com.example.application.views;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Route("")
public class MainView extends VerticalLayout {

    private final List<Button> buttons;
    @Autowired
    public MainView(List<Button> buttons) {
        this.buttons = buttons;
        setUpMainView();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
    }

    private void setUpMainView() {
        addClassName("main-view");
        setSizeFull();
        for (Button b : buttons) {
            add(b);
            configureButton(b);
        }
    }

    private void configureButton(Button button) {
        button.addClassName("chapter-button");
        button.addThemeVariants(ButtonVariant.LUMO_LARGE);

        button.addClickListener(event -> {
            button.getUI().ifPresent(ui -> ui.navigate(button.getText().toLowerCase()));
        });

    }

}
