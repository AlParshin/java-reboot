package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Класс TravelService, позволяющий получить расстояние между городами по их координатам.
 * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
 * https://planetcalc.com/71/
 * Внутри класса TravelService запрещено использовать циклы и итераторы!
 * Можно использовать только streams и методы, принимающие предикаты!
 * Нельзя объявлять другие поля в классе! Использовать только коллекцию cities!
 */
public class TravelService {

    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        cities.add(cityInfo);
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {
//        for (CityInfo city: cities) {
//            if (city.getName().equals(cityName)) {
//                cities.remove(city);
//            }
        cities.removeIf(c -> cityName.equalsIgnoreCase(c.getName()));
        }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        List<String> citiesNames = new ArrayList<>();
//        for (CityInfo city: cities) {
//            citiesNames.add(city.getName());
//        }

        return cities.stream().map(CityInfo::getName).toList();
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public int getDistance(String srcCityName, String destCityName) {
        // Радиус земли
        final double EARTH_RADIUS = 6372795;
        // Входные параметры
        double srcLatitude = 0, srcLongitude = 0, destLatitude = 0, destLongitude = 0;

//        for (CityInfo city : cities) {
//            if (city.getName().equals(srcCityName)) {
//                srcLatitude = city.getPositionLatitude();
//                srcLongitude = city.getPositionLongitude();
//            } else if (city.getName().equals(destCityName)) {
//                destLatitude = city.getPositionLatitude();
//                destLongitude = city.getPositionLongitude();
//            }
//        }

        List<CityInfo> src = cities.stream().filter(f -> f.getName().equals(srcCityName)).toList();
        srcLatitude = src.get(0).getPositionLatitude();
        srcLongitude = src.get(0).getPositionLongitude();

        List<CityInfo> dest = cities.stream().filter(f -> f.getName().equals(destCityName)).toList();
        destLatitude = dest.get(0).getPositionLatitude();
        destLongitude = dest.get(0).getPositionLongitude();

        // косинусы и синусы широт и разницы долгот
        double cl1 = Math.cos(srcLatitude);
        double cl2 = Math.cos(destLatitude);
        double sl1 = Math.sin(srcLatitude);
        double sl2 = Math.sin(destLatitude);
        double delta = destLongitude - srcLongitude;
        double cdelta = Math.cos(delta);
        double sdelta = Math.sin(delta);

        // вычисления длины большого круга
        double y = Math.sqrt(Math.pow(cl2 * sdelta, 2) + Math.pow(cl1 * sl2 - sl1 * cl2 * cdelta, 2));
        double x = sl1 * sl2 + cl1 * cl2 * cdelta;

        //
        double ad = Math.atan2(y, x);
        double dist = ad * EARTH_RADIUS;

        return (int)dist;
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {
//        List<String> citiesNear = new ArrayList<>();
//        for (int i = 0; i < cities.size(); i++) {
//            if ((this.getDistance(cityName, cities.get(i).getName()) <= radius) && (!cities.get(i).getName().equals(cityName)))  {
//                citiesNear.add(cities.get(i).getName());
//            }
//        }
//        return citiesNear;

        return cities.stream().filter(f -> ((this.getDistance(cityName, f.getName()) <= radius) && (!f.getName().equals(cityName)))).map(CityInfo::getName).toList();
    }

    @Override
    public String toString() {
        return "TravelService{" +
                "cities=" + cities +
                '}';
    }
}