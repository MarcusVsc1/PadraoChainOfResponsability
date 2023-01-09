package br.ufjf.exceptions;

public class BrokenRuleException extends Exception{

    public BrokenRuleException(String message) {
        super("A senha não satisfaz a política de senha na seguinte regra: " + message);
    }


}