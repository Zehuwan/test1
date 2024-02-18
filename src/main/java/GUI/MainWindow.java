package GUI;

import Structures.SimulationOptions;
import Creatures.Creature;
import Creatures.Person;
import Simulation.Spawner;
import Structures.HeightMap;
import Structures.Position;
import Terrain.BiomeCreator;
import Terrain.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa wyświetlająca główne okno z symukacją
 */
public class MainWindow extends JFrame implements ActionListener{
    /**
     * Lista zakażonych osób
     */
    private ArrayList<Person> people = new ArrayList<Person>();
    /**
     * Lista stworzeń
     */
    private List<Creature> creatures;
    /**
     * Zbuforowana mapa, wcześniej utworzona przez generator mapy
     */
    private BufferedImage mapImage;
    /**
     * Lista miejsc odrodzenia postaci
     */
    private List<Spawner> spawn;
    /**
     * Listy z nazwami i parametrami chorób
     */
    SimulationOptions simOptions;
    /**
     * Główne okno
     */
    JPanel panel = new JPanel();
    /**
     * Pierwszy tekst - "Epidemic"
     */
    CustomLabel title_1;
    /**
     * Drugi tekst - "Simulation"
     */
    CustomLabel title_2;
    /**
     * Kontener, w którym znajduje się wygenerowana mapa
     */
    JPanel panel2 = new JPanel();
    /**
     * Kontener, w którym znajduje się liczba chorób oraz legenda
     */
    JPanel panel3 = new JPanel();
    /**
     * Pytanie o nazwę pierwszej choroby
     */
    JLabel vir1;
    /**
     * Aktualizująca się liczba pierwszej choroby
     */
    JLabel vir1_amount;
    /**
     * Pytanie o nazwę drugiej choroby
     */
    JLabel vir2;
    /**
     * Aktualizująca się liczba drugiej choroby
     */
    JLabel vir2_amount;
    /**
     * Pytanie o nazwę trzeciej choroby
     */
    JLabel vir3;
    /**
     * Aktualizująca się liczba trzeciej choroby
     */
    JLabel vir3_amount;
    /**
     * Pytanie o nazwę czwartej choroby
     */
    JLabel vir4;
    /**
     * Aktualizująca się liczba czwartej choroby
     */
    JLabel vir4_amount;
    /**
     * Element legendy, pierwsza choroba
     */
    JLabel legend1;
    /**
     * Element legendy, druga choroba
     */
    JLabel legend2;
    /**
     * Element legendy, trzecia choroba
     */
    JLabel legend3;
    /**
     * Element legendy, czwarta choroba
     */
    JLabel legend4;

    /**
     * Konstruktor głównego okna z symulacją
     * @param simOptions - opcje symulacji, listy z nazwami oraz parametrami chorób
     * @param map - mapa na której odbywa się symulacja
     * @param gui - główne okno
     */
    public MainWindow(SimulationOptions simOptions, Map map, GUI gui) {
        this.simOptions = simOptions;

        // Ustawienia ramki
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1200, 800);
        setTitle("Epidemic Simulation");
        setResizable(false);

        // Ustawienia panelu 1
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        Color panel1_color = new Color(160, 0, 0);
        panel.setBackground(panel1_color);

        // Ustawienia panelu 2
        panel2.setBounds(25, 25, 700, 700);
        HeightMap heightMap = map.getHeightMap();
        this.creatures = map.getCreatureList();
        this.spawn = map.getSpawnerList();
        mapImage = new BufferedImage(map.getMapSize(), map.getMapSize(),
                BufferedImage.TYPE_3BYTE_BGR);
        WritableRaster mapImageRaster = mapImage.getRaster();
        for (int i = 0; i < map.getMapSize(); i++) {
            for (int j = 0; j < map.getMapSize(); j++) {
                mapImageRaster.setPixel(i, j,
                        BiomeCreator.getBiomeColor(heightMap.height[i][j]));
            }
        }
        MapPanel mapPanel = new MapPanel();
        mapPanel.setPreferredSize(
                new Dimension(map.getMapSize(), map.getMapSize()));

        //Color panel2_color = new Color(1, 255, 255);
        //panel2.setBackground(panel2_color);
        add(mapPanel);
        panel2.setLayout(null);
        panel.setLayout(null);
        setVisible(true);

        // Ustawienia panelu 3
        panel3.setBounds(795, 300, 330, 425);
        Color panel3_color = new Color(150, 0, 0);
        panel3.setBackground(panel3_color);
        panel3.setLayout(null);

