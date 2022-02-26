package me.geza3d.compasstweaks;

import me.geza3d.compasstweaks.commands.CommandTweak;
import net.minecraftforge.common.MinecraftForge;

public class Tweak {

	public static Tweak instance;
	boolean isEnabled;
	
	public Tweak() {
		instance = this;
		isEnabled = false;
		NitroTweaks.TWEAKS.add(this);
		NitroTweaks.TWEAKSBYNAME.put(this.getClass().getSimpleName(), this);
		new CommandTweak(this);
	}
	
	public void toggle() {
		if(isEnabled) {
			disable();
		} else {
			enable();
		}
	}
	
	public void enable() {
		if(!isEnabled) {
			MinecraftForge.EVENT_BUS.register(this);
			isEnabled = true;
		}
	}
	
	public void disable() {
		if(isEnabled) {
			MinecraftForge.EVENT_BUS.unregister(this);
			isEnabled = false;
		}
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
}
