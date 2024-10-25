package com.example.ProgettoJavaSettimana5.exceptions;

public class EmptyListException extends RuntimeException{
    public EmptyListException() {
        super("La ricerca non ha prodotto risultato");
    }
}
