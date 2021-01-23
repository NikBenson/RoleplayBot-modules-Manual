package com.github.nikbenson.roleplaybot.modules.manual;

import com.github.nikbenson.roleplaybot.configurations.ConfigurationManager;
import com.github.nikbenson.roleplaybot.configurations.ConfigurationPaths;
import com.github.nikbenson.roleplaybot.configurations.JSONConfigured;
import net.dv8tion.jda.api.entities.Guild;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ManualManager implements JSONConfigured {
	private final Guild GUILD;

	private Map<String, String> manuals = new HashMap<>();

	public ManualManager(Guild guild) {
		GUILD = guild;
	}

	public String getManual(String query) {
		return manuals.get(query);
	}

	@Override
	public JSONObject getJSON() {
		return null;
	}

	@Override
	public File getConfigPath() {
		return new File(ConfigurationManager.getInstance().getConfigurationRootPath(GUILD), ConfigurationPaths.MANUALS_FILE);
	}

	@Override
	public void loadFromJSON(JSONObject json) {
		manuals = json;
	}

	@Override
	public Guild getGuild() {
		return GUILD;
	}
}
