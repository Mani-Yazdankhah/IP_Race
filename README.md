# Racing connection attempts to minimize connection latency

### This program uses dummy elements and fake data to simulate a network connection scenario. First a DNS lookup is performed to obtain a list of all available addresses for a domain, then concurrent connection attempts are made to minize latency.
### As soon as a connection has been established, the program sends and receives some data, before closing the connection.

# Classes:
## DNS.java:
This is a dummy DNS class that randomly returns between 1 and 15 IP addresses for a lookup prompt (using DNS.lookUp)

## Socket.java:
This class acts as a dummy socket that attempts to establish connections (using Socket.connect). If a successful connection was established, data can be sent (using Socket.send) or recieved (using Socket.receive). Finally the connection can be closed using Socket.disconnect.

## Main.java:
This is the main class which performs a lookup and starts the connection race.
