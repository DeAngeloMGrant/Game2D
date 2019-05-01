/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2d;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Dude {

    int x, dx, y, nx2, nx, left, dy;
    Image test2;
    int ammo = 10;
static ArrayList bullets;

    public Dude() {
        ImageIcon i = new ImageIcon("C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\test2.gif");
        test2 = i.getImage();
        x = 75;
        nx2 = 653;
        nx = 0;
        y = 172;
        left = 250;
        bullets = new ArrayList();
    }
    public Rectangle getBounds()
    {
        return new Rectangle(left,y, 96, 126);
    }
   public static ArrayList getBullets(){
        return bullets;
    }
    
    public void fire(){
        System.out.println(bullets.size() + "" + ammo);
        if (ammo > 0){
            ammo--;
        
        Bullet z = new Bullet ((left + 60), (y+ 154/2));
    bullets.add(z);}

    }

    public void move() {
        if (dx != -1) {
            if (left + dx <= 250) 
                left = left + dx;
             else {
                x = x + dx;
                nx2 = nx2 + dx;
                nx = nx + dx;
            }
        
        }
        else{
        if (left + dx > 0) 
            left = left + dx;
        }
        }
    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getLeft(){
        return left;
    }
    public int getdx(){
        return dx;
    }
    public int getnX(){
        return nx;
    }
    public int getnX2(){
        return nx2;
    }

    public Image getImage() {
        return test2;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
         if (key == KeyEvent.VK_SPACE) {
            fire();
         
         }
        if (key == KeyEvent.VK_UP)
        {
            dy = 1;
        }
    }
    

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) 
            dx = 0;
        

        if (key == KeyEvent.VK_RIGHT) 
            dx = 0;
        
        if (key == KeyEvent.VK_UP)
            dy = 0;
    }

   

    

}
