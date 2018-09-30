package com.andy.aop.introspection;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-30
 **/
public class Point {

    private Integer x;

    private Integer y;

    public Point(Integer x, Integer y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

}