        // Ustawienia tekstu
        title_1 = new CustomLabel("EPIDEMIC");
        title_1.setBounds(830, 150, 300, 70);
        //title_1.setFont(new Font("Mars Bold", Font.PLAIN, 25));
        Color title_color = new Color(220, 220, 220);
        title_1.setForeground(title_color);

        title_2 = new CustomLabel("SIMULATION");
        title_2.setBounds(795, 190, 400, 70);
        //title_2.setFont(new Font("Mars Bold", Font.PLAIN, 25));
        title_2.setForeground(title_color);

        vir1 = new JLabel("Liczebnosc choroby " + simOptions.virus_Names.get(0) + ":");
        vir1.setBounds(40, 0, 300, 70);
        vir1.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir1.setForeground(Color.WHITE);

        vir2 = new JLabel("Liczebnosc choroby " + simOptions.virus_Names.get(1) + ":");
        vir2.setBounds(40, 70, 300, 70);
        vir2.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir2.setForeground(Color.WHITE);

        vir3 = new JLabel("Liczebnosc choroby " + simOptions.virus_Names.get(2) + ":");
        vir3.setBounds(40, 140, 300, 70);
        vir3.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir3.setForeground(Color.WHITE);

        vir4 = new JLabel("Liczebnosc choroby " + simOptions.virus_Names.get(3) + ":");
        vir4.setBounds(40, 210, 300, 70);
        vir4.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir4.setForeground(Color.WHITE);

        vir1_amount = new JLabel(String.valueOf(Person.numInfected_0));
        vir1_amount.setBounds(40, 35, 300, 70);
        vir1_amount.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir1_amount.setForeground(Color.WHITE);

        vir2_amount = new JLabel(String.valueOf(Person.numInfected_1));
        vir2_amount.setBounds(40, 105, 300, 70);
        vir2_amount.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir2_amount.setForeground(Color.WHITE);

        vir3_amount = new JLabel(String.valueOf(Person.numInfected_2));
        vir3_amount.setBounds(40, 175, 300, 70);
        vir3_amount.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir3_amount.setForeground(Color.WHITE);

        vir4_amount = new JLabel(String.valueOf(Person.numInfected_3));
        vir4_amount.setBounds(40, 245, 300, 70);
        vir4_amount.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir4_amount.setForeground(Color.WHITE);

        legend1 = new JLabel("Czerwony - "+simOptions.virus_Names.get(0));
        legend1.setBounds(40, 300, 300, 50);
        legend1.setFont(new Font("Helvetica", Font.PLAIN, 15));
        legend1.setForeground(Color.WHITE);

        legend2 = new JLabel("Zolty - "+simOptions.virus_Names.get(1));
        legend2.setBounds(40, 325, 300, 50);
        legend2.setFont(new Font("Helvetica", Font.PLAIN, 15));
        legend2.setForeground(Color.WHITE);

        legend3 = new JLabel("Rozowy - "+simOptions.virus_Names.get(2));
        legend3.setBounds(40, 350, 300, 50);
        legend3.setFont(new Font("Helvetica", Font.PLAIN, 15));
        legend3.setForeground(Color.WHITE);

