package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // o objeto todo é solido
    public int solidAreaDefaltX = 0;
    public int solidAreaDefaltY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            // if para renderizar apenas o que for visivel
            g2.drawImage(image, screenX, screenY, null);
            
            // DEBUG COLLISION
            // Color c = new Color(0, 0, 255, 200); // 255 max transparencia
            // g2.setColor(c);
            // g2.fillRect(screenX, screenY, 48, 48);

        }
    }
}
