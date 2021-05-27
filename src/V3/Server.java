// 服务器端程序
import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.*;
import java.io.*;
import java.net.*;


public class Server{
    public static String send = "";
    public static serverVisible f = null;
    public static Boolean flag = false;            // get到文本就true
    public static GET g;

//    内部类开线程
static class GET implements Runnable{
      @Override
      public void run(){
        send =f.getTe();
        flag = true;
  }
}
    public static void main(String args[]) {
      f = new serverVisible("聊天室服务器界面");
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
      g = new GET();
      new Thread(g).start();

      try{
        ServerSocket server=null;
        try{ 
          // 创建Server套接字并绑定端口4700
          server=new ServerSocket(4700);        
        }catch(Exception e) {
          System.out.println("can not listen to:"+e); 
        // 打印捕获到的异常
        }
        Socket socket=null;
        try{
          socket=server.accept();   //  尝试监听客户端链接请求  
        }catch(Exception e) {
          System.out.println("Error."+e);  // 打印捕获到的异常
        }
        String line;

        DataInputStream dis;

        // 接收别人发来的信息
        dis =new DataInputStream(socket.getInputStream());

         //通过管道给别人发送信息
          DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
         //创建socket提供的getOutputStream实例对象

        // BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
        //创建输入流实例
        // System.out.println("Client:"+is.readLine());
        f.receive(dis.readUTF(), "lai客户端");
        // 打印自己输入的信息
//        line = "我爱你";
//          dos.writeUTF(line);
        // 读取输入流内容
        while(true){
        // 如果读取到的内容不是"bye"进入循环
//          os.println(line);
//          //打印读取到的内容
//          os.flush();
          //清除输出流缓存
          // System.out.println("Server:"+line);
          if(flag){
            dos.writeUTF(send);
            f.receive(send, "服务端");
            //打印服务端的输入内容

            // System.out.println("Client:"+is.readLine());
            f.receive(dis.readUTF(), "客户端");
            // 打印客户端的输入内容
          }



          // line 是自己输入要发送给别人的
//          line = f.getTe();
          // 读取下一行文本
          flag = false;
        }
//        os.close(); // 关闭socket相关联的输出流
//        dis.close(); // 关闭socket相关联的输入流
//        socket.close(); // 关闭socket
//        server.close(); // 关闭serverSocket
      }catch(Exception e){
        System.out.println("Error:"+e); 
        // 打印捕获到的异常
      }
    }
  }