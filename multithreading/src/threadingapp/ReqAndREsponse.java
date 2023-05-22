package threadingapp;

import java.io.Serializable;

public class ReqAndREsponse implements Serializable {
    private double money;

    public ReqAndREsponse(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "ReqAndREsponse{" +
                "money=" + money +
                '}';
    }
}
