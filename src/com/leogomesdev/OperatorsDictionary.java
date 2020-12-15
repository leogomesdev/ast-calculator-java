package com.leogomesdev;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Contains a centralized list of valid math operation names.
 * Also contains the resolver of the acceptable operations.
 */
public class OperatorsDictionary {
    public List<String> ALL = Arrays.asList("plus", "minus", "times", "divided", "newOperator");

    public String TYPE_NUM = "NUM";
    public String TYPE_LEFT_PAREN = "L_PAREN";
    public String TYPE_RIGHT_PAREN = "R_PAREN";
    public String TYPE_OPERATOR = "OPERATOR";

    public String END_OF_EXPRESSION = "END_OF_EXPRESSION";

    public HashMap<String, Integer> PRECEDENCES = new HashMap<String, Integer>() {{
        put("plus", 1);
        put("minus", 1);
        put("times", 2);
        put("divided", 2);
        put("newOperator", 3);
    }};

    /**
     * This function do the correct math operation
     * @param operationName name of the math operation
     * @param v1 first value
     * @param v2 second value
     * @return the result of v1 [operationName] v2
     * @throws Exception if the operationName is unexpected
     */
    public double resolve(String operationName, double v1, double v2) throws Exception {
        switch (operationName) {
            case "plus":
                return v1 + v2;
            case "minus":
                return v1 - v2;
            case "times":
                return v1 * v2;
            case "divided":
                return v1 / v2;
            case "newOperator":
                return v1 * (v2/2);
            default:
                throw new Exception("Operation is invalid: " + operationName);
        }
    }
}
