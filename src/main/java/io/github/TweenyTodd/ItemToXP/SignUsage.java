package io.github.TweenyTodd.ItemToXP;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SignUsage implements Listener{
	
    public SignUsage(ItemToXP plugin)
    {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void SignUser(PlayerInteractEvent event)
    {
    	if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
    	{    		
    	if (event.getClickedBlock().getType().equals(Material.WALL_SIGN) || event.getClickedBlock().getType().equals(Material.SIGN_POST))
    	{
    		Sign sign = (Sign) event.getClickedBlock().getState();
    		if (sign.getLine(0).equalsIgnoreCase("§a[ItemToXP]") && event.getPlayer().hasPermission("itxp.use"))
    		{
    			Integer amnt;
    			try
    			{
    				amnt = Integer.parseInt(sign.getLine(1));
    				if (amnt==0)
    				{
    					event.getPlayer().sendMessage("Item Amount cannot be zero.");
    					return;
    				}
    				else
    				{
    					Material item = Material.getMaterial(sign.getLine(2));
    				if (item != null && item != Material.AIR)
    				{
    					Integer xp;
    					try
    					{
    						xp = Integer.parseInt(sign.getLine(3));
    						if(xp==0)
    						{
    							event.getPlayer().sendMessage("Experience cannot be 0.");
    							return;
    						}
    						else
    						{
    							ItemStack itemstack = new ItemStack(item,amnt);
    							if (event.getPlayer().getInventory().contains(item,amnt))
    							{
    							event.getPlayer().getInventory().removeItem(itemstack);
    							event.getPlayer().updateInventory();
    							event.getPlayer().giveExpLevels(xp);
    			    			event.getPlayer().sendMessage(ChatColor.BLUE + "You gave " + sign.getLine(1) + " of " + item.toString() + " for " + sign.getLine(3) + " experience.");
    			    			return;    								
    							}
    							else
    							{
    							event.getPlayer().sendMessage(ChatColor.RED + "You don't have " + sign.getLine(1) + " of " + item.toString());
    							return;
    							}

    						}
    						
    					}
    					catch(Exception e)
    					{
    						event.getPlayer().sendMessage("This sign has an incorrect experience.");
    						return;
    					}
    				}
    				else
    				{
    					event.getPlayer().sendMessage("This sign has an incorrect Item.");
    					return;
    				}
    			}	
    				}

    			catch(Exception e)
    			{
    				event.getPlayer().sendMessage("This sign has an incorrect Item Amount.");
    				return;
    			}

    		}
    		else if(sign.getLine(0).equalsIgnoreCase("§a[ItemToXP]"))
    		{
    			event.getPlayer().sendMessage("You don't have permission to use this sign.");
    			return;
    		}
    		else{
    			return;    			
    		}
    	}
    	else{
    		return;
    	}
    	}
    	else{
    		return;
    	}
    }
    
}
