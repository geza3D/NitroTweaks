package me.geza3d.nitrotweaks.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

import me.geza3d.nitrotweaks.NitroTweaks;
import me.geza3d.nitrotweaks.Tweak;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class Config {

	public static Config instance;
	public static final String DIR = Minecraft.getMinecraft().mcDataDir.getAbsolutePath() + "\\nitrotweaks\\";
	
	public Config(){
		instance = this;
		loadTweakStatus();
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onDisconnect(ClientDisconnectionFromServerEvent e) {
		saveTweakStatus();
	}
	
	private static void loadTweakStatus() {
		File f = new File(DIR + "\\tweakstatus.json");
		String json = readFileIfExists(f);
		if(json == null) return;
		Gson gson = new Gson();
		String[] modules = gson.fromJson(json, String[].class);
		for(String name : modules) {
			Tweak tweak = NitroTweaks.TWEAKSBYNAME.get(name);
			if(tweak != null) tweak.enable();
		}
	}
	
	private static void saveTweakStatus() {
		List<String> activeModules = new ArrayList<String>();
		for(Tweak module : NitroTweaks.TWEAKS) {
			if(module.isEnabled()) {
				activeModules.add(module.getClass().getSimpleName());
			}
		}
		Gson gson = new Gson();
		String moduleStatus = gson.toJson(activeModules.toArray());
		writeIntoFile(DIR, "tweakstatus.json", moduleStatus);
	}
	
	private static String readFileIfExists(File file) {
		if(file.exists()) {
			try {
				return new String(Files.readAllBytes(Paths.get(file.getPath())), StandardCharsets.UTF_8);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private static void writeIntoFile(String dir, String fileName, String data) {
		File file = new File(dir+"\\"+fileName);
		file.delete();
		Path path = Paths.get(dir);
		if(!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(file.exists()) {
			try {
				FileWriter w = new FileWriter(file);
				data = data.replace(",",",\n\t");
				data = data.replace("{","{\n\t");
				data = data.replace("}","\n}");
				data = data.replace("[","[\n\t");
				data = data.replace("]","\n]");
				w.write(data);
				w.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
