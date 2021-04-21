package com;

public class class3 implements interface3{
    private int speed = 0;

    @Override
    public void drive() {
        System.out.println("im driving");
    }

    @Override
    public void speedUp(int speed) {
        this.speed = speed;
        System.out.println("speed"  + speed);
    }
}
