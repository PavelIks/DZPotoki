package com.company;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.Random;

public class Main
{
    static int My_Array[];
    static class My_Array_Thread extends Thread
    {
        public void run()
        {
            try
            {
                Random random = new Random();
                My_Array = new int[10];
                for (int i = 0; i < My_Array.length; i++) { My_Array[i] = random.nextInt(); }
                for (int i = 0; i < My_Array.length; i++) { System.out.print(My_Array[i] + " "); } System.out.print("\n");

                Thread.sleep(500);
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
        }
    }

    static class My_Average_Thread extends Thread
    {
        public void run()
        {
            try
            {
                double avg = Arrays.stream(My_Array).average().orElse(Double.NaN);
                System.out.println(avg);

                Thread.sleep(500);
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
        }
    }

    static class My_Average_Sum extends Thread
    {
        public void run()
        {
            try
            {
                int count = IntStream.of(My_Array).sum();
                System.out.println(count);

                Thread.sleep(500);
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        new My_Array_Thread().start();
        Thread.sleep(1000);
        new My_Average_Thread().start();
        new My_Average_Sum().start();
    }
}