package me.xBuhari.MGuard.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.xBuhari.MGuard.MGuardMain;
import me.xBuhari.MGuard.Sifreleme;
import me.xBuhari.MGuard.Veriables;

public class Freeze implements Listener {

	private JavaPlugin api;
	
	private Sifreleme sifreleme;
	
	
	public Freeze(JavaPlugin plugin) {
		this.api = plugin;
		this.sifreleme = MGuardMain.getSifreleme();
	}
	
	//Bug Fixed, Poyraz Ama Ghotten!

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if (Veriables.freezelist.contains(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', api.getConfig().getString("dil.girismesaj")));
		}
	}
	
	@EventHandler
	public void onDrop(PlayerPickupItemEvent e) {
		if (Veriables.freezelist.contains(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', api.getConfig().getString("dil.girismesaj")));
		}
	}
	
	@EventHandler
	public void onInvOpen(InventoryOpenEvent e) {
		if (Veriables.freezelist.contains(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', api.getConfig().getString("dil.girismesaj")));
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (Veriables.freezelist.contains(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', api.getConfig().getString("dil.girismesaj")));
		}
	}
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (Veriables.freezelist.contains(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', api.getConfig().getString("dil.girismesaj")));
		}
	}
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (Veriables.freezelist.contains(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', api.getConfig().getString("dil.girismesaj")));
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if (Veriables.freezelist.contains(e.getPlayer())) {
			e.setCancelled(true);
			if (!e.getMessage().contains(" ")) {	
				e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', api.getConfig().getString("dil.girismesaj")));
				return;
			}
			String cmd = e.getMessage().split(" ")[0];
			String arg1 = e.getMessage().split(" ")[1];
			if (!cmd.equalsIgnoreCase("/" + api.getConfig().getString("ayarlar.kullankomut"))) {
				e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', api.getConfig().getString("dil.girismesaj")));
			}
			else {
				if (this.sifreleme.code(arg1).equalsIgnoreCase(MGuardMain.sifre)) {
					Veriables.freezelist.remove(e.getPlayer());
					e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', api.getConfig().getString("dil.dogrulandi")));
				}
				else {
					e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', api.getConfig().getString("dil.sifreyanlis")));
				}
			}
		}
	}
}
