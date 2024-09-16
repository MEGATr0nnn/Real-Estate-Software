import com.example.real_estate_software.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OwnerTest {
    private Owner owner;

    @BeforeEach
    public void setUp() {
        owner = new Owner("Johnny", "Walker", "johnnywalker@email.com", "abcd1234");
    }

    @Test
    public void testGetID() {
        assertEquals(1, owner.getId());
    }

    @Test
    public void testSetID() {
        owner.setId(3);
        assertEquals(3, owner.getId());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("Johnny", owner.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        owner.setFirstName("Will");
        assertEquals("Will", owner.getFirstName());
    }
    @Test
    public void testGetLastName() {
        assertEquals("Walker", owner.getLastName());
    }

    @Test
    public void testSetLastName() {
        owner.setLastName("Johns");
        assertEquals("Johns", owner.getLastName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("johnnywalker@email.com", owner.getEmail());
    }

    @Test
    public void testSetEmail() {
        owner.setEmail("jwalker@email.com");
        assertEquals("jwalker@email.com", owner.getEmail());
    }

    @Test
    public void testGetPassword() {
        assertEquals("abcd1234", owner.getPassword());
    }

    @Test
    public void testSetPassword() {
        owner.setPassword("12345678");
        assertEquals("12345678", owner.getPassword());
    }

    @Test
    public void testGetConnection() {
        assertTrue(owner.getConnection());
    }

    @Test
    public void testSetConnection() {
        owner.setConnection(false);
        assertFalse(owner.getConnection());
    }
}
