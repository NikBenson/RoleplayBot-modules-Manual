package com.github.NikBenson.RoleplayBot.modules.manual;

import com.github.NikBenson.RoleplayBot.commands.Command;
import com.github.NikBenson.RoleplayBot.configurations.ConfigurationManager;
import com.github.NikBenson.RoleplayBot.modules.RoleplayBotModule;
import com.github.NikBenson.RoleplayBot.modules.manual.commands.Manual;
import net.dv8tion.jda.api.entities.Guild;

import java.util.HashMap;
import java.util.Map;

public class ManualModule implements RoleplayBotModule {
	private static ManualModule instance;
	private final Map<Guild, ManualManager> managers = new HashMap<>();

	public ManualModule() {
		instance = this;
		Command.register(new Manual());
	}

	@Override
	public boolean isActive(Guild guild) {
		return managers.containsKey(guild);
	}

	@Override
	public void load(Guild guild) {
		if(!managers.containsKey(guild)) {
			ManualManager manualManager = new ManualManager(guild);
			ConfigurationManager configurationManager = ConfigurationManager.getInstance();

			configurationManager.registerConfiguration(manualManager);
			try {
				configurationManager.load(manualManager);
			} catch (Exception ignored) {}

			managers.put(guild, manualManager);
		}
	}

	@Override
	public void unload(Guild guild) {
		managers.remove(guild);
	}

	public static ManualManager getManualManager(Guild guild) {
		return instance.managers.get(guild);
	}
}
