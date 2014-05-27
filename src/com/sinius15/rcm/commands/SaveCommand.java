package com.sinius15.rcm.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.sinius15.rcm.Lang;
import com.sinius15.rcm.Main;
import com.sinius15.rcm.RCMCommand;

public class SaveCommand extends RCMCommand{

	public SaveCommand(Main parent) {
		super(parent);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		parent.data.saveConfig();
		sender.sendMessage(ChatColor.YELLOW + Lang.SAVE_DONE);
	}

	@Override
	public String getName() {
		return "save";
	}

	@Override
	public String help() {
		return Lang.HELP_SAVE;
	}

	@Override
	public boolean onlyPlayer() {
		return false;
	}

}
