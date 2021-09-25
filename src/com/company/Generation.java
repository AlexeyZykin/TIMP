package com.company;
import javax.swing.*;

import com.company.Car.Car;
import com.company.Car.CargoCar;
import com.company.Car.PassengerCar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class Generation extends Thread{
    private JPanel window;
    private double P;
    private int N;
    Car car;
    String carName;
    private boolean isWorked = true;



    public Generation(DisplayPanel window, String car_name, int N, double P) {
        this.window=window;
        this.N=N;
        this.P=P;
        this.carName=car_name;
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    public void run(){
        //рисуем машины
        while (isWorked) {

            if (Math.random() < P) {
                System.out.println(Math.random());
                if (carName == PassengerCar.getStaticName()) {
                    car = new PassengerCar();
                    car.SetTimeLive(Control.TimeLivingPassenger);
                    Control.BornList.put(car.getId(), Control.TimeSimulation);//добавление времени жизни
                } else {
                    car = new CargoCar();
                    car.SetTimeLive(Control.TimeLivingCargo);
                    Control.BornList.put(car.getId(), Control.TimeSimulation);//добавление времени жизни
                }

                if (isWorked) {
                    car.Draw(window);
                    Control.list.add(car);//добавление объекта
                    Control.idList.add(car.getId());
                }
            }
            try {
                this.sleep(N);
            } catch (InterruptedException e) {
                e.printStackTrace();
                this.interrupt();
            }
        }
        }

    public void stopThread() {
        isWorked = false;
    }
    }

