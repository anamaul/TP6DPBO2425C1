import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640; // Batas bawah adalah 640

    int playerStartPosX = frameWidth/2;
    int playerStartPosY = frameHeight/2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view;
    Image birdImage;
    Player player;

    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;

    int pipeVelocityX = -2;

    boolean gameOver = false;

    // Variabel ground dan groundY Dihapus

    int score = 0;

    public Logic(){
        // groundY = frameHeight - groundHeight; // Dihapus

        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                placePipes();
            }
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public int getScore() {
        return score;
    }

    public void restartGame() {
        player.setPosY(playerStartPosY);
        player.setPosX(playerStartPosX);
        player.setVelocityY(0);
        pipes.clear();
        gameOver = false;
        score = 0;

        if (view != null) {
            view.updateScoreLabel(score);
        }

        gameLoop.start();
        pipesCooldown.start();
    }

    public void setView(View view)
    {
        this.view = view;
    }

    public Player getPlayer()
    {
        return player;
    }

    public ArrayList<Pipe> getPipes()
    {
        return pipes;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }

    // Getter ground Dihapus
    // public Image getGroundImage() { return groundImage; }
    // public int getGroundY() { return groundY; }

    public void placePipes(){
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void move(){
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0)); // Batas atas

        // ********** KOREKSI 1: Hapus pencegahan jatuh di sini **********
        // Logika pencegahan jatuh di move() dihilangkan agar collision bisa dideteksi
        // di actionPerformed saat burung melewati batas.
        // if (player.getPosY() + player.getHeight() >= frameHeight) {
        //     player.setPosY(frameHeight - player.getHeight());
        // }
        // ***************************************************************

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);
        }
    }

    // Metode untuk mendeteksi collision
    public boolean collision(Player player, Pipe pipe) {
        return player.getPosX() < pipe.getPosX() + pipe.getWidth() &&
                player.getPosX() + player.getWidth() > pipe.getPosX() &&
                player.getPosY() < pipe.getPosY() + pipe.getHeight() &&
                player.getPosY() + player.getHeight() > pipe.getPosY();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();

        // Cek collision dengan pipa dan update skor
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);

            // Cek Collision
            if (collision(player, pipe)) {
                gameOver = true;
            }

            // Logika skor: Hanya cek pipa atas (indeks genap)
            if (i % 2 == 0) {
                if (pipe.getPosX() + pipe.getWidth() < player.getPosX() && !pipe.isPassed()) {
                    score = score + 1;
                    pipe.setPassed(true);

                    // Tandai juga pipa pasangannya (pipa bawah)
                    Pipe lowerPipe = pipes.get(i + 1);
                    lowerPipe.setPassed(true);

                    if (view != null) {
                        view.updateScoreLabel(score);
                    }
                }
            }
        }

        // ********** KOREKSI 2: Logika Game Over di Batas Bawah **********
        // Cek jika player menyentuh dasar frame (frameHeight = 640)
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            gameOver = true;
            // Tetapkan posisi burung di dasar frame agar tidak menghilang
            player.setPosY(frameHeight - player.getHeight());
        }
        // ****************************************************************

        // Jika game over, hentikan semua timer
        if (gameOver) {
            gameLoop.stop();
            pipesCooldown.stop();
        }

        if (view != null){
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (!gameOver) {
                player.setVelocityY(-10);
            }
        }

        // Restart game dengan tombol R
        if (e.getKeyCode() == KeyEvent.VK_R){
            if (gameOver) {
                restartGame();
            }
        }

        // Exit program dengan tombol E saat game over
        if (e.getKeyCode() == KeyEvent.VK_E){
            if (gameOver) {
                System.exit(0);
            }
        }
    }

    public void keyReleased(KeyEvent e){}
}