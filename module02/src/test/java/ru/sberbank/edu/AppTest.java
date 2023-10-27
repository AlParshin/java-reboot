package ru.sberbank.edu;

import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;

/**
 * Тест создан для проверки корректности
 *  считывания данных из файла "start.txt"
 *  и сохранения статистики в файл "statistic.txt"
 */

public class AppTest {
    @Test
    public void testSave() throws IOException {
        GetStatistic gs = new GetStatistic();
        System.out.println(gs.getLineCount());
        System.out.println(gs.getLongestLine());
        System.out.println(gs.getSpaceCount());
        gs.saveToFile(gs.getLineCount(), gs.getSpaceCount(), gs.getLongestLine());
        Assert.assertEquals(gs.getLineCount(), 7);
        Assert.assertEquals(gs.getSpaceCount(), 6);
        Assert.assertEquals(gs.getLongestLine(), "657   98");

    }
}
