package fr.anthonyquere.teashop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor // creer des contructeurs avec 1 seul parametre pour tout les attrtibut de la class, les autres attribut sont instancié avec les valeurs par defaut ou 0/false/null en fonction du type d'objet
public class TeaShop {
    private final Map<String, Tea> availableTeas = new HashMap<>();
    private int waterTemperature;

    public void addTea(Tea tea) {
        availableTeas.put(tea.getName().toLowerCase(), tea);
    }

    public TeaCup prepareTea(String teaName) {
        Tea tea = availableTeas.get(teaName.toLowerCase());
        if (tea == null) {
            throw new IllegalArgumentException("Tea not available: " + teaName);
        }

        TeaCup cup = new TeaCup();
        cup.addWater(waterTemperature);
        cup.addTea(tea);
        return cup;
    }

    public void setWaterTemperature(int celsius) {
        if (celsius < 0 || celsius > 100) {
            throw new IllegalArgumentException("Water temperature must be between 0 and 100°C");
        }
        this.waterTemperature = celsius;
    }
}
