// Задание 1: При старте приложения запускаются три потока. Первый поток заполняет массив случайными числами. Два других потока ожидают заполнения. Когда массив заполнен оба потока запускаются. Первый поток находит сумму элементов массива, второй поток среднеарифметическое значение в массиве. Полученный массив, сумма и среднеарифметическое возвращаются в метод main, где должны быть отображены.
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

// Задание 2: Пользователь с клавиатуры вводит путь к файлу. После чего запускаются три потока. Первый поток заполняет файл случайными числами. Два других потока ожидают заполнения. Когда файл заполнен оба потока стартуют. Первый поток находит все простые числа, второй поток факториал каждого числа в файле. Результаты поиска каждый поток должен записать в новый файл. В методе main необходимо отобразить статистику выполненных операций.
/*package com.company;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main
{
    static int My_Array[];
    static String file_with_numbers_path;
    static String file_with_numbers_name;
    static File file_with_results;
    static class CommonResource { int even_numbers = 0; int odd_numbers = 0; }

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
                File file = new File(file_with_numbers_path);
                // Количество чётных чисел
                res.even_numbers = 0;
                try (Scanner sc = new Scanner(new File(file_with_numbers_path)))
                {
                    while (sc.hasNextInt()) { int en = sc.nextInt(); if (en % 2 == 0 && en != 0) { res.even_numbers++; } }
                }
                catch (IOException ex) { ex.printStackTrace(); }
                System.out.println("Количество чётных чисел:\t" + res.even_numbers);
                try { printResult1(file); System.out.println(); }
                catch (FileNotFoundException e) { e.printStackTrace(); }
                try { Thread.sleep(100); }
                catch (InterruptedException e) { e.printStackTrace(); }

                // Записать количество чётных чисел в txt-файл
                String fileData1 = "\nКоличество чётных чисел:\t" + res.even_numbers;
                try { Files.write(Paths.get(String.valueOf(file_with_results)), fileData1.getBytes(), StandardOpenOption.APPEND); }
                catch (IOException e) { System.out.println(e); }

                // Количество нечётных чисел
                res.odd_numbers = 0;
                try (Scanner sc = new Scanner(new File(file_with_numbers_path)))
                {
                    while (sc.hasNextInt()) { int i1 = sc.nextInt(); if (i1 % 2 != 0 && i1 != 0) { res.odd_numbers++; } }
                }
                catch (IOException ex) { ex.printStackTrace(); }
                System.out.println("Количество нечётных чисел:\t" + res.odd_numbers);
                try { printResult2(file); System.out.println(); }
                catch (FileNotFoundException e) { e.printStackTrace(); }
                try { Thread.sleep(100); }
                catch (InterruptedException e) { e.printStackTrace(); }

                // Записать количество нечётных чисел в txt-файл
                String fileData2 = "\nКоличество нечётных чисел:\t" + res.odd_numbers;
                try { Files.write(Paths.get(String.valueOf(file_with_results)), fileData2.getBytes(), StandardOpenOption.APPEND); }
                catch (IOException e) { System.out.println(e); }
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
            BufferedWriter bufferedwriter = null;
            try { bufferedwriter = new BufferedWriter(new FileWriter(file_with_numbers_name)); }
            catch (IOException e) { e.printStackTrace(); }
            for (int i = 0; i < new_data.length; i++)
            {
                try { bufferedwriter.write(new_data[i] + " "); }
                catch (IOException e) { e.printStackTrace(); }
            }
            try { bufferedwriter.flush(); }
            catch (IOException e) { e.printStackTrace(); }
            try { bufferedwriter.close(); }
            catch (IOException e) { e.printStackTrace(); }
            System.out.println("Файл перезаписан и прочитан!");
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException
    {
        // Создать txt-файл для записи результатов
        String file_with_results_name = "res.txt";
        String file_with_results_path = "C:/Users/User_PavelIks/IdeaProjects/console1";
        file_with_results = new File(file_with_results_path, file_with_results_name);
        if (file_with_results.createNewFile())
        { System.out.println("Создан файл " + file_with_results_name); }
        else { System.out.println("Обнаружен файл " + file_with_results_name); }

        // Указать путь+файл(-.txt) и проверить на предмет наличия файла
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введи путь: "); // C:/Users/User_PavelIks/IdeaProjects/console1/text.txt
        file_with_numbers_path = scanner.nextLine();
        file_with_numbers_name = file_with_numbers_path;
        File file_with_numbers = new File(file_with_numbers_name); if (file_with_numbers.exists()) { System.out.println("Файл есть!"); } else { System.out.println("Файла нет, но будет создан!"); }

        // Thread: Сгенерировать рандомные числа
        new My_random_numbers_Thread().start();
        Thread.sleep(500);

        // Прочитать и показать данные из файла в консоле
        try(FileInputStream fis = new FileInputStream(file_with_numbers_name))
        {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String file_data = new String(buffer);
            System.out.println("\nСодержимое файла:\t\t\t" + file_data);
            int i = -1;
            while ((i=fis.read()) != -1) { System.out.print((char)i); }
        }
        catch (IOException ex) { System.out.println(ex.getMessage()); }
        Thread.sleep(150);

        // Thread: Старт синхронных потоков
        CommonResource resource1 = new CommonResource();
        Thread t1 = new Thread(new CountThread(resource1));
        t1.start();
    }
}*/

// Задание 3: Пользователь с клавиатуры вводит путь к существующей директории и к новой директории. После чего запускается поток, который должен скопировать содержимое директории в новое место. Необходимо сохранить структуру директории. В методе main необходимо отобразить статистику выполненных операций.
/*package com.company;
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Main
{
    static String first_path; static String second_path;

    // Поток
    static class My_Thread extends Thread
    {
        public void run()
        {
            try
            {
                // Копировать содержимое из папки-A и вставить в папку-B
                File file = new File(first_path);
                File[] array_of_files = file.listFiles();
                Path path = Paths.get(second_path);
                if (array_of_files != null)
                {
                    for (File files : array_of_files)
                    {
                        try { Files.copy(files.toPath(), path.resolve(files.getName()), StandardCopyOption.REPLACE_EXISTING); }
                        catch (IOException e) { e.printStackTrace(); }
                    };
                    System.out.print("Успешно скопировано и вставлено.\n" );
                }
            }
            catch (Exception exception) { exception.printStackTrace(); }
        }
    }

    public static void main(String[] args)
    {
        // Ввод путей
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Введи 1 путь: "); // C:/Users/User_PavelIks/IdeaProjects/console1/Folder_1
        first_path = scanner1.nextLine();
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Введи 2 путь: "); // C:/Users/User_PavelIks/IdeaProjects/console1/Folder_2
        second_path = scanner2.nextLine();

        // Перечень файлов
        String list = first_path;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of(list)))
        {
            System.out.print("Перечень файлов:\n");
            int number = 0;
            for (Path files: stream) { System.out.println(number++ + ". " + files.getFileName()); }
        }
        catch (IOException | DirectoryIteratorException x) { System.err.println(x); }

        // Thread: Начать копирование и вставку
        new My_Thread().start();
    }
}*/