import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.util.Log;




/**
 * @Class Client
 * @brief This Class allows the communication with Unity throw sockets.
 */
public class Client implements Runnable {

	private String messageToSend ="";
	private String messageReceived = "";
	String SERVERIP = "127.0.0.1";
	int SERVERPORT = 6001;
	
	
	public void run() {
		// TODO Auto-generated method stub
		try {
			
            // Retrieve the ServerName
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);
            
            Log.d("UDP", "C: Connecting...");
            
            
            /* Create new UDP-Socket */
            DatagramSocket socket = new DatagramSocket(6000);
            
            messageToSend = messageToSend +"\0";
            
            /* Prepare some data to be sent. */
            byte[] buf = (messageToSend).getBytes();
            
            /* Create UDP-packet with
             * data & destination(url+port) */
            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, SERVERPORT);
            Log.d("UDP", "C: Sending: '" + messageToSend + "'");
            
            /* Send out the packet */
            socket.send(packet);
            Log.d("UDP", "C: Sent.");
            Log.d("UDP", "C: Done.");
            
            
            socket.close();

       } catch (Exception e) {
            Log.e("UDP", "C: Error", e);
       } 
	}

	
	
	public void setMessage(String s){
		messageToSend = s;
	}
}