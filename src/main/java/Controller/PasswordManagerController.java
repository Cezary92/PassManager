package Controller;

public class PasswordManagerController {
/**
    private PasswordManagerModel model;
    private PasswordManagerView view;

    public PasswordManagerController(PasswordManagerModel model, PasswordManagerView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public void addPassword(String password) {
        model.addPassword(password);
        view.updatePasswordList();
    }

    public void removePassword(int index) {
        model.removePassword(index);
        view.updatePasswordList();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PasswordManagerModel model = new PasswordManagerModel("passwords.txt");
            PasswordManagerView view = new PasswordManagerView(model);
            PasswordManagerController controller = new PasswordManagerController(model, view);
        });
    }
    */
}
