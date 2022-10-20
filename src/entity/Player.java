package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//import player.*;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultvalues();
        getPlayerImage();
        direction = "down";
    }

    public void setDefaultvalues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/plyAtasKanan.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/plyAtasKiri.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/plyBawahKanan.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/plyBawahKiri.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/plyKanan.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/plyjalanKanan.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/plyKiri.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/plyJalanKiri.png"));
        }catch(IOException e) {
            e.printStackTrace();
            System.out.println("kesalahan berada disini");
        }
    }

    public void update() {
        if (keyH.upPressed == true) {
            direction = "up";
            y -= speed;
        }

        if(keyH.downPressed == true) {
            direction = "down";
            y += speed;
        }

        if(keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
        }

        if(keyH.rightPressed == true) {
            direction = "right";
            x += speed;
        }

    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

        switch(direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
