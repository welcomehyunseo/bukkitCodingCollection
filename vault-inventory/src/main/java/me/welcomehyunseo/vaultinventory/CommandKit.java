package me.welcomehyunseo.vaultinventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CommandKit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            //Creating inventories. 1: Name of player inventory is linked to 2: How many slots in the inventory 3: Title of inventory
            Inventory vault = Bukkit.createInventory(player, 9, "Your Vault");


            ItemStack fristItem = firstItem();
            ItemStack item2 = firstItem();
            ItemStack item3 = firstItem();


            ItemStack[] items = { fristItem, item2, item3};
            vault.setContents(items);

            ItemStack[] items_from_inventory = vault.getContents();

            player.openInventory(vault);
        }

        return true;
    }

    public ItemStack firstItem() {
        ItemStack item = new ItemStack(Material.ACACIA_FENCE);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 100);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName(ChatColor.AQUA + "Item name");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "some lore");
        lore.add(ChatColor.DARK_PURPLE + "some more lore heehehehe");
        meta.addEnchant(Enchantment.DIG_SPEED, 10, true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

}
