package com.company.Car;


import com.company.Car.Car;
import com.company.DisplayPanel;
import com.company.Control;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CargoCar extends Car {
    static public Image Cargo_Car;
    static public int amount_car=0;
    public static String staticName = "Cargo Car";


    public CargoCar(){
        setName("Cargo Car");
        amount_car++;
        setSize(1);
}

    @Override
    public void drive() {
    }

    public static String getStaticName() { return staticName; }

    @Override //рисование картинки
    public void Draw(JPanel window)
    {
        int x=0, y=0;
        try {
            x = ThreadLocalRandom.current().nextInt(0, window.getWidth() - this.getSize() * 55);
            y = ThreadLocalRandom.current().nextInt(0, window.getHeight() - this.getSize() * 55);
        }
        catch(IllegalArgumentException ia){
            x = 0;
            y = window.getHeight() - window.getHeight();;
        }

        this.setPosX(x);
        this.setPosY(y);

        window.getGraphics().drawImage(Cargo_Car, x, y, getSize() * 55, getSize() * 55, null);

    }


    @Override
    public Image getImg() {
        return Cargo_Car;
    }
/*
    @Override
    public void RespawnCar(JPanel window){
        window.getGraphics().drawImage(Cargo_Car, this.getPosX(), this.getPosY(), getSize() * 55, getSize() * 55, null);
    }*/
}
