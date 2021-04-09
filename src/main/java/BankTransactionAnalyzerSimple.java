import java.io.IOException;

public class BankTransactionAnalyzerSimple {

    public static void main(String[] args) throws IOException {
        BankStatementAnalyzer analyzer = new BankStatementAnalyzer();

        BankStatementParser bankStatementCSVParser = new BankStatementCSVParser();
        String fileName = "data.csv";

        analyzer.analyze(fileName, bankStatementCSVParser);
    }



}
