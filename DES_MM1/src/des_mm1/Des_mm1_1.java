package des_mm1;

import java.util.Random;

public class Des_mm1_1 {

    public static void main(String[] args) {

            //Observation
            for (int i = 0; i < 10; i++) {
                //Variable initialization
                double arrived = 0, departure = 0,currentTime = 0, meanQ = 0;
                int capacity = 1, state = 0;
                double MAX_departure = 10000000;
                double lambda = 0.8;
                double miu = 1;
                
                //Initialize an Evnet and add the Evnet to the EventList and the QueueBuffer
                Event event = new Event(Type.ARRIVAL, 1 / lambda);
                EventList list = new EventList();
                list.add(event);
                QueueBuffer buffer = new QueueBuffer();

                //Serve unti the MAX_arrived reach
                while (true) {
                    Event e = list.poll();
                    currentTime = e.time;

                    if (e.type == Type.ARRIVAL) {
                        arrived++;
                        
                        //Calculate the mean size of the queue
                        meanQ = ((arrived - 1) * meanQ + buffer.queueSize()) / arrived;

                        //When the server is avaliable
                        if (state < capacity) {
                            state++;

                            //Generate the next departure time
                            double depTime = exprand(miu);
                            list.add(new Event(Type.DEPARTURE, currentTime + depTime));

                        } //When the server is busy
                        else {
                            
                            //add the event to buffer
                            buffer.add(e);

                        }
                        //Generate the next arrival time
                        double nextArrTime = exprand(lambda);
                        list.add(new Event(Type.ARRIVAL, currentTime + nextArrTime));
                    }
                    //Departure
                    else {
                        if (buffer.queueSize() != 0) {
                            buffer.poll();
                            //Generate the next departure time
                            double depTime = exprand(miu);
                            list.add(new Event(Type.DEPARTURE, currentTime + depTime));
                        } else {
                            state--;
                        }
                        departure++;
                        //System.out.println(departure);
                    }

                    if (departure < MAX_departure) {
                        continue;
                    } else {
                        System.out.println((i+1) + ") " + "\n" + "Mean Queue Size: " + (meanQ + 1) + "\t" + "rho: " + lambda / miu + "\n");

                        break;
                    }
                }
            }
    }

    static double exprand(double x) {
        Random r = new Random();
        return Math.log(1 - r.nextDouble()) / (-x);
    }
}
