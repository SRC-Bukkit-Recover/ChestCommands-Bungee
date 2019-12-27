package me.hsgamer.chestcommands_bungee.addon.action;

import co.aikar.taskchain.TaskChain;
import com.gmail.filoghost.chestcommands.api.IconCommand;
import me.hsgamer.chestcommands_bungee.addon.Main;
import org.bukkit.entity.Player;

public class SendIconCommand extends IconCommand {

  public SendIconCommand(String command) {
    super(command);
  }

  @Override
  public void addToTaskChain(Player player, TaskChain<?> taskChain) {
    taskChain.sync(
        () -> Main.getUtils().connect(player, hasVariables ? getParsedCommand(player) : command));
  }
}
