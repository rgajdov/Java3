import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, UnsupportedEncodingException {
	// write your code here
        System.setOut(new java.io.PrintStream(System.out, true, "Cp866"));
        new DB();
    }
}
