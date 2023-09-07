package dev.mazyar8.newbie;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import dev.mazyar8.newbie.config.Config;
import dev.mazyar8.newbie.event.EntityDamageEvent;
import dev.mazyar8.newbie.event.Event;
import dev.mazyar8.newbie.util.Time;

public class Newbie extends JavaPlugin {
	
	public Config config;
	private List<BossBar> bossBarList = new ArrayList<>();
	private static Newbie newbie;

	@Override
	public void onEnable() {
		newbie = this;
		config = new Config(getConfig());
		addEvent(new EntityDamageEvent());
		
		int minutes = Config.PROTECTION_TIME * 60;
		int i = minutes/3;
		for (int i1 = 0; i1 < minutes; i1++) {
			BossBar bossBar = getServer().createBossBar("newbie expiration: " + getTimeLabel(i1), i1 > i * 2 ? BarColor.RED : i1 > i ? BarColor.YELLOW : BarColor.GREEN, BarStyle.SEGMENTED_20);
			bossBar.setProgress((i1/(minutes/20)) * 0.05);
			bossBarList.add(bossBar);
		}
		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for (Player p : getServer().getOnlinePlayers()) {
					long elpastedTimeByHour = Time.getElapsedTimeByHour(p.getFirstPlayed());
					if (elpastedTimeByHour < Config.PROTECTION_TIME) {
						int elpastedTimeByMinute = (int) Time.getElapsedTimeByMinute(p.getFirstPlayed());
						clearPlayerBossBar(p);
						bossBarList.get(minutes - elpastedTimeByMinute).addPlayer(p);
					}
				}
			}
			
		}, 0, 20);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void addEvent(Event e) {
		Bukkit.getPluginManager().registerEvents(e, this);
	}
	
	public String getTimeLabel(int i) {
		int hour = i/60;
		int minute = i - (hour * 60);
		return hour + "h " + minute + "m";
	}
	
	public void clearPlayerBossBar(Player player) {
		for (BossBar b : bossBarList)
			b.removePlayer(player);
	}
	
	public static Newbie getNewbie() {
		return newbie;
	}
	
}
