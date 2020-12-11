package com.leogomesdev.services;

import com.leogomesdev.OperatorsDictionary;
import com.leogomesdev.models.Token;

import java.util.List;

/**
 * Parse tokens into a result using the math operators.
 */
class ParserService {
    private OperatorsDictionary operatorsDictionary = new OperatorsDictionary();
    private Integer index = 0;
    private List<Token> tokens;


    ParserService(List<Token> tokens) {
        this.tokens = tokens;
    }

    private void moveToNext() {
        this.index++;
    }

    private Token current() {
        if (this.index < this.tokens.size()) {
            return this.tokens.get(this.index);
        }

        return new Token(this.operatorsDictionary.END_OF_EXPRESSION);
    }

    /**
     * Given a "node" of operation (e.g. x "operation" y), it'll call the correct eval(x, y)
     * It's recursive because it needs to resolve "x" and "y" before calling the eval for "operation"
     * @param precedence control of "importance" of operations. Force * and / to be resolved before - and + operators
     * @return the numeric result of the expression
     * @throws Exception in case of incomplete expression (missing parentheses closing or number to operate)
     */
    double parseOperations(Integer precedence) throws Exception {
        double result = this.parseExpression();
        while (
                this.current().type.equals(this.operatorsDictionary.TYPE_OPERATOR) && this.current().precedence > precedence
        ) {
            Token token = this.current();
            this.moveToNext();
            double secondValue = this.parseOperations(token.precedence);
            result = this.operatorsDictionary.resolve(token.value, result, secondValue);
        }
        return result;
    }

    /**
     * Return the value of the current number, if it's just a number
     * If the current token is a parenthesis, will parse all operations inside those and then return its result
     * @return the result of the "node"
     * @throws Exception in case of incomplete expression (missing parentheses closing or number to operate)
     */
    private double parseExpression() throws Exception {
        if (this.current().type.equals(this.operatorsDictionary.TYPE_NUM)) {
            double value = Double.parseDouble(this.current().value);
            this.moveToNext();
            return value;
        }

        if (this.current().type.equals(this.operatorsDictionary.TYPE_LEFT_PAREN)) {
            this.moveToNext();
            double result = this.parseOperations(0);
            if (!this.current().type.equals(this.operatorsDictionary.TYPE_RIGHT_PAREN)) {
                throw new Exception("Expect to close parentheses");
            }
            this.moveToNext();
            return result;
        }
        throw new Exception("Expect to find an operand");
    }
}
