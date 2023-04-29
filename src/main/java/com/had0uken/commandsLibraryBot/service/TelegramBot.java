package com.had0uken.commandsLibraryBot.service;

import com.had0uken.commandsLibraryBot.config.BotConfig;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;

    public TelegramBot(BotConfig config){
        this.config=config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage()&&update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatID = update.getMessage().getChatId();

            switch (messageText){
                case "/start":

                        startCommandReceived(chatID, update.getMessage().getChat().getFirstName());
                        break;
                default: sendMessage(chatID,"Sorry, command was not recognized");
            }
        }
    }

    private void startCommandReceived(long chatID, String name) throws TelegramApiException {
        String answer = "Hi, " + name + "! Nice to meet you!";
        sendMessage(chatID,answer);
    }
    private void sendMessage(long chatID, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatID));
        message.setText(textToSend);


        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }


    }
}
