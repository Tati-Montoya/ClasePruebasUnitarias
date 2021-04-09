import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.Month;


import static org.junit.jupiter.api.Assertions.*;
public class BankStatementProcessorTest {

 private final BankStatementProcessor processor = new BankStatementProcessor(null);

 @Test
    public void shouldBeReturnTotalForMonth (){
     Month month = Month.FEBRUARY;

     double total = processor.calculateTotalForMonth(month);

     System.out.println(total);
 }
}
