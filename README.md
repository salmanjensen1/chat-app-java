Algorithm:

1. A User is created based on Name and PhoneNumber only
2. Messages are created by Two Users, Timestamp and the text itself. The Timestamp is later used to sort the latest messages
3. ChatAppFeatures Class has the following features (User Creation, Message Sending, Get Message List for a specific person, Get Top Frequent People Messaged)
4. (User Creation) => A map is maintained with phoneNumbers as Key. Before creating a user it is ensured that the key (phone number) is Unique.
5. (Message Sending) => To send a message, we simply maintain a List of All Messages. Input validation is done to see if sender or receiver isnt Null.
6. (Get Message List for a specific person_ => Given the phone number of a specific person, All Message is iterated. If the number is found either as sender or receiver, it is pushed in the new Message List. The 
   List is then sorted based on Descending Order Timestamp. (newest message first)
7. (Get Top Frequent People Messaged) => The Message List retrieved previously is passed to this function. A new map with phoneNumbers as key and number of messages as Value is created. The phone number where
   a message is sent or received from by the Host is identified by iterating over the message list. Total message count is stored against that phonenumber.
8. (Continued) => Given a the Top Number of people given as input, we sort the List (converted from Map) of Top Frequent People in Descending order. A list of user is returned based on the number given
   input number. Or if the list is shorter we terminate early.
