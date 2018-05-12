package be.afhistos.HellRealm.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Grade implements org.bukkit.command.CommandExecutor{

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((cmd.getName().equalsIgnoreCase("setgrade")) && 
      ((sender instanceof Player))) {
      Player p = (Player)sender;
      if (args.length >= 1) {
        File playerdataYml = new File("plugins/HellRealm/playerdata.yml");
        FileConfiguration pdC = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(playerdataYml);
        if (args[0].equalsIgnoreCase("aide")) {
          p.sendMessage("§7---------");
          p.sendMessage("§6Aide:");
          p.sendMessage("§6  -HellRealm, Gestion des Grades");
          p.sendMessage("§e     /setgrade  <joueur> <grade>");
          p.sendMessage("§3     Le grade correspond à  son pseudo affiché dans le chat");
          p.sendMessage("§3     Le placeholder §b%p%§3 est remplacé par le joueur.");
          p.sendMessage("§d     Plugin by afhistos, Chef du nether");
          p.sendMessage("     §mUn peu de pub ne fait pas de mal");
        }
        else
        {
          @SuppressWarnings("deprecation")
		Player cible = p.getServer().getPlayer(args[0]);
          if (cible != null) {
            String lol = args[1].replace("%p%", " " + args[0]).replace("&", "§");
            pdC.set("data." + args[0] + ".grade", lol);
            try {
              pdC.save(playerdataYml);
            } catch (IOException e) {
              e.printStackTrace();
            }
            p.sendMessage("§e Le grade §f" + lol + " §eà  été attribué au joueur §5" + args[0]);
            
            @SuppressWarnings("deprecation")
			Player TP = p.getServer().getPlayer(args[0]);
            TP.setDisplayName(lol);
            return true;
          }
          
          p.sendMessage("§4Erreur: §cJoueur inconnu !");
        }
      }
      else
      {
        p.sendMessage("§4Erreur: §c Arguments invalides");
        p.sendMessage("§6Faites /setgrade aide pour obtenir de l'aide");
      }
    }
    
    return false;
  }
}
