package com.homes.tabletoprobot;

import com.codahale.metrics.Counter;
import com.homes.tabletoprobot.dto.Direction;
import com.homes.tabletoprobot.dto.Robot;
import com.homes.tabletoprobot.dto.TableTop;
import com.homes.tabletoprobot.metrics.MetricsMeters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TabletopRobotApplication {

    private Logger log = Logger.getLogger(TabletopRobotApplication.class.getName());

    private TableTop tableTop;
    private Robot bb_8;
    private boolean isRobotOnTable;
    private MetricsMeters metricsMeters;
    private Counter placeExecuteCounter;
    private Counter leftExecuteCounter;
    private Counter rightExecuteCounter;
    private Counter moveExecuteCounter;
    private Counter reportExecuteCounter;
    private boolean enableMetrics;
    private boolean enableLogging;


    public TabletopRobotApplication(int lengthOfTabletop) {

        try {
            tableTop = new TableTop(lengthOfTabletop);
            bb_8 = new Robot();

            InputStream inputStream = new FileInputStream("src/main/resources/config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            enableMetrics = Boolean.valueOf(properties.getProperty("enableMetrics"));
            enableLogging = Boolean.valueOf(properties.getProperty("enableLogging"));

            metricsMeters = new MetricsMeters(enableMetrics);
            placeExecuteCounter = metricsMeters.getRegistry().counter("placeCommandCount");
            leftExecuteCounter = metricsMeters.getRegistry().counter("leftCommandCount");
            rightExecuteCounter = metricsMeters.getRegistry().counter("rightCommandCount");
            moveExecuteCounter = metricsMeters.getRegistry().counter("moveCommandCount");
            reportExecuteCounter = metricsMeters.getRegistry().counter("reportCommandCount");

            if (enableLogging) {
                log.setLevel(Level.ALL);
            } else {
                log.setLevel(Level.WARNING);
            }

            inputStream.close();

        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE, "Config file not found!!");
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception while reading config file");
        }
    }

    public boolean isRobotOnTable() {
        return isRobotOnTable;
    }

    public void place(int x, int y, Direction direction) {
        if(x >= 0 && x < tableTop.getTableTopLength()
                && y >=0 && y < tableTop.getTableTopLength()) {

            bb_8.place(x, y, direction);
            isRobotOnTable = true;
            log.info("Placed robot on board at X:" + bb_8.getX_coordinate() + ", Y: " + bb_8.getY_coordinate());
        }
        placeExecuteCounter.inc();
    }

    public void left() {
        if(isRobotOnTable) {
            log.info("Turning left...");
            bb_8.left();
        }
        leftExecuteCounter.inc();
    }

    public void right() {
        if(isRobotOnTable) {
            log.info("Turning right...");
            bb_8.right();
        }
        rightExecuteCounter.inc();
    }

    public void move() {
        if(isRobotOnTable) {
            log.info("Moving 1 unit forward...");

            int curr_x = bb_8.getX_coordinate();
            int curr_y = bb_8.getY_coordinate();
            Direction curr_direction = bb_8.getFacing_direction();

            switch (curr_direction) {
                case EAST:
                    if (curr_x < tableTop.getTableTopLength() - 1) {
                        bb_8.moveEast();
                    }
                    break;

                case WEST:
                    if (curr_x > 0) {
                        bb_8.moveWest();
                    }
                    break;

                case NORTH:
                    if (curr_y < tableTop.getTableTopLength() - 1) {
                        bb_8.moveNorth();
                    }
                    break;

                case SOUTH:
                    if (curr_y > 0) {
                        bb_8.moveSouth();
                    }
                    break;
            }
        }
        moveExecuteCounter.inc();
    }

    public Robot report() {
        reportExecuteCounter.inc();
        if(isRobotOnTable) {
            log.info("Returning current state of robot...");
            if(enableMetrics) {
                metricsMeters.reportMetrics();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return bb_8.report();
        }
        return null;
    }
}
