package me.hsgamer.chestcommands_bungee.addon.action;

import co.aikar.taskchain.TaskChain;
import com.gmail.filoghost.chestcommands.api.IconCommand;
import com.gmail.filoghost.chestcommands.util.FormatUtils;
import me.hsgamer.chestcommands_bungee.addon.Main;
import org.bukkit.entity.Player;

public class AlertIconCommand extends IconCommand {

  public AlertIconCommand(String command) {
    super(FormatUtils.addColors(command));
  }

  @Override
  public void addToTaskChain(Player player, TaskChain<?> taskChain) {
    taskChain.sync(() -> Main.getUtils().alert(player, getParsedCommand(player)));
  }
}
