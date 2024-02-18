package Simulation;

import Creatures.Virus;
import Structures.Position;
import Terrain.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa obsługująca miejsce narodzin stworzenia
 */
public class Spawner extends Creatures.Virus
{
    /**
     * Lista wirusów
     */
    public ArrayList<Virus> Virus = new ArrayList<Virus>();
    /**
     * Pozycja (x,y), na której pojawiać się będą utworzone zakażone osoby
     */
    public Position position;
    /**
     * Identyfikator wirusa, którym ktoś jest zakażony
     */
    private int virusID;
    /**
     * Mapa, na której odbywa się symulacja
     */
    private final Map parentMap;

    /**
     * Konstruktor tworzący miejsce narodzin osoby zakażonej
     * @param position - pozycja osoby zakażonej
     * @param virusID - identyfikator, aby dowiedzieć się, którym wirusem jest zakażony
     * @param parentMap - mapa, na której odbywa się symulacja
     */
    public Spawner(Position position, int virusID, Map parentMap) {
        super();

        this.position = position;
        this.virusID = virusID;
        this.parentMap = parentMap;
        ArrayList<Object> SpawnPoints = new ArrayList<>();
        SpawnPoints = new ArrayList<>();

    }

    /**
     * Metoda przekazująca dane o miejscu narodzin
     * @return - zwraca dane o miejscu narodzin
     */
    public static List<Virus> getSpawner() {
        return getSpawner();
    }

    /**
     * Metoda dodająca
     */
    public void addSpawner() {
    }

    /**
     * Metoda przekazująca dalej kolor osoby zakażonej
     * @return - kolor osoby zakażonej
     */
    public Color getTeamColor() {
        return new Color((virusID & 4) > 0 ? 255 : 0, (virusID & 2) > 0 ? 255 : 0,
                (virusID & 1) > 0 ? 255 : 0);
    }
}
