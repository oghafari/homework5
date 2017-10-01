package observerpattern;

import java.util.*;


public class TextState implements Subject {
	static ArrayList<Observer> observers;
	static int oSize;
	static String arrytoString;
	
	public TextState() {
		observers = new ArrayList<Observer>();
	}

	public void registerObservers(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
		for (Observer ob : observers) {
			System.out.println(ob.getClass().getSimpleName() + " is waiting for TextState Notifications");
	}
	}
	
	public void unregisterObserver(Observer o) {
		observers.remove(o);
		oSize = observers.size();
		for (Observer observer : observers) {
			System.out.println(observer.getClass().getSimpleName() + " is waiting for TextState Notifications");
	}
	}
}
