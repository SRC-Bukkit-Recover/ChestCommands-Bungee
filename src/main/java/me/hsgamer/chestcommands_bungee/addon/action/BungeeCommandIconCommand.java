package me.hsgamer.chestcommands_bungee.addon.action;

import co.aikar.taskchain.TaskChain;
import com.gmail.filoghost.chestcommands.api.IconCommand;
import me.hsgamer.chestcommands_bungee.addon.Main;
import org.bukkit.entity.Player;

public class BungeeCommandIconCommand extends IconCommand {

  public BungeeCommandIconCommand(String command) {
    super(command);
  }

  @Override
  public void addToTaskChain(Player player, TaskChain<?> taskChain) {
    taskChain.sync(() -> Main.getUtils().sendCommand(player, getParsedCommand(player)));
  }
}
