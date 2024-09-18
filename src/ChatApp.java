
// a user is created on two properties: name & number only

import java.util.*;


public class ChatApp {
    public static void main(String[] args){

        ChatAppFeatures chatService = new ChatAppFeatures();

        // Creating users
        User geralt = chatService.createUser("Geralt", "01717001473");
        User jane = chatService.createUser("Jane", "01717001472");
        User yennefer = chatService.createUser("Yennefer", "9987512");
        User triss = chatService.createUser("Triss", "6822485215");
        User vasemir = chatService.createUser("Vasemir", "6651712");

        // Sending messages
        chatService.sendMessage(geralt.getPhoneNumber(), jane.getPhoneNumber(), "Hello!");
        chatService.sendMessage(jane.getPhoneNumber(), geralt.getPhoneNumber(), "Hi!");

        chatService.sendMessage(geralt.getPhoneNumber(), triss.getPhoneNumber(), "Hello!");
        chatService.sendMessage(triss.getPhoneNumber(), geralt.getPhoneNumber(), "Hi!");
        chatService.sendMessage(geralt.getPhoneNumber(), triss.getPhoneNumber(), "How are you?");
        chatService.sendMessage(triss.getPhoneNumber(), geralt.getPhoneNumber(), "I'm good!");

        chatService.sendMessage(geralt.getPhoneNumber(), yennefer.getPhoneNumber(), "Hello!");
        chatService.sendMessage(yennefer.getPhoneNumber(), geralt.getPhoneNumber(), "Hi!");
        chatService.sendMessage(geralt.getPhoneNumber(), yennefer.getPhoneNumber(), "How are you?");
        chatService.sendMessage(yennefer.getPhoneNumber(), geralt.getPhoneNumber(), "I'm good!");
        chatService.sendMessage(yennefer.getPhoneNumber(), geralt.getPhoneNumber(), "Please Reply!");
        chatService.sendMessage(yennefer.getPhoneNumber(), geralt.getPhoneNumber(), "dont ghost me");
        chatService.sendMessage(yennefer.getPhoneNumber(), geralt.getPhoneNumber(), "I know you lost your memories");
        chatService.sendMessage(yennefer.getPhoneNumber(), geralt.getPhoneNumber(), "We need to find Ciri");

        chatService.sendMessage(yennefer.getPhoneNumber(), triss.getPhoneNumber(), "I hate you.");

        chatService.sendMessage(geralt.getPhoneNumber(), vasemir.getPhoneNumber(), "Hello!");
        chatService.sendMessage(vasemir.getPhoneNumber(), geralt.getPhoneNumber(), "Hi!");
        chatService.sendMessage(geralt.getPhoneNumber(), vasemir.getPhoneNumber(), "How are you?");
        chatService.sendMessage(vasemir.getPhoneNumber(), geralt.getPhoneNumber(), "I'm good!");
        chatService.sendMessage(vasemir.getPhoneNumber(), geralt.getPhoneNumber(), "I'm good!");
        chatService.sendMessage(vasemir.getPhoneNumber(), geralt.getPhoneNumber(), "I'm good!");

        // Get messages for John
        List<Message> geraltMessages = getMessages(chatService, geralt);

        // Top 10 friends based on message exchange
        topFriends(chatService, geraltMessages, geralt, 3);

        // Get messages for John
        List<Message> yenneferMessages = getMessages(chatService, yennefer);

        // Top 10 friends based on message exchange
        topFriends(chatService, yenneferMessages, yennefer, 2);
    }

    private static void topFriends(ChatAppFeatures chatService, List<Message> geraltMessages, User geralt, int numberOfTopFriends) {
        List<User> topFriends = chatService.getTopFriends(geraltMessages, geralt.getPhoneNumber(), numberOfTopFriends);
        System.out.println("\nTop Friends of: "+ geralt.getName()+"\n");
        for (int i=1; i<=topFriends.size(); i++) {
            System.out.println(i+". Name: "+topFriends.get(i-1).getName()+", Phone Number: "+ topFriends.get(i-1).getPhoneNumber());
        }
    }

    private static List<Message> getMessages(ChatAppFeatures chatService, User geralt) {
        List<Message> geraltMessages = chatService.getMessagesForUser(geralt.getPhoneNumber());
        for (Message msg : geraltMessages) {
            System.out.println(msg.getSender().getName() + " to " + msg.getReceiver().getName() + ": " + msg.getText());
        }
        return geraltMessages;
    }
}
