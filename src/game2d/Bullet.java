/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2d;

import java.awt.*;
import javax.swing.ImageIcon;

/**
 *
 * @author De'Angelo
 */
public class Bullet {

    int x, y;
    Image img;
    boolean visible;
    int getX;
    int getY;

    public Bullet(int startX, int startY) {
        x = startX;
        y = startY;
        ImageIcon newBullet = new ImageIcon("C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\fireball.jpg");
        img = newBullet.getImage();
        visible = true;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 87, 55);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getVisible() {
        return visible;
    }

    public Image getImage() {
        return img;
    }

    public void move() {
        x = x + 2;
        if (x > 700) {
            visible = false;
        }

    }
}
