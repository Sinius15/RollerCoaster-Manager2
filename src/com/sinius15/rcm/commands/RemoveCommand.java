package com.sinius15.rcm.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.sinius15.rcm.Lang;
import com.sinius15.rcm.Main;
import com.sinius15.rcm.RCMCommand;
import com.sinius15.rcm.Util;

public class RemoveCommand extends RCMCommand{

	public RemoveCommand(Main parent) {
		super(parent);
	}

	@Override
	public String getName() {
		return "remove";
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length < 1){
			sender.sendMessage(ChatColor.RED + Lang.USAGE + " " + Lang.HELP_REMOVE);
			return;
		}
		if(parent.data.removePoint(args[0])){
			sender.sendMessage(ChatColor.YELLOW + Util.capitalize(Lang.POINT + " " + args[0] + " " + Lang.REMOVED + ".")); //Point sin removed.
		}else{
			sender.sendMessage(ChatColor.YELLOW + Util.capitalize(Lang.POINT + " '" + args[0] + "' " + Lang.DOES_NOT_EXIST + ".")); //Point sin does not exist.
		}
	}

	@Override
	public String help() {
		return Lang.HELP_REMOVE;
	}

	@Override
	public boolean onlyPlayer() {
		return false;
	}


}
