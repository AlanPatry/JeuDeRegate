package fr.ensicaen.regatta.mvp.view;

import fr.ensicaen.regatta.mvp.Main;
import fr.ensicaen.regatta.mvp.presenter.GamePresenter;
import fr.ensicaen.regatta.mvp.presenter.IGameView;
import fr.ensicaen.regatta.mvp.presenter.UserAction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;

import static java.lang.Math.sqrt;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

public class GameView implements IGameView {
    private static Stage _stage;
    private GamePresenter _gamePresenter;
    private Ellipse _boat;
    @FXML
    private Text _text;
    @FXML
    private Text _text2;
    @FXML
    private AnchorPane _base;

    public void setGamePresenter( GamePresenter gamePresenter ) {
        _gamePresenter = gamePresenter;
    }

    public void rotate( Ellipse boat, double val ) {
        boat.setRotate(val);
    }

    public Ellipse drawBoat( double x, double y, double rx, double ry ) {
        Ellipse boat = new Ellipse(x, y, rx, ry);
        boat.setFill(BLACK);
        _base.getChildren().add(boat);
        return boat;
    }

    public Text writePosition(double x, double y) {
        _text.setText("x = " + Math.round(x*100)/100 + "\ny = " + Math.round(y*100)/100);
        _text.setFont(Font.font ("Verdana", 15));
        _text.setFill(BLACK);
        _text.setX(50);
        _text.setY(520);
        return _text;
    }

    public Text writeSpeed(double dx, double dy) {
        _text2.setText("V = " + Math.round(sqrt(dx*dx + dy*dy)*100)/100);
        _text2.setFont(Font.font ("Verdana", 15));
        _text2.setFill(BLACK);
        _text2.setX(50);
        _text2.setY(500);
        return _text2;
    }

    public void move( Ellipse boat, double dx, double dy ) {
        boat.setLayoutX(boat.getLayoutX() + dx);
        boat.setLayoutY(boat.getLayoutY() + dy);
    }

    public void update( double dx, double dy, double angle ) {
        rotate(_boat, angle);
        move(_boat, dx, dy);
    }

    public void show() {
        _stage.show();
    }

    public void addBoat( double x, double y ) {
        _boat = drawBoat(x, y, 6, 16);
    }

    public void addPositions(double x, double y) {_text = writePosition(x,y); }

    public void addSpeed(double dx, double dy) {_text2 = writeSpeed(dx,dy); }

    private void handleKeyPressed( KeyCode code ) {
        if (code == KeyCode.SPACE) {
            _gamePresenter.handleUserAction(UserAction.START);
        } else if (code == KeyCode.LEFT) {
            _gamePresenter.handleUserAction(UserAction.LEFT);
        } else if (code == KeyCode.RIGHT) {
            _gamePresenter.handleUserAction(UserAction.RIGHT);
        }
    }

    public static class GameViewFactory {
        private GameViewFactory() {
            // Factory class as Utility class where the constructor is private
        }

        public static GameView createView() throws IOException {
            FXMLLoader loader = new FXMLLoader(LoginView.class.getResource("SpotMap.fxml"), LoginView.getMessageBundle());
            Parent root = loader.load();
            GameView view = loader.getController();
            Scene scene = new Scene(root, 800, 600);
            Stage stage = new Stage();
            stage.setTitle(LoginView.getMessageBundle().getString("project.title"));
            stage.setScene(scene);
            _stage = stage;
            scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                KeyCode code = event.getCode();
                view.handleKeyPressed(code);
            });
            return view;
        }
    }
}