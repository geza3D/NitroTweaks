package me.geza3d.compasstweaks.commands;

import me.geza3d.compasstweaks.NitroTweaks;
import me.geza3d.compasstweaks.Tweak;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class CommandTweaks extends BasicCommand {

	@Override
	public String getCommandName() {
		return ";tweaks";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Shows you all tweaks in the mod. If the tweak name is red, it's disabled. If tweak name is green, it's enabled";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		String tweaks = "";
		for(Tweak tweak : NitroTweaks.TWEAKS) {
			if(!tweaks.isEmpty()) tweaks += "§b, ";
			if(tweak.isEnabled()) {
				tweaks += "§a";
			} else {
				tweaks += "§c";
			}
			tweaks += tweak.getClass().getSimpleName();
		}
		NitroTweaks.info("Tweaks: " + tweaks);
	}

}
