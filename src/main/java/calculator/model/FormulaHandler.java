package calculator.model;

import calculator.validation.Validator;

public class FormulaHandler {
    private final String formula;

    public FormulaHandler(String formula){
        this.formula = formula.replace("\\n", "\n");
    }

    public String getCustomDelimiter(){
        Integer delimiterEndIndex = checkCustomDelimiter();
        if (delimiterEndIndex == null) {
            return null;
        }
        String customDelimiter = formula.substring(2, delimiterEndIndex);
        Validator.validateDelimiter(customDelimiter);
        return customDelimiter;
    }

    public Integer checkCustomDelimiter(){
        if (formula.startsWith("//")) {
            int delimiterEndIndex = formula.indexOf("\n");
            Validator.validateSeparator(delimiterEndIndex);
            return delimiterEndIndex;
        }
        return null;
    }

    public String getNumbersPart(){
        Integer delimiterEndIndex = checkCustomDelimiter();
        if (delimiterEndIndex != null) {
            return formula.substring(delimiterEndIndex + 1);
        }
        return formula;
    }
}
