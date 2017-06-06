/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DESMultiMMKKQ;
/**
 *
 * @author waichiu
 */
enum Type {
    ARRIVAL, DEPARTURE 
}

public class Event implements Comparable<Event> {
    int origin;
    double time;
    Type type;
    int serverFarm;

    public Event(Type type, double time,int origin,int serverFarm) {
        this.type = type;
        this.time = time;
        this.origin=origin;
        this.serverFarm=serverFarm;
    }

    @Override
    public int compareTo(Event e) {
        if (time > e.time) {
            return 1;
        } else if (time < e.time) {
            return -1;
        } else {
            return 0;
        }

        //return time.compareTo(e.time);
    }
}
