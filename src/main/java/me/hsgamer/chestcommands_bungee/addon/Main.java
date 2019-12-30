package me.hsgamer.chestcommands_bungee.addon;

import com.gmail.filoghost.chestcommands.api.Addon;
import com.gmail.filoghost.chestcommands.serializer.CommandSerializer;
import me.hsgamer.chestcommands_bungee.addon.action.AlertIconCommand;
import me.hsgamer.chestcommands_bungee.addon.action.BungeeCommandIconCommand;
import me.hsgamer.chestcommands_bungee.addon.action.SendIconCommand;
import org.bukkit.Bukkit;

public class Main extends Addon {
  private static BungeeUtils utils;

  public Main() {
    super("ChestCommands-Bungee");
  }

  public static BungeeUtils getUtils() {
    return utils;
  }

  @Override
  public void onEnable() {
    utils = new BungeeUtils(getPlugin());
    if (!Bukkit.getMessenger().isOutgoingChannelRegistered(getPlugin(), "BungeeCord")) {
      Bukkit.getMessenger().registerOutgoingPluginChannel(getPlugin(), "BungeeCord");
    }
    if (!Bukkit.getMessenger().isOutgoingChannelRegistered(getPlugin(), "ChestCommands")) {
      Bukkit.getMessenger().registerOutgoingPluginChannel(getPlugin(), "ChestCommands");
    }

    CommandSerializer.register("server:?", SendIconCommand.class);
    CommandSerializer.register("alert:", AlertIconCommand.class);
    CommandSerializer.register("bungee:", BungeeCommandIconCommand.class);
  }

  @Override
  public void onDisable() {
    utils = null;
    if (Bukkit.getMessenger().isOutgoingChannelRegistered(getPlugin(), "BungeeCord")) {
      Bukkit.getMessenger().unregisterOutgoingPluginChannel(getPlugin(), "BungeeCord");
    }
    if (Bukkit.getMessenger().isOutgoingChannelRegistered(getPlugin(), "ChestCommands")) {
      Bukkit.getMessenger().unregisterOutgoingPluginChannel(getPlugin(), "ChestCommands");
    }
  }
}
