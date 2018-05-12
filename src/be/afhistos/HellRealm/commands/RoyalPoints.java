package be.afhistos.HellRealm.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class RoyalPoints implements org.bukkit.command.CommandExecutor
{
  public RoyalPoints() {}
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((cmd.getName().equalsIgnoreCase("royalpoints")) && 
      ((sender instanceof Player))) {
      Player p = (Player)sender;
      if ((p.hasPermission("hellrealm.rp")) && 
        (args.length > 0)) {
        File playerdataYml = new File("plugins/HellRealm/playerdata.yml");
        FileConfiguration pdC = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(playerdataYml);
        if (args[0].equalsIgnoreCase("give"))
        {
          @SuppressWarnings("deprecation")
		Player cible = p.getServer().getPlayer(args[1]);
          if (cible != null) {
            int amount = Integer.valueOf(args[2]).intValue();
            if (amount != 0) {
              int current = pdC.getInt("data." + args[1] + ".royalpoints");
              pdC.set("data." + args[1] + ".royalpoints", Integer.valueOf(current + amount));
              try {
                pdC.save(playerdataYml);
              } catch (IOException e) {
                e.printStackTrace();
              }
              int total = pdC.getInt("data." + args[1] + ".royalpoints");
              p.sendMessage("§e+ §5" + args[2] + " §eRoyalPoints ajoutés au joueur §5" + args[1]);
              p.sendMessage("§eil à  §5" + total + " §eRoyalPoints au total !");
              return true;
            }
            
            p.sendMessage("§4Erreur:§c Montant invalide !");
            return true;
          }
          

          p.sendMessage("§4Erreur:§c Joueur inconnu !");
          return true;
        }
        
        if (args[0].equalsIgnoreCase("take"))
        {
          @SuppressWarnings("deprecation")
		Player cible = p.getServer().getPlayer(args[1]);
          if (cible != null) {
            int amount = Integer.valueOf(args[2]).intValue();
            if (amount != 0) {
              int current = pdC.getInt("data." + args[1] + ".royalpoints");
              pdC.set("data." + args[1] + ".royalpoints", Integer.valueOf(current - amount));
              try {
                pdC.save(playerdataYml);
              } catch (IOException e) {
                e.printStackTrace();
              }
              int total = pdC.getInt("data." + args[1] + ".royalpoints");
              p.sendMessage("§5" + args[2] + " §eRoyalPoints retirés au joueur §5" + args[1]);
              p.sendMessage("§eil à  §5" + total + " §eRoyalPoints au total !");
              if (total < 0) {
                pdC.set("data." + args[1] + ".royalpoints", Integer.valueOf(0));
                try {
                  pdC.save(playerdataYml);
                } catch (IOException e) {
                  e.printStackTrace();
                }
                p.sendMessage("§eIl est à  moins de §50 RoyalPoints§e ! On le remet à  Zéro ;)");
              }
              return true;
            }
            

            p.sendMessage("§4Erreur:§c Montant invalide !");
            return true;
          }
          

          p.sendMessage("§4Erreur:§c Joueur inconnu !");
          return true;
        }
        

        if ((args[0].equalsIgnoreCase("help")) || (args[0].equalsIgnoreCase("aide")) || (args[0].equalsIgnoreCase(null))) {
          p.sendMessage("§7---------");
          p.sendMessage("§6Aide:");
          p.sendMessage("§6  -HellRealm, Gestion des RoyalPoints");
          p.sendMessage("§e     /royalpoints add/take <joueur> <montant>");
          p.sendMessage("§3     + d'informations » §b/help royalpoints");
          p.sendMessage("§d     Plugin by afhistos, Chef du nether");
          p.sendMessage("     §mUn peu de pub ne fait pas de mal");
          return true;
        }
      }
    }
    

    return false;
  }
}
