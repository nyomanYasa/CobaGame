package main;
import javax.swing.JFrame;

public class App{
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");
        JFrame window = new JFrame();
        window.setResizable(false);
        window.setTitle("2d adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }

    
}
