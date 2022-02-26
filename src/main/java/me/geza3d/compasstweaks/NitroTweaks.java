package me.geza3d.compasstweaks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.geza3d.compasstweaks.commands.CommandTweaks;
import me.geza3d.compasstweaks.config.Config;
import me.geza3d.compasstweaks.tweaks.PersistentChat;
import me.geza3d.compasstweaks.tweaks.SneakBackport;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = NitroTweaks.MODID, version = NitroTweaks.VERSION)
public class NitroTweaks {

	public static final String MODID = "nitrotweaks";
	public static final String VERSION = "1.0";
	public static final int CHATLINEID = 69420;
	
	public static final Map<String, Tweak> TWEAKSBYNAME = new HashMap<String, Tweak>();
	public static final List<Tweak> TWEAKS = new ArrayList<Tweak>();
	
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		initTweaks();
		initCommands();
		new Config();
    }
	
	public void initTweaks() {
		new SneakBackport();
		new PersistentChat();
	}
	
	public void initCommands() {
		new CommandTweaks();
	}
	
	public static void info(String info) {
		Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new ChatComponentText("§8[§4NitroTweaks§8] §3" + info), CHATLINEID);
	}
}
