package com.sinius15.rcm;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.sinius15.rcm.commands.*;

public class Main extends JavaPlugin{
	
	public Data data;
	public ArrayList<RCMCommand> commands = new ArrayList<>();
	
	@Override
	public void onLoad() {
		
		commands.add(new AddCommand(this));
		commands.add(new RemoveCommand(this));
		commands.add(new ListCommand(this));
		commands.add(new HelpCommand(this));
		commands.add(new StartCommand(this));
		commands.add(new SetCartCommand(this));
	};
	@Override
	public void onEnable() {
		data = new Data(this);
		data.loadPoints();
		data.saveConfig();
		
		getCommand("rcm").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		for(RCMCommand c : commands){
			if(c.onCommand(sender, command, label, args))
				return true;
		}
		return false;
	}
	
	
}
