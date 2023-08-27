package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Operation;

@RestController
public class CalculatorController {

	private List<Operation> history = new ArrayList<>();
	private static final int HISTORY_SIZE = 20;

	@GetMapping("/")
	public String listEndpoints() {
		return "Available Endpoints:\n" + "/history - List last 20 operations\n" + "/{operands}/{operation}/{operands} - Perform a mathematical operation";
	}

	@GetMapping("/history")
	public List<Operation> getHistory() {
		return history;
	}

	@GetMapping("/{operands}/{operation}/{operands2}")
	public Operation performOperation(@PathVariable String operands, @PathVariable String operation, @PathVariable String operands2) {

		String expression = operands + operation + operands2;
		int result = calculateResult(operands, operation, operands2);

		Operation operationObject = new Operation(expression, result);
		addToHistory(operationObject);

		return operationObject;
	}

	@GetMapping("/{operands1}/{operation1}/{operands2}/{operation2}/{operands3}")
	public Operation performSecondLevelOperation(@PathVariable String operands1, @PathVariable String operation1, @PathVariable String operands2, @PathVariable String operation2,
			@PathVariable String operands3) {

		String expression = operands1 + operation1 + operands2 + operation2 + operands3;
		int result = calculateResult(operands1, operation1, operands2);
		result = calculateResult(String.valueOf(result), operation2, operands3);

		Operation operationObject = new Operation(expression, result);
		addToHistory(operationObject);

		return operationObject;
	}

	private int calculateResult(String operands, String operation, String operands2) {
		int result = 0;
		try {
			int op1 = Integer.valueOf(operands);
			int op2 = Integer.valueOf(operands2);
			switch (operation.toLowerCase()) {
			case "plus":
				result = op1 + op2;
				break;
			case "minus":
				result = op1 - op2;
				break;
			case "into":
				result = op1 * op2;
				break;
			case "divide":
				result = op1 % op2;
				break;
			}
		} catch (NumberFormatException e) {
			System.out.print("please enter a valid number");
		}
		return result;
	}

	private void addToHistory(Operation operation) {
		history.add(0, operation);
		if (history.size() > HISTORY_SIZE) {
			history.remove(history.size() - 1);
		}
	}
}
