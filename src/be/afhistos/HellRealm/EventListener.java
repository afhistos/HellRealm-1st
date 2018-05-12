package be.afhistos.HellRealm;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventListener implements org.bukkit.event.Listener {
	File playerdataYml = new File("plugins/HellRealm/playerdata.yml");
	FileConfiguration pdC = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(playerdataYml);
  
	@EventHandler
	public void onJoin(PlayerJoinEvent event) { Player p = event.getPlayer();
	    event.setJoinMessage(" ");
	    if (!pdC.isSet("data." + p.getName())) {
	      Bukkit.broadcastMessage("§l[§a+§f§l] §5[Evil Soul] §f" + event.getPlayer().getName() + "§c à rejoint l'enfer !");
	      pdC.createSection("data." + p.getName() + ".royalpoints");
	      pdC.createSection("data." + p.getName() + ".grade");
	      pdC.set("data." + p.getName() + ".royalpoints", Integer.valueOf(0));
	      pdC.set("data." + p.getName() + ".grade", "§5[Evil Soul]§f " + p.getName());
	      String displayname = "§5[Evil Soul]§f " + p.getName();
	      p.setDisplayName(displayname);
	      try {
	        pdC.save(playerdataYml);
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	    else
	    {
	      String dp = pdC.getString("data." + p.getName() + ".grade");
	      p.setDisplayName(dp);
	      Bukkit.broadcastMessage("§l[§a+§f§l] §f" + event.getPlayer().getDisplayName() + "§c à rejoint l'enfer !");
	    }
	  }
	  
	  @EventHandler
	  public void onChat(AsyncPlayerChatEvent event) {
	    event.setCancelled(true);
	    Player p = event.getPlayer();
	    if (p.hasPermission("hellrealm.colors")) {
	      String msg = event.getMessage();
	      Bukkit.broadcastMessage(p.getDisplayName() + " §a» §f" + msg.replace("&", "§"));
	    }
	    else {
	      Bukkit.broadcastMessage(p.getDisplayName() + " §a»§f " + event.getMessage());
	    }
	  }
	  
	  @EventHandler
	  public void onLeft(PlayerQuitEvent event) { event.setQuitMessage("§l[§4-§f§l] §f" + event.getPlayer().getDisplayName() + " §cà quitté le monde des démons !"); }
	  
	  @EventHandler
	  public void Modifer(PlayerCommandPreprocessEvent event) {
	    if (event.getMessage().contains("/hellrealms:")) {
	      if (!event.getPlayer().isOp()) {
	        event.setCancelled(true);
	      }
	      else {
	        event.setCancelled(false);
	      }
	    }
	  }
	  @EventHandler
	  public void onBreak(PlayerItemBreakEvent event) {
		  ItemStack brokei = event.getBrokenItem();
		  ItemMeta brokeiM = brokei.getItemMeta();
		  ItemStack newi = new ItemStack(brokei);
		  newi.setItemMeta(brokeiM);
		  event.getPlayer().getInventory().addItem(newi);
		  event.getPlayer().sendMessage("�8" + brokei + "�7r�par� !");
	  }
	}






