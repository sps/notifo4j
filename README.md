Notifo4j
--------

a java client for [Notifo](http://www.notifo.com/)

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


for the non-maven types, here are the required dependencies

* [notifo4j](http://github.com/sps/notifo4j/downloads)
* [gson 1.4](http://code.google.com/p/google-gson/downloads/list)
* [apache commons httpclient 4.0.1](http://hc.apache.org/downloads.cgi)
* [commons-logging 1.1.1](http://commons.apache.org/logging/download_logging.cgi)
* [commons-codec (commons-logging dependency)](http://commons.apache.org/codec/download_codec.cgi)
