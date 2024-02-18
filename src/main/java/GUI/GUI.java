package GUI;

import Main.Main;
import Structures.SimulationOptions;
import Terrain.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.IOException;

/**
 * Klasa ekranu tytułowego
 */
public class GUI extends JFrame implements ActionListener {
    /**
     * Dialog z użytkownikiem z tekstem oraz przyciskiem "OK"
     */
    private static class MessageDialog extends JDialog {
        /**
         * Konstruktor otwierający okno dialogowe z wiadomością dla użytkownika
         * @param message - wiadomość wyświetlona użytkownikowi
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

    /**
     * Główny kontener
     */
    JPanel panel = new JPanel();
    /**
     * Mniejszy kontener, w ciemniejszym kolorze na środku kontenera głównego
     */
    JPanel panel2 = new JPanel();
    /**
     * Tekst tytułowy
     */
    CustomLabel label;
    /**
     * Pytanie o nazwę pierwszej choroby
     */
    JLabel vir1;
    /**
     * Pytanie o nazwę drugiej choroby
     */
    JLabel vir2;
    /**
     * Pytanie o nazwę trzeciej choroby
     */
    JLabel vir3;
    /**
     * Pytanie o nazwę czwartej choroby
     */
    JLabel vir4;
    /**
     * Pole tekstowe, w którym użytkownik wprowadza nazwę pierwszej choroby
     */
    JTextField vir1_name = new MaxLengthTextField(13);
    /**
     * Pole tekstowe, w którym użytkownik wprowadza nazwę drugiej choroby
     */
    JTextField vir2_name = new MaxLengthTextField(13);
    /**
     * Pole tekstowe, w którym użytkownik wprowadza nazwę trzeciej choroby
     */
    JTextField vir3_name = new MaxLengthTextField(13);
    /**
     * Pole tekstowe, w którym użytkownik wprowadza nazwę czwartej choroby
     */
    JTextField vir4_name = new MaxLengthTextField(13);
    /**
     * Pytanie o parametry pierwszej choroby
     */
    JLabel vir1_param;
    /**
     * Pytanie o parametry drugiej choroby
     */
    JLabel vir2_param;
    /**
     * Pytanie o parametry trzeciej choroby
     */
    JLabel vir3_param;
    /**
     * Pytanie o parametry czwartej choroby
     */
    JLabel vir4_param;
    /**
     * Model przekazywany do pierwszego "spinnera" liczbowego
     */
    SpinnerModel model1 = new SpinnerNumberModel(50, 1, 100, 1);
    /**
     * Model przekazywany do drugiego "spinnera" liczbowego
     */
    SpinnerModel model2 = new SpinnerNumberModel(50, 1, 100, 1);
    /**
     * Model przekazywany do trzeciego "spinnera" liczbowego
     */
    SpinnerModel model3 = new SpinnerNumberModel(50, 1, 100, 1);
    /**
     * Model przekazywany do czwartego "spinnera" liczbowego
     */
    SpinnerModel model4 = new SpinnerNumberModel(50, 1, 100, 1);
    /**
     * "Spinner" liczbowy z parametrami pierwszej choroby
     */
    JSpinner vir1_spin = new JSpinner(model1);
    /**
     * "Spinner" liczbowy z parametrami drugiej choroby
     */
    JSpinner vir2_spin = new JSpinner(model2);
    /**
     * "Spinner" liczbowy z parametrami trzeciej choroby
     */
    JSpinner vir3_spin = new JSpinner(model3);
    /**
     * "Spinner" liczbowy z parametrami czwartej choroby
     */
    JSpinner vir4_spin = new JSpinner(model4);
    /**
     * Przycisk rozpoczynający symulację
     */
    JButton begin = new JButton("Symuluj");
    /**
     * Listy z nazwami i parametrami chorób
     */
    SimulationOptions simOptions = new SimulationOptions();
    /**
     * Kopia list z nazwami i parametrami chorób, przekazywane dalej
     */
    public static SimulationOptions simOptions_copy = new SimulationOptions();

