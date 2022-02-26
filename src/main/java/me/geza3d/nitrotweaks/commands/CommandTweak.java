package me.geza3d.nitrotweaks.commands;

import me.geza3d.nitrotweaks.NitroTweaks;
import me.geza3d.nitrotweaks.Tweak;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;

public class CommandTweak extends CommandBase {

	Tweak tweak;

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
	
	public CommandTweak(Tweak tweak) {
		this.tweak = tweak;
		ClientCommandHandler.instance.registerCommand(this);
	}
	
	@Override
	public String getCommandName() {
		return ";"+tweak.getClass().getSimpleName().toLowerCase();
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "Enables the tweak";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		tweak.toggle();
		String msg = tweak.getClass().getSimpleName() + " has been ";
		if(tweak.isEnabled()) {
			msg += EnumChatFormatting.GREEN + "enabled";
		} else {
			msg += EnumChatFormatting.RED + "disabled";
		}
		NitroTweaks.info(msg);
	}

}
