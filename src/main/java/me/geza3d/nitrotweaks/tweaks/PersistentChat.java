package me.geza3d.nitrotweaks.tweaks;

import java.lang.reflect.Field;

import org.lwjgl.input.Keyboard;

import me.geza3d.nitrotweaks.Tweak;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class PersistentChat extends Tweak {

	String saved = "";
	
	@SubscribeEvent
	public void onOpenChat(GuiScreenEvent.InitGuiEvent.Post e) throws IllegalArgumentException, IllegalAccessException {
		if(e.gui instanceof GuiChat) {
			GuiChat chat = (GuiChat) e.gui;
			Field field = ReflectionHelper.findField(GuiChat.class, "inputField", "field_146415_a");
			GuiTextField textField = (GuiTextField) field.get(chat);
			if(textField != null)
				textField.setText(saved);
		}
	}
	
	@SubscribeEvent
	public void onOpenChat(GuiScreenEvent.KeyboardInputEvent.Pre e) {
		if(e.gui instanceof GuiChat) {
			if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) saved = "";
		}
	}
	
	@SubscribeEvent
	public void onOpenChat(GuiScreenEvent.KeyboardInputEvent.Post e) throws IllegalArgumentException, IllegalAccessException {
		if(e.gui instanceof GuiChat) {
			GuiChat chat = (GuiChat) e.gui;
			Field field = ReflectionHelper.findField(GuiChat.class, "inputField", "field_146415_a");
			GuiTextField textField = (GuiTextField) field.get(chat);
			if(textField != null) {
				saved = textField.getText();
			}
		}
	}
}
