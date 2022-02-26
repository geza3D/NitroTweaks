package me.geza3d.nitrotweaks.commands;

import me.geza3d.nitrotweaks.NitroTweaks;
import me.geza3d.nitrotweaks.Tweak;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

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
			if(!tweaks.isEmpty()) tweaks += EnumChatFormatting.DARK_AQUA + ", ";
			if(tweak.isEnabled()) {
				tweaks += EnumChatFormatting.GREEN;
			} else {
				tweaks += EnumChatFormatting.RED;
			}
			tweaks += tweak.getClass().getSimpleName();
		}
		NitroTweaks.info("Tweaks: " + tweaks);
	}

}
