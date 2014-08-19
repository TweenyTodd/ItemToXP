package io.github.TweenyTodd.ItemToXP;

import org.bukkit.plugin.java.JavaPlugin;

public class ItemToXP extends JavaPlugin {
	@Override
	public void onEnable()
	{
		getLogger().info("ItemToXP has been enabled!");
		this.getCommand("itxp").setExecutor(new Command(this));
		new SignCheck(this);
		new SignUsage(this);
	}
	@Override
	public void onDisable()
	{
		getLogger().info("ItemToXP has been disabled!");
	}
}
