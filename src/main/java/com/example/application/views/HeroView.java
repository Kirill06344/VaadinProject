package com.example.application.views;

import com.example.application.tasks.hero.*;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashMap;
import java.util.Map;

@Route("hero")
public class HeroView extends VerticalLayout {

    private final Hero hero;
    private final Map<String, MovementType> movementStorage = initializeMovementTypes();

    private final TextArea point;

    private final Image currentType;

    @Autowired
    public HeroView(Hero hero) {
        this.hero = hero;
        point = new TextArea();
        setUpPointArea();
        currentType = new Image("images/walking.png", "walk");
        setAlignItems(Alignment.CENTER);
        setUpHeroView();
    }

    private void setUpPointArea() {
        point.setValue(String.valueOf(hero.getCurrentPoint()));
        point.setReadOnly(true);
        point.setClassName("point");
        point.addThemeVariants(TextAreaVariant.LUMO_ALIGN_CENTER);
    }

    private void setUpHeroView() {
        Button exitButton = new Button("Exit");
        setUpExitButton(exitButton);
        Button moveButton = new Button("Move");
        setUpMoveButton(moveButton);
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(moveButton, exitButton);
        buttons.setAlignItems(Alignment.CENTER);
        ListBox<String> movementTypes = new ListBox<>();
        add(movementTypes, currentType);
        setAlignSelf(Alignment.END, movementTypes);
        setAlignSelf(Alignment.CENTER, buttons);
        setUpMovementTypes(movementTypes);
        add(point, buttons);
    }

    private void setUpExitButton(Button button) {
        button.addClassName("chapter-button");
        button.addClickListener(event -> {
            button.getUI().ifPresent(ui -> ui.navigate(""));
        });
    }

    private void setUpMoveButton(Button button) {
        button.addClassName("chapter-button");
        button.addClickListener(e -> {
            hero.move();
            point.setValue(String.valueOf(hero.getCurrentPoint()));
        });
    }

    private void setUpMovementTypes(ListBox<String> movementTypes) {
        movementTypes.setItems("walking", "horse", "plane");
        movementTypes.setValue("walking");
        movementTypes.addValueChangeListener(event -> {
            if (!event.getHasValue().isEmpty()) {
                hero.setMovementType(movementStorage.get(event.getValue()));
                currentType.setSrc("images/" + event.getValue() + ".png");
            }
        });
        movementTypes.getStyle().set("margin-right", "24px");
    }

    private static Map<String, MovementType> initializeMovementTypes() {
        Map<String, MovementType> movementTypes = new HashMap<>();
        movementTypes.put("walking", new Walking());
        movementTypes.put("horse", new RidingAHorse());
        movementTypes.put("plane", new TravellingByPlane());
        return movementTypes;
    }
}
