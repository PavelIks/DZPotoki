package com.company;

import java.util.Random;

public class Main
{
    static class My_Thread extends Thread
    {
        My_Thread(String name)
        {
            super(name);
        }

        public void run()
        {
            System.out.printf(Thread.currentThread().getName() + " started!\n");
            try
            {
                Random random = new Random();
                int Array[] = new int[10];
                for (int i = 0; i < Array.length; i++)
                {
                    Array[i] = random.nextInt();
                }
                for (int i = 0; i < Array.length; i++)
                {
                    System.out.print(Array[i] + " ");
                }
                System.out.print("\n");

                Thread.sleep(500);
            }
            catch(InterruptedException e)
            {
                System.out.println("Thread has been interrupted");
            }
            System.out.printf(Thread.currentThread().getName() + " fiished!\n");
        }
    }

    public static void main(String[] args)
    {
        new My_Thread("My_Thread").start();
    }
}