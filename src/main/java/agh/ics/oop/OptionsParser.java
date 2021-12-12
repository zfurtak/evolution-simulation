package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        int length = 0;
        for (String arg : args){
            switch (arg){
                case "f", "b", "r", "l", "forward", "backward", "right", "left" -> length ++;
                default -> throw new IllegalArgumentException(arg + " is not legal move specification");
            };
        }
        MoveDirection[] result = new MoveDirection[length];
        int i = 0;
        for (String arg : args) {
            switch (arg) {
                case "forward", "f" -> {
                    result[i] = (MoveDirection.FORWARD); i++; }
                case "right", "r" -> {
                    result[i] = (MoveDirection.RIGHT); i++; }
                case "backward", "b" -> {
                    result[i] = (MoveDirection.BACKWARD); i++; }
                case "left", "l" -> {
                    result[i] = (MoveDirection.LEFT); i++; }
            }
        }
        return result;
    }
}
