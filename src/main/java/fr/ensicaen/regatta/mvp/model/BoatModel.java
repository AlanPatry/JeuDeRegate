package fr.ensicaen.regatta.mvp.model;

public class BoatModel {
    private double _x = 580;
    private double _y = 480;
    private double _dx = 0;
    private double _dy = 0;
    private int _anglePositive = 0;

    private String _model; //Figaro Or Oceanis

    private int _sailSize; //1 for Grande and 2 for petite

    private int _nbPassengers; // "2" or "4"

    private int chrono = 0;

    private final Weather weather = new Weather();

    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    public void rotate( int angle ) {
        _anglePositive = (360 + _anglePositive + angle) % 360;
    }

    public double getAngle() {
        return _anglePositive;
    }

    public double getDx() {
        return _dx;
    }

    public double getDy() {
        return _dy;
    }

    public String getModel() {
        return _model;
    }

    public int getSailSize() {
        return _sailSize;
    }

    public int getNbPassengers() {
        return _nbPassengers;
    }

    public void setBoatModel( String model ) {
        _model = model;
    }

    public void setSailSize( String sailSize ) {
        if (sailSize.equals("Grande")) {
            _sailSize = 1;
        } else if (sailSize.equals("Petite")) {
            _sailSize = 2;
        }
    }

    public void setNbPassengers( String nb ) {
        _nbPassengers = Integer.parseInt(nb);
    }

    public void move() throws Exception {
        Polar _polar_type = new Polar("polaire-" + _model + ".pol");
        if ((_x < 30 || _x > 760 || _y < 50 || _y > 550) && chrono < 50) {
            _dx = _dy = 0;
            _x += 0;
            _y += 0;
            chrono++;
        } else {
            if (Math.abs(weather.get_windAngle() - _anglePositive) >= 180) {
                _dx = (_polar_type.getSpeedFromFile(360 - Math.abs(weather.get_windAngle() - _anglePositive), weather.get_windSpeed()) * Math.sin(_anglePositive * Math.PI / 180)/2)/_sailSize;
                _dy = (-_polar_type.getSpeedFromFile(360 - Math.abs(weather.get_windAngle() - _anglePositive), weather.get_windSpeed()) * Math.cos(_anglePositive * Math.PI / 180)/2)/_sailSize;
            } else {
                _dx = (_polar_type.getSpeedFromFile(Math.abs(weather.get_windAngle() - _anglePositive), weather.get_windSpeed()) * Math.sin(_anglePositive * Math.PI / 180)/2)/_sailSize;
                _dy = (-_polar_type.getSpeedFromFile(Math.abs(weather.get_windAngle() - _anglePositive), weather.get_windSpeed()) * Math.cos(_anglePositive * Math.PI / 180)/2)/_sailSize;
            }
            _x += _dx;
            _y += _dy;
            chrono = 0;
        }
    }
}
