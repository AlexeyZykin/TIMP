package com.company;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;

import com.company.Car.Car;
import com.company.Car.CargoCar;
import com.company.Car.PassengerCar;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashMap;

enum TypeCar{
    Passenger, Cargo
}

public class Control extends JFrame{
    private JPanel mainField;
    private JPanel ControlPanel;
    private DisplayPanel displayPanel;
    private JButton Stop;
    private JButton Start;
    private JRadioButton timeVisible;
    private JRadioButton timeHidden;
    private JCheckBox GetInfo;
    private JTextField CargoTimeSpawn;
    private JComboBox CargoProbability;
    private JTextField PassengerTimeSpawn;
    private JComboBox PassengerProbability;
    private JLabel timerLabel;
    private JTextField TimeLiveCargo;
    private JTextField TimeLivePassenger;
    private JButton currentObjects;
    private Menu MyMenu;

    static public ArrayList<Car> list = new ArrayList<>();
    static public TreeSet<Integer> idList = new TreeSet();
    static public HashMap<Integer,Integer> BornList = new HashMap();

    Habitat habitat;
    boolean isLaunched=false;
    static public int TimeSimulation=1;
    public static int TimeLivingCargo = 5;
    public static int TimeLivingPassenger = 5;

    private int N1;
    private int N2;
    private double P1=1.0;
    private double P2=1.0;

    public Control(String Window_Cars) {
        super(Window_Cars);
        MyMenu = new Menu();
        configureMenu();
        this.setContentPane(mainField);
        ControlPanel.setFocusable(true);
        displayPanel = new DisplayPanel();
        displayPanel.setBackground(new Color(95, 139,98));
        mainField.add(displayPanel);

        habitat = new Habitat(displayPanel);

        timerLabel.setVisible(false);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        Stop.setEnabled(false);
        timeHidden.setSelected(true);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);

        int i = 10;//Заполнение вероятностей появления машин
        while(i<=100) {
            this.CargoProbability.addItem(i);
            this.PassengerProbability.addItem(i);
            i+=10;
        }

