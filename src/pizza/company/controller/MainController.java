package pizza.company.controller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import pizza.company.model.CodeMessage;

import java.util.LinkedList;
import java.util.List;

public class MainController extends TelegramLongPollingBot {
    private static final String BOT_USERNAME="@Pitza_zakaz_01_bot";
    private static final String TOKEN="6427659303:AAFhpabZEhtOm7ZWdkS2V9mh8u7KM9izcvM";

    private GeneralController generalController;
    public MainController(){
        generalController=new GeneralController();
    }
    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message=update.getMessage();
        try{
            if (update.hasCallbackQuery()){
                      String data=update.getCallbackQuery().getData();
                 message = update.getCallbackQuery().getMessage();

                this.SendMsg(generalController.handle(message.getChatId(),data,message.getMessageId()));
            } else if (update.getMessage().hasContact()||update.getMessage().hasLocation()) {
                SendMessage sendMessage=new SendMessage();
                sendMessage.setChatId(message.getChatId());
                if (update.getMessage().hasContact()){

                sendMessage.setText("Lokatsiyangizni kiriting");
                ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setSelective(true);
                List<KeyboardRow> rowList=new LinkedList<>();
                KeyboardRow keyboardRow1=new KeyboardRow();
                KeyboardButton keyboardButton1=new KeyboardButton();
                keyboardButton1.setText("\uD83D\uDCE4 Share Locaion \uD83D\uDCE4");
                keyboardButton1.setRequestLocation(true);
                keyboardRow1.add(keyboardButton1);
                rowList.add(keyboardRow1);
                replyKeyboardMarkup.setKeyboard(rowList);
                sendMessage.setReplyMarkup(replyKeyboardMarkup);
                this.SendMsg(sendMessage);
            } else if (update.getMessage().hasLocation()) {
                    sendMessage.setText("Rahmat 3daqiqada yetkazib beramiz lekin bu yolgon");
                this.SendMsg(sendMessage);
            }} else if (message.hasText()) {
                long chatId=message.getChatId();
               int messageId=message.getMessageId();
                String text=message.getText();
                if (text !=null){
                    this.SendMsg(generalController.handle(chatId,text,messageId));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void SendMsg(SendMessage sendMessage)throws Exception{
        execute(sendMessage);
    }
    public void SendMsg(CodeMessage codeMessage)throws Exception{
        switch (codeMessage.getType()){
            case MESSAGE -> execute(codeMessage.getSendMessage());
            case EDIT -> execute(codeMessage.getEditMessageText());
            case VIDEO -> execute(codeMessage.getSendVideo());
        }
    }
}
