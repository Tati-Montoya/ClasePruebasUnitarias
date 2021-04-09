import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public void analyze(String filename, BankStatementParser parser) throws IOException {
        Path path = Paths.get(RESOURCES + filename);
        List<String> lines = Files.readAllLines(path);

        List<BankTransaction> transactions = parser.parseLines(lines);
        BankStatementProcessor processor = new BankStatementProcessor(transactions);

        //Clase anonima
      /*List<BankTransaction> expensiveTransaction = processor
              .findTransactions(new BankTransactionIsFebruaryAndExpensive() {
                  @Override
                  public boolean test(BankTransaction transaction) {
                      boolean isFeb = transaction.getDate().getMonth().equals(Month.FEBRUARY);
                      boolean isExpensive = transaction.getAmount() >= 1000;
                      return isFeb && isExpensive;
                  }

              }); */

        //Expresion lambda
        List<BankTransaction> expensiveTransaction = processor
                .findTransactions(transaction -> {
                    boolean isFeb = transaction.getDate().getMonth().equals(Month.FEBRUARY);
                    boolean isExpensive = transaction.getAmount() >= 1000;
                    return isFeb && isExpensive;
                });
        List<BankTransaction> chepTransaction = processor.findTransactions(transaction -> {
            return transaction.getAmount() < 500;
        });

        collectSummary(processor);
    }

    public static void collectSummary(BankStatementProcessor bankStatementProcessor) {
        System.out.println("Total = " + bankStatementProcessor.calculateTotal());
        System.out.println("JANUARY Total = " + bankStatementProcessor.calculateTotalForMonth(Month.JANUARY));
        System.out.println("FEBRUARY Total = " + bankStatementProcessor.calculateTotalForMonth(Month.FEBRUARY));
        System.out.println("Total Expenses = " + bankStatementProcessor.calculateExpensesPerMonthAndCategory(Month.FEBRUARY, "Rent"));

        System.out.println("Total salary received is " + bankStatementProcessor.calculateForCategory("Salary"));

    }
}
