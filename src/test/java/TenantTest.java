import com.example.real_estate_software.model.Tenant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TenantTest {
    private Tenant tenant;
    private Tenant tenant2;

    @BeforeEach
    public void setUp() {
        tenant = new Tenant("Jim-Bob", "Jones", "jimmybobj@email.com", "0422334455", 600, true);
        tenant2 = new Tenant("No", "One", "n1@email.com", "00000010", 0, false);
    }

    @Test
    public void testGetID() {
        tenant.setId(1);
        assertEquals(1, tenant.getId());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("Jim-Bob", tenant.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        tenant2.setFirstName("Will");
        assertEquals("Will", tenant2.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Jones", tenant.getLastName());
    }

    @Test
    public void testSetLastName() {
        tenant2.setLastName("Perry");
        assertEquals("Perry", tenant2.getLastName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("jimmybobj@email.com", tenant.getEmail());
    }

    @Test
    public void testSetEmail() {
        tenant2.setEmail("wperry@email.com");
        assertEquals("wperry@email.com", tenant2.getEmail());
    }

    @Test
    public void testValidEmailAddress(){
        assertTrue(tenant.checkValidEmail());
    }

    @Test
    public void testInvalidEmailAddress(){
        tenant2.setEmail("1111234556.com");
        assertFalse(tenant2.checkValidEmail());
    }

    @Test
    public void testGetPhoneNumber() {
        assertEquals("0422334455", tenant.getPhoneNumber());
    }

    @Test
    public void testSetPhoneNumber() {
        tenant2.setPhoneNumber("0412312312");
        assertEquals("0412312312", tenant2.getPhoneNumber());
    }

    @Test
    public void testGetRentOwed() {
        assertEquals(600, tenant.getRentOwed());
    }

    @Test
    public void testSetRentOwed() {
        tenant2.setRentOwed(5);
        assertEquals(5, tenant2.getRentOwed());
    }

    @Test
    public void testGetAssignedToProp() {
        assertEquals(true, tenant.getAssignedToProp());
    }

    @Test
    public void testSetAssignedToProp() {
        tenant2.setAssignedToProp(true);
        assertEquals(true, tenant2.getAssignedToProp());
    }
}
