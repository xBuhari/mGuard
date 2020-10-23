package me.xBuhari.MGuard.Commands;

import java.util.Base64;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.xBuhari.MGuard.MGuardMain;
import me.xBuhari.MGuard.Veriables;

public class mguard implements CommandExecutor {
	
	private MGuardMain api;
	
	public mguard(MGuardMain plugin) {
		this.api = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if (sender instanceof Player && cmd.getName().equalsIgnoreCase("mguard")) {
			Player p = (Player) sender;
			if (!sender.isOp()) {
				snd(p , "&7| &3mGuard v" + api.getDescription().getVersion());
				snd(p , "&7| &aBunun için yetkin yok!");
				snd(p , "&7| &2" + new String(Base64.getDecoder().decode(gt().getBytes())));
				return false;
			}
			if (args.length == 0) {
				snd(p , "&7| &3mGuard v" + api.getDescription().getVersion());
				snd(p , "&7| &aYardım almak için &6/mguard help");
				snd(p , "&7| &2" + new String(Base64.getDecoder().decode(gt().getBytes())));
			}
			else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("yardim") || args[0].equalsIgnoreCase("yard�m")) {
					snd(p , "&3mGuard v" + api.getDescription().getVersion());
					snd(p , "&7| &a/mguard liste &8� &cKoruma listesini gösterir.");
					snd(p , "&7| &a/mguard sifre <sifre> &8� &cŞifreyi değiştirir.");
					snd(p , "&7| &a/mguard ekle <oyuncu> &8� &cKoruma listesine oyuncu ekler.");
					snd(p , "&7| &a/mguard sil <oyuncu> &8� &cKoruma listesinden oyuncu siler.");
					snd(p , "&7| &a/mguard temizle &8� &cKoruma listesini temizle.");
					snd(p , "&7| &a/mguard reload &8� &cEklentinin config.yml dosyasını yeniden yükler.");
				}
				else if (args[0].equalsIgnoreCase("liste") || args[0].equalsIgnoreCase("list")) {
					snd(p , "&7| &3mGuard v" + api.getDescription().getVersion());
					snd(p , "&7| &6Liste:");
					for (String _s : Veriables.MGList) {
						snd(p , "&7- &9" + _s);
					}
				}
				else if (args[0].equalsIgnoreCase("temizle") || args[0].equalsIgnoreCase("clear")) {
					snd(p , "&7| &3mGuard v" + api.getDescription().getVersion());
					snd(p , "&7| &6Liste temizlendi.");
					Veriables.MGList.clear();
					api.getConfig().set("guardlist", Veriables.MGList);
					api.saveConfig();
				}
				else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("restart")) {
					snd(p , "&7| &3mGuard v" + api.getDescription().getVersion());
					snd(p , "&7| &6Eklenti yeniden başlatıldı.");
					api.reloadConfig();
				}
				else {
					snd(p , "&7| &3mGuard v" + api.getDescription().getVersion());
					snd(p , "&7| &aYardım almak için &6/mguard help");
					snd(p , "&7| &2" + new String(Base64.getDecoder().decode(gt().getBytes())));
				}
			}
			else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("ekle") || args[0].equalsIgnoreCase("add")) {
					if (Veriables.MGList.contains(args[1])) {
						snd(p , "&7| &cBu kişi listede zaten var.");
					}		
					else {
						Veriables.MGList.add(args[1]);
						snd(p , "&7| &aListeye eklendi.");
						api.getConfig().set("guardlist", Veriables.MGList);
						api.saveConfig();
					}
				}
				else if (args[0].equalsIgnoreCase("sil") || args[0].equalsIgnoreCase("remove")) {
					if (!Veriables.MGList.contains(args[1])) {
						snd(p , "&7| &cBu kişi listede zaten yok.");
					}
					else {
						Veriables.MGList.remove(args[1]);
						snd(p , "&7| &aListeden çıkarıldı.");
						api.getConfig().set("guardlist", Veriables.MGList);
						api.saveConfig();
					}
				}
				else if (args[0].equalsIgnoreCase("sifre")) {
					if (Veriables.MGList.contains(p.getName()) && p.isOp()) {
						snd(p , "&7| &cŞifre belirlendi!");
						api.getConfig().set("ayarlar.sifre", MGuardMain.getSifreleme().code(args[1]));
						api.saveConfig();
						MGuardMain.sifre = MGuardMain.getSifreleme().code(args[1]);
					}
					else {
						snd(p , "&7| &cBunun için hem güvenlik listesinde hem de OP olmalısınız.");
					}
				}
				else {
					snd(p , "&7| &3mGuard v" + api.getDescription().getVersion());
					snd(p , "&7| &aYardım almak için &6/mguard help");
					snd(p , "&7| &2" + new String(Base64.getDecoder().decode(gt().getBytes())));
				}
			}
			else {
				snd(p , "&7| &3mGuard v" + api.getDescription().getVersion());
				snd(p , "&7| &aYardım almak için &6/mguard help");
				snd(p , "&7| &2" + new String(Base64.getDecoder().decode(gt().getBytes())));
			}
		}
		return false;
	}

	private void snd(Player p , String s) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
	}
	private String gt() {
		StringBuilder sb = new StringBuilder();
		sb.append("WW");
		sb.append("Fw");
		sb.append("xL");
		sb.append("FtY" + "8");
		sb.append("SxIHh");
		sb.append("CdWh");
		sb.append("hcml");
		sb.append("QdlA=");
		return sb.toString();
	}
}
