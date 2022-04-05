package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.MessageAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class Municion extends AtomicInteger {

    MessageAuthor propietarioArma;
    String nombrepropietario;

    public Municion(int initialValue, MessageAuthor propietarioArma) {
        super(initialValue);
        this.propietarioArma = propietarioArma;
        this.nombrepropietario= propietarioArma.getDisplayName();
    }

    public void setPropietarioArma(MessageAuthor autor){
        this.propietarioArma=autor;
    }

    public MessageAuthor getPropietarioArma(){
        return propietarioArma;
    }

    public String getPropietarioNombre(){
        return nombrepropietario;
    }

}
