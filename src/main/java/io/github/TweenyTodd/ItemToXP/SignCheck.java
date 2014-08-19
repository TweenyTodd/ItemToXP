package io.github.TweenyTodd.ItemToXP;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignCheck implements Listener
{
    public SignCheck(ItemToXP plugin)
    {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
 
    @EventHandler
    public void signChecking(SignChangeEvent event)
    {
        Player player = event.getPlayer();
     
        if (event.getLine(0).equals("§a[ItemToXP]"))
        {
        	if (player.hasPermission("itxp.create"))
        	{
        		player.sendMessage(ChatColor.BLUE + "[ItemToXP] Sign created.");
        		return;
        	}
        	else
        	{
        		player.sendMessage(ChatColor.RED + "[ItemToXP] You don't have permission to create an ITXP sign.");
        		event.setCancelled(true);	
        	}
        }
    }
}
