package dev.mazyar8.newbie.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import dev.mazyar8.newbie.config.Config;
import dev.mazyar8.newbie.util.Time;

public class EntityDamageEvent extends Event {

	@EventHandler
	public void onPlayerJoin(EntityDamageByEntityEvent e) {
		Entity entity = e.getEntity(), damager = e.getDamager();
		if (entity instanceof Player && damager instanceof Player) {
			Player player = (Player) entity, damagerPlayer = (Player) damager;
			if (Time.getElapsedTimeByHour(damagerPlayer.getFirstPlayed()) < Config.PROTECTION_TIME) {
				damagerPlayer.sendMessage(Config.YOU_HAVE_PROTECTION);
				e.setCancelled(true);
			} else if (Time.getElapsedTimeByHour(player.getFirstPlayed()) < Config.PROTECTION_TIME) {
				damagerPlayer.sendMessage(Config.TARGET_HAVE_PROTECTION);
				e.setCancelled(true);
			}
		}
	}
	
}
