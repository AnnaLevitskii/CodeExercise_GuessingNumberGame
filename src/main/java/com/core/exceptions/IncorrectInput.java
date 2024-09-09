package com.core.exceptions;

public class IncorrectInput extends Exception{
    public IncorrectInput(String exc){
        super("Incorrect Input: \n" +
                exc + " \n" );
    }
}
