package des_mm1;

import java.util.PriorityQueue;

public class EventList {
    PriorityQueue<Event> list = new PriorityQueue<Event>();
    
    void add(Event e){
        list.add(e);
    }
    
    Event poll(){
        return list.poll();
    }
}
