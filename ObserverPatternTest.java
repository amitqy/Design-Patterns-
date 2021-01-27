package observer;

import java.util.ArrayList;
import java.util.List;

/*
Any use case like price change notification, delivery notification will implement this
interface
 */
interface Subject {
    void register(Observer obj);

    void unregister(Observer obj);

    void notifyObservers();
}

/*
Any one who wants to listen to notifications implements this interface
 */

interface Observer {
    void update(String location);
}

// class of  a particular use case : delivery
class DeliveryData implements Subject {

    /*
    Make a List of all observers who want to get notified.
     */
    private List<Observer> observers;
    private String location;

    public DeliveryData() {
        /*
        Initialise the list
         */
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(Observer obj) {
        observers.add(obj);
    }

    @Override
    public void unregister(Observer obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {
        for (Observer obj : observers) {
            obj.update(location);
        }
    }

    public void locationChanged() {
        this.location = getLocation();
        notifyObservers();
    }

    public String getLocation() {
        return "SomePlace";
    }

}

// class of someone who wants to get notifications by observing : seller
class Seller implements Observer {

    private String location;

    @Override
    public void update(String location) {
        this.location = location;
        showLocation();
    }

    public void showLocation() {
        System.out.println("Notification at Seller - Current Location " + location);
    }
}
// class of someone who wants to get notifications by observing : user
class User implements Observer {

    private String location;

    @Override
    public void update(String location) {
        this.location = location;
        showLocation();
    }

    public void showLocation() {
        System.out.println("Notification at Seller - Current Location " + location);
    }
}
// class of someone who wants to get notifications by observing : Delivery warehouse
class DeliveryWareHouse implements Observer {
    private String location;

    @Override
    public void update(String location) {
        this.location = location;
        showLocation();
    }

    public void showLocation() {
        System.out.println("Notification at Warehouse - Current Location " + location);
    }
}

public class ObserverPatternTest {


    public static void main(String[] args) {
        DeliveryData topic = new DeliveryData();

        Observer obj1 = new Seller();
        Observer obj2 = new User();
        Observer obj3 = new DeliveryWareHouse();

        topic.register(obj1);
        topic.register(obj2);
        topic.register(obj3);

        topic.locationChanged();

        topic.unregister(obj3);

        System.out.println();

        topic.locationChanged();


    }


}








