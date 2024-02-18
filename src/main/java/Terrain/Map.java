package Terrain;

import Creatures.Creature;
import Creatures.Virus;
import Simulation.Spawner;
import Structures.HeightMap;
import Structures.Position;
import Structures.SimulationOptions;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa odpowiedzialna za generowanie mapy
 */
public class Map
{
    /**
     * Lista zawierająca punkty narodzin danego stworzenia
     */
    private final List<Spawner> SpawnPoints;
    /**
     * Wysokość mapy, konieczna do rozróżnienia biomów
     */
    private final HeightMap heightMap;
    /**
     * Lista postaci znajdujących się na mapie
     */
    private final List<Creature> creatures;
    /**
     * Zmienna typu logicznego, odpowiedzialna za informację o zakończeniu symulacji
     */
    private boolean simulationComplete;
    /**
     * Dane konieczne do przeprowadzenia symulacji
     */
    private SimulationOptions simulationOptions;
    /**
     * Generator liczb pseudolosowych
     */
    private final Random random;
    /**
     * Licznik stanów mapy
     */
    private int tickCounter = 0;

    /**
     * Metoda odpowiedzialna za przekazanie danych o ustawieniu danego biomu na mapie
     * @param position - pozycja biomu
     * @return - położenie finalne na mapie
     */
    public BiomeCreator.Biome getBiomeAt(Position position)
    {
        return BiomeCreator.getBiome(
                heightMap.height[position.x][position.y]);
    }

    /**
     * Metoda odpowiedzialna za wylosowanie położenia danego biomu na mapie
     * @param allowedBiomes - lista dozwolonych biomów, które istnieją
     * @return - pozycja biomu na mapie
     */
    public Position getRandomPosition(
            List<BiomeCreator.Biome> allowedBiomes) {
        Position position;
        while (true) {
            position = new Position(random.nextInt(simulationOptions.mapSize),
                    random.nextInt(simulationOptions.mapSize));
            BiomeCreator.Biome posBiome = getBiomeAt(position);
            for (BiomeCreator.Biome allowedBiome : allowedBiomes) {
                if (posBiome == allowedBiome)
                    return position;
            }
        }
    }

    /**
     * Konstruktor generujący mapę
     * @param simulationOptions - opcje symulacji, wprowadzone przez użytkownika w ekranie początkowym
     */
    public Map(SimulationOptions simulationOptions) {
        this.simulationOptions = simulationOptions;
        random = new Random();
        Gradient gradient = new Gradient();
        heightMap = new HeightMap();
        heightMap.height = gradient.generateGradientNoise2D();

        SpawnPoints = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Position newSpawnerPosition =
                    getRandomPosition(List.of(BiomeCreator.Biome.PLAINS));
            Spawner spawner = new Spawner(newSpawnerPosition, i, this);
            //Spawn.add(spawner);

            for (int j = 0; j < this.simulationOptions.teamPopulation; j++) {
                spawner.addSpawner();
            }
        }

        this.creatures = new ArrayList<>();

    }

    /**
     * Metoda przekazująca dane o wysokości danego obiektu na mapie
     * @return - wysokość obiektu na mapie
     */
    public HeightMap getHeightMap() {
        return heightMap;
    }

    /**
     * Metoda przekazująca listę stworzeń
     * @return - lista stworzeń
     */
    public List<Creature> getCreatureList() {
        return creatures;
    }

    /**
     * Metoda przekazująca listę punktów utworzenia stworzeń
     * @return - lista punktów
     */
    public List<Spawner> getSpawnerList() {
        return SpawnPoints;
    }

    /**
     * Metoda przekazująca dane o wielkości mapy
     * @return - wielkość mapy
     */
    public int getMapSize() {
        return simulationOptions.mapSize;
    }
}
