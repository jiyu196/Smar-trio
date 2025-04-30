package domain;

import java.io.Serializable;

public class CalculatorApp implements Serializable{
	public static void main(String[] args) {
	    CalculatorApp app = new CalculatorApp("Calculator");
	    app.run();
	}
	String name;
	
	public CalculatorApp(String name) { 
		this.name= name;
	}
	
	public void run() {
	    while (true) {
	        // 1. Ask user what they want to do: add, subtract, etc.
	    	
	        // 2. Ask for first number
	        // 3. Ask for second number
	        // 4. Perform the operation
	        // 5. Print the result
	        // 6. Ask if they want to continue
	        //    If not, break
	    }
	}
	
	public String getName() {
        return name;
    }

}
