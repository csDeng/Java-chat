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


    public static void main(String args[]) {
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

            f = new serverVisible("聊天室服务器界面", socket, "服务端");
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

            Send send = new Send(socket, f);
            Receive receive  = new Receive(socket, f);
            new Thread(send).start();
            new Thread(receive).start();

        }catch(Exception e){
            System.out.println("Error:"+e);
            // 打印捕获到的异常
        }


    }
  }