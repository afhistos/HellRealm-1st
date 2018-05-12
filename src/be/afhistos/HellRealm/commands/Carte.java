package be.afhistos.HellRealm.commands;

import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Carte implements org.bukkit.command.CommandExecutor{
	
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    if (args.length == 0) {
	      File playerdataYml = new File("plugins/HellRealm/playerdata.yml");
	      FileConfiguration pdC = YamlConfiguration.loadConfiguration(playerdataYml);
	      if ((sender instanceof org.bukkit.entity.Player)) {
	        String player = sender.getName();
	        String grade = pdC.getString("data." + sender.getName() + ".grade").replace(sender.getName(), "");
	        int rpoints = pdC.getInt("data." + sender.getName() + ".royalpoints");
	        sender.sendMessage("ß8---------------");
	        sender.sendMessage("ßePseudo: ßb" + player);
	        sender.sendMessage("ßeRang: ßb" + grade);
	        sender.sendMessage("ßeRoyal ß6Points: ßb" + rpoints);
	        sender.sendMessage("ß8---------------");
	        return true;
	      }
	      
	      sender.sendMessage("owNoooooooo... Tu n'es pas un diable :p");
	      return false;
	    }
	    
	
	    File playerdataYml = new File("plugins/HellRealm/playerdata.yml");
	    FileConfiguration pdC = YamlConfiguration.loadConfiguration(playerdataYml);
	    if (pdC.contains("data." + args[0] + ".royalpoints")) {
	      String player = args[0];
	      String grade = pdC.getString("data." + args[0] + ".grade").replace(player, "");
	      int rpoints = pdC.getInt("data." + args[0] + ".royalpoints");
	      sender.sendMessage("ß8---------------");
	      sender.sendMessage("ßePseudo: ßb" + player);
	      sender.sendMessage("ßeRang: ßb" + grade);
	      sender.sendMessage("ßeRoyal ß6Points: ßb" + rpoints);
	      sender.sendMessage("ß8---------------");
	      return true;
	    }
	    
	    sender.sendMessage("ß4Ce joueur n'est pas dans notre Base de Donn√©es !");
	    
	
	    return false;
	  }
	}
