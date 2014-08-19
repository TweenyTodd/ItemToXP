package io.github.TweenyTodd.ItemToXP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor{
	private final ItemToXP plugin;
	 
	public Command(ItemToXP plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}

	public void reload (CommandSender sender)
	{
		Bukkit.getServer().getPluginManager().disablePlugin(plugin);
		Bukkit.getServer().getPluginManager().enablePlugin(plugin);
		sender.sendMessage(ChatColor.BLUE + "[ItemToXP] ITXP has been reloaded.");
		Bukkit.getServer().getLogger().info("ItemToXP was reloaded by" + sender.getName());
	}
	@Override
	public boolean onCommand(CommandSender sender,
			org.bukkit.command.Command cmd, String arg, String[] args)
	{
		if (sender instanceof Player) {
	           Player player = (Player) sender;
	           if(args.length == 0){
	        	   
	        	    	sender.sendMessage(ChatColor.RED + "Not enough arguments.");
	        	    	sender.sendMessage("/itxp reload");
	        	    	sender.sendMessage("/itxp create (ItemID) (ItemAmount) (XP)");
	        	    	return true;
	        	    
	           }
	           else if (args.length < 4)
	        	    {
	        	    if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("itxp.reload"))
	        	    {
	        	    	reload(sender);
	        	    	return true;
	        	    }
	        	    else if (args[0].equalsIgnoreCase("reload"))
	        	    {	
	        	    	sender.sendMessage(ChatColor.RED + "[ItemToXP] You don't have permission to reload!");
	        	    	return true;
	        	    }
	        	    else if (args[0].equalsIgnoreCase("create")){

       	       	    	sender.sendMessage("/itxp create (ItemID) (ItemAmount) (XP)");
       	    		}
       	    		else
       	    		{   	    			
       	    			sender.sendMessage("/itxp reload");
       	    			sender.sendMessage("/itxp create (ItemID) (ItemAmount) (XP)");
       	    		}
       	    		return true;
	        	    }
	           else if (args.length == 4)
	           {
	        	   if (args[0].equalsIgnoreCase("create") && sender.hasPermission("itxp.create"))
	        	   {
	        		   try
	        		   {
	        		   Integer itemid = Integer.parseInt(args[1]);
	        		   Material item = Material.getMaterial(itemid);
	        		   if (item != null && item != Material.AIR)
	        		   {
	        			   try
	        			   {
	        				   Integer amnt = Integer.valueOf(args[2]);
	        				   if (amnt==0){
	        					   sender.sendMessage("Item Amount cannot be 0.");
	        					   return true;
	        				   }
	        				   else
	        				   {
	        				   try
	        				   {
	        					   Integer xp = Integer.valueOf(args[3]);
	        					   if (xp==0){
	        						   sender.sendMessage("Experience cannot be 0.");
	        						   return true;
	        					   }
	        					   else{
	        					   // Get target block
	        					   Block target = player.getTargetBlock(null, 100);
	        					   // Get block above target block
	        					   Block block = target.getRelative(0, 1, 0);
	        					   // Set to sign
	        					   block.setType(Material.SIGN_POST);
	        					   Sign sign = (Sign) block.getState();
	        					   
	        					   // The next section is modified from https://forums.bukkit.org/threads/solved-set-sign-direction.87514/
	        					   // Borrowed code from ferrybig
	        					   org.bukkit.material.Sign Sign = new org.bukkit.material.Sign(Material.SIGN_POST);
	        				       // Borrowed code from r0306 
	        					   BlockFace dir = null;
	        				        
	        				        float y = player.getLocation().getYaw();
	        				     
	        				        if( y < 0 ){y += 360;}
	        				     
	        				        y %= 360;
	        				     
	        				        int i = (int)((y+8) / 22.5);
	        				        // Modified from r0306's code because it gave the wrong direction.
	        				        if(i == 0){dir = BlockFace.SOUTH;}
	        				        else if(i == 1){dir = BlockFace.SOUTH_SOUTH_WEST;}
	        				        else if(i == 2){dir = BlockFace.SOUTH_WEST;}
	        				        else if(i == 3){dir = BlockFace.WEST_SOUTH_WEST;}
	        				        else if(i == 4){dir = BlockFace.WEST;}
	        				        else if(i == 5){dir = BlockFace.WEST_NORTH_WEST;}
	        				        else if(i == 6){dir = BlockFace.NORTH_WEST;}
	        				        else if(i == 7){dir = BlockFace.NORTH_NORTH_WEST;}
	        				        else if(i == 8){dir = BlockFace.NORTH;}
	        				        else if(i == 9){dir = BlockFace.NORTH_NORTH_EAST;}
	        				        else if(i == 10){dir = BlockFace.NORTH_EAST;}
	        				        else if(i == 11){dir = BlockFace.EAST_NORTH_EAST;}
	        				        else if(i == 12){dir = BlockFace.EAST;}
	        				        else if(i == 13){dir = BlockFace.EAST_SOUTH_EAST;}
	        				        else if(i == 14){dir = BlockFace.SOUTH_EAST;}
	        				        else if(i == 15){dir = BlockFace.SOUTH_SOUTH_EAST;}
	        				        else {dir = BlockFace.SOUTH;}
	        				     // end borrowed code from r0306
	        				     dir = dir.getOppositeFace();
	        				     Sign.setFacingDirection(dir);
	        				     // end borrowed code from ferrybig
	        				     sign.setData(Sign);
	        				     sign.setLine(0, "§a[ItemToXP]");
	        				     sign.setLine(1, amnt.toString());
	        				     sign.setLine(2, item.toString());
	        				     sign.setLine(3, xp.toString());
	        				     sign.update();
	        				     return true;
	        					   
	        						   
	        					   }
	        				   }
	        				   catch(Exception e)
	        				   {
	        					   sender.sendMessage("Invalid XP Amount!");
	        					   return true;
	        				   }	        					   
	        				   }

	        			   }
	        			   catch(Exception e)
	        			   {
	        				   sender.sendMessage(ChatColor.RED + "Invalid ItemAmount!");
	        				   return true;
	        			   }
	        		   }
	        		   else
	        		   {
	        			   sender.sendMessage(ChatColor.RED + "Invalid ItemID!");
	        			   return true;
	        		   }
	        		   }
	        		   catch(Exception e)
	        		   {
	        			   sender.sendMessage(ChatColor.RED + "Invalid ItemID!");
	        			   return true;
	        		   }

	        	   }
	        	   else if (args[0].equalsIgnoreCase("create"))
	        	   {
	        		   sender.sendMessage(ChatColor.RED + "[ItemToXP] You don't have permission to create a sign.");
	        		   return true;
	        	   }
	        	   else
	        	   {
	        		   	sender.sendMessage(ChatColor.RED + "Incorrect usage.");
      	    			sender.sendMessage("/itxp reload");
      	    			sender.sendMessage("/itxp create (ItemID) (ItemAmount) (XP)");
      	    			return true;
	        	   }
	           }
	           else {
	        	   sender.sendMessage(ChatColor.RED + "Too many arguments.");
	        	   sender.sendMessage("/itxp reload");
	        	   sender.sendMessage("/itxp create (ItemID) (ItemAmount) (XP)");
	        	   return true;
	           }
	        } 
		else {
			if(args[0].equalsIgnoreCase("reload"))
			{
				reload(sender);
				return true;
			}
	           sender.sendMessage("Console cannot do that command!");
	           return true;
	        }
	}
}
