package me.technotalks.ridermc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
/*import org.bukkit.plugin.PluginDescriptionFile;*/
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Main extends JavaPlugin implements Listener {

    public static Main instance = null;
    //main handler
    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e) {
        if (e.getHand()!=EquipmentSlot.HAND){
            return;
        }
        else{
            Player player = e.getPlayer();
            boolean permission = this.getConfig().getBoolean("usePermission");
            boolean requireSaddle= this.getConfig().getBoolean("requireSaddle");
            boolean rideMessage = this.getConfig().getBoolean("sendRideMessage");
            if (permission) {
                if (player.hasPermission("rider.rideall")) {
                    if (requireSaddle) {
                        if (!player.hasPermission("rider.bypass_saddle")) {
                            if(player.getInventory().getItemInMainHand().getType() == Material.SADDLE) {
                                if(rideMessage) {
                                    player.sendMessage(ChatColor.GOLD+"Enjoy your ride!");
                                }
                                e.getRightClicked().addPassenger(e.getPlayer());
                            }else {
                                player.sendMessage(ChatColor.RED+"You must have a saddle to ride!");
                            }
                        }else {
                            if(rideMessage) {
                                player.sendMessage(ChatColor.GOLD+"Enjoy your ride!");
                            }
                            e.getRightClicked().addPassenger(e.getPlayer());
                        }
                    }else {
                        if(rideMessage) {
                            player.sendMessage(ChatColor.GOLD+"Enjoy your ride!");
                        }
                        e.getRightClicked().addPassenger(e.getPlayer());
                    }

                }else {
                    player.sendMessage(ChatColor.RED+"You don't have the required Permission! rider.rideall");
                    player.sendMessage(ChatColor.RED+"Contact a Admin if you think this is a mistake!");
                }
            }else {
                if (requireSaddle) {
                    if(player.getInventory().getItemInMainHand().getType() == Material.SADDLE) {
                        if(rideMessage) {
                            player.sendMessage(ChatColor.GOLD+"Enjoy your ride!");
                        }
                        e.getRightClicked().addPassenger(e.getPlayer());
                    }else{
                        player.sendMessage(ChatColor.RED+"You must have a saddle to ride!");
                        /*player.sendMessage("Equipment "+e.getRightClicked());*/
                    }
                }else {
                    if(rideMessage) {
                        player.sendMessage(ChatColor.GOLD+"Enjoy your ride!");
                    }
                    e.getRightClicked().addPassenger(e.getPlayer());
                }
            }

        }

    }

    @Override
    public void onEnable() {
        UpdateChecker updateChecker = new UpdateChecker();
        try {
            if (updateChecker.getLatestVersion().equals(getDescription().getVersion())) {
                getLogger().info(ChatColor.GOLD+"You are running the latest version of Rider!");
            } else {
                getLogger().info(ChatColor.RED+"You are running version "+getDescription().getVersion()+"! The latest version is "+updateChecker.getLatestVersion()+"! Please update!");
                getLogger().info(ChatColor.GOLD+"If the above message states that you are using a version 'ahead' please ignore it.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info(ChatColor.GOLD+"Rider has loaded");
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {

        getLogger().info(ChatColor.GOLD + "Rider has unloaded!");

    }
}
