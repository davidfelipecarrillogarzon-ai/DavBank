import javax.swing.*;

public class functionMenu {

    public int selectedOptionFuncitonMenu(){
        String[] buttonsFunctionMenu = {"View Account Statement", "Perform A Movement", "transaction History", "Security", "Close Session"};
        int selectedOptionFunctionMenu = JOptionPane.showOptionDialog(null, "HEllO What are you going to do now", "DavBank", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttonsFunctionMenu, buttonsFunctionMenu[0]);

        return selectedOptionFunctionMenu;
    }
    public void functionMenu(User loggedUser){
        while(true) {
            int selectedOption = selectedOptionFuncitonMenu();
            switch (selectedOption) {
                case -1, 4:
                    return;
                case 0:
                    viewAccountStatement(loggedUser);
                    break;
            }
        }
    }
    public void viewAccountStatement(User loggedUser){
        while(true) {
            String[] buttonsViewAccountStatements = {"View My Balance", "View My Card", "Back"};
            int selectedOptionStatementsMenu = JOptionPane.showOptionDialog(null, "Choose An Option", "DavBank", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttonsViewAccountStatements, buttonsViewAccountStatements[0]);
            if (selectedOptionStatementsMenu == 0) {
                JOptionPane.showMessageDialog(null, "Your current balance is: " + loggedUser.getBalance());
            } else if (selectedOptionStatementsMenu == 1) {
                JOptionPane.showMessageDialog(null, "Your card Number is " + loggedUser.getCardNumber());
            } else {
                return;
            }
        }
    }


}
