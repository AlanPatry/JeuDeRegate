package fr.ensicaen.regatta.mvp.view;

import fr.ensicaen.regatta.mvp.Main;
import fr.ensicaen.regatta.mvp.presenter.ILoginView;
import fr.ensicaen.regatta.mvp.presenter.LoginPresenter;
import fr.ensicaen.regatta.mvp.presenter.ILoginView;
import fr.ensicaen.regatta.mvp.presenter.LoginPresenter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class LoginView implements ILoginView {
    private LoginPresenter _loginPresenter;
    private Stage _stage;
    @FXML
    private TextField _nickName;
    @FXML
    private Label _errorMessage;
    @FXML
    private ComboBox<String> _comboBox1;
    @FXML
    private ComboBox<String> _comboBox2;
    @FXML
    private ComboBox<String> _comboBox3;

    private static int _languageStatus = 0; // odd number for english, and even number for french

    public void setLoginPresenter( LoginPresenter presenter ) {
        _loginPresenter = presenter;
    }

    public void show() {
        _stage.show();
    }

    @Override
    public void close() {
        _stage.close();
    }

    @Override
    public void displayError( String message ) {
        _errorMessage.setText(message);
    }

    @FXML
    private void onClickOnStartGame() {
        String s1 = _comboBox1.getSelectionModel().getSelectedItem();
        String s2 = _comboBox2.getSelectionModel().getSelectedItem();
        String s3 = _comboBox3.getSelectionModel().getSelectedItem();
        if (s1 == null) {
            s1 = "";
        }
        else if (s2 == null) {
            s2 = "";
        }
        else if (s3 == null) {
            s3 = "";
        }
        _loginPresenter.launchGame(_nickName.getText(), s1, s2, s3);
    }

    @FXML
    private void switchLanguage() {
        _languageStatus++;
    }

    public static int getLanguageStatus() {
        return _languageStatus;
    }

    @FXML
    public void fillComboBox() {
        _comboBox1.setItems(FXCollections.observableArrayList("figaro", "oceanis-37"));
        _comboBox2.setItems(FXCollections.observableArrayList("Grande", "Petite"));
        _comboBox3.setItems(FXCollections.observableArrayList("2", "4"));
    }

    public static ResourceBundle getMessageBundle() {
        if ( getLanguageStatus() % 2 == 0 ) {
            return ResourceBundle.getBundle("fr.ensicaen.regatta.mvp.MessageBundle");
        } else {
            return ResourceBundle.getBundle("fr.ensicaen.regatta.mvp.MessageBundle_en_US");
        }

    }

    public static class LoginViewFactory {
        private LoginViewFactory() {
            // Factory class as Utility class where the constructor is private
        }

        public static LoginView createView( Stage primaryStage ) throws IOException {
            FXMLLoader loader = new FXMLLoader(LoginView.class.getResource("LoginDialog.fxml"), LoginView.getMessageBundle());
            Parent root = loader.load();
            LoginView view = loader.getController();
            Scene scene = new Scene(root);
            view._stage = primaryStage;
            primaryStage.setScene(scene);
            return view;
        }
    }
}