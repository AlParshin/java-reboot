package ru.sberbank.edu;

import java.io.IOException;

/**
 * В интерфейсе добавлен способ сохранения в базу данных.
 * Также в каждый метод добавлены исключения.
 */
public interface Statistic {

    int getLineCount() throws IOException;
    int getSpaceCount() throws IOException;
    String getLongestLine() throws IOException;
    void saveToFile(int lineCount, int spaceCount, String line) throws IOException;
    void saveToDatabase(int lineCount, int spaceCount, String line);

}
