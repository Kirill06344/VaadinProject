package com.example.application.configuration;

import com.vaadin.flow.component.button.Button;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ButtonsConfiguration {
    @Bean
    public List<Button> buttonList() {
        return List.of(
                new Button("Hero"),
                new Button("Annotation"),
                new Button("Animals"),
                new Button("Translator"),
                new Button("Stream API"),
                new Button("Multithreading")
        );
    }

}