        legend4 = new JLabel("Czarny - "+simOptions.virus_Names.get(3));
        legend4.setBounds(40, 375, 300, 50);
        legend4.setFont(new Font("Helvetica", Font.PLAIN, 15));
        legend4.setForeground(Color.WHITE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.yesButtonText", "Tak");
                UIManager.put("OptionPane.noButtonText", "Nie");
                int result = JOptionPane.showConfirmDialog(
                        MainWindow.this,
                        "Czy na pewno chcesz wyjsc?",
                        "Wyjscie",
                        JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Laczenie elementow
        panel.add(panel2);
        panel.add(panel3);
        panel.add(title_1);
        panel.add(title_2);
        panel2.add(mapPanel);
        panel3.add(vir1);
        panel3.add(vir2);
        panel3.add(vir3);
        panel3.add(vir4);
        panel3.add(vir1_amount);
        panel3.add(vir2_amount);
        panel3.add(vir3_amount);
        panel3.add(vir4_amount);
        panel3.add(legend1);
        panel3.add(legend2);
        panel3.add(legend3);
        panel3.add(legend4);

        // Dodanie panelu do ramki
        add(panel);
        setVisible(true);

        for (int i=0;i<50;i+=1)
        {
            people.add(new Person());
        }

        Timer t = new Timer(16, this);
        t.restart();
        Timer t2 = new Timer(16, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateLabels();
            }
        });
        t2.restart();


    }

    /**
     * Metoda aktualizująca liczebność chorób po danym czasie z Timera t2
     */
    public void updateLabels()
    {
        vir1_amount.setText(String.valueOf(Person.numInfected_0));
        vir2_amount.setText(String.valueOf(Person.numInfected_1));
        vir3_amount.setText(String.valueOf(Person.numInfected_2));
        vir4_amount.setText(String.valueOf(Person.numInfected_3));
    }

    /**
     * Klasa do obsługi czcionki niezainstalowanej przez użytkownika
     */
    public class CustomLabel extends JLabel {
        /**
         * Niezainstalowana czcionka
         */
        private Font customFont;
        /**
         * Metoda wykonująca inne metody
         * @param text - tekst, któremu zostanie zmieniona czcionka
         */
        public CustomLabel(String text) {
            super(text);
            loadCustomFont();
            setCustomFont();
        }
        /**
         * Metoda wczytująca czcionkę z pliku .ttf, obsługująca wyjątek nieistnienia jej
         */
        private void loadCustomFont() {
            try {
                File fontFile = new File("Mars-Bold.ttf");
                customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
        }
        /**
         * Metoda ustawiająca wczytaną wcześniej czcionkę
         */
        private void setCustomFont() {
            if (customFont != null) {
                Font labelFont = customFont.deriveFont(Font.PLAIN,25);
                setFont(labelFont);
            }
        }

    }

    /**
     * Metoda kończąca symulacje, gdy któraś z chorób osiągnie liczebność równą 50
     */
    public void finishSimulation()
    {
        if(Person.numInfected_0==50 || Person.numInfected_1==50 || Person.numInfected_2==50 || Person.numInfected_3==50)
        {
            new MessageDialog("Symulacja zakonczona!");
            System.exit(0);
        }

    }

    /**
     * Klasa odpowiedzialna za narysowanie wygenerowanej mapy
     */
    public class MapPanel extends JPanel
    {
        /**
         * Metoda odpowiedzialna za narysowanie ikony wirusa
         * @param g - grafika
         * @param position - pozycja wirusa
         * @param color - kolor wirusa
         */
        private void drawVirusIcon(Graphics g, Position position, Color color)
        {
            g.setColor(color);
            g.fillRect(position.x - 3, position.y - 3, 7, 7);
            g.setColor(Color.RED);
            g.drawRect(position.x - 3, position.y - 3, 7, 7);
        }

        /**
         * Metoda odpowiedzialna za narysowanie spawnu wirusa
         * @param g - grafika
         * @param position - pozycja wirusa
         * @param color - kolor spawnu
         */
        private void drawSpawnerIcon(Graphics g, Position position,
                                     Color color) {
            g.setColor(color);
            g.fillPolygon(new int[]{position.x, position.x + 8, position.x,
                            position.x - 8},
                    new int[]{position.y + 8, position.y, position.y - 8,
                            position.y}, 4);
            g.setColor(Color.BLACK);
            g.drawPolygon(new int[]{position.x, position.x + 8, position.x,
                            position.x - 8},
                    new int[]{position.y + 8, position.y, position.y - 8,
                            position.y}, 4);
        }

        /**
         * Metoda rysująca mapę oraz postacie na tej mapie
         * @param g - grafika
         */
        @Override
        public void paint(Graphics g) {
            g.drawImage(mapImage, 0, 0, null);

            for(Person p: people)
            {
                p.paint(g);
            }

            for(int i =0; i < people.size();i++) {
                for(int j = i+1 ; j < people.size();j++){
                    people.get(i).fight(people.get(j));
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }
    }

    /**
     * Metoda wykonana po czasie Timera t
     * @param e - wydarzenie, do którego odnosi się ActionListener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        finishSimulation();
    }

    /**
     * Klasa do dialogu z użytkownikiem
     */
    private static class MessageDialog extends JDialog {
        /**
         * Konstruktor dialogu z użytkownikiem z tekstem oraz przyciskiem "OK"
         * @param message
         */
        public MessageDialog(String message) {
            super();
            setLayout(new BorderLayout());
            add(new JLabel(message, SwingConstants.CENTER),
                    BorderLayout.CENTER);
            JButton okButton = new JButton("OK");
            add(okButton, BorderLayout.SOUTH);
            okButton.addActionListener(actionEvent -> setVisible(false));
            pack();
            setResizable(false);
            setModalityType(ModalityType.APPLICATION_MODAL);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }
}
