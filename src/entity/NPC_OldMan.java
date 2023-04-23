package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {

        super(gp);
        direction = "down";
        speed = 1;
        getImage();
    }

    public void getImage() {

        up1 = setup("src\\res\\npc\\pinguim_up_1.png");
        up2 = setup("src\\res\\npc\\pinguim_up_2.png");
        down1 = setup("src\\res\\npc\\pinguim_down_1.png");
        down2 = setup("src\\res\\npc\\pinguim_down_2.png");
        left1 = setup("src\\res\\npc\\pinguim_left_1.png");
        left2 = setup("src\\res\\npc\\pinguim_left_2.png");
        right1 = setup("src\\res\\npc\\pinguim_right_1.png");
        right2 = setup("src\\res\\npc\\pinguim_right_2.png");
    }

    public void setAction() {

        actionLockCounter++;
        
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up a number 1 to 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

}