        this.Start.addActionListener(this::actionStart);
        this.Stop.addActionListener(this::actionStop);
        this.GetInfo.addActionListener(this::toggleInfoShown);
        this.timeVisible.addActionListener(this::timeVisible);
        this.timeHidden.addActionListener(this::timeHidden);
        this.CargoTimeSpawn.addActionListener(this::CargoTimeSpawn);
        this.PassengerTimeSpawn.addActionListener(this::PassengerTimeSpawn);
        this.CargoProbability.addActionListener(this::CargoProbability);
        this.PassengerProbability.addActionListener(this::PassengerProbability);
        this.TimeLiveCargo.addActionListener(this::TimeLiveCargo);
        this.TimeLivePassenger.addActionListener(this::TimeLivePassenger);
        this.currentObjects.addActionListener(this::startCurrentInfoDialog);
    }


    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            TimeSimulation++;
            if(timeVisible.isSelected())
                timerLabel.setText("Время работы программы: " + TimeSimulation);
            if(CheckTimeRespawn(TimeLivingCargo)){
                Respawn(TypeCar.Cargo);
            }
            if(CheckTimeRespawn(TimeLivingPassenger)){
                Respawn(TypeCar.Passenger);
            }
        }
    };
    Timer timer = new Timer(1000,taskPerformer);



    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
        @Override
        public boolean dispatchKeyEvent(final KeyEvent e) {
            if(e.getID() == KeyEvent.KEY_RELEASED) {
                switch (e.getKeyCode()) {
                    //T
                    case KeyEvent.VK_T: {
                        if (timeVisible.isSelected()) {
                            timeHidden.doClick();
                        } else if (timeHidden.isSelected()) {
                            timeVisible.doClick();
                        }
                        break;
                    }
                    //B
                    case KeyEvent.VK_B: {
                        Start();
                        break;
                    }
                    //E
                    case KeyEvent.VK_E: {
                        Stop();
                        break;
                    }
                }
            }
            return false;
        }
    };

    public void Respawn(TypeCar typeCar){
        habitat.respawn(typeCar);
    }

    public void actionStart(ActionEvent e){
        Start();
    }

    public void Start(){
        if(!isLaunched){
            timerLabel.setVisible(true);
            timeVisible.setSelected(true);
            habitat.ChangeProperties(this.P1, this.N1, this.P2, this.N2);
            habitat.start();
            timer.start();
            Start.setEnabled(false);
            Stop.setEnabled(true);
            isLaunched=true;
        }
    }

    public void actionStop(ActionEvent e){
        Stop();
    }

    public void StopGenerations(){
        if(isLaunched){
            habitat.stop();
            timer.stop();
            isLaunched = false;
            Stop.setEnabled(false);
            Start.setEnabled(true);
        }
    }

    public void Stop(){
        int amountCargo = CargoCar.amount_car;
        int amountPassenger = PassengerCar.amount_car;

        if(GetInfo.isSelected()){
            GetInfo(amountCargo,amountPassenger);
        }
        else{
            StopGenerations();
        }
        repaint();
    }


    public void GetInfo(int amountCargo, int amountPassenger){
        int response  = JOptionPane.showConfirmDialog(this,"Amount cargo car: " + amountCargo  +
                "\nAmount passenger car: " + amountPassenger +
                "\nTime passed: " + TimeSimulation,"Завершить симуляцию?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        switch (response){

            case JOptionPane.YES_OPTION:{
                StopGenerations();
                break;
            }
            case JOptionPane.CLOSED_OPTION:{
                StopGenerations();
                break;
            }
        }
    }

    private void startCurrentInfoDialog(ActionEvent actionEvent) {
        currentObjects co = new currentObjects(this);
        co.setBounds(500,500, 250,250);
        co.setVisible(true);
    }


    public void configureMenu(){
        MyMenu.simOptionStart.addActionListener(this::actionStart);
        MyMenu.simOptionStop.addActionListener(this::actionStop);

        MyMenu.infoOptionShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetInfo.setSelected(!GetInfo.isSelected());
            }
        });

        MyMenu.timerOptionShow.addActionListener(this::timeVisible);
        MyMenu.timerOptionHide.addActionListener(this::timeHidden);

        this.setJMenuBar(MyMenu.menuBar);
    }

    public void toggleInfoShown(ActionEvent e){
        MyMenu.infoOptionShow.setSelected(GetInfo.isSelected());
    }

    public void timeVisible(ActionEvent e){
        timerLabel.setVisible(true);
        MyMenu.timerOptionShow.setSelected(true);
        timeVisible.setSelected(true);
    }

    public void timeHidden(ActionEvent e){
        timerLabel.setVisible(false);
        MyMenu.timerOptionHide.setSelected(true);
        timeHidden.setSelected(true);
    }

    public void CargoTimeSpawn(ActionEvent e){
        try{
            this.N1 = Integer.parseInt(this.CargoTimeSpawn.getText().toString())*1000;
        }catch (Exception exception){
            this.N1 = 1000;
        }

        System.out.println(this.N1+"");
    }

    public void PassengerTimeSpawn(ActionEvent e){
        try{
            this.N2 = Integer.parseInt(this.PassengerTimeSpawn.getText().toString())*1000;
        }catch (Exception exception){
            this.N2 = 1000;
        }

        System.out.println(this.N2+"");
    }

    public void CargoProbability(ActionEvent e){
        this.P1 = Double.parseDouble(this.CargoProbability.getSelectedItem().toString())/100;

        System.out.println(this.P1+"");
    }

    public void PassengerProbability(ActionEvent e){
        this.P2 = Double.parseDouble(this.PassengerProbability.getSelectedItem().toString())/100;

        System.out.println(this.P2+"");
    }

    private void TimeLiveCargo(ActionEvent actionEvent) {
        try{
            this.TimeLivingCargo = Integer.parseInt(this.TimeLiveCargo.getText().toString());
        }catch (Exception exception){
            this.TimeLivingCargo = 5;
        }
    }

    private void TimeLivePassenger(ActionEvent actionEvent) {
        try{
            this.TimeLivingPassenger = Integer.parseInt(this.TimeLivePassenger.getText().toString());
        }catch (Exception exception){
            this.TimeLivingPassenger = 5;
        }
    }

    private boolean CheckTimeRespawn(int TimeLiving){
        if(Control.TimeSimulation % TimeLiving == 0) return true;
        else return false;

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
