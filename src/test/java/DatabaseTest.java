import com.example.real_estate_software.model.*;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    @Test
    public void testConnection() {
        Connection conn = DatabaseConnection.getInstance();
        assertEquals(true, conn != null);
    }
}
