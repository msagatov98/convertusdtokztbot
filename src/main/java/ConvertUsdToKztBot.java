

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class ConvertUsdToKztBot extends TelegramLongPollingBot {

    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new ConvertUsdToKztBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();

        if (msg != null && msg.hasText()) {

            try {
                Double d = Double.parseDouble(msg.getText());
                String text = Double.toString(d) + " доллар: " + Double.toString(377.26*d) + " тенге";
                sendMsg(msg, text);
            } catch (Exception e) {
                sendMsg(msg, "Введите сумму доллара!");
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "ConvertUsdToKztBot";
    }

    @Override
    public String getBotToken() {
        return "799752684:AAF8VuOSkQ1hIszk7aEWhojYwIFid5lCLBM";
    }

    private void sendMsg(Message msg, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(msg.getChatId());
        //sendMessage.setReplyToMessageId(msg.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}