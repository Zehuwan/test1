package Creatures;

import Structures.Position;
import Terrain.Map;

import java.awt.*;

/**
 * Klasa wirusa, którym zakażona zostanie osoba
 */
public class Virus extends Creature {
    /**
     * Maksymalny wskaźnik zarażalności
     */
    public int maxInfectiousness;
    /**
     * Maksymalna prędkość zarażania
     */
    public int maxSpeed;
    /**
     * Mnożnik na podstawie parametrów dobranych na początku
     */
    double multiplier;

    /**
     * Konstruktor wirusa
     * @param parentMap - mapa, na której wirusy się znajdują
     * @param position - pozycja wirusa
     * @param health - zdrowie
     * @param attackStrength - siła ataku, z jaką wirus atakuje
     * @param speed - prędkość poruszania się wirusa
     */
    Virus(Map parentMap, Position position, int health, int attackStrength, int speed) {
        super(parentMap, position, health, attackStrength, speed);
    }

    /**
     * Metoda wywołanie wirusa
     */
    public Virus() {
        super();

    }

    /**
     * Odrodzenie wirusa
     * @return - odrodzony wirus
     */
    @Override
    public Creature resurrect() {
        return null;
    }

}
