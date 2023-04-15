package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Keyhandler;

public class Player extends Entity {

    GamePanel gp;
    Keyhandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, Keyhandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2;
        screenY = gp.screenHeight / 2;

        solidArea = new Rectangle();
        solidArea.x = 15; // pociçao
        solidArea.y = 25; // pociçao
        solidAreaDefaltX = solidArea.x;
        solidAreaDefaltY = solidArea.y;
        solidArea.width = 15; // tamanho x
        solidArea.height = 20; // tamanho y

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 25 - (gp.tileSize / 2);
        worldY = gp.tileSize * 20;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {

            File f1 = new File("./src/res/player/papa_up_1.png");
            File f2 = new File("./src/res/player/papa_up_2.png");
            File f3 = new File("./src/res/player/papa_down_1.png");
            File f4 = new File("./src/res/player/papa_down_2.png");
            File f5 = new File("./src/res/player/papa_left_1.png");
            File f6 = new File("./src/res/player/papa_left_2.png");
            File f7 = new File("./src/res/player/papa_right_1.png");
            File f8 = new File("./src/res/player/papa_right_2.png");
            up1 = ImageIO.read(f1);
            up2 = ImageIO.read(f2);
            down1 = ImageIO.read(f3);
            down2 = ImageIO.read(f4);
            left1 = ImageIO.read(f5);
            left2 = ImageIO.read(f6);
            right1 = ImageIO.read(f7);
            right2 = ImageIO.read(f8);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed == true
                || keyH.downPressed == true
                || keyH.leftPressed == true
                || keyH.rightPressend == true) {

            if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            } else if (keyH.leftPressed == true) {
                direction = "left";

            } else if (keyH.rightPressend == true) {
                direction = "right";

            }
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLESION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Papa pegou uma chave!");
                    break;
                case "Door":
                if (hasKey > 0) {
                    gp.obj[i] = null;
                    hasKey--;
                    gp.ui.showMessage("Abriu");
                    gp.playSE(3);
                    }
                    else{
                        gp.ui.showMessage("Cade a chave?");
                    }
                    System.out.println("Key:" + hasKey);
                    break;
                case "Boots":
                    gp.playSE(2);
                    speed -= 2; // velocidade bota
                    gp.obj[i] = null;
                    gp.ui.showMessage("Voce pegou botas pessadas!");
                    break;
                    case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
            // System.out.println("index = " + i);
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;

            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;

            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;

            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;

        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
