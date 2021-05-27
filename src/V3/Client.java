// 客户端
import java.io.*;
import java.net.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;




public class Client {
    public static void main(String args[]) {
      serverVisible f = new serverVisible("聊天室客户端界面");      
      // 设置窗口大小
      f.setBounds(0,0,800,600);
      

      // 关闭整体窗口
      f.addWindowListener(new WindowAdapter(){
          public void windowClosing( WindowEvent evt){
              f.setVisible(false);
              f.dispose();
              System.exit(0);
          }
      });
      f.setResizable(false);
      f.setVisible(true);

      try{
        Socket socket=new Socket("127.0.0.1",4700); 
        // 创建socket套接字并绑定ip127.0.0.1端口4700
        
        // 创建发送的线程
        Send send = new Send(socket);
        // 创建接收的线程
        Receive receive = new Receive(socket);

        new Thread(send).start();
        new Thread(receive).start();
        
      }catch(Exception e) {
        System.out.println("Error"+e); // 打印捕获到的异常
      }
  }
}