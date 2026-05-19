import javax.swing.*;

public class functionMenu {

    public int selectedOptionFuncitonMenu() {
        String[] buttonsFunctionMenu = {"View Account Statement", "Perform A Movement", "transaction History", "Security", "Close Session"};
        int selectedOptionFunctionMenu = JOptionPane.showOptionDialog(null, "HEllO What are you going to do now", "DavBank", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttonsFunctionMenu, buttonsFunctionMenu[0]);

        return selectedOptionFunctionMenu;
    }

    public void functionMenu(User loggedUser) {
        while (true) {
            int selectedOption = selectedOptionFuncitonMenu();
            switch (selectedOption) {
                case -1, 4:
                    return;
                case 0:
                    viewAccountStatement(loggedUser);
                    break;
                case 1:
                    performMovement(loggedUser);
            }
        }
    }

    public void viewAccountStatement(User loggedUser) {
        while (true) {
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

    public void deposit(User loggedUser) {
        double moneyDeposit = 0;
        while (true) {
            String moneyDepositstr = JOptionPane.showInputDialog("Enter the amount to deposit");
            if (moneyDepositstr == null) return;
            if (moneyDepositstr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Don´t let the field empty");
                continue;
            }
            try {
                moneyDeposit = Double.parseDouble(moneyDepositstr);
                if (moneyDeposit < 1) {
                    JOptionPane.showMessageDialog(null, "You cannot deposit less than one dollar");
                    continue;
                }
                loggedUser.addBalance(moneyDeposit);
                JOptionPane.showMessageDialog(null, "Successfully deposited: $" + moneyDeposit);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter only numbers");
            }
        }
    }

    public void withdraw(User loggedUser){
        double moneyWithdraw = 0;
        while(true){
            String moneyWithdrawstr = JOptionPane.showInputDialog("Enter the withdraw to deposit");
            if (moneyWithdrawstr == null) return;
            if (moneyWithdrawstr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Don´t let the field empty");
                continue;
            }
            try {
                moneyWithdraw = Double.parseDouble(moneyWithdrawstr);
                if (moneyWithdraw < 1) {
                    JOptionPane.showMessageDialog(null, "You cannot withdraw less than one dollar");
                    continue;
                }
                if(moneyWithdraw > loggedUser.getBalance()){
                    JOptionPane.showMessageDialog(null, "Insufficient funds! Your current balance is: $" + loggedUser.getBalance());
                    continue;
                }
                loggedUser.subtractBalance(moneyWithdraw);
                JOptionPane.showMessageDialog(null, "Successfully withdrawn: $" + moneyWithdraw);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter only numbers");
            }
        }
    }

    public void performMovement(User loggedUser) {
        String[] optionsMovements = {"Deposit", "Withdraw", "Back"};
        int selectedOptionMovements = JOptionPane.showOptionDialog(null, "What is the next move", "DavBank", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMovements, optionsMovements[0]);
        if(selectedOptionMovements==0){
            deposit(loggedUser);
        } else if (selectedOptionMovements == 1) {
            withdraw(loggedUser);
        }else{return;}
    }
}
