package com.dz.menu.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public String menuFilePath(@Value("${menu.file.path}") String menuFilePath) {
        return menuFilePath;
    }

}
