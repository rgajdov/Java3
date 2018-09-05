import java.io.*;
import java.util.*;

public class Main {

    private static void readFile() {
        FileInputStream inFile = null;
        try {
            inFile = new FileInputStream("src/main/resources/data.txt");
            byte[] byteArray = new byte[inFile.available()];
            int count = inFile.read(byteArray, 0, inFile.available());
            System.out.println("Считано из файла в массив: " + count + " байт");
            System.out.println("Содержимое массива:");
            //System.out.println(Arrays.toString(byteArray));
            for (byte array : byteArray) {
                System.out.print((char) array);
            }
            inFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inFile != null) {
                    inFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void mergeFiles() throws IOException {
        String[] fileNames = {
                "src/main/resources/input1.txt",
                "src/main/resources/input2.txt",
                "src/main/resources/input3.txt",
                "src/main/resources/input4.txt",
                "src/main/resources/input5.txt"
        };
        List<InputStream> al = new ArrayList<>();
        SequenceInputStream fileSequence = null;
        FileInputStream[] inputFiles = new FileInputStream[fileNames.length];
        FileOutputStream outputFile = new FileOutputStream("src/main/resources/output.txt");

        try {
            for (int i = 0; i < fileNames.length; i++) {
                inputFiles[i] = new FileInputStream(fileNames[i]);
                al.add(inputFiles[i]);
            }
            Enumeration<InputStream> list = Collections.enumeration(al);
            fileSequence = new SequenceInputStream(list);
            int readBuffer = fileSequence.read();
            while (readBuffer != -1) {
                outputFile.write(readBuffer);
                readBuffer = fileSequence.read();
            }
            System.out.println("Файл output.txt сформирован!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileSequence != null) {
                fileSequence.close();
            }
        }
    }

    private static void readPerPage() throws FileNotFoundException {
        System.out.println("Читаем файл War_and_Peace.txt");

        long t = System.currentTimeMillis();
        FileInputStream in = new FileInputStream("src/main/resources/War_and_Peace.txt");
        try {
            int pageSize = 1800;
            byte[] byteArray = new byte[in.available()];
            int count = in.read(byteArray, 0, in.available());
            System.out.println("Время загрузки и считывания файла в массив: " + (System.currentTimeMillis() - t) + " мс.");
            int pageCount = count / pageSize;
            int lastPageSize = count % pageSize;
            if (lastPageSize > 0) pageCount = pageCount + 1;

            System.out.println("Размер массива byteArray: " + byteArray.length);
            System.out.println("Прочитано байт из файла: " + count);
            System.out.println("Размер страницы: " + pageSize);
            System.out.println("Страниц всего: " + pageCount);
            System.out.println("Количество символов на последней странице: " + lastPageSize);

            System.out.print("Введите номер страницы от 1 до " + pageCount + ": ");
            Scanner sc = new Scanner(System.in);
            int pageNum = sc.nextInt();

            int startByte;
            int endByte;
            if (pageNum == 1) {
//                startByte = pageNum * pageSize - pageSize + (pageNum - 1);
                startByte = pageNum * pageSize - pageSize;
                endByte = startByte + pageSize;
            } else if (pageNum == pageCount) {
                startByte = pageNum * pageSize - pageSize;
                endByte = startByte + lastPageSize - 1;
            } else {
                startByte = pageNum * pageSize - pageSize;
                endByte = startByte + pageSize;
            }

//            System.out.println("startByte: " + startByte);
//            System.out.println("endByte: " + endByte);

            long t2 = System.currentTimeMillis();
            for (int i = startByte; i <= endByte; i++) {
                System.out.print((char) byteArray[i]);
            }
            System.out.println("\n\n");
            System.out.println("Время загрузки страницы: " + (System.currentTimeMillis() - t2) + " мс.");

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        System.setOut(new java.io.PrintStream(System.out, true, "Cp866"));

        System.out.println("Задание 1." );
        readFile();

        System.out.println("\n");

        System.out.println("Задание 2.");
        mergeFiles();

        System.out.println("\n");

        System.out.println("Задание 3.");
        readPerPage();
    }
}
