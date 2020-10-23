package me.xBuhari.MGuard;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Veriables {

	public static ArrayList<String> MGList = new ArrayList<String>();
	public static ArrayList<Player> freezelist = new ArrayList<Player>();
	
	public static Boolean authmemod = false;

	public void load() {
		if (new File("plugins" + File.separator + "MGuard" + File.separator + "config.yml").exists()) {
			for (String _data : MGuardMain.MGuard().getConfig().getStringList("guardlist")) {
				MGList.add(_data);
			}
		}
		else {
			MGuardMain.MGuard().saveDefaultConfig();
			for (OfflinePlayer _data : MGuardMain.MGuard().getServer().getOperators()) {
				MGList.add(_data.getName());
			}
			MGuardMain.MGuard().getConfig().set("guardlist", MGList);
			MGuardMain.MGuard().saveConfig();
		}
		MGuardMain.MGuard().getLogger().info("Koruma listesinde " + MGList.size() + " kisi var.");
	}
}
