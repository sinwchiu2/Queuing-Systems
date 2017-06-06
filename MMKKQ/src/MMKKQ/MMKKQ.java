/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MMKKQ;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author waichiu
 */

/*
Variables and input parameters: classnote P.159
serNo = number of servers;
customers = number of customers in the system (customerueue size);
blockProb = estimation for the blocking probability;
arrivals = number of customer arrivals counted so far;
blocked = number of blocked customers counted so far;
maxCustomer = maximal number of customer arrivals (it is used for the stopping condition);
mu = service rate;
lambda= arrival rate.
 */
public class MMKKQ {

    public static void main(String[] args) {
        double Qsize = 0, arr = 0, blocked = 0, maxQ = 20, blockProb;
        int serverCHN = 10, serverENG = 2, serverDual = 5;
        Scanner input = new Scanner(System.in);

        /*        System.out.print("Enter the number of Chinese servers: ");
        serverCHN = input.nextInt();
        System.out.print("Enter the number of English servers: ");
        serverENG = input.nextInt();
        System.out.print("Enter the number of Dual servers: ");
        serverDual = input.nextInt();
         */
        System.out.print("Enter the arrival rate lambda for Chinsese user: ");
        double lambdaCHN = input.nextDouble();
        System.out.print("Enter the arrival rate lambda for English user: ");
        double lambdaENG = input.nextDouble();
        System.out.print("Enter the service rate mu: ");
        double mu = input.nextDouble();
        double sum = lambdaCHN + lambdaENG + (serverCHN+serverENG+serverDual)*mu;

        PrintWriter pw;
        while (true) {
            try {
                pw = new PrintWriter("Results.txt");
                double random = getRandom();
                if (random < 0.9) {
                    if (random <= (lambdaCHN / (lambdaCHN + Qsize * mu))) {
                        arr++;
                        if (Qsize == serverCHN) {
                            blocked++;
                        } else {
                            Qsize++;
                        }
                    } else {
                        Qsize--;
                    }
                    if (arr < maxQ) {
                        continue;
                    } else {
                        blockProb = blocked / arr;
                        pw.println(blockProb);
                        pw.close();
                        System.out.println(blockProb);
                        break;
                    }
                }
                if(random > 0.9){
                if (random <= (lambdaENG / (lambdaENG + Qsize * mu))) {
                        arr++;
                        if (Qsize == serverENG) {
                            blocked++;
                        } else {
                            Qsize++;
                        }
                    } else {
                        Qsize--;
                    }
                    if (arr < maxQ) {
                        continue;
                    } else {
                        blockProb = blocked / arr;
                        pw.println(blockProb);
                        pw.close();
                        System.out.println(blockProb);
                        break;
                    }
                }                    
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static double getRandom() {
        return ThreadLocalRandom.current().nextDouble(0, 1);
    }
}
