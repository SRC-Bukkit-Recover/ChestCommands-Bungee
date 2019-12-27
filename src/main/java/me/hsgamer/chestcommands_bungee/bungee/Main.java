package me.hsgamer.chestcommands_bungee.bungee;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class Main extends Plugin implements Listener {
  @Override
  public void onEnable() {
    this.getProxy().getPluginManager().registerListener(this, this);
    this.getProxy().registerChannel("ChestCommands");
  }

  @EventHandler
  public void onPluginMessage(PluginMessageEvent e) {
    if (e.getTag().equalsIgnoreCase("ChestCommands")) {
      DataInputStream in = new DataInputStream(new ByteArrayInputStream(e.getData()));
      try {
        String subChannel = in.readUTF();
        if (subChannel.equals("Command")) {
          String command = in.readUTF();
          getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), command);
        }
      } catch (IOException ex) {
        getLogger().log(Level.WARNING, "Error when dispatching command", ex);
      }
    }
  }
}
