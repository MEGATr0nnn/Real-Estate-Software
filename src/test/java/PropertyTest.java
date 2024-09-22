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
        property = new Property("123 wallaby way", 2, 1, 2, 0, 200, 0, false);
        property2 = new Property("123 harrison lane", 4, 2, 2, 2, 400, 0, true);
        property3 = new Property("123 diddy way", 2, 1, 2, 2, 200, 100, true);
    }

    @Test
    public void test_Get_Address(){
        property.setAddress("diddy");
        assertEquals("diddy", property.getAddress());
    }

    @Test
    public void test_Set_Num_Beds(){
        property.setNum_Beds(3);
        assertEquals(3, property.getNum_Beds());
    }

    @Test
    public void test_Set_Num_Baths(){
        property.setNum_Baths(10);
        assertEquals(10, property.getNum_Baths());
    }

}
