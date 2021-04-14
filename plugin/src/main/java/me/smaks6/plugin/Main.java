
//
// Copyright (c) 2021 smaks6
//

package me.smaks6.plugin;

import com.sk89q.worldguard.WorldGuard;
import me.smaks6.api.NokautEnum;
import me.smaks6.plugin.Listener.*;
import me.smaks6.plugin.cmd.deathnow.deathnowcmd;
import me.smaks6.plugin.cmd.nokaut.nokautcmd;
import me.smaks6.plugin.cmd.nokaut.tabnokautcmd;
import me.smaks6.plugin.cmd.podniesGracza.podniesGraczaCmd;
import me.smaks6.plugin.cmd.rzucgracza.rzucgraczaCmd;
import me.smaks6.plugin.nokaut.Nokaut;
import me.smaks6.plugin.nokaut.Ocuc;
import me.smaks6.plugin.service.WorldGuardFlag;
import me.smaks6.plugin.service.updatechecker;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public class Main extends JavaPlugin{
	
	private static Main instance;

	private String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	
    public static HashMap<Player, NokautEnum> gracze = new HashMap<Player, NokautEnum>();// value = stoi, lezy, nies

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
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Server version: " + version);

		checkVersion();

		int pluginId = 9923;
		Metrics metrics = new Metrics(this, pluginId);

		registerEvents();

		//register WorldguardFlag
		new WorldGuardFlag().registerFlag();
		
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

	private void registerEvents(){

		Bukkit.getServer().getPluginManager().registerEvents(new Nokaut(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Ocuc(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CommandListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DamageByEntityListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DeathEvent(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DropItemListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EntityRegainHealthListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new InteractListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MoveListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PickupItemListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new QuitListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ShootBowListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EntityDismountListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new KickPlayerListenear() , this);


	}

	private void checkVersion(){
		if(!(version.equals("v1_16_R3") || version.equals("v1_16_R2") || version.equals("v1_16_R1"))){

			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "======================================");
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + " ");
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Sory, but your version is not support!");
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + " ");
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "======================================");

			getServer().getPluginManager().disablePlugin(Main.getInstance());
		}
	}
}



	
	
