package com.company;

import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.Random;

public class Main
{
    static int My_Array[];
    static class My_Thread_1 extends Thread
    {
        My_Thread_1(String name)
        {
            super(name);
        }

        public void run()
        {
            System.out.printf(Thread.currentThread().getName() + " started!\n");
            try
            {
                Random random = new Random(); My_Array = new int[10];
                for (int i = 0; i < My_Array.length; i++)
                { My_Array[i] = random.nextInt(); }
                for (int i = 0; i < My_Array.length; i++)
                { System.out.print(My_Array[i] + " "); } System.out.print("\n");

                Thread.sleep(500);
            }
            catch(InterruptedException e)
            { System.out.println("Thread has been interrupted"); }
            System.out.printf(Thread.currentThread().getName() + " fiished!\n");
        }
    }

    static class My_Thread_2 extends Thread
    {
        My_Thread_2(String name)
        {
            super(name);
        }

        public void run()
        {
            System.out.printf(Thread.currentThread().getName() + " started!\n");
            try
            {
                /*double average = 0;
                if (My_Array.length > 0)
                { double sum = 0;
                for (int j = 0; j < My_Array.length; j++) { sum += My_Array[j]; }
                average = sum / My_Array.length; }
                System.out.println(average);*/
                OptionalDouble count = IntStream.of(My_Array).average();
                System.out.println(count);
                Thread.sleep(500);
            }
            catch(InterruptedException e)
            { System.out.println("Thread has been interrupted"); }
            System.out.printf(Thread.currentThread().getName() + " fiished!\n");
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        new My_Thread_1("My_Thread_1").start();
        Thread.sleep(1000);
        new My_Thread_2("My_Thread_2").start();
    }
}