package com.homes.tabletoprobot;

import com.homes.tabletoprobot.dto.Direction;
import com.homes.tabletoprobot.dto.Robot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadInputFromFile {

    private static int TABLETOP_LENGTH = 5;

    public static void main(String[] args) throws IOException {
        List<String> allowedCommands = Arrays.asList("left", "right", "move", "place", "report");
        List<String> commands = Files.readAllLines(Paths.get("src/test/java/resources/input.txt"),
                StandardCharsets.UTF_8);
        TabletopRobotApplication application = new TabletopRobotApplication(TABLETOP_LENGTH);

        commands.forEach( command -> {
            String[] commandWithoutSpaces = command.split(" ");
            ArrayList<String> outputs = new ArrayList<>();
            if (allowedCommands.stream().anyMatch(commandWithoutSpaces[0]::equalsIgnoreCase)) {
                switch(commandWithoutSpaces[0].toUpperCase()) {
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

                        if(reportedRobot != null) {
                            outputs.add("Output: " + reportedRobot.getX_coordinate() +
                                    "," + reportedRobot.getY_coordinate() +
                                    "," + reportedRobot.getFacing_direction());
                        }
                        break;
                }
            }
            outputs.stream().forEach(System.out::println);
        });

    }
}
