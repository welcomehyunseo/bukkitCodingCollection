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

            ItemStack item1 = new ItemStack(Material.BEEF, 1);
            vault.setItem(3, item1);
            vault.addItem(item1);
            vault.clear();

            ItemStack item2 = new ItemStack(Material.ACACIA_FENCE);

            item2.addUnsafeEnchantment(Enchantment.DURABILITY, 100);
            ItemMeta meta = item2.getItemMeta();
            meta.setUnbreakable(true);
            meta.setDisplayName(ChatColor.AQUA + "Item name");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "some lore");
            lore.add(ChatColor.DARK_PURPLE + "some more lore heehehehe");
            meta.setLore(lore);
            item2.setItemMeta(meta);


            ItemStack[] items = {item1, item2};
            vault.setContents(items);

            ItemStack[] items_from_inventory = vault.getContents();

            player.openInventory(vault);
        }

        return true;
    }
}
