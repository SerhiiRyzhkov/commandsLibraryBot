package com.had0uken.commandsLibraryBot.service;

import com.had0uken.commandsLibraryBot.config.BotConfig;
import com.had0uken.commandsLibraryBot.model.*;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
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
    @Autowired
    private CommandRepository commandRepository;
    @Autowired
    private TechnologyRepository technologyRepository;

    final BotConfig config;
    private static final String ERROR_TEXT = "Error occurred: ";

    private static final String BACK_BUTTON = "Back button";
    private static final String HELP_TEXT = """
            Hi there! I'm a bot designed to help you with SQL, GIT AND DOCKER commands. 
            Here are some of the commands you can use:

            Type /start to see a welcome message

            Type /help to see this message again
                        
            Type /deletedata to delete all your personal data that we keep
                        
            Type /mydata to view the information that we keep about you

            """;

    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "get a welcome message"));
        listOfCommands.add(new BotCommand("/mydata", "get your data stored"));
        listOfCommands.add(new BotCommand("/deletedata", "delete my data"));
        listOfCommands.add(new BotCommand("/help", "info how to use this bot"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
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

    private void startCommandReceived(long chatID, String name, EditMessageText editMessageText) {
        String answer = EmojiParser.parseToUnicode("Hi, " + name + "! Nice to meet you! " + ":wink:" + " Please " +
                "select the technology:");
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInLine = new ArrayList<>();

        for (Technology technology : technologyRepository.findAll()) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(technology.getTechnology());
            button.setCallbackData(String.valueOf(technology.getId()));
            rowInLine.add(button);
        }
        rowsInline.add(rowInLine);
        markup.setKeyboard(rowsInline);


        log.info("Replied to user " + name);
        if (editMessageText == null) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatID);
            sendMessage.setText(answer);
            sendMessage.setReplyMarkup(markup);
            executeMessage(sendMessage);
        } else {
            editMessageText.setChatId(chatID);
            editMessageText.setText(answer);
            editMessageText.setReplyMarkup(markup);
            executeMessage(editMessageText);
        }
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            handleCommand(update);
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String callbackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            EditMessageText editMessageText = new EditMessageText();
            editMessageText.setMessageId((int) messageId);
            editMessageText.setChatId(chatId);

            List<String> technologiesCBList = new ArrayList<>();
            technologyRepository.findAll().forEach(el -> technologiesCBList.add(String.valueOf(el.getId())));

            List<String> commandsCBList = new ArrayList<>();
            commandRepository.findAll().forEach(el -> commandsCBList.add(el.getCallbackData()));

            if (callbackData.equals(BACK_BUTTON)) {
                handleBackButton(callbackQuery, editMessageText);
            } else if (technologiesCBList.contains(callbackData)) {
                handleTechnologyId(callbackQuery, editMessageText);
            } else if (commandsCBList.contains(callbackData)) {
                handleCommandCB(callbackQuery, editMessageText);
            }
        }

    }

    private void handleBackButton(CallbackQuery callbackQuery, EditMessageText editMessageText) {
        long userID = callbackQuery.getFrom().getId();
        long chatId = callbackQuery.getMessage().getChatId();
        GetChatMember getChatMember = new GetChatMember();
        getChatMember.setChatId(chatId);
        getChatMember.setUserId(userID);
        try {
            ChatMember chatMember = execute(getChatMember);
            String firstName = chatMember.getUser().getFirstName();
            startCommandReceived(chatId, firstName, editMessageText);
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private void handleCommandCB(CallbackQuery callbackQuery, EditMessageText editMessageText) {
        Command command = commandRepository.findByCallbackData(callbackQuery.getData());
        String text = command.getMeaning() + "\n\nSyntax:\n" + command.getSyntax() +
                "\n\n" + "Example:\n" + command.getExample();
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(getBackButton());
        markupInline.setKeyboard(rowsInline);
        editMessageText.setText(text);
        editMessageText.setReplyMarkup(markupInline);
        executeMessage(editMessageText);
    }

    private void handleTechnologyId(CallbackQuery callbackQuery, EditMessageText editMessageText) {
        List<Command> list =
                commandRepository.findByTechnologyID(Integer.parseInt(callbackQuery.getData()));
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        for (Command command : list) {
            List<InlineKeyboardButton> rowInLine = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(command.getMeaning());
            button.setCallbackData(command.getCallbackData());
            rowInLine.add(button);
            rowsInline.add(rowInLine);
        }
        rowsInline.add(getBackButton());
        markupInline.setKeyboard(rowsInline);
        String text = "Select the command, you are interested in:";
        editMessageText.setText(text);
        editMessageText.setReplyMarkup(markupInline);
        executeMessage(editMessageText);
    }


    private void handleCommand(Update update) {
        String messageText = update.getMessage().getText();
        long chatID = update.getMessage().getChatId();
        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        switch (messageText) {
            case "/start" -> {
                registerUser(update.getMessage());
                startCommandReceived(chatID, update.getMessage().getChat().getFirstName(), null);
            }
            case "/help" -> {
                message.setText(HELP_TEXT);
                executeMessage(message);
            }
            case "/mydata" -> {
                message.setText(myData(update));
                executeMessage(message);
            }
            case "/deletedata" -> {
                User user = userRepository.findById(chatID).get();
                userRepository.delete(user);
                log.info("user deleted: " + user);
            }
            default -> {
                message.setText("Sorry, command was not recognized");
                executeMessage(message);
            }
        }
    }


    private void registerUser(Message message) {
        if (userRepository.findById(message.getChatId()).isEmpty()) {
            Chat chat = message.getChat();
            User user = new User(message.getChatId(), chat.getFirstName(), chat.getLastName(),
                    chat.getUserName(), new java.sql.Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            log.info("user saved: " + user);
        }
    }


    private void executeMessage(EditMessageText message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }

    private List<InlineKeyboardButton> getBackButton() {
        List<InlineKeyboardButton> backButtonList = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("<< BACK TO MAIN MENU");
        button.setCallbackData(BACK_BUTTON);
        backButtonList.add(button);
        return backButtonList;
    }


    private String myData(Update update) {
        return "Hello, " + update.getMessage().getChat().getFirstName() + "! Here is your data:\n" +
                "\n" +
                "Name: " + update.getMessage().getChat().getFirstName() + "\n" +
                "Last Name: " + update.getMessage().getChat().getLastName() + "\n" +
                "Username: " + update.getMessage().getChat().getUserName() + "\n";
    }
}


