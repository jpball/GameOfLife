import java.util.ArrayList;
public class EditQueue implements Subject{
    private ArrayList<Observer> observers;
    //--
    public EditQueue(){
        observers = new ArrayList<Observer>();
    }
    //--
    @Override
    public void notifyObservers(int x, int y, boolean isRevive) {
        for (Observer observer : observers) {
            observer.update(x, y, isRevive);
        }
    }
    //--
    @Override
    public void attach(Observer o) {
        observers.add(o);
    }
    //--
    @Override
    public void detach(Observer o) {
        observers.remove(o);        
    }
}
