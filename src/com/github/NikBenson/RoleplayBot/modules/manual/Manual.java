package com.github.NikBenson.RoleplayBot.modules.manual;

import com.github.NikBenson.RoleplayBot.commands.Command;
import com.github.NikBenson.RoleplayBot.modules.RoleplayBotModule;
import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;
import java.util.Map;

public class Manual implements RoleplayBotModule {
	private static Manual instance;
	private final Map<Guild, ManualManager> managers = new HashMap<>();

	public Manual() {
		instance = this;
		Command.register(new com.github.NikBenson.RoleplayBot.modules.manual.commands.Manual());
	}

	@Override
	public boolean isActive(Guild guild) {
		return managers.containsKey(guild);
	}

	@Override
	public void load(Guild guild) {
		if(!managers.containsKey(guild)) {
			managers.put(guild, new ManualManager(guild));
		}
	}

	@Override
	public void unload(Guild guild) {
		if(managers.containsKey(guild)) {
			managers.remove(guild);
		}
	}

	public static ManualManager getManualManager(Guild guild) {
		return instance.managers.get(guild);
	}
}
