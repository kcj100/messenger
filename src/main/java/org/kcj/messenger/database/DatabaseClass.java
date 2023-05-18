package org.kcj.messenger.database;

import java.util.HashMap;
import java.util.Map;
import org.kcj.messenger.model.Message;
import org.kcj.messenger.model.Profile;

public class DatabaseClass {

    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    static {
        messages.put(1L, new Message(1L, "Hello World", "kcj"));
        messages.put(2L, new Message(2L, "Hello Jersey", "kcj"));
        profiles.put("kcj", new Profile(3L, "kcj", "kal", "jo"));
    }

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

}
