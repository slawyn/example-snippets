import os
import sys
import time
import socket
import threading

def receiveUDP():
    pass
 
def sendUDP():
    pass
 
localIP     = "127.0.0.1"
localPort   = 2013
bufferSize  = 1

 
msgFromServer       = "Hello UDP Client"
bytesToSend         = str.encode(msgFromServer)
id_distribution_packet  = b"\x00\x03\x01\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00"
sync_packet  =            b"\x00\x01\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00"

# Create a datagram socket
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# Bind to address and ip

print("TCP connecting")

try:
    s.connect((localIP, localPort))
except Exception as e:
    print(type(e))
    print(e)
print("TCP connected!!!")



start = 0
# echo loop
try:
    while(True):
        inp = input()
        s.send(inp.encode())  # send id 
        message = s.recv(1)
   
        #print(") " +str((time.time()-start)*1000)+"ms ")
        print(message)

        # Sending a reply to client
        start = time.time()
except Exception as e:
    print(e)
    os.system("PAUSE") 
    
   