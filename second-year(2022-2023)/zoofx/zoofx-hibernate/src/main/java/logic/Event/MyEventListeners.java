package logic.Event;

import java.util.LinkedList;
import java.util.List;

public class MyEventListeners {
    private List<MyEventListener> _eventListeners = new LinkedList<>();

    public void addEventListener(MyEventListener eventListener){
        _eventListeners.add(eventListener);
    }

    public void clearEventListener(){
        _eventListeners.clear();
    }

    public void notifyEventListeners(MyEvent event){
        for (MyEventListener eventListener: _eventListeners) {
            try {
                eventListener.processEvent(event);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
