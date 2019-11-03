# Import socket module 
import socket                
  
# Create a socket object 
s = socket.socket()          
  
# Define the port on which you want to connect 
port = 6000                
  
# connect to the server on local computer 
s.connect(('192.168.4.1', port)) 
s.send(b'Hello World!!!')
# receive data from the server 
message = (s.recv(1024))
print(message)
message = message.decode("utf-8")
file = open('people_in_need.txt', 'w')
file.write(message)
file.close()
#print (s.recv(1024)) 
# close the connection 
s.close()    