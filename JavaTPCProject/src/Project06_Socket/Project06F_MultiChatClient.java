package Project06_Socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class Project06F_MultiChatClient {
    public static void main(String[] arg) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            Scanner scanner = new Scanner(System.in);
            System.out.print("name:");
            String name = scanner.nextLine();
            Thread sender = new Thread(new ClientSender(socket,name)); // 이름도 보내야 한다.
            Thread receiver = new Thread(new ClientReceiver(socket));
            sender.start();
            receiver.start();
        } catch(Exception e) { e.printStackTrace(); }
    }
    // inner class
    static class ClientSender extends Thread {
        Socket socket; DataOutputStream out; String name;
        ClientSender(Socket socket, String name) {
            this.socket = socket; this.name = name;
            try {
                out = new DataOutputStream(socket.getOutputStream()); // server에 데이터 보내기 위해
            }catch(Exception e) { e.printStackTrace(); }
        }
        public void run() {
            Scanner scanner = new Scanner(System.in);
            try{
                if (out != null) out.writeUTF(name); // 처음에 닉네임 보내기 위해
                while(out != null) {
                    String message = scanner.nextLine();
                    if (message.equals("quit")) break; // 서버와 연결 끊어진다.
                    out.writeUTF("["+name+"]"+message); // 메세지 보냄
                }
                out.close();
                socket.close();// quit 소켓 끊어진다.
            }catch(Exception e) { e.printStackTrace(); }
        }
    }
    static class ClientReceiver extends Thread {
        Socket socket; DataInputStream in;
        ClientReceiver(Socket socket) {
            this.socket = socket;
            try{
                in = new DataInputStream(socket.getInputStream()); // data 읽는다.
            }catch(Exception e) { e.printStackTrace(); }
        }
        public void run() {
            while(in != null) { // data 계속 읽어오고 있으면
                try{
                    System.out.println(in.readUTF());
                } catch(Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            try {
                in.close();
                socket.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}