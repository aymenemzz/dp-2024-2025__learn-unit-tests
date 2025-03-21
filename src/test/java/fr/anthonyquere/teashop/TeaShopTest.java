package fr.anthonyquere.teashop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeaShopTest {

    @InjectMocks
    private TeaShop teaShop;

    @Mock
    private Tea tea;

    @Mock
    private TeaCup teaCup;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(tea.getName()).thenReturn("Earl Grey");
        when(tea.getSteepingTimeSeconds()).thenReturn(180);
        when(tea.getIdealTemperatureCelsius()).thenReturn(90);
    }

    @Test
    @DisplayName("should add tea to available teas")
    void should_add_tea_to_available_teas() {
        teaShop.addTea(tea);
        assertEquals(tea, teaShop.getAvailableTeas().get("earl grey"));
    }

    @Test
    @DisplayName("should prepare tea when it is available")
    void should_prepare_tea_when_available() {
        teaShop.addTea(tea);
        teaShop.setWaterTemperature(90);

        TeaShop spyTeaShop = spy(teaShop);
        doReturn(teaCup).when(spyTeaShop).prepareTea("Earl Grey");

        TeaCup preparedCup = spyTeaShop.prepareTea("Earl Grey");

        assertNotNull(preparedCup);
        verify(spyTeaShop).prepareTea("Earl Grey");
    }

    @Test
    @DisplayName("should throw exception when preparing unavailable tea")
    void should_throw_exception_when_preparing_unavailable_tea() {
        assertThrows(IllegalArgumentException.class, () -> teaShop.prepareTea("Green Tea"));
    }

    @Test
    @DisplayName("should set valid water temperature")
    void should_set_valid_water_temperature() {
        teaShop.setWaterTemperature(85);
        assertEquals(85, teaShop.getWaterTemperature());
    }

    @Test
    @DisplayName("should throw exception when setting invalid water temperature")
    void should_throw_exception_when_setting_invalid_water_temperature() {
        assertThrows(IllegalArgumentException.class, () -> teaShop.setWaterTemperature(-5));
        assertThrows(IllegalArgumentException.class, () -> teaShop.setWaterTemperature(105));
    }

    @Test
    @DisplayName("should prepare tea when tea is not null")
    void should_prepare_tea_when_tea_is_not_null() {
        // Arrange
        teaShop.addTea(tea);
        teaShop.setWaterTemperature(90);

        // Act
        TeaCup preparedCup = teaShop.prepareTea("Earl Grey");

        // Assert
        assertNotNull(preparedCup, "The returned TeaCup should not be null");
        assertEquals("Earl Grey", preparedCup.getTea().getName(), "The TeaCup should contain the correct tea");
        assertEquals(90, preparedCup.getCurrentTemperatureCelsius(), "The water temperature should be set correctly");
    }
}
