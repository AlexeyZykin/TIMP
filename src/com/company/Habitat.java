package com.company;

import com.company.Car;
import com.company.PassengerCar;
import com.company.CargoCar;
import javax.swing.*;
import java.awt.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Habitat {
    static private ArrayList<Car> list = new ArrayList<>();
    static Control window;
    static CargoCar cargocar = new CargoCar();
    static PassengerCar passengerCar = new PassengerCar();

    static private int seconds = 0;
    static private int width = 1280;
    static private int height = 800;
    static private int N1 = 1000;
    static private int N2 = 1000;
    static private double P1 = 0.4;
    static private double P2 = 0.5;

    static Generation thread1;
    static Generation thread2;

    Habitat(Control window) {
        this.window = window;
        this.window.setSize(width, height);
    }

    public static ArrayList<Car> GetList() {
        return list;
    }

    public static void SetList(ArrayList<Car> list) {
        Habitat.list = list;
    }

    public void start() {
        //проверка на правильность пути
        try {
            cargocar.Cargo_Car = ImageIO.read(new File("src/com/company/Picture/CargoCar.png"));
            System.out.println("Изображение считалось");
        } catch (IOException e) {
            System.out.println("Ошибка! Изображение не считалось");
        }

        try {
            passengerCar.Passenger_Car = ImageIO.read(new File("src/com/company/Picture/PassengerCar.png"));
            System.out.println("Изображение считалось");
        } catch (IOException e) {
            System.out.println("Ошибка! Изображение не считалось");
        }

        thread1 = new Generation(window, cargocar, N1, P1, list);
        thread2 = new Generation(window, passengerCar, N2, P2, list);
        thread1.start();
        thread2.start();
    }

    public void Respawn() {
        try {
            thread1.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 0;
        while (i < list.size()) {
            list.get(i).RespawnCar(window);
            i++;
        }
    }

    public static void stop() {
        if (thread1 != null) thread1.interrupt();
        if (thread2 != null) thread2.interrupt();
    }

    public int update() {
        return seconds++;
    }
}