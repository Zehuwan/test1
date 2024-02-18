package Structures;

/**
 * Klasa odpowiedzialna za ustawienie pozycji
 */
public class Position {
    /**
     * Koordynata szerokości
     */
    public int x;
    /**
     * Koordynata długości
     */
    public int y;

    /**
     * Konstruktor pozycji
     * @param x - szerokość na płaszczyznie
     * @param y - długość na płaszczyznie
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Metoda obliczająca dystans pomiędzy obiektami
     * @param p1 - obiekt pierwszy
     * @param p2 - obiekt drugi
     * @return - różnica pomiędzy nimi
     */
    public static int squaredDistanceBetween(Position p1, Position p2) {
        int deltaX = p1.x - p2.x;
        int deltaY = p1.y - p2.y;
        return deltaX * deltaX + deltaY * deltaY;
    }
}