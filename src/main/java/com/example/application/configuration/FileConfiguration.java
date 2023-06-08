package com.example.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class FileConfiguration {

    @Bean
    public File directoryFile() {
       return new File("src/main/resources/dictionary.txt");
    }

}
