package Controller;

import Model.PasswordManagerModel;
import View.MainView;

import javax.swing.*;

public class PasswordManagerController {

    private PasswordManagerModel model;
    private MainView mainView;

    public PasswordManagerController(PasswordManagerModel model, MainView view) {
        this.model = model;
        this.mainView = view;
        this.mainView.setController(this);
    }

    public void addPassword(String username, String password) {
        model.addPassword(username, password);
        mainView.updatePasswordList();
    }

    public void removePassword(String username) {
        model.removePassword(username);
        mainView.updatePasswordList();
    }
    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
