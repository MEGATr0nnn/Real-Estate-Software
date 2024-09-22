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
        property = new Property("123 wallaby way", 2, 1, 2, false, 0, 200, 0);
        property2 = new Property("123 harrison lane", 4, 2, 2, true, 2, 400, 0);
        property3 = new Property("123 constitution way", 2, 1, 2, true, 2, 200, 100);
    }

    @Test
    public void test_Get_Address(){
        property.setAddress("constitution");
        assertEquals("constitution", property.getAddress());
    }

    @Test
    public void test_Set_Num_Beds(){
        property.setNum_Beds(3);
        assertEquals(3, property.getNum_Beds());
    }

    @Test
    public void test_Set_Num_Baths(){
        property.setNum_Bath(10);
        assertEquals(10, property.getNum_Bath());
    }

}
