package pizza.company.controller;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import pizza.company.enums.CodeMessageType;
import pizza.company.model.Btn;
import pizza.company.model.CodeMessage;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class GeneralController {
   public CodeMessage handle(long chatId,String text,int messageId){
       CodeMessage codeMessage=new CodeMessage();
       SendMessage sendMessage=new SendMessage();
       sendMessage.setChatId(chatId);
       EditMessageText editMessageText=new EditMessageText();
       editMessageText.setChatId(chatId);
       editMessageText.setMessageId(messageId);
       if (text.equals("/start")||text.equals("/restart")||text.equals("/help")||text.equals("/settings")){
       if (text.equals("/start")||text.equals("/restart")){
       sendMessage.setText("Botga xush kelibsiz tanlang ");
       sendMessage.setReplyMarkup(Btn.inlineKeyboardMarkup(Btn.rowCollection(Btn.row(Btn.btn("Pizzalar","/pizzas"),(Btn.btn("Fast foods","/fastFood"))))));
           codeMessage.setSendMessage(sendMessage);
           codeMessage.setType(CodeMessageType.MESSAGE);

       }  else if (text.equals("/help")) {
           File file=new File("E://Bexruzning rasmlari/maxsus/qwerty.mp4");
           SendVideo sendVideo=new SendVideo();
           sendVideo.setChatId(chatId);
           sendVideo.setVideo(new InputFile(file));
           sendVideo.setCaption("Bu video siz *uchun* ");
           sendVideo.setParseMode(ParseMode.MARKDOWN);
           codeMessage.setSendVideo(sendVideo);
           codeMessage.setType(CodeMessageType.VIDEO);
       } else if (text.equals("/settings")) {
          sendMessage.setText("1./start-boshlamoq\n"+
                              "2./restart-qayta boshlamoq\n" +
                              "3./help-yordam \n" +
                              "4./settings-qurilmalar");
           codeMessage.setSendMessage(sendMessage);
           codeMessage.setType(CodeMessageType.MESSAGE);
       }

       }else if (text.equals("/startt")) {
           editMessageText.setText("Botga xush kelibsiz tanlang ");
           editMessageText.setReplyMarkup(Btn.inlineKeyboardMarkup(Btn.rowCollection(Btn.row(Btn.btn("\uD83C\uDF55 Pizzalar \uD83C\uDF55","/pizzas"),(Btn.btn("Fast foods","/fastFood"))))));
           codeMessage.setType(CodeMessageType.EDIT);
           codeMessage.setEditMessageText(editMessageText);
       }else{
           if (text.equals("/pizzas")||text.equals("/fastFood")){
               editMessageText.setText("Tanalang");
               if (text.equals("/pizzas")){
                   editMessageText.setReplyMarkup(Btn.inlineKeyboardMarkup(Btn.rowCollection(Btn.row(Btn.btn("\uD83C\uDF55 DonarPizza \uD83C\uDF55","/todo/donarPizza"),Btn.btn("\uD83C\uDF55 ExtraPizza \uD83C\uDF55","/todo/extraPizza")),Btn.row(Btn.btn("\uD83C\uDF55 Italian pizza \uD83C\uDF55","/todo/italianPizza")),Btn.row(Btn.btn(" \uD83D\uDD19 Go back","/startt")))));
               } else  {
                   editMessageText.setReplyMarkup(Btn.inlineKeyboardMarkup(Btn.rowCollection(Btn.row(Btn.btn("\uD83C\uDF2D HotDog \uD83C\uDF2D","/todo/hotDog"),Btn.btn("\uD83C\uDF2F Lavash \uD83C\uDF2F","/todo/lavash")),Btn.row(Btn.btn("\uD83C\uDF2E NonKabob \uD83C\uDF2E","/todo/NonKabob")),Btn.row(Btn.btn(" \uD83D\uDD19 Go back","/startt")))));
               }
               codeMessage.setEditMessageText(editMessageText);
               codeMessage.setType(CodeMessageType.EDIT);

           } else if (text.startsWith("/todo/")) {
             sendMessage.setText("Conctingizni jonating");
             ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
               replyKeyboardMarkup.setResizeKeyboard(true);
               replyKeyboardMarkup.setSelective(true);
               List<KeyboardRow> rowList=new LinkedList<>();
               KeyboardRow keyboardRow1=new KeyboardRow();
               KeyboardButton keyboardButton1=new KeyboardButton();
               keyboardButton1.setText("\uD83D\uDCE4 Share contact \uD83D\uDCE4");
               keyboardButton1.setRequestContact(true);
               keyboardRow1.add(keyboardButton1);
               rowList.add(keyboardRow1);
               replyKeyboardMarkup.setKeyboard(rowList);
             sendMessage.setReplyMarkup(replyKeyboardMarkup);
             codeMessage.setSendMessage(sendMessage);
             codeMessage.setType(CodeMessageType.MESSAGE);

           }
       }
       return codeMessage;}
   public    SendMessage keyWord(long chatId,String text){
       SendMessage sendMessage=new SendMessage();
        sendMessage.setText("nechta olasiz "+text+"dan");
        sendMessage.setReplyMarkup(Btn.inlineKeyboardMarkup(Btn.rowCollection(Btn.row(Btn.btn("1","1"+text),Btn.btn("2","2_"+text),Btn.btn("3","3_"+text),Btn.btn("4","4_"+text),Btn.btn("5","5_"+text)))));
       return sendMessage;
    }
}
