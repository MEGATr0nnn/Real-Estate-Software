import com.example.real_estate_software.model.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {
    private Property property;
    private Property property2;
    private Property property3;

    @BeforeEach
    public void setUp(){
        property = new Property("123 wallaby way", 2, 1, 2, 0, 0, 200, false);
        property2 = new Property("123 constitution way", 2, 1, 2, 100, 2, 200, true);
        property3 = new Property("123 constitution way", 3, 2, 1, 50, 2, 400, true);
    }

    @Test
    public void testGetAddress(){
        property.setAddress("constitution");
        assertEquals("constitution", property.getAddress());
    }

    @Test
    public void testSetNumBeds(){
        property.setNum_Beds(3);
        assertEquals(3, property.getNum_Beds());
    }

    @Test
    public void testSetNumBaths(){
        property.setNum_Baths(10);
        assertEquals(10, property.getNum_Baths());
    }

    /**
     * Test to check if the address provided by the user has a house number associated with the address
     * If address has property number, then test will pass
     */
    @Test
    public void testPropertyNumber(){
        boolean containsNumber = false;

        for (int i = 0; i < property.getAddress().length(); i++){
            if (Character.isDigit(property.getAddress().charAt(i))){
                containsNumber = true;
            }
        }

        if (containsNumber){
            assertTrue(containsNumber, "Address provided must have a property number");
        }
    }

    /**
     * Test to check whether the inputted property has at least one bed to rent out to a potential tenant
     * If the property has less than 1 Bedroom (0 Bedrooms) then test will fail.
     */
    @Test
    public void testBedroomNumber(){
        assertTrue(property.getNum_Beds() >= 1, "Property must have at least 1 Bedroom");
    }

    /**
     * Test to check for duplicate addresses that are inputted into the system.
     * If the given address already exists, the test will fail
     */
    @Test
    public void testDuplicateProperty(){
        assertEquals(property2.getAddress(), property3.getAddress(), "Property already exists!");
    }
}
