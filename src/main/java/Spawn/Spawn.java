package Spawn;

import Simulation.Spawner;

import java.util.EnumMap;
import java.util.HashMap;

/**
 * Klasa odpowiedzialna za wygenerowanie postaci
 */
public class Spawn
{
    /**
     * Punkty, na których postać ma się wygenerować
     */
    protected final Spawner parentSpawnPoints;

    /**
     * Konstruktor generujący postać
     * @param parentSpawnPoints - koordynaty generowania postaci
     */
    Spawn(Spawner parentSpawnPoints) {
        this.parentSpawnPoints = parentSpawnPoints;
    }
}
