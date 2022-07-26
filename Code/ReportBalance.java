package IIITLBank;

import java.text.NumberFormat;

public class ReportBalance {
    public ReportBalance() {
    }

    public static void printBalance(double balance, double availableFunds) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String balanceFormatted = currency.format(balance);
        String availableFundsFormatted = currency.format(availableFunds);
        Header.head();
        System.out.println("        path : transactions --> check account balance\n");
        System.out.println("______________________________________");
        System.out.println("YOUR AVAILABLE ACCOUNT BALANCE IS : " + balanceFormatted);
        System.out.println("______________________________________");
    }
}
