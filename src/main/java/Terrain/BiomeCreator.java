package Terrain;

/**
 *  Klasa odpowiedzialna za wygenerowanie biomów
 */
public class BiomeCreator {
    /**
     * Wyliczenie trzech typów biomów, które będą generowane
     */
    public enum Biome {
        /**
         * Biom oceaniczny
         */
        OCEAN,
        /**
         * Biom lądu
         */
        PLAINS,
        /**
         * Biom gór
         */
        MOUNTAINS
    }

    /**
     * Metoda przekazująca dane o biomach
     * @param height - wysokość, na której znajduje się dany biom
     * @return - dane o biomach
     */
    public static Biome getBiome(double height)
    {
        if (height < 1.0 / 3)
            return Biome.OCEAN;
        else if (height < 2.0 / 3)
            return Biome.PLAINS;
        else
            return Biome.MOUNTAINS;
    }

    /**
     * Metoda odpowiedzialna za przekazanie koloru danego biomu
     * @param height - wysokość, na której biom się znajduje
     * @return - zmienna typu tablicowego liczbowego z danym kolorem biomu
     */
    public static int[] getBiomeColor(double height)
    {
        if (height < 1.0 / 3)
            return new int[]{(int) (height * 255), (int) (height * 255), 255};
        else if (height < 2.0 / 3)
            return new int[]{(int) ((height - 1.0 / 3) * 1.2 * 255),
                    (int) ((height / 2 + 2.0 / 3) * 255),
                    (int) ((height - 1.0 / 3) * 1.2 * 255)};
        else
            return new int[]{(int) ((height - 1.0 / 3) * 1.5 * 128),
                    (int) ((height - 1.0 / 3) * 1.5 * 128),
                    (int) ((height - 1.0 / 3) * 1.5 * 128)};
    }
}
