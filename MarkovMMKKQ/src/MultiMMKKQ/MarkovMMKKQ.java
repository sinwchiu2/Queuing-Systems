package MultiMMKKQ;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author waichiu
 */
public class MarkovMMKKQ {

    public static void main(String[] args) {
        Random r = new Random();
        PrintWriter pw_A, pw_B, pw_C;

        double lambda_A = 2, lambda_B = 2, mu = 1;

/*        mu = r.nextDouble();
        do {
            lambda_A = r.nextDouble();
        } while (lambda_A > mu);
        do {
            lambda_B = r.nextDouble();
        } while (lambda_B > mu);
        System.out.println("\nlambda_A: " + lambda_A + "\nlambda_B: " + lambda_B + "\nmu: " + mu + "\nrho_A: " + lambda_A / mu + "\nrho_B: " + lambda_B / mu + "\nrho_C: " + (lambda_A + lambda_B) / (mu) + "\n");
*/
        try {
            pw_A = new PrintWriter("out_A.txt");
            pw_B = new PrintWriter("out_B.txt");
            pw_C = new PrintWriter("out_C.txt");

            // Simuation
            for (int i = 0; i < 10; i++) {
                // Variable initialization
                int numOfServer_A = 0, numOfServer_B = 0, numOfServer_C = 3;
                int Q_A = 0, Q_B = 0, Q_C = 0;
                double numCustomer_A = 0, numCustomer_B = 0, numCustomer_C = 0;
                double numBlkCustomer_A = 0, numBlkCustomer_B = 0, numBlkCustomer_C = 0;
                double maxNumCustomer = 10000000;

                while (true) {
                    double uniform = r.nextDouble();
                    double sum = lambda_A + lambda_B + (Q_A + Q_B + Q_C) * mu;

                    //Arrival of A
                    if (uniform <= lambda_A / sum) {
                        if (Q_A < numOfServer_A) {
//                            System.out.print("Arrival of A");
                            numCustomer_A++;
                            Q_A++;
                        } else if (Q_C < numOfServer_C) {
//                            System.out.print("A -> Arrival of C");
                            numCustomer_C++;
                            Q_C++;
                        } else if (numOfServer_C == 0) {
//                                System.out.print("Block A");
                            numCustomer_A++;
                            numBlkCustomer_A++;
                        } else {
//                                System.out.print("Block C");
                            numCustomer_C++;
                            numBlkCustomer_C++;
                        }
                    } //Arrival of B
                    else if (uniform > lambda_A / sum && uniform <= (lambda_A + lambda_B) / sum) {
                        if (Q_B < numOfServer_B) {
//                            System.out.print("Arrival of B");
                            numCustomer_B++;
                            Q_B++;
                        } else if (Q_C < numOfServer_C) {
//                            System.out.print("B -> Arrival of C");
                            numCustomer_C++;
                            Q_C++;
                        } else if (numOfServer_C == 0) {
//                                System.out.print("Block B");
                            numCustomer_B++;
                            numBlkCustomer_B++;
                        } else {
//                                System.out.print("Block C");
                            numCustomer_C++;
                            numBlkCustomer_C++;
                        }
                    } //Departure of A
                    else if (uniform > (lambda_A + lambda_B) / sum) {
                        if (uniform <= (lambda_A + lambda_B + Q_A * mu) / sum) {
                            Q_A--;
//                          System.out.println("Departure of A");
                        } //Departure of B
                        else if (uniform > (lambda_A + lambda_B + Q_A * mu) / sum) {
                            if (uniform <= (lambda_A + lambda_B + (Q_A + Q_B) * mu) / sum) {
                                Q_B--;
//                          System.out.println("Departure of B");                      
                            } //Departure of C
                            else if (uniform > (lambda_A + lambda_B + (Q_A + Q_B) * mu) / sum) {
                                Q_C--;
//                      System.out.println("Departure of C");
                            }
                        }
                    }

                    if ((numCustomer_C) < maxNumCustomer) //Checking when both numOfServer_A and numOfServer_B =0
                    //                    if((numCustomer_C) < maxNumCustomer)      
                    {
                        continue;
                    } else {
                        double blkProb_A = numBlkCustomer_A / numCustomer_A;
                        double blkProb_B = numBlkCustomer_B / numCustomer_B;
                        double blkProb_C = numBlkCustomer_C / numCustomer_C;
                        System.out.println(i + 1 + ")");
//                        System.out.println("Blocking Prob of A: " + blkProb_A);
//                        System.out.println("Blocking Prob of B: " + blkProb_B);
                        System.out.println("Blocking Prob: " + blkProb_C);

                        break;
                    }
                }
            }
            pw_A.close();
            pw_B.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
