package com.had0uken.commandsLibraryBot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {
    /*@Value("${bot.name")*/
    String botName="commandsLibraryBot";
    /*@Value("${bot.token")*/
    String token="6078980292:AAGNvKeyFQTmaqYf4d3ZJvpptaQMlQuhTsE";
}
