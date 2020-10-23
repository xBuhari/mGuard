package me.xBuhari.MGuard;

import org.bukkit.plugin.java.JavaPlugin;

import me.xBuhari.MGuard.Sifreleme.Turler;
import me.xBuhari.MGuard.Commands.mguard;
import me.xBuhari.MGuard.Listeners.Authme;
import me.xBuhari.MGuard.Listeners.Freeze;
import me.xBuhari.MGuard.Listeners.JoinAndQuit;

public class MGuardMain extends JavaPlugin {
	
	private static JavaPlugin instance;
	
	public static String sifre;
	
	private static Sifreleme Sifreleme;
	
	public void onEnable() {
		instance = this;
		if (getServer().getPluginManager().getPlugin("AuthMe") != null) {
			Veriables.authmemod = true;
			getLogger().info("AuthMe bulundu! AuthMe modu aktif.");
			getServer().getPluginManager().registerEvents(new Authme(this), this);
		}
		this.reg();
		sifre = getConfig().getString("ayarlar.sifre");
		getLogger().info("Sifre " + sifre + " olarak belirlendi");
	}
	
	public static JavaPlugin MGuard() {
		return instance;
	}
	
	public static Sifreleme getSifreleme() {
		return Sifreleme;
	}
	
	private void reg() {
		new Veriables().load();
		sifrelemeBelirle(getConfig().getString("ayarlar.sifreleme"));
		getServer().getPluginManager().registerEvents(new JoinAndQuit(this), this);
		getServer().getPluginManager().registerEvents(new Freeze(this), this);
		getCommand("mguard").setExecutor(new mguard(this));
	}
	
	private void sifrelemeBelirle(String sifreleme) {
		Turler t = null;
		if (sifreleme.equalsIgnoreCase("MD5")) {
			t = Turler.MD5;
		}
		if (sifreleme.equalsIgnoreCase("SHA256")) {
			t = Turler.SHA256;
		}
		if (sifreleme.equalsIgnoreCase("SHA1")) {
			t = Turler.SHA1;
		}
		if (sifreleme.equalsIgnoreCase("BASE64")) {
			t = Turler.Base64;
		}
		getLogger().info("Sifreleme turu " + t.name() + " olarak belirlendi");
		Sifreleme = new Sifreleme(t);
	}
	
}
