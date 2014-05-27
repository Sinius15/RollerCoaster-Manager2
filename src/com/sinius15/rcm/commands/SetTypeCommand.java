package com.sinius15.rcm.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.sinius15.rcm.Lang;
import com.sinius15.rcm.Main;
import com.sinius15.rcm.RCMCommand;
import com.sinius15.rcm.RollerPoint;
import com.sinius15.rcm.Util;

public class SetTypeCommand extends RCMCommand{

	public SetTypeCommand(Main parent) {
		super(parent);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(args.length < 2){
			sender.sendMessage(ChatColor.RED + Lang.HELP_SET_TYPE);
			return;
		}
		RollerPoint point = parent.data.getPointByName(args[0]);
		if(point == null){
			sender.sendMessage(ChatColor.RED + Lang.THIS_POINT_DOES_NOT_EXIST);
			return;
		}
		String type = args[1];
		if(type.equalsIgnoreCase("start")){
			point.type = RollerPoint.START;
		}else if(type.equalsIgnoreCase("end")){
			point.type = RollerPoint.END;
		}else{
			sender.sendMessage(ChatColor.RED + Lang.HELP_SET_TYPE);
			return;
		}
		sender.sendMessage(ChatColor.YELLOW + point.name + " " + "is now a " +  Lang.POINT);  //
		
		
		
	}

	@Override
	public String getName() {
		return "setType";
	}

	@Override
	public String help() {
		return Lang.HELP_SET_TYPE;
	}

	@Override
	public boolean onlyPlayer() {
		return false;
	}

}
