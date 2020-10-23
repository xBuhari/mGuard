package me.xBuhari.MGuard.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.xBuhari.MGuard.Veriables;

public class JoinAndQuit implements Listener {

	private JavaPlugin api;
	
	public JoinAndQuit(JavaPlugin plugin) {
		this.api = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (Veriables.authmemod) {
			return;
		}
		if (Veriables.MGList.contains(e.getPlayer().getName())) {
			for (int i = 0 ; i < 4 ; i++) {
				e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', api.getConfig().getString("dil.girismesaj")));
			}
			if (!Veriables.freezelist.contains(e.getPlayer())) {
				Veriables.freezelist.add(e.getPlayer());
			}
		}
		if (Veriables.freezelist.contains(e.getPlayer())) {
			api.getServer().getScheduler().scheduleSyncDelayedTask(api, new Runnable() { 
				public void run() { 
					if (Veriables.freezelist.contains(e.getPlayer())) {
						e.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&' , api.getConfig().getString("ayarlar.kickmesaji")));
					}
				} 
			}, 20 * api.getConfig().getInt("ayarlar.kicksuresi"));
		}
	} 
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (Veriables.freezelist.contains(e.getPlayer())) {
			Veriables.freezelist.remove(e.getPlayer());
		}
	}
}
