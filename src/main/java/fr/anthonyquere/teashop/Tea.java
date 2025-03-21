package fr.anthonyquere.teashop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Tea {
    private String name;
    private int steepingTimeSeconds;
    private int idealTemperatureCelsius;
    private boolean loose; // loose leaf vs tea bag

    // Changement du non puisque c'est un attribut private et que lombok va creer les methode isLoose() et setLoose() puisque c'est un type boolean
    // L'utilisation de Lombok qui permet de générer des Setter et des Getter est plus simple et garantie que le code soit operationnel et sans bug
    // L'utilisation du AllArgsConstructor permet de générer un constructeur avec tout les paramettre


    // ces changement evitent de devoir creer des test pour toutes ces methodes et ca allege visuelement le code ce qui le rend plus simple a comprendre et donc plus maintenable
}
