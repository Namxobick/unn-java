package logic.Event;

import java.util.EventListener;

public interface MyEventListener extends EventListener {
    void processEvent(MyEvent event) throws Exception;
}
