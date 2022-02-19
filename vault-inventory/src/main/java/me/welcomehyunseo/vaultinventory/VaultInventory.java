package me.welcomehyunseo.vaultinventory;

import org.bukkit.plugin.java.JavaPlugin;

public final class VaultInventory extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("vault").setExecutor(new CommandKit());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
