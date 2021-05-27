import java.io.Closeable;
import java.io.DataInputStream;
import java.net.Socket;

public class Receive implements Runnable {
    private DataInputStream dis;
    private Boolean flag = true;
    private serverVisible f;
    private void close(Closeable c){
        flag = false;
        CloseUtil.closeAll(c);
    }
    public Receive(Socket socket, serverVisible s){
        f = s;
        try {
            dis  = new DataInputStream(socket.getInputStream());

        } catch (Exception e) {
            //TODO: handle exception
            close(dis);
        }
    }
    private String getMessage(){
        String str = "";
        try {
            str = dis.readUTF();
        } catch (Exception e) {
            //TODO: handle exception
            close(dis);
        }
        return str;
    }
    @Override
    public void run(){
        while(flag){
//            System.out.println("收到服务器信息"+getMessage());
            f.receive(getMessage(),"服务器");
        }
    }
}


// TODO 线程出现死循环