package com.example.demo;

import org.javacord.api.entity.message.MessageAuthor;

public class Personaje {
    MessageAuthor usuario;
    String nombre;

    int pdv;
    int fuerza;
    int agilidad;
    int destreza;
    int carisma;
    int voluntad;

    public Personaje(MessageAuthor usuario, int pdv, int fuerza, int agilidad, int destreza, int carisma, int voluntad) {
        this.usuario = usuario;
        this.pdv = pdv;
        this.fuerza = fuerza;
        this.agilidad = agilidad;
        this.destreza = destreza;
        this.carisma = carisma;
        this.voluntad = voluntad;
    }

    public Personaje(String nombre, int pdv, int fuerza, int agilidad, int destreza, int carisma, int voluntad) {
        this.nombre = nombre;
        this.pdv = pdv;
        this.fuerza = fuerza;
        this.agilidad = agilidad;
        this.destreza = destreza;
        this.carisma = carisma;
        this.voluntad = voluntad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MessageAuthor getUsuario() {
        return usuario;
    }

    public void setUsuario(MessageAuthor usuario) {
        this.usuario = usuario;
    }

    public int getPdv() {
        return pdv;
    }

    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getAgilidad() {
        return agilidad;
    }

    public void setAgilidad(int agilidad) {
        this.agilidad = agilidad;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getCarisma() {
        return carisma;
    }

    public void setCarisma(int carisma) {
        this.carisma = carisma;
    }

    public int getVoluntad() {
        return voluntad;
    }

    public void setVoluntad(int voluntad) {
        this.voluntad = voluntad;
    }

    @Override
    public String toString() {
        return "Stats de "+ nombre +": " +
                "\npdv:     " + pdv +
                "\nfuerza:  " + fuerza +
                "\nagilidad:" + agilidad +
                "\ndestreza:" + destreza +
                "\ncarisma: " + carisma +
                "\nvoluntad:" + voluntad ;
    }
}
