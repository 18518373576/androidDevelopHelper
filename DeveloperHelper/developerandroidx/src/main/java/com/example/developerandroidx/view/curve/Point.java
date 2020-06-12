package com.example.developerandroidx.view.curve;

/**
 * 作者： zjf 2020/6/12 4:10 PM
 * 参考：
 * 描述：
 */
public class Point {
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private float x;
    private float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
