/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2d;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel implements ActionListener, Runnable {

    Dude p;
    public Image img;
    Timer time;
    int v = 172;
    Thread animator;
    Enemy en;
    Enemy en2;
    Chest ch;
    Boss bo;
    Bochest ch2;
    
    String answer;

    boolean lost = false;
    boolean a = false;
    boolean done2 = false;
    boolean info = false;
    boolean ques = false;
    boolean level = false;

    static Font font = new Font("Times New Roman", Font.BOLD, 24);

    public Board() {
        p = new Dude();
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon i = new ImageIcon("C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\still.jpg");//imgur.com
        img = i.getImage();
        time = new Timer(5, this);
        time.start();
        en = new Enemy(700, 227, "C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\enemy2.gif");
        en2 = new Enemy(700, 227, "C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\enemy2.gif");
        ch = new Chest(900, 227, "C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\chest.png");
        ch2 = new Bochest(1700, 10, "C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\bochest.gif");
        bo = new Boss(1700, 10, "C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\boss.png");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkCollisions();
        ArrayList bullets = Dude.getBullets();
        for (int w = 0; w < bullets.size(); w++) {
            Bullet m = (Bullet) bullets.get(w);
            if (m.getVisible() == true) {
                m.move();
            } else {
                bullets.remove(w);
            }
        }

        p.move();

        if (p.x > 400) {
            en.move(p.getdx(), p.getLeft());
        }
        if (p.x > 500) {
            en2.move(p.getdx(), p.getLeft());
        }
        if (p.x > 400) {
            ch.move(p.getdx(), p.getLeft());
        }
        if (p.x > 400) {
            ch2.move(p.getdx(), p.getLeft());
        }
        if (p.x > 400) {
            bo.move(p.getdx(), p.getLeft());
        }
        repaint();
    }

    public void checkCollisions() {
        Rectangle r1 = en.getBounds();
        Rectangle r2 = en2.getBounds();
        Rectangle r3 = ch.getBounds();
        Rectangle r4 = bo.getBounds();
        Rectangle r5 = ch2.getBounds();

        ArrayList bullets = Dude.getBullets();
        for (int w = 0; w < bullets.size(); w++) {
            Bullet m = (Bullet) bullets.get(w);
            Rectangle m1 = m.getBounds();
            if (r1.intersects(m1) && en.Alive()) {
                en.isAlive = false;
                en = new Enemy(-500, -500, "C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\enemy2.gif");
                m.visible = false;
            } else if (r2.intersects(m1) && en2.Alive()) {
                en2.isAlive = false;
                en2 = new Enemy(-500, -500, "C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\enemy2.gif");
                m.visible = false;
            }
            if (r3.intersects(m1) && ch.Alive()) {
                ch.isAlive = false;
                m.visible = false;
            }

            if (r4.intersects(m1) && bo.Alive()) {
                bo.isAlive = false;
                bo = new Boss(-500, -500, "C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\boss.png");
                m.visible = false;
            }

        }
        Rectangle d = p.getBounds();
        lost = d.intersects(r1) || d.intersects(r2) || d.intersects(r4);
        lost = d.intersects(r4);

        info = d.intersects(r3);
        ques = d.intersects(r5);

    }

    @Override
    public void paint(Graphics g) {
        if (lost == true) {

            System.exit(0);
        } else if (p.dy == 1 && done2 == false) {
            done2 = true;
            animator = new Thread(this);
            animator.start();
        }
        if (info == true) {

            ch = new Chest(-500, -500, "C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\chest.png");
            JOptionPane.showMessageDialog(null, "A track is one of the thousands of concentric circles that make up the surface of a magnetic disk.");

            // remember to add info about memory for player.
        }
        if (ques == true) {
            ch2 = new Bochest(-500, -500, "C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\bochest.gif");
            answer = JOptionPane.showInputDialog("Where is the track located?");
           
           
        }
        if ("magnetic disk".equals(answer)){
            level = true;
        }
            lost = true;
        
        if (level == true){
            ImageIcon i = new ImageIcon("C:\\Users\\De'Angelo\\Documents\\NetBeansProjects\\Game2D\\src\\night.jpg");//imgur.com
            img = i.getImage();
            
        }
    
        
        

        p.y = v;
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if ((p.getX() - 20) % 1200 == 0) {
            p.nx = 0;
        }
        if ((p.getX() - 628) % 1200 == 0) {
            p.nx2 = 0;
        }
        g2d.drawImage(img, 643 - p.nx2, 0, null);
        if (p.getX() >= 20) {
            g2d.drawImage(img, 643 - p.nx, 0, null);
            g2d.drawImage(p.getImage(), p.left, v, null);
        }
        ArrayList bullets = Dude.getBullets();
        for (int w = 0; w < bullets.size(); w++) {
            Bullet m = (Bullet) bullets.get(w);
            g2d.drawImage(m.getImage(), m.getX(), v + 52, null);
        }
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Ammo left: " + p.ammo, 500, 50);
        if (p.x > 400) {
            if (en.Alive() == true) {
                g2d.drawImage(en.getImage(), en.getX(), en.getY(), (null));
            }
        }
        if (p.x > 500) {
            if (en2.Alive() == true) {
                g2d.drawImage(en2.getImage(), en2.getX(), en2.getY(), (null));
            }
        }
        if (p.x > 500) {
            if (ch.Alive() == true) {
                g2d.drawImage(ch.getImage(), ch.getX(), ch.getY(), (null));
            }
            if (p.x > 500) {
                if (bo.Alive() == true) {
                    g2d.drawImage(bo.getImage(), bo.getX(), bo.getY(), (null));
                }
                if (p.x > 500) {
                    if (ch2.Alive() == true) {
                        g2d.drawImage(ch2.getImage(), ch2.getX(), ch2.getY(), (null));
                    }
                }
            }
        }
    }

    private class AL extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }
    }
    boolean h = false;
    boolean done = false;

    public void cycle() {
        if (h == false) {

            v--;
        }
        if (v == 125) {
            h = true;
        }
        if (h == true && v <= 172) {
            v++;
            if (v == 172) {
                done = true;
            }
        }
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        while (done == false) {
            cycle();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = 10 - timeDiff;
            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (Exception e) {
            }
            beforeTime = System.currentTimeMillis();
        }
        done = false;
        h = false;
        done2 = false;
    }

}
