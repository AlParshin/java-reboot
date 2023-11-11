package ru.sberbank.edu;

/**
 * Класс CityInfo, который хранит координаты города и его название.
 */
public class CityInfo {

    private String name;
    private GeoPosition position;

    /**
     * Ctor.
     *
     * @param name     - city name
     * @param position - position
     */
    public CityInfo(String name, GeoPosition position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return this.name;
    }

    public double getPositionLatitude() {
        return this.position.getLatitude();
    }

    public double getPositionLongitude() {
        return this.position.getLongitude();
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "name='" + this.name + '\'' +
                ", position=" + this.position +
                '}';
    }
}
