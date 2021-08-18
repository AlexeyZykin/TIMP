package com.company;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Control window = new Control("Window_Cars");
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.run();

    }
}

