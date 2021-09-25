package com.company.Car;

import com.company.DisplayPanel;
import com.company.Control;
import javax.swing.*;

public interface IBehavior {

    int getSize();

    int getPosX();

    int getPosY();

    String getName();

    int getId();

    int getTimeLive();

    int getTimeBorn();

    void setSize(int size);

    void setPosX(int PosX);

    void setPosY(int PosY);

    void setName(String name);

    void setId(int id);

    void SetTimeLive(int TimeLive);

    void SetTimeBorn(int TimeBorn);

    void drive();

    void Draw(JPanel window);

    //void RespawnCar(JPanel window);
}
