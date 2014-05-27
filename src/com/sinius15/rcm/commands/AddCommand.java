package com.sinius15.rcm.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sinius15.rcm.Lang;
import com.sinius15.rcm.Main;
import com.sinius15.rcm.RCMCommand;
import com.sinius15.rcm.RollerPoint;
import com.sinius15.rcm.Util;

public class AddCommand extends RCMCommand{
	
	public AddCommand(Main parent) {
		super(parent);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(args.length < 2){
			sender.sendMessage(ChatColor.RED + Lang.USAGE + " " + Lang.HELP_ADD);
			return;
		}

		String type = args[0];
		String name = args[1];
		
		Player who = (Player) sender;
		Block b = Util.getTargetBlock(who, 20);
		if(b == null){
			sender.sendMessage(ChatColor.YELLOW + Lang.NO_POINT);
			return;
		}
		
		Location loc = b.getLocation();
		

		if(parent.data.addPoint(loc, name, type.equals(Lang.START) ? RollerPoint.START : RollerPoint.END)){
			sender.sendMessage(ChatColor.YELLOW + Util.capitalize(Lang.POINT + " " + name + " " + Lang.ADDED + ".")); //Point sin added.
		}else{
			sender.sendMessage(ChatColor.RED + Util.capitalize(Lang.THE_NAME + " " + name + " " + Lang.IS_ALREADY_IN_USE + ".")); //The name sin is already in use.
		}
	}
	
	@Override
	public String getName() {
		return "add";
	}


	@Override
	public String help() {
		return Lang.HELP_ADD;
	}

	@Override
	public boolean onlyPlayer() {
		return true;
	}



}
