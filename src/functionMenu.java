import javax.swing.*;

public class functionMenu {

    TransactionSystem TSystem = new TransactionSystem();

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
                    break;
                case 2:
                    viewTransactionHistory(loggedUser);
                    break;
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

    public void depositMenu(User loggedUser) {
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
                boolean transaction = TSystem.deposit(loggedUser, moneyDeposit);
                if (!transaction) {
                    JOptionPane.showMessageDialog(null, "You cannot deposit less than one dollar");
                    continue;
                }
                JOptionPane.showMessageDialog(null, "Successfully deposited: $" + moneyDeposit);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter only numbers");
            }
        }
    }

    public void withdrawMenu(User loggedUser){
        double moneyWithdraw = 0;
        while(true){
            String moneyWithdrawstr = JOptionPane.showInputDialog("Enter the value of the withdraw");
            if (moneyWithdrawstr == null) return;
            if (moneyWithdrawstr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Don´t let the field empty");
                continue;
            }
            try {
                moneyWithdraw = Double.parseDouble(moneyWithdrawstr);

                int transaction = TSystem.withdraw(loggedUser, moneyWithdraw);
                if (transaction == 1) {
                    JOptionPane.showMessageDialog(null, "You cannot withdraw less than one dollar");
                    continue;
                }
                if(transaction == 0){
                    JOptionPane.showMessageDialog(null, "Insufficient funds! Your current balance is: $" + loggedUser.getBalance());
                    continue;
                }
                if(transaction == 2){
                    JOptionPane.showMessageDialog(null, "Successfully withdrawn: $" + moneyWithdraw);
                    break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter only numbers");
            }
        }
    }

    public void performMovement(User loggedUser) {
        String[] optionsMovements = {"Deposit", "Withdraw", "Back"};
        int selectedOptionMovements = JOptionPane.showOptionDialog(null, "What is the next move", "DavBank", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsMovements, optionsMovements[0]);
        if(selectedOptionMovements==0){
            depositMenu(loggedUser);
        } else if (selectedOptionMovements == 1) {
            withdrawMenu(loggedUser);
        }else{return;}
    }

    public void viewTransactionHistory(User loggedUser){
        java.util.ArrayList<TransactionHistory> history = loggedUser.getTransactionHistory();

        if(history.isEmpty()){
            JOptionPane.showMessageDialog(null, "No transaction found yet.", "DavBank - History", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder report = new StringBuilder("---Transaction History ---\n\n");

        for(TransactionHistory tx : history){
            report.append(tx.getDetails()).append("\n");
        }
        JOptionPane.showMessageDialog(null, report.toString(), "DavBank - History", JOptionPane.INFORMATION_MESSAGE);
    }
}