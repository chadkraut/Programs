package calculator;

import java.util.StringTokenizer;

import data_structures.Queue;
import data_structures.Stack;

public class ExpressionEvaluator {
	private Queue<String> qList;
	private Stack<String> sList;
	private String  postFix,  infix;

	public ExpressionEvaluator(){
		qList = new Queue<>();
		sList = new Stack<>();
	}
	public String processInput(String s) {
		infix = s;
		infixToPostfix();
		evaluator();
		return sList.pop();
	}

	private void infixToPostfix(){ //this method converts ioText to a postfix string
			try {
				StringTokenizer tok = new StringTokenizer(infix);
				while(tok.hasMoreElements()){
					String token = tok.nextToken();
					if (token.equals("("))
						sList.push(token);
					if (token.equals(")")) {
						token = sList.pop();
					while (!token.equals("(")) {
						qList.enqueue(token);
						token = sList.pop();
					}
				}
					if (isOperator(token)) {
						while (!sList.isEmpty() && !sList.peek().equals("(") && (precedence(token) >= precedence(sList.peek()))) { //sList.isEmpty is having trouble
							qList.enqueue(sList.pop());
						}
						sList.push(token);
					}
					if (isNumeric(token)) {
						qList.enqueue(token);
					}
				}
				while (!sList.isEmpty()){
					qList.enqueue(sList.pop());
				}
				StringBuffer b = new StringBuffer();
				while(!qList.isEmpty()) {
					b.append(qList.dequeue() + " ");
				}
				postFix= b.toString().trim();
			} catch (Exception e) {
				postFix = "Error";
			}

		}
	private void evaluator() { //this method parses through the postfix string to send it to be evaluated
		try {
			StringTokenizer tok = new StringTokenizer(postFix);
			while (tok.hasMoreElements()) { //should stop here but isn't getting an error with stringtokenizer, possibly looping too many times
				String token = tok.nextToken();
				if (isNumeric(token))
					sList.push(token);
				else if (isOperator(token)) {
					double num1 = Double.parseDouble(sList.pop());
					double num2 = Double.parseDouble(sList.pop());
					sList.push(evaluate(num1, num2, token));
				}
			}
		} catch (Exception e) {
			sList.push("Error");
		}

	}

	private String evaluate(double n1, double n2, String token){ //this method does the actual math calculations
		switch (token) {
			case "+":
				return " " + (n1 + n2);
			case "-":
				return " " + (n2 - n1);
			case "*":
				return " " + (n1 * n2);
			case "/":
				return " " + (n2/ n1);
			case "^":
				return " " + (Math.pow(n2, n1));
		}
		return "error"; //check for errors here bruh
	}
	private int precedence(String op) { // this method checks the precedence of the operands for PEMDAS
		switch (op) {
			case "^":
				return 5;
			case "*":
				return 4;
			case "/":
				return 3;
			case "+":
				return 2;
			case "-":
				return 1;
		}
		return 0; //check for errors here i guess
	}
	private static boolean isNumeric(String str) //this method checks to see if an operand is a number
	{  
		try  
		{  
			double d = Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}
	private boolean isOperator(String s){ //returns true if an operator is in fact an operator
		return s.equals("^") || s.equals("*") || s.equals("/") || s.equals("+") || s.equals("-");
	}
}
