/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DES_MMKK;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author waichiu
 */
public class DES_MMKK {

    public static void main(String[] args) {
        //variables initialisation
        double arrivals = 0, blocked = 0;
        int state = 0, server;
        double curTime = 0;

        //prompt to input basic parameters
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of servers: ");
        server = input.nextInt();
        System.out.print("Enter the arrival rate lambda: ");
        double lambda = input.nextDouble();
        System.out.print("Enter the service rate mu: ");
        double mu = input.nextDouble();        

        //Generate an exponetial random number with lambda
        Random r = new Random();

        //Initialize an Evnet and add the Evnet to the EventList
        EventList list = new EventList();
        list.add(new Event(Type.ARRIVAL, 1/lambda));

        //process 10000 arrivals
        do{
            Event e = list.poll();
            curTime = e.time;
            
            //arrivals processing
            if (e.type == Type.ARRIVAL) {
                arrivals++;
                if (state < server) {
                    state++;
                    list.add(new Event(Type.DEPARTURE, (curTime+expRand(r,mu))));
                } else {
                    blocked++;
                }

                list.add(new Event(Type.ARRIVAL, curTime+expRand(r,lambda)));
            } else {
                state--;
            }
        }while(arrivals < 10000000);
        
        System.out.println(blocked / arrivals);
    }

    //Generate an exponential random number
    private static double expRand(Random r, double m) {
        return (-Math.log(1 - r.nextDouble())) / m;
    }
}
