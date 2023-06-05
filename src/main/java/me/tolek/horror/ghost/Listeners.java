package me.tolek.horror.ghost;

import me.tolek.horror.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Listeners implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event == null) { return; }

        Player p = event.getPlayer();
        if (p == Utils.hunter) {
            if (Utils.gstate == GhostState.SILENT) {
                Utils.detectable = false;
                Utils.invisible = true;
                event.setCancelled(true);
            } else if (Utils.gstate == GhostState.NFSILENT) {
                Utils.detectable = true;
                Utils.invisible = true;
            } else if (Utils.gstate == GhostState.HUNT) {
                Utils.detectable = true;
                Utils.invisible = false;
            }
        }
    }

}
