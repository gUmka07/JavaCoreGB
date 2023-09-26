package ru.gb;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorkingWithFiles {
    private static final Random random = new Random();
    private static final int CHAR_BOUNT_L = 65;
    private static final int CHAR_BOUNT_H = 90;
    private static final String TO_SEARCH = "GeekBrains";

    public static void main(String[] args) throws IOException {
        //System.out.println(generateSymbols(15));
        writeFileContents("sample01.txt", 30, 1);
        writeFileContents("sample02.txt", 30);
        concatenate("sample01.txt", "sample02.txt", "sampleOut.txt");

        if (searchInFile("sampleOut.txt", TO_SEARCH)) {
            System.out.printf("Файл %s содержит искомое слово %s\n", "sampleOut.txt", TO_SEARCH);
        }

        String[] fileNames = new String[10];
        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = "file_" + i + "." + "txt";
            writeFileContents(fileNames[i], 30, 3);
            System.out.printf("файл %s создан.\n", fileNames[i]);
        }

        List<String> result = searcMatch(new File("."), TO_SEARCH);
        System.out.println(Arrays.toString(new List[]{result}));

        createBackup(".");
    }

    /**
     * Метод гинерации некоторой последовательности символов
     *
     * @param count количество символов
     * @return последовательность символов
     */
    private static String generateSymbols(int count) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            stringBuilder.append((char) random.nextInt(CHAR_BOUNT_L, CHAR_BOUNT_H + 1));
        }
        return stringBuilder.toString();
    }

    /**
     * Запись последовательность символов в файл
     *
     * @param fileName имя файла
     * @param length   количество символов
     * @throws IOException
     */
    private static void writeFileContents(String fileName, int length) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(generateSymbols(length).getBytes());
        }
    }

    private static void writeFileContents(String fileName, int length, int i) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            if (random.nextInt(i) == 0) {
                fileOutputStream.write(TO_SEARCH.getBytes());
            }
            fileOutputStream.write(generateSymbols(length).getBytes());
        }
    }

    private static void concatenate(String fileIn1, String fileIn2, String fileOut) throws IOException {
        // на запись
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileOut)) {
            int c;
            try (FileInputStream fileInputStream = new FileInputStream(fileIn1)) {
                while ((c = fileInputStream.read()) != -1) {
                    fileOutputStream.write(c);
                }
            }

            try (FileInputStream fileInputStream = new FileInputStream(fileIn2)) {
                while ((c = fileInputStream.read()) != -1) {
                    fileOutputStream.write(c);
                }
            }
        }
    }

    /**
     * Определяет сожержиться в фале искомое слово
     *
     * @param fileName имя фйла
     * @param search   искомое слово
     * @return результат поиска
     * @throws FileNotFoundException
     */
    private static boolean searchInFile(String fileName, String search) throws FileNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            byte[] searchData = search.getBytes();
            int c;
            int i = 0;
            while ((c = fileInputStream.read()) != -1) {
                if (c == searchData[i]) {
                    i++;
                } else {
                    i = 0;
                    if (c == searchData[i])
                        i++;
                    continue;
                }
                if (i == searchData.length) {
                    return true;
                }
            }
            return false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> searcMatch (File dir, String search) throws IOException{
        dir = new File(dir.getCanonicalPath());
        List<String> list = new ArrayList<>();
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) continue;
            if (searchInFile(file.getName(), search)) {
                list.add(file.getName());

            }
        }
        return list;
    }

    /**
     * Резервная копия файлов из указанной директории
     * @param directoryPath директория из которой нужно создать резервные копии файлов
     */

    public static void createBackup(String directoryPath) {
        File directory = new File(directoryPath);
        File backupDirectory = new File("./backup");

        // Создаем папку для резервной копии, если она не существует
        if (!backupDirectory.exists()) {
            backupDirectory.mkdir();
        }

        // Получаем список файлов в указанной директории
        File[] files = directory.listFiles();

        if (files != null) {
            // Обходим все файлы и создаем их резервные копии
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        // Создаем путь для резервной копии файла в папке ./backup
                        String backupFilePath = backupDirectory.getAbsolutePath()
                                + File.separator + file.getName();

                        // Копируем файл в резервную папку
                        Files.copy(file.toPath(), new File(backupFilePath).toPath(),
                                StandardCopyOption.REPLACE_EXISTING);

                        System.out.println("Создана резервная копия файла: " + backupFilePath);
                    } catch (IOException e) {
                        System.out.println("Ошибка при создании резервной копии файла: " + file.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
