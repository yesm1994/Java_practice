package com.steven.util;
import java.util.Random;
/**
 * Created by Steven on 2016/11/8.
 */

public class RandomApp {
    private Random random1 = null;
    private Random random2 = null;

    public RandomApp(){
        random1 = new Random();
        random2 = new Random(12345);
    }

    public void print(){
        System.out.println("random int : " + random1.nextInt());
        System.out.println("random float : " + random1.nextFloat());
        System.out.println("random double : " + random1.nextDouble());
        System.out.println("random Gaussian : " + random1.nextGaussian());

        for (int i = 0; i < 5; i++) {
            System.out.println("random2 : " + random2.nextInt());
        }
    }

    public static void run(){
        RandomApp randomApp = new RandomApp();
        randomApp.print();
    }
}
