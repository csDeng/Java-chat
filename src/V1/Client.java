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
        BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
        //创建输入流实例

        PrintWriter os=new PrintWriter(socket.getOutputStream());
        // 创建输出流实例

        BufferedReader is=new BufferedReader(
new InputStreamReader(socket.getInputStream()));
        // 创建socket提供的getInputStream实例对象

        String readline;
        readline=sin.readLine(); // 读取一行输入流
        while(!readline.equals("bye")){ 
        // 当读取到的输入不为"bye"的时候
          os.println(readline); 

          // 打印读取到的内容
          os.flush(); 

          // 清空输出缓存
          
          // System.out.println("Client:"+readline); 
          f.receive(readline,"客户端");
          
          // 打印客户端读到的内容

          // System.out.println("Server:"+is.readLine()); 
          f.receive(is.readLine(),"服务端");
          
          // 打印服务端收到的内容
          readline=sin.readLine(); // 读取下一行内容
          
        } 
        os.close(); // 关闭socket相关联的输出流
        is.close(); // 关闭socket相关联的输入流
        socket.close(); //
      }catch(Exception e) {
        System.out.println("Error"+e); // 打印捕获到的异常
      }
  }
}