package com.example.demo.bot;

import com.example.demo.models.BotMessage;
import com.example.demo.repo.BotRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

/**
 *
 * This example bot is an echo bot that just repeats the messages sent to him
 *
 */
@Component
public class Bot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(Bot.class);
    final
    BotRepo botRepo;
    @Value("${bot.token}")
    private String token;
    int k=2;
    BotMessage botMessageGL;
    @Value("${bot.name}")
    private String username;

    public Bot(BotRepo botRepo) {
        this.botRepo = botRepo;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            SendMessage response = new SendMessage();
            Long chatId = message.getChatId();
            response.setChatId(String.valueOf(chatId));
            String text = message.getText();
           if(text.contains("@") && text.contains(".") && !text.contains(" ") && k==0){
                    response.setText("Отлично! Теперь вы можете вводить свой(и) вопрос(ы)!");
                    k=1;
                    botMessageGL=new BotMessage();
                    botMessageGL.setLogin(text);

           }
           else if(k==1){
               botMessageGL.setMessage(text);
               response.setText("Спасибо, любимая моя заинька Наря");
               k=2;
               botRepo.save(botMessageGL);
           }
           else if(k==2){
               response.setText("Здравствуйте! Введите email, чтобы задать вопрос");
               k=0;
           }
           else if(k==0){
               response.setText("Введите email");
           }
            try {

                execute(response);
                logger.info("Sent message \"{}\" to {}", text, chatId);
            } catch (TelegramApiException e) {
                logger.error("Failed to send message \"{}\" to {} due to error: {}", text, chatId, e.getMessage());
            }
        }
    }

    @PostConstruct
    public void start() {
        logger.info("username: {}, token: {}", username, token);
    }

}