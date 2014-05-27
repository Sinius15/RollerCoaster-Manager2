package com.sinius15.rcm.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.sinius15.rcm.Lang;
import com.sinius15.rcm.Main;
import com.sinius15.rcm.RCMCommand;

public class HelpCommand extends RCMCommand{

	public HelpCommand(Main parent) {
		super(parent);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		String[] out = new String[parent.commands.size()];
		out[0] = ChatColor.YELLOW + Lang.COMMANDS;
		int i = 1;
		for(RCMCommand c : parent.commands){
			if(c.getName().equals(getName()))
				continue;
			out[i] = ChatColor.YELLOW + c.help();
			i++;
		}
		sender.sendMessage(out);
	}

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String help() {
		return null;
	}

	@Override
	public boolean onlyPlayer() {
		return false;
	}

}
