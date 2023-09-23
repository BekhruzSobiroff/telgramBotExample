package pizza.company.model;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Btn {
    public static InlineKeyboardButton btn(String text,String callbackData){
        return InlineKeyboardButton.builder().text(text).callbackData(callbackData).build();
    }
    public static List<InlineKeyboardButton> row(InlineKeyboardButton... inlineKeyboardButton){
        List<InlineKeyboardButton> row=new LinkedList<>();
        row.addAll(Arrays.asList(inlineKeyboardButton));
    return row;
    }
    public static List<List<InlineKeyboardButton>> rowCollection(List<InlineKeyboardButton>... row){
        List<List<InlineKeyboardButton>> rowCollection=new LinkedList<>();
        rowCollection.addAll(Arrays.asList(row));
        return rowCollection;
    }
    public static InlineKeyboardMarkup inlineKeyboardMarkup(List<List<InlineKeyboardButton>> rowCollection){
       InlineKeyboardMarkup inlineKeyboardMarkup=new InlineKeyboardMarkup();
       inlineKeyboardMarkup.setKeyboard(rowCollection);

   return inlineKeyboardMarkup;
    }
}
