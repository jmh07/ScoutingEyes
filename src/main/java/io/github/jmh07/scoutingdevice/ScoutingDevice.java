package io.github.jmh07.scoutingdevice;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class ScoutingDevice extends JavaPlugin implements CommandExecutor, Listener {

    final private String SCOUT_DEVICE_NAME = ChatColor.GOLD + "Eye of the Lord";

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Scouting Device plugin has started!");
        this.getCommand("scoutdevice").setExecutor(this::onCommand);
        this.getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack scoutingDevice = createScoutingDevice();
            player.getInventory().addItem(scoutingDevice);
        }

        return true;
    }

    @EventHandler()
    public void onUse(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(SCOUT_DEVICE_NAME)){
                player.sendMessage("Hello World!");
                getLogger().info("Eye of the Lord was used!");
            }
        }

    }

    private ItemStack createScoutingDevice() {
        ItemStack scoutDevice = new ItemStack(Material.ENDER_EYE);

        ItemMeta scoutDeviceMeta = scoutDevice.getItemMeta();

        scoutDeviceMeta.setDisplayName(SCOUT_DEVICE_NAME);

        ArrayList<String> scoutDeviceLore = new ArrayList<>();

        scoutDeviceLore.add(ChatColor.BLUE + "The Lord blessed this Eye with the power of finding deserters.");

        scoutDeviceMeta.setLore(scoutDeviceLore);

        scoutDevice.setItemMeta(scoutDeviceMeta);

        return scoutDevice;
    }

}
