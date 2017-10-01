package observerpattern;

import java.util.Scanner;

public class Controller {
	static Scanner scanStr = new Scanner(System.in);
	static String input;
	static TextState textstate = new TextState();
	static obs1 o1 = new obs1(textstate);
	static obs2 o2 = new obs2(textstate);
	static obs3 o3 = new obs3(textstate); 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		enterinput();
	
	}
		
		
		public static void enterinput() {
			System.out.print("input > ");
			input = scanStr.nextLine();
			 String[] commandSplit = input.split(" ");
			
			
			 if (input.equals("register observers")) {
				if (TextState.oSize > 0) {
					System.out.println("Cannot register observer");
					enterinput();
				}
				else {
				textstate.registerObservers(o1);
				textstate.registerObservers(o2);
				textstate.registerObservers(o3);
				enterinput();
				}
			}
			
			else if (input.equals("unregister")) {
				int arrySz = TextState.oSize;
				if (arrySz == 0) {
					System.out.println("Observer list is empty");
					enterinput();
				}
				else {
				textstate.unregisterObserver(o1);
				textstate.unregisterObserver(o2);
				textstate.unregisterObserver(o3);
				System.out.println("All Observers unregistered");	
				enterinput();
				}
			}
				else {
				System.out.println("Unknown command");
				enterinput();
			}
					
		}
}
	
