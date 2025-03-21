package fr.anthonyquere.teashop;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor // creer un constructeur sans parametre avec toute les valeurs initialiser par default ou avec 0/false/null selon le type d'objet
@Getter
@Setter
public class TeaCup {
    private int currentTemperatureCelsius;
    private Tea tea;
    private boolean isEmpty;
    private int steepingStartTime; // in seconds

    public void addWater(int temperatureCelsius) {
        this.currentTemperatureCelsius = temperatureCelsius;
        this.isEmpty = false;
    }

    public void addTea(Tea tea) {
        if (isEmpty) {
            throw new IllegalStateException("Cannot add tea to an empty cup!");
        }
        this.tea = tea;
        this.steepingStartTime = getCurrentTimeInSeconds();
    }

    public boolean isReadyToDrink() {
        if (tea == null || isEmpty) return false;

        int steepingTime = getCurrentTimeInSeconds() - steepingStartTime;
        return steepingTime >= tea.getSteepingTimeSeconds() &&
                isTemperatureIdeal();
    }

    private boolean isTemperatureIdeal() {
        return Math.abs(currentTemperatureCelsius - tea.getIdealTemperatureCelsius()) <= 5;
        /*
        * je n ecomprend pas pourquoi quand je lance mes test avec coverage je vois que j'ai qu'une seule branche sur
        * 2 de tester mais dan sje vais voir en debug je vois que je test bien le true et le false
        */
    }

    private int getCurrentTimeInSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
