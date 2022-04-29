package com.homes.tabletoprobot;

import com.homes.tabletoprobot.dto.Direction;
import com.homes.tabletoprobot.dto.Robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class ReadInputFromUser {

    private static int TABLETOP_LENGTH = 5;

    public static void main(String[] args) throws IOException {
        List<String> allowedCommands = Arrays.asList("left", "right", "move", "place", "report");
        TabletopRobotApplication application = new TabletopRobotApplication(TABLETOP_LENGTH);

        String inputCommand = "report";

        while (!inputCommand.equalsIgnoreCase("QUIT")) {
            System.out.println("Enter command for robot: ");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            inputCommand = bufferedReader.readLine();

            String[] commandWithoutSpaces = inputCommand.split(" ");
            if (allowedCommands.stream().anyMatch(commandWithoutSpaces[0]::equalsIgnoreCase)) {
                switch (commandWithoutSpaces[0].toUpperCase()) {
                    case "PLACE":
                        if(commandWithoutSpaces.length == 4) {
                            Direction direction = Direction.fromString(commandWithoutSpaces[3]);
                            if(direction != null) {
                                int x = Integer.valueOf(commandWithoutSpaces[1]);
                                int y = Integer.valueOf(commandWithoutSpaces[2]);
                                application.place(x, y, direction);
                            }
                        }
                        break;

                    case "LEFT":
                        application.left();
                        break;

                    case "RIGHT":
                        application.right();
                        break;

                    case "MOVE":
                        application.move();
                        break;

                    case "REPORT":
                        Robot reportedRobot = application.report();

                        if (reportedRobot != null) {
                            System.out.println("Output: " + reportedRobot.getX_coordinate() +
                                    "," + reportedRobot.getY_coordinate() +
                                    "," + reportedRobot.getFacing_direction());
                        }
                        break;
                }
            }
        }
    }
}
