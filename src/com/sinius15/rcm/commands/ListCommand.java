package com.sinius15.rcm.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.sinius15.rcm.Lang;
import com.sinius15.rcm.Main;
import com.sinius15.rcm.RCMCommand;
import com.sinius15.rcm.RollerPoint;
import com.sinius15.rcm.Util;

public class ListCommand extends RCMCommand{

	public ListCommand(Main parent) {
		super(parent);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(parent.data.points.size() == 0){
			sender.sendMessage(ChatColor.RED + Lang.THERE_ARE_NO_ROLLERCOASTERPOINTS_DEFINED);
			return;
		}
		String s = ChatColor.YELLOW + Util.capitalize(Lang.POINTS) + ": ";
		for(RollerPoint p : parent.data.points)
			s = s + p.name + ", ";
		s = s.substring(0, s.length()-2);
		sender.sendMessage(s);
	}

	@Override
	public String getName() {
		return "list";
	}

	@Override
	public String help() {
		return Lang.HELP_LIST;
	}

	@Override
	public boolean onlyPlayer() {
		return false;
	}


}
