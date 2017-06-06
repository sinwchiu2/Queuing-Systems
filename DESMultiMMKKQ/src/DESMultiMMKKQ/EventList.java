/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DESMultiMMKKQ;

import java.util.PriorityQueue;
/**
 *
 * @author waichiu
 */
public class EventList {

    PriorityQueue<Event> list = new PriorityQueue<>();

    void add(Event e) {
        list.add(e);
    }

    Event poll() {
        return list.poll();
    }
}
