package com.company;

import com.company.Car.Car;
import com.company.Car.CargoCar;
import com.company.Car.PassengerCar;

import javax.swing.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Habitat {
    static DisplayPanel window;
    static private int width = 1000;
    static private int height = 800;
    static private int N1 = 1000;
    static private int N2 = 1000;
    static private double P1 = 0.4;
    static private double P2 = 0.5;
    static Generation thread1;
    static Generation thread2;

    Habitat(DisplayPanel window) {
        this.window = window;
    }


    public void start() {
        //проверка на правильность пути
        try {
            CargoCar.Cargo_Car = ImageIO.read(new File("src/com/company/Picture/CargoCar.png"));
            System.out.println("Изображение считалось");
        } catch (IOException e) {
            System.out.println("Ошибка! Изображение не считалось");
        }

        try {
            PassengerCar.Passenger_Car = ImageIO.read(new File("src/com/company/Picture/PassengerCar.png"));
            System.out.println("Изображение считалось");
        } catch (IOException e) {
            System.out.println("Ошибка! Изображение не считалось");
        }

        thread1 = new Generation(window, CargoCar.getStaticName(), N1, P1);
        thread2 = new Generation(window, PassengerCar.getStaticName(), N2, P2);
        thread1.start();
        thread2.start();
    }

    public void respawn(TypeCar typeCar) {
        if(Control.list.size()!=0) {
            boolean OBJFound = false;
            int i = 0;
            if (typeCar == TypeCar.Cargo) {//удалает первый элемент переданного типа и отрисовывает все остальные объекты и всех вспомагательных данных
                while (i < Control.list.size()) {
                    if(Control.list.get(i).getName() != CargoCar.getStaticName()){
                        OBJFound = true;
                        break;
                    }
                    i++;
                }
                CargoCar.amount_car--;
            } else {
                while (i < Control.list.size()) {
                    if(Control.list.get(i).getName() != PassengerCar.getStaticName()){
                        OBJFound = true;
                        break;
                    }
                    i++;
                }
                PassengerCar.amount_car--;
            }
            if(OBJFound == true) {
                Control.idList.remove(Control.list.get(i).getId());
                Control.BornList.remove(Control.list.get(i).getId());
                Control.list.remove(i);
                window.repaint();
            }
        }
    }

    public static void stop() {
        if(thread1 != null) thread1.stopThread();
        if(thread2 != null) thread2.stopThread();
        for(int i = 0; i < Control.list.size(); i++){
            if(Control.list.get(i).getName() == CargoCar.getStaticName())
                CargoCar.amount_car--;
            else{
                PassengerCar.amount_car--;
            }

        }
        Control.list.clear();
    }


    public void ChangeProperties(double P1, int N1, double P2, int N2){
        if(N1 == 0)N1 = 1000;
        if(N2 == 0)N2 = 1000;

        Habitat.N1 = N1;
        Habitat.P1 = P1;
        Habitat.N2 = N2;
        Habitat.P2 = P2;
    }
}