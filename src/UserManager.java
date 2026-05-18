import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    public User actualUser;
    private static ArrayList<User> userList = new ArrayList<>();
    public List<User> list(){return userList;}


    public static int idIterator = 1;
    public void handleRegistration(){
            int id = idIterator;
            String name = "";
            while (true) {
                name = JOptionPane.showInputDialog("Write your complete name");//Name for the card and app
                if (name == null) {
                    JOptionPane.showMessageDialog(null, "You canceled the register");
                    return;
                }

                if (name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Don´t let the field empty");
                    continue;
                }

                break;
            }
            String pass = "";
            while (true) {
                pass = JOptionPane.showInputDialog("Write your password (more than 8 characters)");//Password for the app
                if (pass == null) {
                    JOptionPane.showMessageDialog(null, "You canceled the register");
                }

                if (pass.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Don´t let the field empty");
                    continue;
                }

                if (pass.length() < 8 || pass.length() > 20) {
                    JOptionPane.showMessageDialog(null, "You password can´t have less than 20 characters and more than 20");
                    continue;
                }

                break;
            }

            long randomCard = (long) (Math.random() * 900000000000000L) + 1000000000000000L;
            String card = String.valueOf(randomCard);
            String passTrans = "";
            while (true) {
                passTrans = JOptionPane.showInputDialog("Write a pin of 4 characters to complete transactions");
                if (passTrans == null) {
                    JOptionPane.showMessageDialog(null, "You canceled the register");
                    return;
                }

                if (passTrans.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Don´t let the field empty");
                    continue;
                }

                if (passTrans.length() > 5 || passTrans.length() < 4) {
                    JOptionPane.showMessageDialog(null, "Only 4 Numbers");
                    continue;
                }

                break;
            }

            double balance = 0;

            //Create the object
            User newUser = new User(id, name, pass, card, passTrans, balance);

            //Save in the list
            userList.add(newUser);

            JOptionPane.showMessageDialog(null, "!User " + name + " has been registered successfully¡");

            idIterator++;
        }

        public User handleLogin(){
        String loginName = JOptionPane.showInputDialog("Enter your User Name");
        String loginPass = JOptionPane.showInputDialog("Enter your Password");
        if (loginName == null || loginPass == null)return null;

        //Search the user with a for and a condition
        for(User u : userList){
            if(u.getUserName().equals(loginName) && u.getPassword().equals(loginPass)){
                JOptionPane.showMessageDialog(null, "¡Welcome back " + u.getUserName() + "!");
                actualUser = u;
                return u;
            }
            }
        JOptionPane.showMessageDialog(null, "User or Password incorrect");
        return null;
        }
    }
