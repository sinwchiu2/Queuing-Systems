package des_mm1;

import java.util.LinkedList;
import java.util.Queue;

public class QueueBuffer {
    Queue<Event> buffer = new LinkedList<Event>();
    
    void add(Event e){
        buffer.add(e);
    }
    
    Event poll(){
        return buffer.poll();
    }
    
    int queueSize(){
        return buffer.size();
    }
}
