package me.hsgamer.chestcommands_bungee.addon;

import com.gmail.filoghost.chestcommands.util.FormatUtils;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeUtils {
  private JavaPlugin plugin;

  public BungeeUtils(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  public void connect(Player player, String server) {
    try {
      if (server.length() == 0) {
        player.sendMessage("§cTarget server was \"\" (empty string) cannot connect to it.");
        return;
      }

      ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(byteArray);

      out.writeUTF("Connect");
      out.writeUTF(server); // Target Server

      sendToBungee(player, "BungeeCord", byteArray);
    } catch (IOException ex) {
      player.sendMessage(ChatColor.RED
          + "An unexpected exception has occurred. Please notify the server's staff about this. (They should look at the console).");
      plugin.getLogger().log(Level.WARNING,
          "Could not connect \"" + player.getName() + "\" to the server \"" + server + "\".", ex);
    }
  }

  public void alert(Player player, String message) {
    try {
      ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(byteArray);

      out.writeUTF("Message");
      out.writeUTF("ALL");
      out.writeUTF(FormatUtils.addColors(message));

      sendToBungee(player, "BungeeCord", byteArray);
    } catch (IOException ex) {
      player.sendMessage(ChatColor.RED
          + "An unexpected exception has occurred. Please notify the server's staff about this. (They should look at the console).");
      plugin.getLogger().log(Level.WARNING,
          "Could not send alert", ex);
    }
  }

  public void sendCommand(Player player, String command) {
    try {
      ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(byteArray);

      out.writeUTF("Command");
      out.writeUTF(command);

      sendToBungee(player, "ChestCommands", byteArray);
    } catch (IOException ex) {
      player.sendMessage(ChatColor.RED
          + "An unexpected exception has occurred. Please notify the server's staff about this. (They should look at the console).");
      plugin.getLogger().log(Level.WARNING,
          "Could not send command", ex);
    }
  }

  private void sendToBungee(Player player, String channel, ByteArrayOutputStream byteArray) {
    player.sendPluginMessage(plugin, channel, byteArray.toByteArray());
  }
}
