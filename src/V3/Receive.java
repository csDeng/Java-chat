import java.io.Closeable;
import java.io.DataInputStream;
import java.net.Socket;

public class Receive implements Runnable {
    private DataInputStream dis;
    private Boolean flag = true;
    private void close(Closeable c){
        flag = false;
        CloseUtil.closeAll(c);
    }
    public Receive(Socket socket){
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
            System.out.println(getMessage());
        }
    }
}
