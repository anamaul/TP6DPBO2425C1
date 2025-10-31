import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Flappy Bird - Main Menu");
        setSize(360, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel utama dengan background cyan
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.CYAN);

        // Label judul
        JLabel titleLabel = new JLabel("FLAPPY BIRD", SwingConstants.CENTER);
        titleLabel.setFont(new Font("monospaced", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(50, 150, 260, 60);
        panel.add(titleLabel);

        // Tombol Play
        JButton playButton = new JButton("PLAY GAME");
        playButton.setFont(new Font("monospaced", Font.BOLD, 24));
        playButton.setBounds(80, 300, 200, 60);
        playButton.setFocusPainted(false);
        playButton.setBackground(new Color(76, 175, 80));
        playButton.setForeground(Color.WHITE);
        playButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutup main menu
                dispose();

                // Buka game window
                startGame();
            }
        });
        panel.add(playButton);

        // Tombol Exit
        JButton exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("monospaced", Font.BOLD, 24));
        exitButton.setBounds(80, 380, 200, 60);
        exitButton.setFocusPainted(false);
        exitButton.setBackground(new Color(244, 67, 54));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Keluar dari program
                System.exit(0);
            }
        });
        panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

    private void startGame() {//tampilan ketika memulai game
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        Logic logika = new Logic();
        View tampilan = new View(logika);
        logika.setView(tampilan);

        tampilan.requestFocus();

        frame.add(tampilan);
        frame.pack();
        frame.setVisible(true);
    }
}