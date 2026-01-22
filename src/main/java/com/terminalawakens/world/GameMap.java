package com.terminalawakens.world;

import static com.terminalawakens.world.TileType.*;

/**
 * Guardar o mapa
 * Mostrar o mapa
 * Validar movimentos
 * Detectar eventos (monstro, loja, vazio)
 */
public class GameMap {
    private final TileType[][] map = {
            {EMPTY, MONSTER, SHOP, EMPTY, MONSTER, EMPTY},
            {EMPTY, MONSTER, EMPTY, MONSTER, EMPTY, LOOT},
            {MONSTER, LOOT, EMPTY, NPC, EMPTY, MONSTER},
            {EMPTY, MONSTER, MONSTER, EMPTY, MONSTER, EMPTY},
            {MONSTER, EMPTY, LOOT, MONSTER, EMPTY, MONSTER},
            {EMPTY, NPC, EMPTY, MONSTER, NPC, BOSS}
    };

    public int getWidth() {
        return map[0].length;
    }

    public int getHeight() {
        return map.length;
    }

    public TileType getTile(int x, int y) {
        return map[y][x];
    }

    public void printMap(int playerX, int playerY) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (x == playerX && y == playerY) {
                    System.out.print("\uD83E\uDDDD ");
                } else {
                    System.out.print(
                            switch (map[y][x]) {
                                case EMPTY -> "⬜ ";
                                case MONSTER -> "👹 ";
                                case LOOT -> "🎁 ";
                                case NPC -> "🧙 ";
                                case SHOP -> "🏪 ";
                                case BOSS -> "👑 ";
                            }
                    );
                }
            }
            System.out.println();
        }
    }

    public void clearTileIfConsumable(int x, int y) {
        if (map[y][x] == MONSTER || map[y][x] == LOOT) {
            map[y][x] = EMPTY;
        }
    }
}
