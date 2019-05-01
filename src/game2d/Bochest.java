/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2d;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author De'Angelo
 */
public class Bochest {

    Image img;
    int x, y;
    boolean isAlive = true;

    public Bochest(int startX, int startY, String location) {
        x = startX;
        y = startY;
        ImageIcon l = new ImageIcon(location);
        img = l.getImage();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean Alive() {
        return isAlive;
    }

    public Image getImage() {
        return img;
    }

    public void move(int dx, int left) {
        if (dx == 1 && !((left + dx) < 150)) {
            x = x - dx;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 300, 250);
    }

}
