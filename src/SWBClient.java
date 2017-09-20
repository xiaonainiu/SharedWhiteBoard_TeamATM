import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by ES on 2017/9/14.
 */
public class SWBClient {
//    InputStream is = new InputStream();
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;

    public static void main(String args[]){
        /**
         * by ES
         * Client
         * start
         */
//        initialize GUI
        SWBClient client = new SWBClient();
//        SWBClient_GUI gui = new SWBClient_GUI();

        try{
            while (true){
//                socket = this.socket;
                client.socket = new Socket("127.0.0.1", 1234);
                client.dis = new DataInputStream(client.socket.getInputStream());
                client.dos = new DataOutputStream(client.socket.getOutputStream());
                String str = null;
                if ((str = client.dis.readUTF()) != null){
                    //get the command and make operation
                    System.out.println();
                }
                client.dis.close();
                client.socket.close();
            }
        }catch (UnknownHostException e){
            e.printStackTrace();

        }catch (SocketException e){
            e.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();

        }
    }
}
