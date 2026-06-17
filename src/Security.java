import javax.swing.*;

public class Security {
    public void changePassword(User loggedUser){
        String[] confirmationButtons = {"Yes", "Back"};
        int selectedOption = JOptionPane.showOptionDialog(null, "¿Do you really want to change your password?", "DavBank-Security", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmationButtons, confirmationButtons[0]);
        switch (selectedOption){
            case -1, 1:
                return;
            case 0:
                int attempts = 3;
                String password = JOptionPane.showInputDialog("Write your password to change it");
                while(!password.equals(loggedUser.getPassword())){
                attempts--;
                if (attempts == 0) return;
                password = JOptionPane.showInputDialog("Incorrect password you have now " + attempts + " attempts");
                if(password==null){return;}
            }
                while (true) {
                    String newPassword = JOptionPane.showInputDialog("Write your new password (more than 8 characters)");//Password for the app
                    if (newPassword == null) {
                        JOptionPane.showMessageDialog(null, "You canceled the change");
                        return;
                    }

                    if (newPassword.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Don´t let the field empty");
                        continue;
                    }
                    if(newPassword.equals(loggedUser.getPassword())){
                        JOptionPane.showMessageDialog(null, "Don't change your password to the same one you already have");
                    }

                    if (newPassword.length() < 8 || newPassword.length() > 20) {
                        JOptionPane.showMessageDialog(null, "You password can´t have less than 8 characters and more than 20");
                        continue;
                    }
                    loggedUser.setPassword(newPassword);
                    break;
                }
        }

    }
}
