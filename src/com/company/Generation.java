package com.company;
import javax.swing.*;
import com.company.PassengerCar;
import com.company.CargoCar;
import com.company.Car;
import java.util.ArrayList;

public class Generation extends Thread{
    private Control window;
    private double P;
    private int N;
    Car car;
    private ArrayList<Car> list;



    public Generation(Control window, Car car, int N, double P, ArrayList<Car> list) {
        this.window=window;
        this.car=car;
        this.N=N;
        this.P=P;
        this.list = list;
    }

    public void run(){
        //рисуем машины
        while (!this.isInterrupted()){
            System.out.println("есть");
            if(Math.random()<P) {
                System.out.println(Math.random());
                if (car.getName() == "Passenger Car") {
                    car = new PassengerCar();
                } else {
                    car = new CargoCar();
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() { car.Draw(window); }
                });
                list.add(car);
            }
            try {
                this.sleep(N);
            } catch (InterruptedException e) {
                e.printStackTrace();
                this.interrupt();
            }
            System.out.println("есть");
        }
    }
}
