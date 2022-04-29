package com.homes.tabletoprobot.dto;

import java.util.logging.Logger;

public enum Direction {

    EAST,
    WEST,
    NORTH,
    SOUTH;

    private static Logger log = Logger.getLogger(Direction.class.getName());

    public static Direction fromString(String stringDirection) {
        if(stringDirection.equalsIgnoreCase("EAST")) {
            return Direction.EAST;
        } else if (stringDirection.equalsIgnoreCase("WEST")) {
            return Direction.WEST;
        } else if (stringDirection.equalsIgnoreCase("NORTH")) {
            return Direction.NORTH;
        } else if (stringDirection.equalsIgnoreCase("SOUTH")) {
            return Direction.SOUTH;
        } else {
            return null;
        }
    }

    public static Direction left(Direction direction) {
        Direction result = direction;
        switch(direction) {
            case EAST:
                result = Direction.NORTH;
                break;
            case WEST:
                result = Direction.SOUTH;
                break;
            case NORTH:
                result = Direction.WEST;
                break;
            case SOUTH:
                result = Direction.EAST;
                break;
            default:
                log.warning("Invalid Direction!!");
        }

        return result;
    }

    public static Direction right(Direction direction) {
        Direction result = direction;
        switch(direction) {
            case EAST:
                result = Direction.SOUTH;
                break;
            case WEST:
                result = Direction.NORTH;
                break;
            case NORTH:
                result = Direction.EAST;
                break;
            case SOUTH:
                result = Direction.WEST;
                break;
            default:
                log.warning("Invalid Direction!!");
        }

        return result;
    }
}
