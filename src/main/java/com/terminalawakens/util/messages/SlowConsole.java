package com.terminalawakens.util.messages;

public class SlowConsole {

    /**
     * Prints the given text to the console slowly, character by character.
     *
     * @param text The text to print
     * @param delayMillis Delay in milliseconds between each character
     */
    public static void printSlowly(String text, long delayMillis) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(); // quebra de linha final
    }

    /**
     * Prints the given text slowly with a default delay of 10 milliseconds per character.
     *
     * @param text The text to print
     */
    public static void printSlowly(String text) {
        printSlowly(text, 10);
    }
}
