package com.leogomesdev.models;

/**
 * A representation of a Token.
 */
public class Token {
    public String type;
    public String value;
    public Integer precedence;

    public Token(String type) {
        this.type = type;
    }

    public Token(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public Token(String type, String value, Integer precedence) {
        this.type = type;
        this.value = value;
        this.precedence = precedence;
    }
}
