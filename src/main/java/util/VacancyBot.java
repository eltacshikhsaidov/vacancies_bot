package util;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Timer;
import java.util.TimerTask;

import static java.util.Objects.isNull;
import static util.AllVacancies.vacancies;

public class VacancyBot extends TelegramLongPollingBot {

    private static int i = 0;

    @Override
    public String getBotUsername() {
        return "mvn_vacancy_bot";
    }

    @Override
    public String getBotToken() {
        return "token that you generated";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message.getText().equals("/start") || message.isUserMessage() || message.isGroupMessage()) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    sendNextVacancy(message);
                }
            }, 0, 60 * 1000);
        }
    }

    @SneakyThrows
    private void sendNextVacancy(Message message) {
        long chatId = message.getChatId();
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption(getNextVacancy().getCaption());
        sendPhoto.setPhoto(getNextVacancy().getImg());
        sendPhoto.setParseMode("Markdown");

        System.out.println("Sending vacancy: " + getNextVacancy().getCaption());
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public Vacancy getNextVacancy() {

        System.out.println("vacancies size: " + vacancies().size());
        System.out.println("value of i: " + i);

        if (i >= vacancies().size()) {
            return null;
        }

        return vacancies().get(i++);
    }
}
