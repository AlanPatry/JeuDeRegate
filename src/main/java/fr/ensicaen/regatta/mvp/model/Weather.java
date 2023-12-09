package fr.ensicaen.regatta.mvp.model;

public class Weather {

    double _windAngle = 60; //degré
    double _windSpeed = 17; //noeud

    public double get_windSpeed() {
        return _windSpeed;
    }

    public double get_windAngle() {
        return _windAngle;
    }

}
