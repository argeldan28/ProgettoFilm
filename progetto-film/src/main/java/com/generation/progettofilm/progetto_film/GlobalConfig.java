package com.generation.progettofilm.progetto_film;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalConfig implements WebMvcConfigurer
{
    @Value("${urlprefix}")
    private String prefix;

    @Override
    public void configurePathMatch(@SuppressWarnings("null") PathMatchConfigurer configurer) 
    {
        configurer.addPathPrefix(prefix, c -> true);
    }
}
