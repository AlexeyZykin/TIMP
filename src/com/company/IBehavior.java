package com.company;

public interface IBehavior {

    int getSize();

    int getPosX();

    int getPosY();

    String getName();

    void setSize(int size);

    void setPosX(int PosX);

    void setPosY(int PosY);

    void setName(String name);

    void drive();

    void Draw(Control window);

    void RespawnCar(Control window);
}
