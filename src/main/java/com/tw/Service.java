package com.tw;

public interface Service {
    String buildInputPrompt(boolean isFirst);

    Object getUserInput();

    boolean isValidCommand(Object command);

    void handleCommand(Object command);

    default void main() {
        System.out.println(buildInputPrompt(true));
        Object command = getUserInput();
        if (!isValidCommand(command)) {
            do {
                System.out.println(buildInputPrompt(false));
                command = getUserInput();
            } while (!isValidCommand(command));
        }
        handleCommand(command);
    }
}
