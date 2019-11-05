# Import socket module 
import socket                
  
# Create a socket object 
s = socket.socket()          
  
# Define the port on which you want to connect 
port = 6000                
  
# connect to the server on local computer 
s.connect(('192.168.4.1', port)) 
s.send(b'data')
print('Connected')
# receive data from the server 
message = (s.recv(1024))
print(message)
if message != 'NaN':
    message = message.decode("utf-8")
    file = open('people_in_need.txt', 'a')
    file.write(message)
    file.close()
    #print (s.recv(1024)) 
    # close the connection 
s.close()    