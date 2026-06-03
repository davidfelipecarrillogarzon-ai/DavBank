public class TransactionSystem{

    public int withdraw(User loggedUser, double amount){
        if(amount > loggedUser.getBalance()){return 0;}
        else if (amount < 1){return 1;}
        else{
            loggedUser.addTransaction(new TransactionHistory("Withdraw", amount));
            loggedUser.subtractBalance(amount); return 2;
        }

    }

    public boolean deposit(User loggedUser, double amount){
        if(amount < 1){return false;
        }else{
            loggedUser.addBalance(amount);
            loggedUser.addTransaction(new TransactionHistory("Deposit", amount));
            return true;
        }
    }
}
