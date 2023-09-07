package dev.mazyar8.newbie.config;

import java.lang.reflect.Field;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

	public static final String YOU_HAVE_PROTECTION = ChatColor.RED + "you are protected by newbie protection, you can't damage anyone";
	public static final String TARGET_HAVE_PROTECTION = ChatColor.RED + "that player have newbie protection";
	public static final int PROTECTION_TIME = 2;
	
	public Config(FileConfiguration fc) {
		for (Field f : getClass().getFields()) {
			String path = f.getName().toLowerCase().replace("_", ".");
			try {
				if (fc.isSet(path)) {
					f.set(this, fc.get(path));
				} else {
					fc.set(path, f.get(this));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
}
