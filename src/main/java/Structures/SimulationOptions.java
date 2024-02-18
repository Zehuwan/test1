package Structures;

import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za opcje konieczne do przeprowadzenia symulacji
 */
public class SimulationOptions
{
    /**
     * Lista przechowująca nazwy chorób, wprowadzone przez użytkownika
     */
    public ArrayList<String> virus_Names = new ArrayList<String>();
    /**
     * Lista przechowująca parametry chorób, wprowadzone przez użytkownika
     */
    public ArrayList<Double> virus_Params = new ArrayList<Double>();
    /**
     * Zmienna przechowująca wielkość generowanej mapy
     */
    public int mapSize=700;
    /**
     * Zmienna przechowująca ilość chorób
     */
    public int nTeams=4;
    /**
     * Zmienna przechowująca początkową ilość zakażonych daną chorobą
     */
    public int teamPopulation=5;
    /**
     * Zmienna przechowująca szybkość przeprowadzenia symulacji
     */
    public static int simulationSpeed=1;
    /**
     * Zmienna przechowująca skalę szumu (konieczna do utworzenia gradientu)
     */
    public double noiseScale=1.0;
    /**
     * Zmienna przechowująca oktawy szumu (konieczna do utworzenia gradientu)
     */

    public int noiseOctaves=10;
}
