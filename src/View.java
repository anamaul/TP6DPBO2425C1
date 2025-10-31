import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    private Logic logic;
    private JLabel scoreLabel;

    int width = 360;
    int height = 640;

    private Image backgroundImage;

    public View(Logic logic) {
        this.logic = logic;
        try {
            // Memuat background.png (ini akan mengisi seluruh layar)
            backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Gagal memuat background.png. Menggunakan warna default.");
        }
        setPreferredSize(new Dimension(width, height));
        setLayout(null); // Gunakan null layout untuk positioning manual

        // Inisialisasi JLabel untuk skor
        scoreLabel = new JLabel("0");
        scoreLabel.setFont(new Font("monospaced", Font.BOLD, 36));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setBounds(180, 40, 150, 30); // x, y, width, height
        add(scoreLabel);

        //tambahkan fokus dan key listener
        //untuk menerima input keyboard
        setFocusable(true);
        addKeyListener(logic);
    }

    public void updateScoreLabel(int score) {//untuk menampilkan jumlah score
        scoreLabel.setText(String.valueOf(score));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Gambar Background
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, width, height, null);
        } else {
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, width, height);
        }

        // Gambar pipes
        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null){
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        // Gambar ground/tanah (Kode Dihapus)
        /*
        Image groundImage = logic.getGroundImage();
        if (groundImage != null) {
            int groundY = logic.getGroundY();

            // Gambar ground berulang untuk menutupi lebar layar
            for (int i = 0; i < width; i += groundImage.getWidth(null)) {
                g.drawImage(groundImage, i, groundY, null);
            }
        }
        */

        // Gambar player di atas ground
        Player player = logic.getPlayer();
        if (player != null){
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        // Tampilkan pesan Game Over
        if (logic.isGameOver()) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("monospaced", Font.BOLD, 48));
            g.drawString("GAME OVER", width/2 - 140, height/2);

            g.setFont(new Font("monospaced", Font.PLAIN, 20));
            g.drawString("Score: " + logic.getScore(), width/2 - 60, height/2 + 50);
            g.drawString("Press R to restart", width/2 - 100, height/2 + 80);
            g.drawString("Press E to exit", width/2 - 90, height/2 + 110);
        }
    }
}