import com.example.real_estate_software.model.Utilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilitiesTest {
    private Utilities utilities;
    private Utilities utilities2;

    @BeforeEach
    public void setUp() {
        utilities = new Utilities(100, 200, 300);
        utilities2 = new Utilities(0, 0, 0);
    }

    @Test
    public void testID() {
        utilities.setId(1);
        assertEquals(1, utilities.getId());
    }

    @Test
    public void testGetWaterUtilities() {
        assertEquals(100, utilities.getWaterUtilities());
    }

    @Test
    public void testSetWaterUtilities() {
        utilities2.setWaterUtilities(300);
        assertEquals(300, utilities2.getWaterUtilities());
    }

    @Test
    public void testGetElectricityUtilities() {
        assertEquals(200, utilities.getElectricityUtilities());
    }

    @Test
    public void testSetElectricityUtilities() {
        utilities2.setElectricityUtilities(600);
        assertEquals(600, utilities2.getElectricityUtilities());
    }

    @Test
    public void testGetGasUtilities() {
        assertEquals(300, utilities.getGasUtilities());
    }

    @Test
    public void testSetGasUtilities() {
        utilities2.setGasUtilities(100);
        assertEquals(100, utilities2.getGasUtilities());
    }

    @Test
    public void testGetTotalUtilities() {
        assertEquals(0, utilities2.getTotalUtilites());
    }

}
