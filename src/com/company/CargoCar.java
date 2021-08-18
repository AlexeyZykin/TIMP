package com.company;


import com.company.Control;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class CargoCar extends Car{
    static public Image Cargo_Car;
    private int size;
    static private int amount_car=0;

    public CargoCar(){
        setName("Cargo Car");
        amount_car++;
        setSize(1);
}

    @Override
    public void drive() {
    }

    @Override //рисование картинки
    public void Draw(Control window)
    {
        int x = ThreadLocalRandom.current().nextInt(0, window.getWidth() - this.getSize() * 55);
        int y = ThreadLocalRandom.current().nextInt(0, window.getHeight() - this.getSize() * 55);
        this.setPosX(x);
        this.setPosY(y);

        window.getGraphics().drawImage(Cargo_Car, x, y, getSize() * 55, getSize() * 55, null);

        System.out.println("Cargo X: " + x + " Y: " + y + " Amount: " + amount_car);
    }

    @Override
    public void RespawnCar(Control window){
        window.getGraphics().drawImage(Cargo_Car, this.getPosX(), this.getPosY(), getSize() * 55, getSize() * 55, null);
    }
}
