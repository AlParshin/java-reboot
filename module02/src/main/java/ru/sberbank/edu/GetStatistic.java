package ru.sberbank.edu;

import java.io.*;

/**
 * Это основной класс, который имплементит интерфес.
 */

public class GetStatistic implements Statistic {

    /**
     * Здесь начальный и конечный файл со статистикой.
     */
    String fileName = "src/main/java/ru/sberbank/edu/start.txt";
    String fileStatistic = "src/main/java/ru/sberbank/edu/statistic.txt";

    /**
     * Метод для подсчета количества строк.
     */

    @Override
    public int getLineCount() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int lines = 0;
        try {
            while (br.readLine() != null) {
                lines++;
            }
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            br.close();
        }
    }

    /**
     * Метод для подсчета количества пробелов.
     */

    @Override
    public int getSpaceCount() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int count = 0;
        String temp;
        try {
            while ((temp = br.readLine()) != null) {
                for (int i = 0; i < temp.length(); i++) {
                    if (temp.charAt(i) == ' ') {
                        count++;
                    }
                }
            }
            return count;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            br.close();
        }
    }

    /**
     * Метод для поиска самой длинной строки.
     */

    @Override
    public String getLongestLine() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String temp;
        String max = "";
        try {
            while ((temp = br.readLine()) != null) {
                if (temp.length() > max.length()) {
                    max = temp;
                }
            }
            return max;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            br.close();
        }
    }

    /**
     * Метод для сохранения в файл.
     */

    @Override
    public void saveToFile(int lineCount, int spaceCount, String line) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Количество строк = ");
        sb.append(lineCount);
        sb.append(", Количество пробелов = ");
        sb.append(spaceCount);
        sb.append(", Самая длинная строка = ");
        sb.append("'" + line + "'");
        FileWriter writer = new FileWriter(fileStatistic);
        writer.write(sb.toString() + "\r\n");
        writer.close();
    }

    /**
     * Метод для сохранения в базу данных.
     * Он пока пуст, поскольку интеграции с БД у нас пока нет.
     */

    @Override
    public void saveToDatabase(int lineCount, int spaceCount, String line) {
        System.out.println("Сохраняет в базу данных...");
    }

}