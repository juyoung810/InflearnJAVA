package Project06_Socket;
import java.io.*;
import java.net.*;
import java.util.*;
public class Project06F_MultiChatServer {
    HashMap clients;
    // 생성자
    Project06F_MultiChatServer() {
        clients = new HashMap();
        // 한 thread가 hashMap 사용하고 있을 때 다른 thread가 사용하지 못하도록 하기 위해
        Collections.synchronizedMap(clients);
    }
    public void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try{
            serverSocket = new ServerSocket(9999);
            System.out.println("start server...");
            while(true) {
                // client의 정보 가진다.
                socket = serverSocket.accept();
                System.out.println(socket.getInetAddress()+":"+
                        socket.getPort()+" connect!");
                // thread에 client의 정보 받아서 브로드 캐스팅 할 수 있도록 한다.
                ServerReceiver thread = new ServerReceiver(socket);
                thread.start(); // run()
            }
        }catch(Exception e) {e.printStackTrace();}
    }
    void sendToAll(String msg) {//브로드캐스팅 기능
        Iterator iterator = clients.keySet().iterator(); // 연결된 모든 사용자에게 읽어드린 data 브로드 캐스팅
        while(iterator.hasNext()) {
            try {
                DataOutputStream out =
                        (DataOutputStream)clients.get(iterator.next());
                out.writeUTF(msg);
            }catch(IOException e) {e.printStackTrace();}
        }
    }
    public static void main(String[] args) {
        new Project06F_MultiChatServer().start();
    }
    //inner class
    class ServerReceiver extends Thread {
        Socket socket; DataInputStream in; DataOutputStream out;
        ServerReceiver(Socket socket) {
            this.socket = socket;
            try{
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            }catch(Exception e) {e.printStackTrace();}
        }
        public void run() {
            String name = "";
            try{
                name = in.readUTF(); // park, lee
                if (clients.get(name) != null) {//같은 이름사용자 존재
                    out.writeUTF("#Aleady exist name : "+name);
                    out.writeUTF("#Please reconnect by other name");
                    System.out.println(socket.getInetAddress()+":"+
                            socket.getPort()+" disconnect!");
                    in.close();
                    out.close();
                    socket.close(); // 해당 client 소켓 종료
                    socket = null;
                } else {//같은 이름 존재하지 않는 경우
                    sendToAll("#"+name+" join!");
                    clients.put(name, out); //hashmap에 해당 사용자의 outputstream 생성
                    while(in != null) { sendToAll(in.readUTF()); } // data 계속 읽어서, 보낸다.
                }
            }catch(IOException e) { e.printStackTrace();
            }finally{
                if (socket != null) { // client에서 끊어진 소켓 서버쪽에서도 끊기 위해
                    sendToAll("#"+name+" exit!");
                    clients.remove(name);
                    System.out.println(socket.getInetAddress()+":"+
                            socket.getPort()+" disconnect!");
                }
            }
        }
    }
}
