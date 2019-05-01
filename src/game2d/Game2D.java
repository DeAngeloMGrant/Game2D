/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2d;

import javax.swing.*;

public class Game2D {

    public Game2D() {
        JFrame frame = new JFrame();
        frame.add(new Board());
        frame.setTitle("2-D Test Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 365);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Game2D();
    }
}
