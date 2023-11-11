package ru.sberbank.edu;

import org.junit.Assert;
import org.junit.Test;

/**
 * Юнит тесты по ДЗ №5
 */
public class AppTest
{
    /**
     * Тест ForTravelService
     */
    @Test
    public void testForTravelService()
    {
        TravelService ts = new TravelService();
        ts.add(new CityInfo("Moscow", new GeoPosition("55(45'00'')", "37(37'00'')")));
        ts.add(new CityInfo("Piter", new GeoPosition("59(56'00'')", "30(19'00'')")));
        ts.add(new CityInfo("Rostov", new GeoPosition("47(14'00'')", "39(42'00'')")));
        ts.add(new CityInfo("Novosibirsk", new GeoPosition("55(1'00'')", "82(56'00'')")));
        ts.add(new CityInfo("Ekaterinburg", new GeoPosition("56(51'00'')", "60(36'00'')")));
        ts.add(new CityInfo("Samara", new GeoPosition("53(14'00'')", "50(10'00'')")));
        ts.add(new CityInfo("Omsk", new GeoPosition("54(59'00'')", "73(22'00'')")));
        ts.add(new CityInfo("Kazan", new GeoPosition("55(47'00'')", "49(10'00'')")));
        ts.add(new CityInfo("Ufa", new GeoPosition("54(49'00'')", "56(4'00'')")));
        ts.add(new CityInfo("Chelyabinsk", new GeoPosition("55(9'00'')", "61(26'00'')")));
        ts.add(new CityInfo("Volgograd", new GeoPosition("48(43'00'')", "44(29'00'')")));
        ts.add(new CityInfo("Voronezh", new GeoPosition("51(43'00'')", "39(16'00'')")));

        System.out.println(ts.toString());

        /**
         * Проверка на корректность удаления записи о городе
         * Проверка функции remove
         */
        int lenStart = ts.citiesNames().size();
        System.out.println("Before: " + ts.citiesNames());
        ts.remove("Ufa");
        System.out.println("After: " + ts.citiesNames());
        int lenFinish = ts.citiesNames().size();
        Assert.assertEquals(lenStart - lenFinish, 1);

        /**
         * Проверка на корректность расчета расстояний между городами
         * Проверка функции getDistance
        */
        System.out.println(ts.getDistance("Moscow", "Piter") +  " metrov");
        Assert.assertEquals(ts.getDistance("Moscow", "Piter"), 634332);
        System.out.println(ts.getDistance("Moscow", "Rostov") +  " metrov");
        Assert.assertEquals(ts.getDistance("Moscow", "Rostov"), 958085);
        /**
         * Проверка на корректность нахождения ближайших городов по заданному радиусу
         * Проверка функции getCitiesNear
         */
        System.out.println(ts.getCitiesNear("Rostov", 1000000));
        Assert.assertEquals(ts.getCitiesNear("Rostov", 1000000).size(), 4 );
        Assert.assertEquals(ts.getCitiesNear("Rostov", 600000).contains("Piter"), false );
        System.out.println(ts.getCitiesNear("Rostov", 600000));
        Assert.assertEquals(ts.getCitiesNear("Rostov", 600000).size(), 2 );
        Assert.assertEquals(ts.getCitiesNear("Rostov", 600000).contains("Volgograd"), true );
        Assert.assertEquals(ts.getCitiesNear("Rostov", 600000).contains("Voronezh"), true );

    }
}