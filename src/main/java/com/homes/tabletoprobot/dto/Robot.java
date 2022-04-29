package com.homes.tabletoprobot.dto;

public class Robot {

    private int x_coordinate;
    private int y_coordinate;
    private Direction facing_direction;

    public int getX_coordinate() {
        return x_coordinate;
    }

    public void updateX_coordinate(int x) {
        this.x_coordinate = x;
    }

    public void updateY_coordinate(int y) {
        this.y_coordinate = y;
    }

    public int getY_coordinate() {
        return y_coordinate;
    }

    public Direction getFacing_direction() {
        return this.facing_direction;
    }

    public void setFacing_direction(Direction facing_direction) {
        this.facing_direction = facing_direction;
    }

    @Override
    public String toString() {
        return "coordinates: {" + this.getX_coordinate() + ", " + this.getY_coordinate() + "}, direction: " + facing_direction;
    }

    public void place(int x, int y, Direction direction) {
        this.x_coordinate = x;
        this.y_coordinate = y;
        this.setFacing_direction(direction);
    }

    public Robot report() {
        return this;
    }

    public void left() {
        this.setFacing_direction(Direction.left(this.facing_direction));
    }

    public void right() {
        this.setFacing_direction(Direction.right(this.facing_direction));
    }

    public void moveEast() {
        this.updateX_coordinate(this.getX_coordinate() + 1);
    }

    public void moveWest() {
        this.updateX_coordinate(this.getX_coordinate() - 1);
    }

    public void moveNorth() {
        this.updateY_coordinate(this.getY_coordinate() + 1);
    }

    public void moveSouth() {
        this.updateY_coordinate(this.getY_coordinate() - 1);
    }
}
