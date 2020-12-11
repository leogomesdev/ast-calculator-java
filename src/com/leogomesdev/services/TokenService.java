package com.leogomesdev.services;

import com.leogomesdev.OperatorsDictionary;
import com.leogomesdev.models.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts an expression in a list of tokens.
 * The tokens will be parsed by ParserService.
 */
class TokenService {
    private OperatorsDictionary operatorsDictionary = new OperatorsDictionary();
    private List<Token> tokens = new ArrayList<>();

    /**
     * Given a string, convert into a list of tokens
     * @param fullExpression the original expression
     * @return the list of Tokens
     * @throws Exception in case of unexpected words in the expression
     */
    List<Token> tokenize(String fullExpression) throws Exception {
        fullExpression = fullExpression.trim();
        String currentString = "";

        for (int index = 0; index < fullExpression.length(); index++) {
            currentString += fullExpression.charAt(index);
            currentString = currentString.trim();
            String nextValue = "";
            if ((index + 1) < fullExpression.length()) {
                nextValue = fullExpression.charAt(index + 1) + "";
            }

            if (this.addStringAsToken(currentString, nextValue)) {
                currentString = "";
            }
        }
        if (!currentString.equals("")) {
            throw new Exception("Unexpected expression: " + currentString);
        }
        return this.tokens;
    }

    /**
     * If the string is valid for a token (a number or operator or parentheses), adds this token into the tokens List
     * @param currentString the string to be evaluated
     * @param nextValue the next string, from fullExpression, that will be evaluated
     * @return true if the token was added, otherwise returns false
     */
    private boolean addStringAsToken(String currentString, String nextValue) {
        if (this.isNumber(currentString) && !this.isNumber(nextValue) && !nextValue.equals(".")) {
            this.tokens.add(new Token(this.operatorsDictionary.TYPE_NUM, currentString));
            return true;
        }

        if (currentString.equals("(")) {
            this.tokens.add(new Token(this.operatorsDictionary.TYPE_LEFT_PAREN));
            return true;
        }

        if (currentString.equals(")")) {
            this.tokens.add(new Token(this.operatorsDictionary.TYPE_RIGHT_PAREN));
            return true;
        }

        if (this.isOperator(currentString) && !this.isOperator(nextValue)) {
            this.tokens.add(new Token(
                    this.operatorsDictionary.TYPE_OPERATOR,
                    currentString,
                    this.operatorsDictionary.PRECEDENCES.get(currentString)
            ));
            return true;
        }

        return false;
    }

    /**
     * Checks if the value is numeric
     * @param value string to verify
     * @return true if numeric
     */
    private boolean isNumber(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the value is a math operator (minus, times, etc.)
     * @param value string to verify
     * @return true if it's an operator
     */
    private boolean isOperator(String value) {
        return this.operatorsDictionary.ALL.contains(value);
    }
}
