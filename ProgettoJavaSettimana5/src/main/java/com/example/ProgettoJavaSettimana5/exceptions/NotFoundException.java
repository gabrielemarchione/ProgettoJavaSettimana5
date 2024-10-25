package com.example.ProgettoJavaSettimana5.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id){
        super("La risorsa con id " + id + " non è presente");
    }
}
