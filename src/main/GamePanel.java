package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
    //screen settings
    final int originaleTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originaleTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidht = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixelds

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    // set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidht, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //metode baru
     @Override
     public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS : " + drawCount);
                drawCount = 0;
                timer = 0;
            }
            
        }
     }

     //metode lama
    // public void run() {
    //     double drawInterval = 1000000000 / FPS;
    //     double nextDrawTime = System.nanoTime() + drawInterval;
        
    //     while( gameThread != null) {    
    //         //System.out.println("the game is running");
    //         // 1 UPDATE : update information character such as position
    //         update();
    //         // 2 DRAW : drwe the screen with updated information
    //         repaint();

    //         try{
    //             double remainingTime = nextDrawTime - System.nanoTime();
    //             remainingTime = remainingTime / 1000000;


    //             Thread.sleep((long) remainingTime);

    //             nextDrawTime += drawInterval;

    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);
        g2.dispose();
    }
}