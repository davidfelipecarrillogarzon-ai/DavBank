import java.util.ArrayList;

public class User {

        private int id;
        private String userName;
        private String password;
        private String cardNumber;
        private String transactionPassword;
        private double balance;
        private ArrayList<TransactionHistory> transactionHistory;

        public User(int id, String userName, String password, String cardNumber, String transactionPassword, double balance){
                this.id = id;
                this.userName = userName;
                this.password = password;
                this.cardNumber = cardNumber;
                this.transactionPassword = transactionPassword;
                this.balance = balance;
                this.transactionHistory = new ArrayList<>();
        }

        //Getters: help extract data from a private object
        public int getId(){return id;}
        public String getUserName(){return userName;}
        public String getPassword(){return password;}
        public String getCardNumber(){return  cardNumber;}
        public String getTransactionPassword(){return transactionPassword;}
        public double getBalance(){return balance;}
        public ArrayList<TransactionHistory> getTransactionHistory(){
                return transactionHistory;
        }


        public void addBalance(double amount){
                if(amount >0){
                        this.balance += amount;
                }
        }
        public void subtractBalance(double amount){
                if(amount >0){
                        this.balance -= amount;
                }
        }
        public void addTransaction(TransactionHistory tx){
                this.transactionHistory.add(tx);
        }

}
