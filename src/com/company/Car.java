package com.company;

import com.company.Control;

abstract class Car implements  IBehavior{
    private int size;
    private int PosX;
    private int PosY;
    private String name;

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
    public void Draw(Control window){ }

   @Override
   public void RespawnCar(Control window){}
}
