package org.kcj.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.kcj.messenger.database.DatabaseClass;
import org.kcj.messenger.exception.DataNotFoundException;
import org.kcj.messenger.model.Message;

public class MessageService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() {

    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public List<Message> getAllMessagesForYear(int year) {
        List<Message> messagesForYear = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Message message : messages.values()) {
            cal.setTime(message.getCreated());
            if (cal.get(Calendar.YEAR) == year) {
                messagesForYear.add(message);
            }
        }
        return messagesForYear;
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {
        ArrayList<Message> list = new ArrayList<Message>(messages.values());
        if (start + size > list.size()) {
            return new ArrayList<Message>();
        }
        return list.subList(start, start + size);
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(long id) {
        return messages.remove(id);
    }

    public Message getMessage(long messageId) {
        Message message = messages.get(messageId);
        if (message == null) {
            throw new DataNotFoundException("Message with id " + messageId + " not found");
        }
        return message;
    }

}
