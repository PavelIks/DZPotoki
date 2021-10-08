// Задание: При старте приложения запускаются три потока. Первый поток заполняет массив случайными числами. Два других потока ожидают заполнения. Когда массив заполнен оба потока запускаются. Первый поток находит сумму элементов массива, второй поток среднеарифметическое значение в массиве. Полученный массив, сумма и среднеарифметическое возвращаются в метод main, где должны быть отображены.
/*package com.company;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main
{
    static int My_Array[];
    static class CommonResource { double average_of_numbers = 0; int sum_of_numbers = 0; }
    // Синхронизация двух потоков: AVG и сумма массива чисел
    static class CountThread implements Runnable
    {
        CommonResource res;
        CountThread(CommonResource com) { res = com; }

        @Override
        public void run()
        {
            synchronized (res)
            {
                // Найти и показать на консоле среднее арифметическое число массива
                res.average_of_numbers = Arrays.stream(My_Array).average().orElse(Double.NaN);
                System.out.println(res.average_of_numbers + " — среднее арифметическое массива");
                // Найти и показать на консоле сумму массива
                res.sum_of_numbers = IntStream.of(My_Array).sum();
                System.out.println(res.sum_of_numbers + " — сумма массива");
            }
        }
    }
    // Поток, генерирующий массив чисел
    static class My_Array_Thread extends Thread
    {
        public void run()
        {
            // Массив из 20 чисел
            My_Array = new int[20];
            // Сгенерировать массив из 20 чисел диапазоном 0-10
            for (int i = 0; i < My_Array.length; i++) { My_Array[i] = (int)(Math.random()*11); }
            // Показать на консоле массив из 20 чисел диапазоном 0-10
            for (int i = 0; i < My_Array.length; i++) { System.out.print(My_Array[i] + " "); }
            System.out.print("— массив (диапазон — 0-10)" + "\n");
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        // Thread: Генерация массива чисел
        new My_Array_Thread().start();
        Thread.sleep(500);
        // Thread: AVG и сумма массива чисел
        CommonResource resource1 = new CommonResource();
        Thread t1 = new Thread(new CountThread(resource1));
        t1.start();
    }
}*/

