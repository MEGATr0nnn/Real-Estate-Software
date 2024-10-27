import com.example.real_estate_software.model.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {
    private Property property;
    private Property property2;

    @BeforeEach
    public void setUp(){
        property = new Property("123 wallaby way", 2, 2, 2, 2, true);
        property2 = new Property("123 constitution way", 0, 0, 0, 0, false);
    }

    @Test
    public void testID() {
        property.setId(1);
        assertEquals(1, property.getId());
    }

    @Test
    public void testAddress(){
        property.setAddress("spongebob");
        assertEquals("spongebob", property.getAddress());
    }

    @Test
    public void testGetNumTenants() {
        assertEquals(2, property.getNum_Tenants());
    }

    @Test
    public void testSetNumTenants() {
        property2.setNum_Tenants(1);
        assertEquals(1, property2.getNum_Tenants());
    }

    @Test
    public void testNumBeds(){
        property2.setNum_Beds(3);
        assertEquals(3, property2.getNum_Beds());
    }

    @Test
    public void testNumBaths() {
        property2.setNum_Baths(2);
        assertEquals(2, property2.getNum_Baths());
    }

    @Test
    public void testNumCars() {
        property2.setNum_Cars(7);
        assertEquals(7, property2.getNum_Cars());
    }

    @Test
    public void testGetHasTenants() {
        assertEquals(true, property.getHas_Tenants());
    }

    @Test
    public void testSetHasTenants() {
        property2.setHas_Tenants(true);
        assertEquals(true, property2.getHas_Tenants());
    }

    @Test
    public void testGetIsSelected() {
        assertEquals(true, property.getIs_Selected());
    }

    @Test
    public void testSetIsSelected() {
        property2.setIs_Selected(true);
        assertEquals(true, property2.getIs_Selected());
    }

    @Test
    public void testHasTenants() {
        assertEquals(true, property.checkHas_Tenants());
    }

    @Test
    public void testHasNoTenants() {
        assertEquals(false, property2.checkHas_Tenants());
    }

}
