import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.net.Socket;

public class Send implements Runnable{
    private BufferedReader br;
    private DataOutputStream dos;
    private Boolean flag = true;
    private serverVisible f;
    public Send(){
        br = new BufferedReader(new InputStreamReader(System.in));
          
    }
    public Send(Socket socket, serverVisible s){
        this(); // 调用本类的无参构造函数
        f = s;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            //TODO: handle exception
            flag = false;
            e.printStackTrace();
            CloseUtil.closeAll(dos);
        }
        
    }
    private String getMessage(){
        String str = "";
        try {
            str = br.readLine();
        } catch (Exception e) {
            //TODO: handle exception
            flag = false;
            CloseUtil.closeAll(br);
        }
        return str;
    }
    private void send(String str){
        try {
//            System.out.println("客户端发送"+str);
            f.receive(str, "客户端");
            dos.writeUTF(str);
        } catch (Exception e) {
            //TODO: handle exception
            flag = false;
            CloseUtil.closeAll(dos);
        }
    }

    @Override
    public void run(){
        while(flag){
            // 调用发送的信息
            this.send(getMessage());
        }
    }
}