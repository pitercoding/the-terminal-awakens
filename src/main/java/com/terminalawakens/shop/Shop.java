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
        for (int i = 0; i < availableItems.size(); i++) {
            System.out.println((i + 1) + " - " + availableItems.get(i).getName()
                    + " | Price: " + itemPrices.get(i) + " gold");
        }
        System.out.println("0 - Exit shop");
    }

    public void buyItem(Character player, int choice, int quantity) {

        if (choice < 1 || choice > availableItems.size()) {
            System.out.println("❌ Invalid item choice.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("❌ Quantity must be greater than zero.");
            return;
        }

        Item item = availableItems.get(choice - 1);
        int unitPrice = itemPrices.get(choice - 1);
        int totalPrice = unitPrice * quantity;

        if (player.getGold() < totalPrice) {
            System.out.println("❌ Not enough gold.");
            return;
        }

        for (int i = 0; i < quantity; i++) {
            player.addItem(item);
        }

        player.addGold(-totalPrice);

        System.out.println("✅ Bought " + quantity + "x " + item.getName()
                + " for " + totalPrice + " gold.");
    }
}

