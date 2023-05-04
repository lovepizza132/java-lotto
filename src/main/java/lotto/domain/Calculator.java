package lotto.domain;

import lotto.utility.Operation;

import java.util.List;
import java.util.Queue;

public class Calculator {

    public Calculator() {
    }

    private Double calculate(Double number1, Double number2, String operator) {

        return Operation.BasicOperation.valueOfOperator(operator).compute(number1, number2);
    }

    public Double calculateFormula(List<Double> numberList, Queue<String> operatorQueue) {
        return numberList.stream()
            .reduce((x, y) -> calculate(x, y, operatorQueue.remove()))
            .get();
    }

}
