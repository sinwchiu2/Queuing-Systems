/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MM1Q;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author sinwchiu2 classnote P.135
 */
public class MM1Q {

    public static void main(String[] args) {
        /*  Qsize = queue size, avgQsize = mean queue size, arrivals = number of customer
            maxQ = max no. of customer, mu = service rate, lambda = arrival rate;
         */
        double Qsize = 0, arrivals = 0, maxQ = 1000000, avgQsize = 0;
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the arrival rate lambda: ");
        double lambda = input.nextDouble();
        System.out.print("Enter the service rate mu: ");
        double mu = input.nextDouble();

        PrintWriter pw;
        while (true) {
            try {
                pw = new PrintWriter("Results.txt");
                double random = getRandom();
                if (random <= lambda / (lambda + (hasQueue(Qsize) * mu))) {
                    arrivals++;
                    avgQsize = ((arrivals - 1) * avgQsize + Qsize) / arrivals;
                    Qsize++;
                } else {
                    Qsize--;
                }
                if (arrivals < maxQ) {
                    continue;
                } else {
                    pw.println(avgQsize);
                    pw.close();
                    System.out.println(avgQsize);
                    break;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
// a uniform U(0; 1) random deviate. A new value for R(01) is generated
// every time it is called.
    private static double getRandom() {
        return ThreadLocalRandom.current().nextDouble(0, 1);
    }

    private static int hasQueue(double customer) {
        if (customer > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
