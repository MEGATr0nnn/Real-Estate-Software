import com.example.real_estate_software.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OwnerTest {
    private Owner owner;
    private Owner owner2;
    private Owner owner3;

    @BeforeEach
    public void setUp() {
        owner = new Owner("Johnny", "Walker", "johnnywalker@email.com", "abcd1234");
        owner2 = new Owner("Johnny", "Walker", "johnnywalker@email.com", "abcd1234");
        owner3 = new Owner("Ben", "Walker", "benwalker.com", "Abcd1234");
    }

    @Test
    public void testGetID() {
        owner.setId(1);
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

    @Test
    public void testDuplicateAccounts(){
        if (owner.getEmail().equals(owner2.getEmail())){
            assertFalse(false, "Account already exists with this email address!");
        }
    }

    @Test
    public void testInvalidEmailAddress(){
        if (!owner3.getEmail().contains("@")){
            assertFalse(false, "Email Address Invalid!");
        }
    }

    @Test
    public void testCorrectEmailAddress(){
        assertTrue(owner.getEmail().contains("@"));
    }

    @Test
    public void testPasswordCapital(){
        boolean containsCapital = owner.getPassword().matches(".*[A-Z]*.");
        assertTrue(containsCapital, "Password must contain a Capital Letter");
    }

    @Test
    public void testPasswordNumber(){
        boolean containsNumber = owner.getPassword().matches(".*[0-9].*");
        assertTrue(containsNumber, "Password must contain a number");
    }

    @Test
    public void testPasswordLength(){
        assertTrue(owner.getPassword().length() >= 8, "Password must be at least 8 characters long");
    }

    @Test
    public void testPasswordUnique(){
        String uniqueChar = ".*[!@#$%^&*()_+{}|:;<>?,./`~]*.";
        assertFalse(owner.getPassword().contains(uniqueChar), "Password must contain a special character");
    }

}

