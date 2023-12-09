package fr.ensicaen.regatta.mvp.presenter;

import fr.ensicaen.regatta.mvp.Main;
import fr.ensicaen.regatta.mvp.view.GameView;
import fr.ensicaen.regatta.mvp.view.LoginView;

import java.io.IOException;

public final class LoginPresenter {
    private ILoginView _loginView;

    public void setLoginView( LoginView loginView ) {
        _loginView = loginView;
    }

    public void launchGame( String nickName, String model, String sailSize, String nbPassengers ) {
        if (nickName.isEmpty() || model.isEmpty()|| sailSize.isEmpty() || nbPassengers.isEmpty() ) {
            _loginView.displayError(LoginView.getMessageBundle().getString("error.emptyBox"));

        } else {
            try {
                GameView view = GameView.GameViewFactory.createView();
                GamePresenter gamePresenter = new GamePresenter(nickName, model, sailSize, nbPassengers);
                view.setGamePresenter(gamePresenter);
                gamePresenter.setGameView(view);
                view.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            _loginView.close();
        }
    }
}
