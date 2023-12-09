package fr.ensicaen.regatta.mvp.presenter;

import fr.ensicaen.regatta.mvp.model.BoatModel;


import fr.ensicaen.regatta.mvp.model.PlayerModel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GamePresenter {
    private final PlayerModel _playerModel;
    private BoatModel _boatModel;
    private IGameView _gameView;
    private boolean _started = false;
    private Timeline _timeline;

    public GamePresenter( String nickName, String model, String sailSize, String nbPassengers ) {
        _playerModel = new PlayerModel();
        _playerModel.setNickname(nickName);
        initGame(model, sailSize, nbPassengers);
    }

    public void setGameView( IGameView gameView ) {
        _gameView = gameView;
        _gameView.addBoat(_boatModel.getX(), _boatModel.getY());
        _gameView.addPositions(_boatModel.getX(), _boatModel.getY());
        _gameView.addSpeed(_boatModel.getDx(), _boatModel.getDy());
    }

    public void handleUserAction( UserAction code ) {
        if (code == UserAction.START) {
            startGame();
        } else {
            changeDirection(code);
        }
    }

    private void startGame() {
        if (!_started) {
            _started = true;
            runGameLoop();
        }
    }

    private void changeDirection( UserAction action ) {
        if (action == UserAction.LEFT) {
            _boatModel.rotate(-(_boatModel.getNbPassengers()));
        } else if (action == UserAction.RIGHT) {
            _boatModel.rotate(+(_boatModel.getNbPassengers()));
        }
    }

    private void initGame(String model, String sailSize, String nbPassengers ) {
        _boatModel = new BoatModel();
        _boatModel.setBoatModel(model);
        _boatModel.setSailSize(sailSize);
        _boatModel.setNbPassengers(nbPassengers);
    }

    private void runGameLoop() {
        _timeline = new Timeline(new KeyFrame(Duration.millis(50), onFinished -> {
            update();
            render();
            _gameView.addPositions(_boatModel.getX(), _boatModel.getY());
            _gameView.addSpeed(_boatModel.getDx(), _boatModel.getDy());

        }));
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    private void update() {
        try {
            _boatModel.move();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void render() {
        _gameView.update(_boatModel.getDx(), _boatModel.getDy(), _boatModel.getAngle());
    }
}
