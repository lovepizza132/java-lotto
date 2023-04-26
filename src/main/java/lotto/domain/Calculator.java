package lotto.domain;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Calculator {
    private static NumberFormat numberFormat = NumberFormat.getInstance();
    private final List<String> formulaList;
    private String result;

    public Calculator(List<String> formulaList) {
        this.formulaList = new ArrayList<>(formulaList);
        this.result = "0";
    }

    public List<String> makeNumberList() {
        return IntStream.range(0, this.formulaList.size())
                .filter(index -> index % 2 == 0)
                .mapToObj(index -> this.formulaList.get(index))
                .collect(Collectors.toList());
    }

    public List<String> makeOperatorList() {
        return IntStream.range(0, this.formulaList.size())
                .filter(index -> index % 2 == 1)
                .mapToObj(this.formulaList::get)
                .collect(Collectors.toList());
    }

    public String calculate(String number1, String number2, String operator) {
        Double doubleNumber1 = Double.parseDouble(number1);
        Double doubleNumber2 = Double.parseDouble(number2);
        numberFormat.setGroupingUsed(false);

        System.out.println("---------------------");
        System.out.println("number1 : " + number1);
        System.out.println("number2 : " + number2);
        System.out.println("doubleNumber1 : " + doubleNumber1);
        System.out.println("doubleNumber2 : " + doubleNumber2);
        System.out.println("operator : " + operator);

        if(operator == "+") {
            System.out.println("result : " + numberFormat.format(doubleNumber1 + doubleNumber2));
            return numberFormat.format(doubleNumber1 + doubleNumber2);
        }

        if(operator == "-") {
            System.out.println("result : " + numberFormat.format(doubleNumber1 - doubleNumber2));
            return numberFormat.format(doubleNumber1 - doubleNumber2);
        }

        if(operator == "*") {
            System.out.println("result : " + numberFormat.format(doubleNumber1 * doubleNumber2));
            return numberFormat.format(doubleNumber1 * doubleNumber2);
        }

        System.out.println("result : " + numberFormat.format(doubleNumber1 / doubleNumber2));
        return numberFormat.format(doubleNumber1 / doubleNumber2);
    }

    public void calculateFormula() {
        List<String> numberList = makeNumberList();
        List<String> operatorList = makeOperatorList();
        Queue<String> operatorQueue = new LinkedList<>(operatorList);

        this.result = numberList.stream()
                .reduce((x, y) -> calculate(x, y, operatorQueue.remove()))
                .get();

    }

    public String showResult() {
        return result;
    }
}
