package Controller;

import Model.PasswordManagerModel;
import View.MainView;

public class PasswordManagerController {

    // Reference to the model
    private PasswordManagerModel model;
    // Reference to the main view
    private MainView mainView;

    // Constructor to initialize the controller with model and view
    public PasswordManagerController(PasswordManagerModel model, MainView view) {
        this.model = model;
        this.mainView = view;
        this.mainView.setController(this);
    }

    // Method to add a password to the model and update the main view
    public void addPassword(String username, String password) {
        model.addPassword(username, password);
        mainView.updatePasswordList();
    }

    // Method to remove a password from the model and update the main view
    public void removePassword(String username) {
        model.removePassword(username);
        mainView.updatePasswordList();
    }
    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
