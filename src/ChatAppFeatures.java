import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ChatAppFeatures {
    //a map to store users where the key are their phone numbers
    private Map<String, User> users = new HashMap<>();
    private List<Message> messages = new ArrayList<>();


    public User createUser(String name, String phoneNumber) { // create users only if the phone number is unique
        if (!users.containsKey(phoneNumber)) {
            User user = new User(name, phoneNumber);
            users.put(phoneNumber, user);
            return user;
        } else {
            return users.get(phoneNumber); // Return existing user if they already exist
        }
    }

    public void sendMessage(String senderPhone, String receiverPhone, String text) {
        User sender = users.get(senderPhone);
        User receiver = users.get(receiverPhone);

        if (sender == null || receiver == null) {
            throw new Error("Missing Sender or Receiver");
        }
        //else create the message and add to the List of all Messages
        Message message = new Message(sender, receiver, text);
        messages.add(message);
    }

    // returns the list of messages of a particular user based on phoneNumber
    public List<Message> getMessagesForUser(String userPhone) {
        List<Message> userMessages = new ArrayList<>();

        for (Message message : messages) {
            if (message.getSender().getPhoneNumber().equals(userPhone) || message.getReceiver().getPhoneNumber().equals(userPhone)) {
                userMessages.add(message);
            }
        }

        // Sort messages in descending order, timestamps that are larger are put first
        userMessages.sort((m1, m2) -> m2.getTimestamp().compareTo(m1.getTimestamp()));

//        for (Message m : userMessages) {
//            System.out.println(m.getTimestamp() + " ");
//        }
//        System.out.println("\n");

        return userMessages;
    }

    //returns a sorted List of a person with given phone number for the most Exchanged Messages in the MessageList
    public List<Map.Entry<String, Integer>> createFrequestMessageList(List<Message> messageList, String phoneNumber) {
        Map<String, Integer> messageCountMap = new HashMap<>();

        // Count number of messages between each pair
        for (Message message : messageList) {
            String senderPhone = message.getSender().getPhoneNumber();
            String receiverPhone = message.getReceiver().getPhoneNumber();
            //creates a key of two phone numbers number1:number2 lexicographically where number1 < number2
            String friendNumber = senderPhone.equals(phoneNumber) ? receiverPhone : senderPhone;
//            System.out.println(friend);
            messageCountMap.put(friendNumber, messageCountMap.getOrDefault(friendNumber, 0) + 1); //increment map[friend] by 1
        }

        //converting the map to list for sorting
        List<Map.Entry<String, Integer>> sortedFriends = new ArrayList<>(messageCountMap.entrySet());
        sortedFriends.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue())); // Sort by value (message count)
        return sortedFriends;
    }

    //given the number of top friends you want to see, iterates a sorted list for most frequent messages exchanged
    public List<User> getTopFriends(List<Message> messageList, String phoneNumber, int numberofTopFriends) {
        List<Map.Entry<String, Integer>> sortedFriends = createFrequestMessageList(messageList, phoneNumber);

        // Get the top 10 friends
        List<User> topFriends = new ArrayList<>();
        for (int i = 0; i < Math.min(numberofTopFriends, sortedFriends.size()); i++) {
            String friendPhone = sortedFriends.get(i).getKey();
//            int mssgCount = sortedFriends.get(i).getValue();
//            System.out.println(friendPhone);
//            System.out.println(mssgCount);
            User friend = users.get(friendPhone);
            topFriends.add(friend);
        }

        return topFriends;

    }
}
