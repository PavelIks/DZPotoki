package com.company;

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
                double a = Math.random();
                System.out.println (a);

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