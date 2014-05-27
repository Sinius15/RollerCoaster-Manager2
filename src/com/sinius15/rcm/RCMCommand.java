package com.sinius15.rcm;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class RCMCommand implements CommandExecutor{

	protected Main parent;
	
	public abstract void execute(CommandSender sender, String[] args);
	public abstract String getName();
	public abstract String help();
	public abstract boolean onlyPlayer();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0)
			return false;
		if(!args[0].equalsIgnoreCase(getName()))   
			return false;
		//yes this is my command
		if(onlyPlayer() && !(sender instanceof Player)){
			sender.sendMessage(ChatColor.YELLOW + Lang.ONLY_PLAYER);
			return true;
		}
		//yes i am in the right form
		if(!checkPermission(sender)){
			sender.sendMessage(ChatColor.YELLOW + Lang.NO_PERMISSION);
			return true;
		}
		//yes i have permission
		execute(sender, Arrays.copyOfRange(args, 1, args.length));
		return true;
	}
	
	public boolean checkPermission(CommandSender sender){
		if(sender.hasPermission("rcm.*"))
			return true;
		return sender.hasPermission("rcm."+ getName().toLowerCase());
	}
	
	public RCMCommand(Main parent){
		this.parent = parent;
	}
}
