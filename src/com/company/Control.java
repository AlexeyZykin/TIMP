package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Control extends JFrame implements KeyListener{
    private JPanel mainField;
    private JLabel timerLabel;
    private JLabel info;
    private ArrayList<Car> list;
    Habitat habitat = new Habitat(this);


    public Control(String Window_Cars) {
        super(Window_Cars);
        this.setContentPane(mainField);
        timerLabel.setVisible(false);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        info.setVisible(false);
        this.addKeyListener(this);
        Toolkit.getDefaultToolkit().setDynamicLayout(false);
    }

    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            timerLabel.setText("Прошло секунд: " + habitat.update());
        }
    };
    Timer timer = new Timer(1000,taskPerformer);


   // public void run(){}


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        habitat.Respawn();
    }

    @Override
    public void keyTyped(KeyEvent e){}


    @Override
    public void keyPressed(KeyEvent e){
        //T
        if(e.getKeyCode()==KeyEvent.VK_T){
            System.out.println("нажали T");
            if(timerLabel.isVisible()){
                timerLabel.setVisible(false);
            }
            else {
                timerLabel.setVisible(true);
            }
        }

        //B
        if(e.getKeyCode()==KeyEvent.VK_B){
            System.out.println("нажали B");
            habitat.start();
            timer.start();
        }

        //E
        if(e.getKeyCode()==KeyEvent.VK_E){
            System.out.println("нажали E");
            habitat.stop();
            timer.stop();
            GetInfo();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){}

    public void GetInfo() {
        list = habitat.GetList();
        int amount_Cargo = 0;
        int amount_Passenger = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName() == "Passenger Car") {
                amount_Passenger++;
            } else {
                amount_Cargo++;
            }
        }
        info.setVisible(true);
        info.setText("Легковых машин: " + Integer.toString(amount_Passenger) + "    Грузовых машин: " + Integer.toString(amount_Cargo));
        info.setForeground(new java.awt.Color(255, 255, 255));
        info.setFont(new Font("Arial", Font.PLAIN, 20));
    }



}
