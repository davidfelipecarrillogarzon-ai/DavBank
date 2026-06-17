import javax.swing.*;
import java.util.ArrayList;

public class SendMoney {
    UserManager userToSend;

    public SendMoney(UserManager userManager){
        this.userToSend = userManager;
    }

    String cardNumberToSend;

    public void sendMoney(String cardNumber, User loggedUser) {
        if(cardNumber == null){return;}
        for (User u : userToSend.getUserArrayList()) {
            if (cardNumber.equals(u.getCardNumber())) {
                cardNumberToSend = u.getCardNumber();
                boolean pVerification = pinVerification(loggedUser);
                double amount = 0;
                if(pVerification){
                    while(true){
                        amount = amountVerification(loggedUser, u);
                        if(amount == 0){JOptionPane.showMessageDialog(null, "You can´t send less than one dollar"); return;}
                        if(amount == -1){return;}
                        if(amount == -2){
                            JOptionPane.showMessageDialog(null, "You don´t have all of that money");
                            JOptionPane.showMessageDialog(null, "Your current balance " + loggedUser.getBalance() + "$");
                            continue;
                        }
                        break;
                    }
                    u.addBalance(amount);
                    TransactionHistory THistory = new TransactionHistory("Received of " + loggedUser.getUserName() + " | " , amount);
                    u.addTransaction(THistory);

                    loggedUser.subtractBalance(amount);
                    THistory = new TransactionHistory("Sent to " + u.getUserName() + " | ", amount);
                    loggedUser.addTransaction(THistory);
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "That card number is not registered in DavBank");
    }
    public boolean pinVerification(User loggedUser) {
        String verificationPin = "";
        while (true) {
            verificationPin = JOptionPane.showInputDialog("Write your transaction pin");
            if (verificationPin == null) return false;
            if (verificationPin.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Don´t let the field empty");
                continue;
            }
            break;
        }
        if(verificationPin.equals(loggedUser.getTransactionPassword())) return true;
        JOptionPane.showMessageDialog(null, "Your transaction pin is incorrect, try again");
        return false;
    }

    public double amountVerification(User loggedUser, User u){
        String amountstr = "";
        double amount = 0;
        while (true) {
            amountstr = JOptionPane.showInputDialog("Write the amount to send to " + u.getUserName());
            if (amountstr == null) return -1;
            if (amountstr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Don´t let the field empty");
                continue;
            }
            try{
                amount = Double.parseDouble(amountstr);
            }catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "Just use numbers");
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Unknown Error Try Again");
                System.out.println("ERROR: " + e);
            }

            if(amount > loggedUser.getBalance()){
                return -2;
            }
            if(amount < 1){return 0;}
            break;
        }
        return amount;
    }
    }