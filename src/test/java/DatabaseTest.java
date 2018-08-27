import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class DatabaseTest {

    private Database db;

    @Before
    public void beforeTest() throws SQLException {
        db = new Database();
    }

//    @After
//    public void afterTest() {
//        db.closeConnection();
//    }

    @Test
    public void insertTest() {
        Assert.assertTrue(db.insert("Куракова", 5));
    }

    @Test
    public void updateTest() {
        Assert.assertTrue(db.update(4, "Федотова", 4));
    }

    @Test
    public void deleteTest() {
        Assert.assertTrue(db.delete(5));
    }
}
