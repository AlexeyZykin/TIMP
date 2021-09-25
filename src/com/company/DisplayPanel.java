package com.company;

import com.company.Car.Car;
import com.company.Car.CargoCar;
import com.company.Car.PassengerCar;
import com.company.Control;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DisplayPanel extends JPanel {
    private Habitat habitat;
    private TypeCar typeCar;
    private Image dbimg;
    private Graphics dbg;

    public DisplayPanel(){
        super();
        setVisible(true);
        setDoubleBuffered(true);
    }

    public void setHabitat(Habitat _habitat){
        habitat = _habitat;
    }

    public void setTypeAnt(TypeCar _car){
        typeCar = _car;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        Ellipse2D circle = new Ellipse2D.Float(10, 10, 20, 20);
        g2.fill(circle);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i = 0;
        Car car;
        while (i < Control.list.size()) {
            car = Control.list.get(i);
            g.drawImage(car.getImg(), car.getPosX(), car.getPosY(),
                    car.getSize() * 55, car.getSize() * 55, this);
            i++;
        }
    }
}
