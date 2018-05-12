package be.afhistos.HellRealm;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import be.afhistos.HellRealm.commands.Grade;
import be.afhistos.HellRealm.commands.RoyalPoints;

public class Main extends org.bukkit.plugin.java.JavaPlugin implements Listener
{
  public Main() {}
  
  File playerdataYml = new File(getDataFolder() + "/playerdata.yml");
  FileConfiguration pdC = YamlConfiguration.loadConfiguration(playerdataYml);
  
  public void saveYml(FileConfiguration ymlConfig, File ymlFile) {
    try { ymlConfig.save(ymlFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void onEnable() {
    getLogger().warning("Plugin started!");
    Listener l = new EventListener();
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(l, this);
    CommandExecutor rankExecutor = new be.afhistos.HellRealm.commands.Carte();
    CommandExecutor rp = new RoyalPoints();
    CommandExecutor sg = new Grade();
    getCommand("carte").setExecutor(rankExecutor);
    getCommand("royalpoints").setExecutor(rp);
    getCommand("setgrade").setExecutor(sg);
    getLogger().info("Verification of files. . .");
    File playerdataYml = new File(getDataFolder() + "/playerdata.yml");
    FileConfiguration playerdataConfig = YamlConfiguration.loadConfiguration(playerdataYml);
    saveYml(playerdataConfig, playerdataYml);
    
    getLogger().info("File are verified !");
    getLogger().info("plugin is ready to use !");
  }
}