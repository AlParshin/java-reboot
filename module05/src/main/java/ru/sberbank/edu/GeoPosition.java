package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Класс GeoPosition, который хранит координаты города (широта и долгота в радианах).
 * Конструктор принимает координаты в градусах, которые затем преобразовываются в конструкторе в радианы.
 * Пример входных значений конструктора:
 * 55
 * 55(45'07'')
 * 59(57'00'')
 */
public class GeoPosition {

    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    /**
     * Ctor.
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) {

        ArrayList<String> arr = new ArrayList<>();
        arr.add(latitudeGradus);
        arr.add(longitudeGradus);

        for (int i = 0; i < arr.size(); i++) {
            String[] gradus = arr.get(i).split("\\(|'|\\)");

            double decimalDegrees = 0, radians = 0;

            if (gradus.length == 1) {
                radians = Double.parseDouble(gradus[0]) * Math.PI / 180;
            }
            else if (gradus.length == 2) {
                decimalDegrees = Double.parseDouble(gradus[0]) + Double.parseDouble(gradus[1]) / 60;
                radians = decimalDegrees * Math.PI / 180;
            }
            else if (gradus.length == 3) {
                decimalDegrees = Double.parseDouble(gradus[0]) + (Double.parseDouble(gradus[1]) + Double.parseDouble(gradus[2]) / 60) / 60;
                radians = decimalDegrees * Math.PI / 180;
            }
            else {
                System.out.println("Введено некорректное градусов в значении " + gradus);
            }
            if (i == 0) {
                this.latitude = radians;
            }
            else {
                this.longitude = radians;
            }
        }

        // parse and set latitude and longitude in radian
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude=" + this.latitude +
                ", longitude=" + this.longitude +
                '}';
    }

    // getters and toString
}