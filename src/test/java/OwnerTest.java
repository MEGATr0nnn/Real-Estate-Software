import com.example.real_estate_software.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The Owner Test Class is the test suite for checking all input parameters for the Owner's Details
 * The test suite contains all cases where the owner inputs incorrect values for differing parameters
 */
public class OwnerTest {
    private Owner owner;
    private Owner owner2;

    @BeforeEach
    public void setUp() {
        owner = new Owner("Johnny", "Walker", "johnnywalker@email.com", "1Password!", true);
        owner2 = new Owner("Jimmy", "Stalker", "jstalk.com", "abcd1234", false);
    }

    @Test
    public void testID() {
        owner.setId(3);
        assertEquals(3, owner.getId());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("Johnny", owner.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        owner2.setFirstName("Larry");
        assertEquals("Larry", owner2.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Walker", owner.getLastName());
    }

    @Test
    public void testSetLastName() {
        owner2.setLastName("Johns");
        assertEquals("Johns", owner2.getLastName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("johnnywalker@email.com", owner.getEmail());
    }

    @Test
    public void testSetEmail() {
        owner2.setEmail("ljohn@email.com");
        assertEquals("ljohn@email.com", owner2.getEmail());
    }

    @Test
    public void testGetPassword() {
        assertEquals("1Password!", owner.getPassword());
    }

    @Test
    public void testSetPassword() {
        owner2.setPassword("12345678");
        assertEquals("12345678", owner2.getPassword());
    }

    @Test
    public void testGetConnection() {
        assertEquals(true, owner.getSignedIn());
    }

    @Test
    public void testSetConnection() {
        owner2.setSignedIn(true);
        assertEquals(true, owner2.getSignedIn());
    }

    @Test
    public void testValidEmailAddress(){
        assertTrue(owner.checkValidEmail());
    }

    @Test
    public void testInvalidEmailAddress(){
        assertFalse(owner2.checkValidEmail());
    }

    /**
     * Test to determine if password inputted has a capitalised character
     */
    @Test
    public void testPasswordCapital(){
        assertTrue(owner.checkCapitalPassword());
    }

    /**
     * Test to determine if password inputted has at least one number
     */
    @Test
    public void testPasswordNumber(){
        assertTrue(owner.checkNumberPassowrd());
    }

    /**
     * Test to determine if password inputted has a length of at least 8 characters
     */
    @Test
    public void testPasswordLength(){
        assertTrue(owner.checkLengthPassword());
    }

    /**
     * Test to determine if password inputted has a special character
     */
    @Test
    public void testPasswordUnique(){
        assertTrue(owner.checkUniqueCharacterPassword());
    }
}
