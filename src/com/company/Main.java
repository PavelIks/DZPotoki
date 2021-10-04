package com.company;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main
{
    static int My_Array[];

    static class My_Array_Thread extends Thread
    {
        public void run()
        {
            try
            {
                My_Array = new int[20];
                for (int i = 0; i < My_Array.length; i++) { My_Array[i] = (int)(Math.random()*11); }
                for (int i = 0; i < My_Array.length; i++) { System.out.print(My_Array[i] + " "); } System.out.print("— массив (диапазон — 0-10)" + "\n");

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
                System.out.println(avg + " — среднее арифметическое массива");

                Thread.sleep(500);
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
        }
    }

    static class My_Sum_Thread extends Thread
    {
        public void run()
        {
            try
            {
                int count = IntStream.of(My_Array).sum();
                System.out.println(count + " — сумма массива");

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
        new My_Sum_Thread().start();
    }
}