// Задание 2: ...
/*package com.company;
import java.io.*;
import java.util.Scanner;

public class Main
{
    static int My_Array[]; static String path1; static String file_name1;
    static class CommonResource { int x1 = 0; int x2 = 0; }

    static class CountThread implements Runnable
    {
        CommonResource res;
        CountThread(CommonResource com) { res = com; }

        // Синхронизация двух потоков
        @Override
        public void run()
        {
            synchronized (res)
            {
                File file = new File(path1);
                // Количество чётных чисел
                res.x1 = 0;
                try (Scanner sc = new Scanner(new File(path1)))
                {
                    while (sc.hasNextInt()) { int i1 = sc.nextInt(); if (i1 % 2 == 0 && i1 != 0) { res.x1++; } }
                }
                catch (IOException ex) { ex.printStackTrace(); }
                System.out.println("Количество чётных чисел:\t" + res.x1);
                try { printResult1(file); System.out.println(); }
                catch (FileNotFoundException e) { e.printStackTrace(); }
                try { Thread.sleep(100); }
                catch (InterruptedException e) { e.printStackTrace(); }

                // Количество нечётных чисел
                res.x2 = 0;
                try (Scanner sc = new Scanner(new File(path1)))
                {
                    while (sc.hasNextInt()) { int i1 = sc.nextInt(); if (i1 % 2 != 0 && i1 != 0) { res.x2++; } }
                }
                catch (IOException ex) { ex.printStackTrace(); }
                System.out.println("Количество нечётных чисел:\t" + res.x2);
                try { printResult2(file); System.out.println(); }
                catch (FileNotFoundException e) { e.printStackTrace(); }
                try { Thread.sleep(100); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    // Поиск чётных чисел
    static void printResult1(File file) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] numbers = line.split(" ");
        System.out.print("Чётные числа:\t\t\t\t");
        for (String number : numbers) { if (Integer.parseInt(number) != 0 && Integer.parseInt(number) % 2 == 0) { System.out.print(number + " "); } }
    }
    // Поиск нечётных чисел
    static void printResult2(File file) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] numbers = line.split(" ");
        System.out.print("Нечётные числа:\t\t\t\t");
        for (String number : numbers) { if (Integer.parseInt(number) != 0 && Integer.parseInt(number) % 2 != 0) { System.out.print(number + " "); } }
    }
    // Генерация массива из 20и случайных чисел диапазоном 0-10
    static class My_random_numbers_Thread extends Thread
    {
        public void run()
        {
            // Сгенерировать массив из 20и рандомных чисел диапазоном 0-10
            My_Array = new int[20]; for (int i = 0; i < My_Array.length; i++) { My_Array[i] = (int)(Math.random()*11); }

            // Перезаписать данные файла и записать в файл массив рандомных чисел
            int new_data[] = My_Array;
            BufferedWriter outputWriter = null;
            try { outputWriter = new BufferedWriter(new FileWriter(file_name1)); }
            catch (IOException e) { e.printStackTrace(); }
            for (int i = 0; i < new_data.length; i++)
            {
                try { outputWriter.write(new_data[i] + " "); }
                catch (IOException e) { e.printStackTrace(); }
            }
            try { outputWriter.flush(); }
            catch (IOException e) { e.printStackTrace(); }
            try { outputWriter.close(); }
            catch (IOException e) { e.printStackTrace(); }
            System.out.println("Файл перезаписан и прочитан!");
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        // Указать путь+файл(-.txt) и проверить на предмет наличия файла
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Введи путь: "); // C:/Users/User_PavelIks/IdeaProjects/console1/text.txt
        path1 = scanner1.nextLine();
        file_name1 = path1;
        File file1 = new File(file_name1); if (file1.exists()) { System.out.println("Файл есть!"); } else { System.out.println("Файла нет!"); }

        // Thread: Сгенерировать рандомные числа
        new My_random_numbers_Thread().start();
        Thread.sleep(500);

        // Прочитать и показать данные из файла в консоле
        try(FileInputStream fis1 = new FileInputStream(file_name1))
        {
            byte[] buffer = new byte[fis1.available()];
            fis1.read(buffer);
            String file_data1 = new String(buffer);
            System.out.println("\nСодержимое файла:\t\t\t" + file_data1);
            int i = -1;
            while ((i=fis1.read()) != -1) { System.out.print((char)i); }
        }
        catch (IOException ex) { System.out.println(ex.getMessage()); }
        Thread.sleep(150);

        // Thread: Старт синхронных потоков
        CommonResource resource1 = new CommonResource();
        Thread t1 = new Thread(new CountThread(resource1));
        t1.start();
    }
}*/

// Задание 3: ...
package com.company;
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        String sourceDirName = "C:/Users/User_PavelIks/IdeaProjects/sync_threads/Sas1";
        String targetSourceDir = "C:/Users/User_PavelIks/IdeaProjects/sync_threads/Sas2";

        // Перечень файлов
        String dir = sourceDirName;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of(dir)))
        {
            System.out.print("Перечень файлов:\n");
            int a1 = 0;
            for (Path files: stream)
            {
                a1++;
                System.out.println(a1+". " +files.getFileName());
            }
        }
        catch (IOException | DirectoryIteratorException x) { System.err.println(x); }

        // Копировать файлы из папки-1 и вставить в папку-2
        File folder = new File(sourceDirName);
        File[] listOfFiles = folder.listFiles();
        Path destDir = Paths.get(targetSourceDir);
        if (listOfFiles != null)
        {
            for (File file : listOfFiles)
            {
                Files.copy(file.toPath(), destDir.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
            };
            System.out.print("Успешно скопировано.\n" );
        }

        System.out.print("Успешно вставлено.\n");
    }
}