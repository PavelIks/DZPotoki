// Задание: При старте приложения запускаются три потока. Первый поток заполняет массив случайными числами. Два других потока ожидают заполнения. Когда массив заполнен оба потока запускаются. Первый поток находит сумму элементов массива, второй поток среднеарифметическое значение в массиве. Полученный массив, сумма и среднеарифметическое возвращаются в метод main, где должны быть отображены.
/*package com.company;

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
}*/

// Задание 2:

package com.company;

import java.io.*;
import java.util.Scanner;

public class Main
{
    static int My_Array[];

    public static void main(String[] args) throws IOException
    {
        // Указать путь+файл и проверить на предмет наличия файла
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Введи путь: "); /*C:/Users/User_PavelIks/IdeaProjects/console1/text.txt*/
        String path1 = scanner1.nextLine();
        String file_name1 = path1;
        File file1 = new File(file_name1); if (file1.exists()) { System.out.println("Файл есть!"); } else { System.out.println("Файла нет!"); }

        // Сгенерировать массив из 20и рандомных чисел диапазоном 0-10
        My_Array = new int[20]; for (int i = 0; i < My_Array.length; i++) { My_Array[i] = (int)(Math.random()*11); }

        // Перезаписать данные файла и записать в файл массив рандомных чисел
        int new_data[] = My_Array;
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(file_name1));
        for (int i = 0; i < new_data.length; i++) { outputWriter.write(new_data[i] + " "); } outputWriter.flush(); outputWriter.close();

        // Поиск чётных чисел
        int count = 0;
        try (Scanner sc = new Scanner(new File("C:/Users/User_PavelIks/IdeaProjects/console1/text.txt")))
        { while (sc.hasNextInt()) { if (sc.nextInt() % 2 == 0) { count++; } } }
        catch (IOException ex) { ex.printStackTrace(); }
        System.out.println(count);
        File file = new File("C:/Users/User_PavelIks/IdeaProjects/console1/text.txt");
        printResult(file);

        // Прочитать и вывести данные из файла
        try(FileInputStream fis1 = new FileInputStream(file_name1))
        {
            byte[] buffer = new byte[fis1.available()];
            fis1.read(buffer);
            String file_data1 = new String(buffer);
            System.out.println("Содержимое файла: " + file_data1);
            int i = -1;
            while ((i=fis1.read()) != -1) { System.out.print((char)i); }
        }
        catch (IOException ex) { System.out.println(ex.getMessage()); }

        System.out.println("Файл перезаписан и прочитан!");
    }

    public static void printResult(File file) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] numbers = line.split(" ");
        for (String number : numbers) { if (Integer.parseInt(number) != 0 && Integer.parseInt(number) % 2 == 0) { System.out.print(number + " "); } }
    }
}