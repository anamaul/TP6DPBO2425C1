import javax.swing.*;//import

public class App {
    public static void main(String[] args) {
        // Tampilkan main menu terlebih dahulu
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenu();
            }
        });
    }
}