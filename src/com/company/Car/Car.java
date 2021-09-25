package com.company.Car;

import com.company.Control;
import com.company.DisplayPanel;

import javax.swing.*;
import java.awt.*;

public abstract class Car implements IBehavior {
    private int id;
    private int size;
    private int PosX;
    private int PosY;
    private String name;
    private int TimeLive;
    private int TimeBorn;

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public int getPosX(){
        return PosX;
    }

    @Override
    public int getPosY(){ return PosY; }

    @Override
    public String getName(){ return name; }

    @Override
    public int getId(){ return id; }

    @Override
    public int getTimeLive(){return TimeLive;}

    @Override
    public int getTimeBorn(){return TimeBorn;}


    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void setPosX(int PosX){
        this.PosX=PosX;
    }

    @Override
    public void setPosY(int PosY){
        this.PosY=PosY;
    }

    @Override
    public void setName(String name){this.name=name;}

    @Override
    public void setId(int id){this.id = id;}

    @Override
    public void SetTimeLive(int TimeLive) { this.TimeLive = TimeLive; }

    @Override
    public void SetTimeBorn(int TimeBorn) { this.TimeBorn = TimeBorn; }

    @Override
    public void Draw(JPanel window){ }

    public abstract Image getImg();

}
