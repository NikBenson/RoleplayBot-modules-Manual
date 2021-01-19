package com.github.NikBenson.RoleplayBot.modules.manual.commands;

import com.github.NikBenson.RoleplayBot.commands.Command;
import com.github.NikBenson.RoleplayBot.commands.context.GuildMessageContext;
import com.github.NikBenson.RoleplayBot.modules.manual.ManualManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import static com.github.NikBenson.RoleplayBot.modules.manual.ManualModule.getManualManager;

public class Manual extends Command<GuildMessageContext> {

	@Override
	public Class<GuildMessageContext> getContext() {
		return GuildMessageContext.class;
	}

	@Override
	public String getRegex() {
		return "man( .*)?";
	}

	@Override
	public String execute(String command, GuildMessageContext context) {
		ManualManager manualManager = getManualManager(((GuildMessageReceivedEvent) context.getParams().get("event")).getGuild());

		if(manualManager != null) {
			String manual;
			if (command.equals("man")) {
				manual = manualManager.getManual("");
			} else {
				manual = manualManager.getManual(command.substring(4).trim().toLowerCase());
			}

			if (manual == null) {
				return "Manual not found!";
			}

			return manual;
		}

		return "Module not activated!";
	}
}
