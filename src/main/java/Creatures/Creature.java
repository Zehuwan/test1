package Creatures;

import Structures.Position;
import Terrain.BiomeCreator;
import Terrain.Map;

import java.awt.*;
import java.util.Random;

/**
 * Klasa abstrakcyjna opisująca stworzenia
 */

public abstract class Creature
{
    /**
     * Zdrowie danego stworzenia
     */
    protected int health;
    /**
     * Siła ataku, z jaką stworzenie atakuje
     */
    protected int attackStrength;
    /**
     * Szybkość poruszania się
     */
    protected final int speed;
    /**
     * Mapa, na której stworzenia żyją
     */
    protected Map parentMap;
    /**
     * Generator liczb pseudolosowych, użyty do ruchu stworzenia
     */
    protected Random random;
    /**
     * Pozycja stworzenia
     */
    public static Position position;
    /**
     * Zakaźność tego stworzenia
     */
    public int infectiousness;
    /**
     * Odporność stworzenia na leki
     */
    public int drugResistance;
    /**
     * Odporność stworzenia na różnych biomach
     */
    public int biomeResistance;
    /**
     * Szybkość zakażania
     */
    public int infectionSpeed;
    /**
     * Ilość danego stworzenia
     */
    public int virusCount;

    /**
     * Konstruktor tworzący stworzenie
     * @param speed - szybkość poruszania
     */
    public Creature(int speed) {

        this.speed = speed;

    }

    /**
     * Konstruktor stworzenia
     */
    public Creature() {

        speed = 1;
    }

    /**
     * Metoda otrzymująca pozycję stworzenia
     * @return - pozycja stworzenia
     */
    public static Position getPosition() {
        return null;
    }
    /**
     * Metoda do odrodzenia stworzenia
     * @return - odrodzone stworzenie
     */
    public abstract Creature resurrect();

    /**
     * Metoda otrzymująca kolor ikony danego stworzenia
     * @return - kolor ikony stworzenia
     */
    public static Color getIconColor() {
        return null;
    }

    /**
     * Metoda otrzymująca identyfikator danego stworzenia
     * @return - identyfikator stworzenia
     */
    public static int getTeamID() {
        return -1;
    }

    /**
     * Wywołanie konstruktora stworzenia
     * @param parentMap - mapa, na której stworzenia żyją
     * @param position - pozycja stworzenia
     * @param health - zdrowie danego stworzenia
     * @param attackStrength - siła ataku, z jaką stworzenie atakuje
     * @param speed - szybkość poruszania się stworzenia
     */
    Creature(Map parentMap, Position position, int health, int attackStrength,
             int speed) {
        this.parentMap = parentMap;
        Creature.position = position;

        this.health = health;
        this.attackStrength = attackStrength;
        this.speed = speed;
        random = new Random();
    }

    /**
     * Metoda generująca nowe dowolne położenie stworzenia
     *
     * @return - nowa pozycja
     */
    protected Position getNewRandomPosition() {
        int currentSpeed = speed;
        switch (parentMap.getBiomeAt(position)) {
            case MOUNTAINS -> currentSpeed /= 2;
            case OCEAN -> currentSpeed /= 4;
        }
        return new Position(Math.min(Math.max(0, position.x +
                        random.nextInt(currentSpeed + 1) *
                                (random.nextBoolean() ? 1 : -1)),
                parentMap.getMapSize() - 1), Math.min(Math.max(0, position.y +
                        random.nextInt(currentSpeed + 1) *
                                (random.nextBoolean() ? 1 : -1)),
                parentMap.getMapSize() - 1));
    }

}
