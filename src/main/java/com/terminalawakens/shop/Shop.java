package com.terminalawakens.shop;

import com.terminalawakens.character.Character;
import com.terminalawakens.items.Item;
import java.util.List;

public class Shop {

    private List<Item> availableItems; // Lista de itens à venda
    private List<Integer> itemPrices;  // Preço correspondente

    public Shop(List<Item> availableItems, List<Integer> itemPrices) {
        this.availableItems = availableItems;
        this.itemPrices = itemPrices;
    }

    public void showItems() {
        System.out.println("\n=== Welcome to the Shop ===");
        for (int i = 0; i < availableItems.size(); i++) {
            System.out.println((i + 1) + " - " + availableItems.get(i).getName()
                    + " | Price: " + itemPrices.get(i) + " gold");
        }
        System.out.println("0 - Exit shop");
    }

    public void buyItem(Character player, int choice) {
        if (choice < 1 || choice > availableItems.size()) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        Item item = availableItems.get(choice - 1);
        int price = itemPrices.get(choice - 1);

        if (player.getGold() >= price) {
            player.addItem(item);
            player.addGold(-price);
            System.out.println("✅ " + player.getName() + " bought " + item.getName() + " for " + price + " gold.");
        } else {
            System.out.println("❌ Not enough gold.");
        }
    }
}

