/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DESMultiMMKKQ;

import java.util.Random;

/**
 *
 * @author waichiu
 */
public class DESMultiMMKKQ {
    private static final Random r = new Random();

    public static void main(String[] args) {
        //generate 10 sets of behaviour using same setting
        for (int i = 0; i < 10; i++) {
            //Variable initialization
            double totalArr = 0;
            int route[][] = new int[3][2];
            route[0][0] = 0;
            route[0][1] = 3;
            route[1][0] = 1;
            route[1][1] = 3;
            route[2][0] = 2;
            route[2][1] = 3;
            

            int state[] = new int[4];
            state[0] = 0;
            state[1] = 0;
            state[2] = 0;
            state[3] = 0;

            int serverAccess[] = new int[4];
            serverAccess[0] = 0;
            serverAccess[1] = 0;
            serverAccess[2] = 0;
            serverAccess[3] = 0;

            int servedArr[] = new int[4];
            servedArr[0] = 0;
            servedArr[1] = 0;
            servedArr[2] = 0;
            servedArr[3] = 0;

            int server[] = new int[4];
            server[0] = 10;
            server[1] = 10;
            server[2] = 10;
            server[3] = 10;

            double currentTime;

            double lambda[] = new double[3];
            lambda[0] = 10;
            lambda[1] = 10;
            lambda[2] = 10;
            double mu = 1;

            //Initialize Event and add two event types to the EventList
            EventList list = new EventList();
            list.add(new Event(Type.ARRIVAL, 1 / lambda[0], 0, -1));
            list.add(new Event(Type.ARRIVAL, 1 / lambda[1], 1, -1));
            list.add(new Event(Type.ARRIVAL, 1 / lambda[2], 2, -1));
            
            //perform serving, set to 10000 arrivals
            do {
                Event e = list.poll();
                currentTime = e.time;

                if (e.type == Type.ARRIVAL) {
                    totalArr++;
                    list.add(new Event(Type.ARRIVAL, currentTime + expRand(r, lambda[e.origin]), e.origin, -1));
                    for (int g = 0; g < route[e.origin].length; g++) {
//                        totalArr++;
                        serverAccess[route[e.origin][g]]++;
                        if (state[route[e.origin][g]] < server[route[e.origin][g]]) {
                            state[route[e.origin][g]]++;
                            servedArr[route[e.origin][g]]++;
                            //Generate the next departure event in server
                            list.add(new Event(Type.DEPARTURE, currentTime + expRand(r, mu), e.origin, route[e.origin][g]));
                            break;
                        }
                    }
                } else {
                    state[e.serverFarm]--;
                }
            } while (totalArr < 1000000);
            System.out.println("result set " + "(" + (i+1) + ")");
            System.out.println("server arrivals 0: " + serverAccess[0] + "\t\t" + "served customers 0: " + servedArr[0] + "\n" + "overflow arrivals 0: " + (double) (serverAccess[0] - servedArr[0]));
            System.out.println("server arrivals 1: " + serverAccess[1] + "\t\t" + "served customers 1: " + servedArr[1] + "\n" + "overflow arrivals 1: " + (double) (serverAccess[1] - servedArr[1]));
            System.out.println("server arrivals 2: " + serverAccess[2] + "\t\t" + "served customers 2: " + servedArr[2] + "\n" + "overflow arrivals 2: " + (double) (serverAccess[2] - servedArr[2]));
            System.out.println("server arrivals 3: " + serverAccess[3] + "\t\t" + "served customers 3: " + servedArr[3]);
            System.out.println("blocked arrivals: " + (double) (serverAccess[3] - servedArr[3]));
            System.out.println("blocking probability: " + (double) (serverAccess[3] - servedArr[3]) / totalArr);
            System.out.println(" ");
        }
    }

    private static double expRand(Random r, double m) {
        return (-Math.log(1 - r.nextDouble())) / m;
    }
}
