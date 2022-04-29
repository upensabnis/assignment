package com.homes.tabletoprobot;

import com.homes.tabletoprobot.dto.Direction;
import com.homes.tabletoprobot.dto.Robot;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TabletopRobotApplicationTest {

    TabletopRobotApplication application;

    @BeforeMethod
    public void init() {
        application = new TabletopRobotApplication(5);
    }

    @Test
    public void testPlaceDroid() {
        Assert.assertFalse(application.isRobotOnTable());
        application.left();
        Assert.assertFalse(application.isRobotOnTable());
        application.right();
        Assert.assertFalse(application.isRobotOnTable());
        application.move();
        Assert.assertFalse(application.isRobotOnTable());
        application.report();
        Assert.assertFalse(application.isRobotOnTable());

        // use out of range co-ordinates
        application.place(-5, 10, Direction.SOUTH);
        Assert.assertFalse(application.isRobotOnTable());

        // bot is placed on table with correct values
        application.place(3,4, Direction.WEST);
        Assert.assertTrue(application.isRobotOnTable());
        Robot testRobot = application.report();
        Assert.assertEquals(3, testRobot.getX_coordinate());
        Assert.assertEquals(Direction.WEST, testRobot.getFacing_direction());
    }

    @Test
    public void left() {
        Assert.assertFalse(application.isRobotOnTable());
        application.place(3,4, Direction.WEST);
        Assert.assertTrue(application.isRobotOnTable());
        application.left();
        Assert.assertEquals(Direction.SOUTH, application.report().getFacing_direction());
    }

    @Test
    public void right() {
        Assert.assertFalse(application.isRobotOnTable());
        application.place(3,4, Direction.WEST);
        Assert.assertTrue(application.isRobotOnTable());
        application.right();
        Assert.assertEquals(Direction.NORTH, application.report().getFacing_direction());
    }

    @Test
    public void move() {

        // move towards west
        application.place(3,4, Direction.WEST);
        application.move();
        Assert.assertEquals(Direction.WEST, application.report().getFacing_direction());
        Assert.assertEquals(2, application.report().getX_coordinate());
        Assert.assertEquals(4, application.report().getY_coordinate());

        // move towards east
        application.place(1,3, Direction.EAST);
        application.move();
        Assert.assertEquals(Direction.EAST, application.report().getFacing_direction());
        Assert.assertEquals(2, application.report().getX_coordinate());
        Assert.assertEquals(3, application.report().getY_coordinate());

        // move towards north
        application.place(1,2, Direction.NORTH);
        application.move();
        Assert.assertEquals(Direction.NORTH, application.report().getFacing_direction());
        Assert.assertEquals(1, application.report().getX_coordinate());
        Assert.assertEquals(3, application.report().getY_coordinate());

        // move towards south
        application.place(1,4, Direction.SOUTH);
        application.move();
        Assert.assertEquals(Direction.SOUTH, application.report().getFacing_direction());
        Assert.assertEquals(1, application.report().getX_coordinate());
        Assert.assertEquals(3, application.report().getY_coordinate());

        // test corner cases
        application.place(0,0, Direction.SOUTH);
        Assert.assertEquals(Direction.SOUTH, application.report().getFacing_direction());
        Assert.assertEquals(0, application.report().getX_coordinate());
        Assert.assertEquals(0, application.report().getY_coordinate());

        application.place(0,0, Direction.WEST);
        Assert.assertEquals(Direction.WEST, application.report().getFacing_direction());
        Assert.assertEquals(0, application.report().getX_coordinate());
        Assert.assertEquals(0, application.report().getY_coordinate());

        application.place(4,4, Direction.NORTH);
        Assert.assertEquals(Direction.NORTH, application.report().getFacing_direction());
        Assert.assertEquals(4, application.report().getX_coordinate());
        Assert.assertEquals(4, application.report().getY_coordinate());

        application.place(4,4, Direction.EAST);
        Assert.assertEquals(Direction.EAST, application.report().getFacing_direction());
        Assert.assertEquals(4, application.report().getX_coordinate());
        Assert.assertEquals(4, application.report().getY_coordinate());
    }
}
