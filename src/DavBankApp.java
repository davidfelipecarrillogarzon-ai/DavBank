import javax.swing.*;
import java.awt.*;

public class DavBankApp extends JFrame{
    public DavBankApp(){
        this.setTitle("DavBank");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setVisible(true);
    }
    private static UserManager userManager = new UserManager();
    public static FunctionMenu functionMenu= new FunctionMenu(userManager);
    public static void main(String[] args) {
        DavBankApp dav = new DavBankApp();
        while (true) {
            dav.entryMenu();
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
                    functionMenu.functionMenu(loggedUser);
                }
                break;
            case 1:
                //Data Capture
                userManager.handleRegistration();
                break;
        }
    }
}