package me.technotalks.ridermc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        boolean permission = this.getConfig().getBoolean("usePermission");
        boolean requireSaddle= this.getConfig().getBoolean("requireSaddle");
        boolean rideMessage = this.getConfig().getBoolean("sendRideMessage");
        if (permission) {
            if (player.hasPermission("rider.rideall")) {
                if (requireSaddle==true) {
                    if (!player.hasPermission("rider.bypass_saddle")) {
                        if(player.getItemInHand().getType() == Material.SADDLE) {
                            if(rideMessage==true) {
                                player.sendMessage(ChatColor.GOLD+"Enjoy your ride!");
                            }
                            e.getRightClicked().setPassenger(e.getPlayer());
                        }else {
                            player.sendMessage(ChatColor.RED+"You must have a saddle to ride!");
                        }
                    }else {
                        if(rideMessage==true) {
                            player.sendMessage(ChatColor.GOLD+"Enjoy your ride!");
                        }
                        e.getRightClicked().setPassenger(e.getPlayer());
                    }
                }else {
                    if(rideMessage==true) {
                        player.sendMessage(ChatColor.GOLD+"Enjoy your ride!");
                    }
                    e.getRightClicked().setPassenger(e.getPlayer());
                }

            }else {
                player.sendMessage(ChatColor.RED+"You don't have the required Permission! rider.rideall");
                player.sendMessage(ChatColor.RED+"Contact a Admin if you think this is a mistake!");
            }
        }else {
            if (requireSaddle==true) {
                if(player.getItemInHand().getType() == Material.SADDLE) {
                    if(rideMessage==true) {
                        player.sendMessage(ChatColor.GOLD+"Enjoy your ride!");
                    }
                    e.getRightClicked().setPassenger(e.getPlayer());
                }else{
                    player.sendMessage(ChatColor.RED+"You must have a saddle to ride!");
                }
            }else {
                if(rideMessage==true) {
                    player.sendMessage(ChatColor.GOLD+"Enjoy your ride!");
                }
                e.getRightClicked().setPassenger(e.getPlayer());
            }
        }


    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        System.out.println(ChatColor.GOLD+"Rider has loaded");
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {

        System.out.println(ChatColor.GOLD+"Rider has unloaded!");

    }
}
