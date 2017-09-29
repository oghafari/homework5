package observer;

import java.util.Scanner;

public class opattern {
	
	static public class Controller {
		
		public void parseInput(String input) {
			
			switch (input) { // a bit like an if, elif, else. default is the "else"
				
			case "quit":
				System.exit(0);
				break;
			
			default:
				System.out.println("Unrecognized Command");
				break;
			}
		}
		
	}
	
	public class Observer {
		//placeholder
	}
	
	public class TextState {
		//placeholder
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in); // needed for reading input
		Controller controller = new Controller(); // declare a controller object
		String input = ""; // initial value of input
		
		while (true) { // this will run forever, or until the program ends
			input = reader.nextLine(); //get user input
			if (input != "") { //if input isn't empty, call parseInput and reset the input variable
				controller.parseInput(input);
				input = "";
			}
		}
		
	}
}
