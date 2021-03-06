package des_mm1;

enum Type { ARRIVAL, DEPARTURE }

public class Event implements Comparable<Event>{
    
    double time;
    Type type; 
    
    public Event(Type type, double time){
        this.type = type;
        this.time = time;
    }
    
    @Override
    public int compareTo(Event e) {
        if(time > e.time)
            return 1;
        else if(time < e.time)
            return -1;
        else
            return 0;
        
        //return time.compareTo(e.time);
    }
}
