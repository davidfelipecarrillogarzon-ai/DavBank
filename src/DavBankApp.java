import java.util.ArrayList;
import javax.swing.*;

public class DavBankApp {
    private static UserManager userManager = new UserManager();
    public static void main(String[] args) {
        while (true) {
            entryMenu();
        }
    }
    public static void entryMenu(){
        //It gives a user the option of Log in or register
        String[] LoginOrRegisterOption = {"Log in", "Register", "Exit"};
        int chosenOption = JOptionPane.showOptionDialog(null, "¿Do you want to register or log in?", "Welcome to DavBank", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, LoginOrRegisterOption, LoginOrRegisterOption[0]);

        switch (chosenOption) {
            case -1, 2:
                System.exit(1);
            case 0:
                User loggedUser = userManager.handleLogin();
                if(loggedUser != null){
                    System.out.println("Aqui va la logica cuando se haga el inicio de sesion");
                }
                break;
            case 1:
                //Data Capture
                userManager.handleRegistration();
                break;
        }
    }
}