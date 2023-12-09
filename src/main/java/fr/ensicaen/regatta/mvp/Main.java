package fr.ensicaen.regatta.mvp;

import fr.ensicaen.regatta.mvp.presenter.LoginPresenter;
import fr.ensicaen.regatta.mvp.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public final class Main extends Application {

    public static void main( String[] args ) {
        launch(args);
    }

    @Override
    public void start( final Stage primaryStage ) throws Exception {
        primaryStage.setTitle(LoginView.getMessageBundle().getString("project.title"));
        LoginView view = LoginView.LoginViewFactory.createView(primaryStage);
        view.fillComboBox();
        LoginPresenter presenter = new LoginPresenter();
        presenter.setLoginView(view);
        view.setLoginPresenter(presenter);
        view.show();
    }

    @Override
    public void stop() {
        System.out.println(LoginView.getMessageBundle().getString("project.bye"));
    }
}