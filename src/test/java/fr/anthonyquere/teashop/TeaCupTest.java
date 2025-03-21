package fr.anthonyquere.teashop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeaCupTest {

    private TeaCup teaCup;
    private Tea tea;

    @BeforeEach
    void setUp() {
        tea = new Tea("Lovely chaï - Dammannn", 5, 80, true);
        teaCup = new TeaCup(25, null, true, 0);
    }

    @Test
    @DisplayName("should add water and update temperature and isEmpty status")
    void should_add_water() {
        teaCup.addWater(90);
        assertEquals(90, teaCup.getCurrentTemperatureCelsius());
        assertFalse(teaCup.isEmpty());
    }

    @Test
    @DisplayName("should throw exception when adding tea to an empty cup")
    void should_throw_exception_when_adding_tea_to_empty_cup() {
        assertThrows(IllegalStateException.class, () -> teaCup.addTea(tea));
    }

    @Test
    @DisplayName("should add tea when cup is not empty")
    void should_add_tea_when_cup_is_not_empty() {
        teaCup.addWater(90);
        teaCup.addTea(tea);

        assertEquals(tea, teaCup.getTea());
        assertNotEquals(0, teaCup.getSteepingStartTime());
    }

    @Test
    @DisplayName("should return false if cup is empty")
    void should_return_false_if_cup_is_empty() {
        assertFalse(teaCup.isReadyToDrink());
    }

    @Test
    @DisplayName("should return false if tea is null")
    void should_return_false_if_tea_is_null() {
        teaCup.addWater(90);
        assertFalse(teaCup.isReadyToDrink());
    }

    @Test
    @DisplayName("should return true when tea is steeped and temperature is ideal")
    void should_return_true_when_tea_is_steeped_and_temperature_is_ideal() throws InterruptedException {
        teaCup.addWater(80);
        teaCup.addTea(tea);
        Thread.sleep(5000); // Simule le temps d'infusion

        assertTrue(teaCup.isReadyToDrink());
    }

    @Test
    @DisplayName("should return false when temperature is not ideal")
    void should_return_false_when_temperature_is_not_ideal() {
        teaCup.addWater(100);
        teaCup.addTea(tea);

        assertFalse(teaCup.isReadyToDrink());
    }

    @Test
    @DisplayName("should return IllegalStateException when adding tea to an empty cup")
    void should_return_illegal_state_exception_when_adding_tea_to_empty_cup() {
        TeaCup emptyTeaCup = new TeaCup(25, null, true, 0);
        Tea tea = new Tea("Earl Grey", 180, 90, true);

        assertThrows(IllegalStateException.class, () -> emptyTeaCup.addTea(tea));
    }

    @Test
    @DisplayName("should return false when tea is null")
    void should_return_false_when_tea_is_null() {
        TeaCup teaCup = new TeaCup(90, null, false, 0);
        assertFalse(teaCup.isReadyToDrink());
    }

    @Test
    @DisplayName("should return false when isEmpty is true")
    void should_return_false_when_is_empty_is_true() {
        Tea tea = new Tea("Earl Grey", 180, 90, true);
        TeaCup teaCup = new TeaCup(90, tea, true, 0);
        assertFalse(teaCup.isReadyToDrink());
    }

    @Test
    @DisplayName("should return true when temperature is within 5 degrees of ideal")
    void should_return_true_when_temperature_is_ideal() {
        Tea tea = new Tea("Earl Grey", 180, 90, true);
        TeaCup teaCup = new TeaCup(92, tea, false, 0); // 92°C est à ±5 de 90°C

        assertTrue(teaCup.isReadyToDrink());
    }
}
