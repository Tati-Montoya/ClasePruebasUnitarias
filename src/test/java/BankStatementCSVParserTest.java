import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;


import static org.junit.jupiter.api.Assertions.*;

class BankStatementCSVParserTest {

    private final BankStatementParser parser = new BankStatementCSVParser();


    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        String line = "30-01-2017,-50,Tesco";

        BankTransaction result = parser.parseFromString(line);

        BankTransaction expected = new BankTransaction(
                LocalDate.of(2017, Month.JANUARY, 30),
                -50,
                "Tesco"
        );
        double tolerance = 0.0d;

        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        assertEquals(expected.getDescription(), result.getDescription());

    }

  @Test
    public void shouldParseOneIncorrectLine() throws Exception {
        String line = "30-01-2017,-50,Rent";
       boolean isEquals = true;
        BankTransaction expected = new BankTransaction(
                LocalDate.of(2017, Month.JANUARY, 30),
                -50,
                "Tesco"
        );

        BankTransaction result = parser.parseFromString(line);

        if(!result.equals(expected)){
            isEquals= false;
        }
        assertFalse(isEquals);
  }
}