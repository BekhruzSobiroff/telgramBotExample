package pizza.company.model;

import lombok.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import pizza.company.enums.CodeMessageType;
@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class CodeMessage {
    private SendMessage sendMessage;
    private EditMessageText editMessageText;
    private SendVideo sendVideo;
    private CodeMessageType type;

}
