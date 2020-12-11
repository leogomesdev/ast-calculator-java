package com.leogomesdev.services;

import com.leogomesdev.models.Token;

import java.util.List;

/**
 * Responsible for calculating the result for expressions.
 */
public class CalculatorService {

    /**
     * Try to calculate a verbose math expression, following the steps:
     * 1) create tokens with TokenService
     * 2) parse the created tokens with ParserService
     * @param expression the original expression to be parsed
     * @return double with the result
     * @throws Exception if the expression is invalid
     */
    public double calculate(String expression) throws Exception {
        TokenService tokenService = new TokenService();
        List<Token> tokens = tokenService.tokenize(expression);
        ParserService parserService = new ParserService(tokens);

        return parserService.parseOperations(0);
    }
}
