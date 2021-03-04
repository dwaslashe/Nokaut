
//
// Copyright (c) 2021 smaks6
//

package me.smaks6.plugin;

import me.smaks6.plugin.Listener.BlockInNokaut;
import me.smaks6.plugin.cmd.deathnow.deathnowcmd;
import me.smaks6.plugin.cmd.nokaut.nokautcmd;
import me.smaks6.plugin.cmd.nokaut.tabnokautcmd;
import me.smaks6.plugin.nokaut.nokaut;
import me.smaks6.plugin.nokaut.ocuc;
import me.smaks6.plugin.service.updatechecker;
import me.smaks6.plugin.cmd.podniesGracza.podniesGraczaCmd;
import me.smaks6.plugin.cmd.rzucgracza.rzucgraczaCmd;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public class Main extends JavaPlugin{
	
	private static Main instance;

	public static final String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	
    public static HashMap<Player, String> gracze = new HashMap<Player, String>();// value = stoi, lezy, nies
    
	public void onEnable() {
		instance = this;
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "|\\     |  |  /");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "| \\    |  | /");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "|  \\   |  |/");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "|   \\  |  |\\");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "|    \\ |  | \\");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "|     \\|  |  \\");
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Enabling the plugin nokaut BY smaks6");

		int pluginId = 9923;
		Metrics metrics = new Metrics(this, pluginId);
		
		BlockInNokaut BlockInNokaut = new BlockInNokaut();
		nokaut nokaut = new nokaut();
		ocuc ocuc = new ocuc();
		Bukkit.getServer().getPluginManager().registerEvents(BlockInNokaut, this);
		Bukkit.getServer().getPluginManager().registerEvents(nokaut, this);
		Bukkit.getServer().getPluginManager().registerEvents(ocuc, this);
		
		new deathnowcmd(this);
		new nokautcmd(this);
		new podniesGraczaCmd(this);
		new rzucgraczaCmd(this);
		getCommand("nokaut").setTabCompleter(new tabnokautcmd());

		if(!getDescription().getAuthors().contains("smaks6")){
			getLogger().info("Please..........");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		getConfig().options().copyDefaults(true);
		saveConfig();
		reloadConfig();

        new updatechecker(85152).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
            	Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "You have the latest version of the plugin");
            	Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Nokaut plugin BY smaks6");
            } else {
            	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You don't have the latest plugin version");
            	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "\\          /");
            	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + " \\        /");
            	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "  \\      /");
            	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "   \\    /");
            	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "    \\  /");
            	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "     \\/");
            	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Nokaut plugin BY smaks6");
            }
        });

	}
	
	public static Main getInstance() {
	    return instance;
	}
}



	
	