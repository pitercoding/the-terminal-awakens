package com.terminalawakens.util.messages;

public class GameEnd {

    /**
     * Mostra a lore final após derrotar o Terminal of Vortex
     */
    public static void finishTerminalOfVortex(String playerName) {
        // Primeira parte da lore com o nome do jogador
        SlowConsole.printSlowly(
                "* * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
                        "*   Congratulations, hero!                             *\n" +
                        "*                                                      *\n" +
                        "* The Terminal of Vortex has been vanquished, and the *\n" +
                        "* labyrinth’s circuits return to peaceful silence.    *\n" +
                        "*                                                      *\n" +
                        "* Your courage, " + playerName + ", has rewritten the   *\n" +
                        "* fate of this digital world.                           *\n" +
                        "*                                                      *\n" +
                        "* Monsters flee, treasures await, and echoes of your  *\n" +
                        "* deeds will be remembered by every byte.             *\n" +
                        "*                                                      *\n" +
                        "* Take a moment to bask in your triumph, brave one.   *\n" +
                        "* * * * * * * * * * * * * * * * * * * * * * * * * * * *"
        );

        // Lore final de agradecimento
        SlowConsole.printSlowly(
                "* * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
                        "*   Thank you for playing The Terminal Awakens!       *\n" +
                        "*                                                      *\n" +
                        "*   Your journey, courage, and wit brought life to    *\n" +
                        "*   this digital adventure.                            *\n" +
                        "*                                                      *\n" +
                        "*   Until next time, " + playerName + ", the Terminal *\n" +
                        "*   awaits your return...                              *\n" +
                        "* * * * * * * * * * * * * * * * * * * * * * * * * * * *"
        );
    }
}