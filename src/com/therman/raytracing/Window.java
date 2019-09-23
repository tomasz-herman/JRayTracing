package com.therman.raytracing;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    public Window(String title, int width, int height){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(title);
        setSize(new Dimension(width, height));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
