package observerpattern;


public interface Subject {
	public void registerObservers(Observer o);
	public void unregisterObserver(Observer o);
}

