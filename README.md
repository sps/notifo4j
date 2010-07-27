Notifo4j
--------

a java client for Notifo (http://www.notifo.com/)

<code>
NotifoClient client = new NotifoHttpClient("username", "apikey");

// send yourself a message


client.sendMessage("here is my message");


// send someone a message

client.sendMessage("username", "here is my message");

// control all available aspects of the message

NotifoMessage message = new NotifoMessage("username", "message body");

message.setSubject("custom subject");

message.setLabel("custom label");

message.setUrl("custom url");

client.sendMessage(message);

</code>


