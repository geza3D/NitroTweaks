package me.geza3d.nitrotweaks.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;

public abstract class BasicCommand extends CommandBase {
	
	public BasicCommand() {
		ClientCommandHandler.instance.registerCommand(this);
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
}