    /**
     * Konstruktor okna początkowego
     */
    public GUI() {
        // ustawienia ramki
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(1000, 800);
        this.setVisible(true);
        this.setTitle("Epidemic Simulation");
        this.setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UIManager.put("OptionPane.yesButtonText", "Tak");
                UIManager.put("OptionPane.noButtonText", "Nie");
                int result = JOptionPane.showConfirmDialog(GUI.this, "Czy na pewno chcesz wyjsc?", "Wyjscie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // ustawienia panelu 1
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        Color panel1_color = new Color(160, 0, 0);
        panel.setBackground(panel1_color);

        // ustawienia panelu 2
        panel2.setBounds(140, 160, 700, 480);
        Color panel2_color = new Color(150, 0, 0);
        panel2.setBackground(panel2_color);
        panel2.setLayout(null);

        // ustawienia tekstu tytulowego
        label = new CustomLabel("EPIDEMIC SIMULATION");
        label.setBounds(140, 50, 800, 70);
        //label.setFont(new Font("Mars Bold", Font.PLAIN, 30));
        Color title_color = new Color(220, 220, 220);
        label.setForeground(title_color);
        panel.setLayout(null);

        // ustawienia nazw wirusow
        vir1 = new JLabel("Podaj nazwe pierwszej choroby: ");
        vir1.setBounds(80, 0, 300, 70);
        vir1.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir1.setForeground(Color.WHITE);

        vir2 = new JLabel("Podaj nazwe drugiej choroby: ");
        vir2.setBounds(80, 40, 400, 70);
        vir2.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir2.setForeground(Color.WHITE);

        vir3 = new JLabel("Podaj nazwe trzeciej choroby: ");
        vir3.setBounds(80, 80, 400, 70);
        vir3.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir3.setForeground(Color.WHITE);

        vir4 = new JLabel("Podaj nazwe czwartej choroby: ");
        vir4.setBounds(80, 120, 400, 70);
        vir4.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir4.setForeground(Color.WHITE);

        // ustawienia pol tekstowych
        vir1_name.setBounds(420, 25, 200, 20);
        vir2_name.setBounds(420, 65, 200, 20);
        vir3_name.setBounds(420, 105, 200, 20);
        vir4_name.setBounds(420, 145, 200, 20);

        // ustawienia tekstow do parametrow
        vir1_param = new JLabel("Wybierz parametry pierwszej choroby: ");
        vir1_param.setBounds(80, 200, 300, 70);
        vir1_param.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir1_param.setForeground(Color.WHITE);

        vir2_param = new JLabel("Wybierz parametry drugiej choroby: ");
        vir2_param.setBounds(80, 240, 300, 70);
        vir2_param.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir2_param.setForeground(Color.WHITE);

        vir3_param = new JLabel("Wybierz parametry trzeciej choroby: ");
        vir3_param.setBounds(80, 280, 300, 70);
        vir3_param.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir3_param.setForeground(Color.WHITE);

        vir4_param = new JLabel("Wybierz parametry czwartej choroby: ");
        vir4_param.setBounds(80, 320, 300, 70);
        vir4_param.setFont(new Font("Helvetica", Font.PLAIN, 15));
        vir4_param.setForeground(Color.WHITE);

        // ustawienia suwakow
        vir1_spin.setBounds(420, 220, 200, 30);
        vir2_spin.setBounds(420, 260, 200, 30);
        vir3_spin.setBounds(420, 300, 200, 30);
        vir4_spin.setBounds(420, 340, 200, 30);

        // ustawienia przycisku do rozpoczecia symulacji
        begin.setBounds(280, 400, 100, 50);
        begin.addActionListener(this);

        this.add(panel, BorderLayout.CENTER);
        panel.add(panel2);
        panel.add(label);
        panel2.add(vir1);
        panel2.add(vir2);
        panel2.add(vir3);
        panel2.add(vir4);
        panel2.add(vir1_name);
        panel2.add(vir2_name);
        panel2.add(vir3_name);
        panel2.add(vir4_name);
        panel2.add(vir1_param);
        panel2.add(vir2_param);
        panel2.add(vir3_param);
        panel2.add(vir4_param);
        panel2.add(vir1_spin);
        panel2.add(vir2_spin);
        panel2.add(vir3_spin);
        panel2.add(vir4_spin);
        panel2.add(begin);
    }

    /**
     * Przekształcenie opcji wprowadzonych przez użytkownika oraz przekazanie dalej
     */
    public void userOptions() {
        String vir_1_name = vir1_name.getText();
        String vir_2_name = vir2_name.getText();
        String vir_3_name = vir3_name.getText();
        String vir_4_name = vir4_name.getText();

        if (vir_1_name.isEmpty() || vir_2_name.isEmpty() || vir_3_name.isEmpty() || vir_4_name.isEmpty()) {
            throw new MissingDataException("Nie wprowadzono nazwy choroby!");
        }

        simOptions.virus_Names.add(vir_1_name);
        simOptions.virus_Names.add(vir_2_name);
        simOptions.virus_Names.add(vir_3_name);
        simOptions.virus_Names.add(vir_4_name);

        int param1 = (int) vir1_spin.getValue();
        int param2 = (int) vir2_spin.getValue();
        int param3 = (int) vir3_spin.getValue();
        int param4 = (int) vir4_spin.getValue();
        double vir_1_param;
        double vir_2_param;
        double vir_3_param;
        double vir_4_param;

        if (param1 > 99) {
            vir_1_param = 1;
        } else {
            vir_1_param = (double) param1 / 100;
        }

        if (param2 > 99) {
            vir_2_param = 1;
        } else {
            vir_2_param = (double) param2 / 100;
        }

        if (param3 > 99) {
            vir_3_param = 1;
        } else {
            vir_3_param = (double) param3 / 100;
        }

        if (param4 > 99) {
            vir_4_param = 1;
        } else {
            vir_4_param = (double) param4 / 100;
        }

        simOptions.virus_Params.add(vir_1_param);
        simOptions.virus_Params.add(vir_2_param);
        simOptions.virus_Params.add(vir_3_param);
        simOptions.virus_Params.add(vir_4_param);
    }

    /**
     * Metoda wykonana po naciśnięciu guzika "Symuluj"
     * @param e - wydarzenie po którym wykonuje się metoda
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            userOptions();
            simOptions_copy = simOptions;
            this.dispose();
            openMainWindow(new Map(simOptions_copy));
        } catch (MissingDataException ex) {
            String message = ex.getMessage();
            String title = "Error";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Główne okno z symulacją
     */
    private MainWindow mainWindow;

    /**
     * Metoda otwierająca główne okno z symulacją
     * @param map - mapa, na której odbywa się symulacja
     */
    public void openMainWindow(Map map) {
        mainWindow = new MainWindow(simOptions_copy,map,this);
    }

    /**
     * Metoda zamykająca główne okno z symulacją
     */
    public void closeMainWindow() {
        mainWindow.dispose();
    }

    /**
     * Metoda wyświetlająca wiadomość użytkownikowi
     * @param message - wiadomość dla użytkownika
     * @param fatal - fatalny błąd wywołujący wyjątek
     */
    public void showMessage(String message, boolean fatal) {
        new MessageDialog(message);
        if (fatal)
            System.exit(1);
    }

    /**
     * Klasa wyjątku o brakujących danych w formularzu na początku
     */
    public class MissingDataException extends RuntimeException {
        /**
         * Wyjątek braku danych
         * @param message - wiadomość dla użytkownika
         */
        public MissingDataException(String message) {
            super(message);
        }
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
                Font labelFont = customFont.deriveFont(Font.PLAIN, 30);
                setFont(labelFont);
            }
        }
    }
}

