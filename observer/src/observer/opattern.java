package observer; //

import java.util.*;

public class opattern {
	
	static public class Controller {
		
			public void parseInput(String input, Observer[] observerArray, TextState textstate) {
			
			switch (input) { // a bit like an if, elif, else. default is the "else"
				
				case "quit":
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
	
	static public class Observer {
		String name;
		boolean listening = false;
		
		public void Register() {
			if (listening == false) {
				listening = true;
				System.out.println(name+" is now registered");
			}
			else {
				System.out.println(name+" is already registered");
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
	}
	
	static public class TextState {
		
		ArrayList<String> notifications = new ArrayList<String>(); // arraylists are like arrays, but can be dynamically allocated
		
		ArrayList<Observer> registeredObservers = new ArrayList<Observer>();
		
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
		
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in); // needed for reading input
		Controller controller = new Controller(); // declare a controller object
		
		TextState textstate = new TextState(); // declare a textstate object
		
		Observer[] observerArray = new Observer[3]; // declare an array of 3 objects
		observerArray[0] = new Observer();
		observerArray[1] = new Observer();
		observerArray[2] = new Observer();
		
		observerArray[0].name = "Observer 1"; //set the names of the observers
		observerArray[1].name = "Observer 2";
		observerArray[2].name = "Observer 3";
		
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
