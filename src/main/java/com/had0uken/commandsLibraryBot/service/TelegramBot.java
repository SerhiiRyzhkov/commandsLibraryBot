package com.had0uken.commandsLibraryBot.service;

import com.had0uken.commandsLibraryBot.config.BotConfig;
import com.had0uken.commandsLibraryBot.model.User;
import com.had0uken.commandsLibraryBot.model.UserRepository;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.grizzly.http.util.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScope;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private UserRepository userRepository;
    final BotConfig config;

    static final String HELP_TEXT = "Hi there! I'm a bot designed to help you with SQL commands. Here are some of the SQL commands you can use:\n\n" +
            "Type /start to see a welcome message\n\n" +
            "Type /help to see this message again\n\n";
    public TelegramBot(BotConfig config){
        this.config=config;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "get a welcome message"));
        listOfCommands.add(new BotCommand("/mydata", "get your data stored"));
        listOfCommands.add(new BotCommand("/deletedata", "delete my data"));
        listOfCommands.add(new BotCommand("/help", "info how to use this bot"));
        listOfCommands.add(new BotCommand("/settings", "set your preferences"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(),null));
        }
            catch (TelegramApiException e){
            log.error("Error setting bot`s command list: " +e.getMessage());
            }

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
                    registerUser(update.getMessage());
                    startCommandReceived(chatID, update.getMessage().getChat().getFirstName());
                    break;
                case "/help":
                    sendMessage(chatID,HELP_TEXT);
                    break;
                default:
                    sendMessage(chatID,"Sorry, command was not recognized");
            }
        }
    }



    private void registerUser(Message message)
    {
        if(userRepository.findById(message.getChatId()).isEmpty()){
            Chat chat = message.getChat();
            User user = new User(message.getChatId(),chat.getFirstName(),chat.getLastName(),
                    chat.getUserName(), new java.sql.Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            log.info("user saved: " + user);

        }
    }
    private void startCommandReceived(long chatID, String name) {
        String answer = EmojiParser.parseToUnicode("Hi, " + name + "! Nice to meet you! "+":wink:"+" Please " +
                "select the technology:");
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();
        InlineKeyboardButton gitButton  = new InlineKeyboardButton();
        gitButton.setText("GIT");
        gitButton.setCallbackData("GIT_BUTTON");
        rowInLine.add(gitButton);
        rowsInline.add(rowInLine);
        markupInline.setKeyboard(rowsInline);
        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        message.setReplyMarkup(markupInline);

        log.info("Replied to user "+name);
        sendMessage(chatID,answer);
    }
    private void sendMessage(long chatID, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatID));
        message.setText(textToSend);


        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: "+ e.getMessage());
        }


    }
}
