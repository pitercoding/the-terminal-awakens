package com.terminalawakens.util.messages;

public class GameEnd {

    /**
     * Mostra a lore final após derrotar o Terminal of Vortex
     */
    public static void finishTerminalOfVortex(String playerName) {
        // Lore de vitória
        SlowConsole.printSlowly(String.format("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Congratulations, hero!                                                               *
                *                                                                                        *
                * The Terminal of Vortex has been vanquished, and the labyrinth’s circuits return to     *
                * peaceful silence.                                                                      *
                *                                                                                        *
                * Your courage, %s, has rewritten the fate of this digital world.                        *
                *                                                                                        *
                * Monsters flee, treasures await, and echoes of your deeds will be remembered by every  *
                * byte.                                                                                  *
                *                                                                                        *
                * Take a moment to bask in your triumph, brave one.                                      *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """, playerName));

        System.out.println();

        // Lore final de agradecimento
        SlowConsole.printSlowly(String.format("""
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                *   Thank you for playing The Terminal Awakens!                                        *
                *                                                                                        *
                *   Your journey, courage, and wit brought life to this digital adventure.              *
                *                                                                                        *
                *   Until next time, %s, the Terminal awaits your return...                             *
                * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """, playerName));
    }
}