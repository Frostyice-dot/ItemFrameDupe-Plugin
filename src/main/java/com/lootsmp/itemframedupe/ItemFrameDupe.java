package com.lootsmp.itemframedupe;

import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemFrameDupe extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("ItemFrameDupe enabled!");
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFrameLeftClick(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof ItemFrame frame)) return;

        ItemStack item = frame.getItem();

        if (item == null || item.getType() == Material.AIR) return;

        event.setCancelled(true);

        frame.getWorld().dropItemNaturally(
            frame.getLocation(),
            item.clone()
        );
    }
          }
