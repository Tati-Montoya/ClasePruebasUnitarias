
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotal() {
        return  summarizeTransaction((accumulator, transaction) -> accumulator + transaction.getAmount());
    }

    public double calculateTotalForMonth(Month month) {
      return summarizeTransaction((accumulator, transaction) -> {
            if(transaction.getDate().getMonth() == month){
                return  accumulator + transaction.getAmount();
            } else{
                return accumulator;
            }
        });
    }

    public double calculateForCategory(String category) {
        return  summarizeTransaction((accumulator, transaction) -> {
            boolean isSomeDescription = transaction.getDescription().equals(category);
            double transactionAmount = transaction.getAmount();
            return isSomeDescription ? transactionAmount + accumulator : accumulator;
        });
    }

    public double summarizeTransaction(BankTransactionSummarizer summarizer){
        double total = 0d;
        for (BankTransaction transaction : bankTransactions) {
            total = summarizer.summarize(total, transaction);
        }
        return  total;
    }

    public double calculateExpensesPerMonthAndCategory(Month month, String category) {
        double gastos = 0d;
        for (BankTransaction transaction : bankTransactions) {
            if (transaction.getDate().getMonth().equals(month) && transaction.getDescription().equals(category)) {
                if(transaction.getAmount() < 0) {
                    gastos += transaction.getAmount();
                }
            }
        }
        return gastos;
    }

    public List<BankTransaction> findTransactions(BankTransactionFilter filter ){
        List<BankTransaction> result = new ArrayList<>();
        for (BankTransaction transaction: bankTransactions) {
            boolean passEvaluation = filter.test(transaction);
            if(passEvaluation){
                 result.add(transaction);
            }
        }
        return result;
    }



}
