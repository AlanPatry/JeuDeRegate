package fr.ensicaen.regatta.mvp.presenter;

public interface IGameView {
    void addBoat( double x, double y );

    void addPositions(double x, double y);

    void addSpeed(double dx, double dy);

    void update( double dx, double dy, double angle );
}
