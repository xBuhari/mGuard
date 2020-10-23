package me.xBuhari.MGuard.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.xephi.authme.events.LoginEvent;
import me.xBuhari.MGuard.Veriables;

public class Authme implements Listener {
	
	private JavaPlugin api;
	
	public Authme(JavaPlugin plugin) {
		this.api = plugin;
	}
	
	@EventHandler
	public void onAuthmeLogin(LoginEvent e) {
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

}
