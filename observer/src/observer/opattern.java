package observer; //

import java.util.*;

public class opattern {
	
	static public class Controller {
		
			public void parseInput(String input, Observer[] observerArray, TextState textstate) {
			
			if (input.startsWith("send ")) {
				input = input.substring(5);
				textstate.Notify(input);
			}
			
			else {
			switch (input) { // a bit like an if, elif, else. default is the "else"
				
				case "quit":
					System.out.println("exiting the program");
					System.exit(0); // exits the program
					break;
				
				case "register observers":
					for (int i = 0; i < observerArray.length; i++) { //go through each of the observers and call the register method on them
						textstate.Register(observerArray[i]);
					}
					break;
				
				case "status":
					for (int i = 0; i < observerArray.length; i++) { //go through each of the observers and call the register method on them
						observerArray[i].getStatus();
					}
					break;
				
				case "unregister":
					for (int i = 0; i < observerArray.length; i++) { //go through each of the observers and call the register method on them
						textstate.UnRegister(observerArray[i]);
					}
					break;
			
				default:
					System.out.println("Unrecognized Command");
					break;
			}
			}
		}
			
		
	}
	
	static public class Observer {
		String name;
		boolean listening = false;
		int count = 0;
		int max = -1;
		ArrayList<Character> keys = new ArrayList<Character>();
		
		public void Register() {
			if (listening == false) {
				listening = true;
				System.out.println(name+" is now registered");
				count = 0;
			}
			else {
				System.out.println(name+" is already registered, but I reset the count");
				count = 0;
			}
		}
		
		public void UnRegister() {
			if (listening == true) {
				listening = false;
				System.out.println(name+" is now unregistered");
			}
			else {
				System.out.println(name+" is already unregistered");
			}
		}
		
		public void getStatus() {
			if (listening == true) {
				System.out.println(name+" is listening");
			}
			else {
				System.out.println(name+" is not listening");
			}
		}
		
		public void Update(String input, TextState textstate) {
			int number = 0;
			
			System.out.println("I am "+name+", the following key characters appear in the input string:");
			
			for (int i = 0; i < input.length(); i++) {
				if (count >= max) {
					System.out.println("I have hit my limit... goodbye");
					textstate.DelayUnRegister(this);
				}
				else if (keys.contains(input.charAt(i))) {
					System.out.println(input.charAt(i));
					count++;
					number++;
				}
				else {
					//do nothing
				}
			}
			
			System.out.println("for a total of "+number+" key characters");
		}
	}
	
	static public class TextState {
		
		ArrayList<Observer> registeredObservers = new ArrayList<Observer>();
		
		ArrayList<Observer> preventConcurrentModification = new ArrayList<Observer>();
		
		public void Register(Observer observer) {
			if (registeredObservers.contains(observer) == false) {
				registeredObservers.add(observer);
			}
			observer.Register();
		}
		
		public void UnRegister(Observer observer) {
			registeredObservers.remove(observer);
			observer.UnRegister();
		}
		
		public void DelayUnRegister(Observer observer) {
			preventConcurrentModification.add(observer);
			observer.UnRegister();
		}
		
		public void Notify(String input) {
			for (int i = 0; i < registeredObservers.size(); i++) {
				registeredObservers.get(i).Update(input, this);
			}
			for (Observer o : preventConcurrentModification) {
				registeredObservers.remove(o);
			}
			preventConcurrentModification.clear();
		}
		
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in); // needed for reading input
		Controller controller = new Controller(); // declare a controller object
		
		TextState textstate = new TextState(); // declare a textstate object
		
		Observer[] observerArray = new Observer[3]; // declare an array of 3 objects
		observerArray[0] = new Observer();
		observerArray[1] = new Observer();
		observerArray[2] = new Observer();
		
		observerArray[0].name = "Observer 1";
		observerArray[0].max = 5;
		observerArray[0].keys.addAll(Arrays.asList('a', 'e', 'o', 'i', 'u', 'A', 'E', 'O', 'I', 'U'));
		
		observerArray[1].name = "Observer 2";
		observerArray[1].max = 10;
		observerArray[1].keys.addAll(Arrays.asList('q', 'w', 'r', 't', 'y', 'p', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm',
				'Q', 'W', 'R', 'T', 'Y', 'P', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'));
		
		observerArray[2].name = "Observer 3";
		observerArray[2].max = 2147483647;
		observerArray[2].keys.addAll(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
		
		String input = ""; // initial value of input
		
		while (true) { // this will run forever, or until the program ends
			input = reader.nextLine(); //get user input
			if (input != "") { //if input isn't empty, call parseInput and reset the input variable
				controller.parseInput(input, observerArray, textstate);
				input = "";
			}
		}
		
	}
}
