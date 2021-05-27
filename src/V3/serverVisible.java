// 服务器端程序
import java.awt.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class serverVisible extends JFrame{
    public String cnt = "";
    public int total = 0;
    public JTextArea area;
    public String te;
    public JTextField text;
    public serverVisible(String title){
        super(title);
        // 内容面板
        Container contentPage = getContentPane();
        // 往内容面板添加控件
        contentPage.setLayout(new BorderLayout());
        text = new JTextField(20);
        contentPage.add(new JLabel("请输入要发送的内容"), BorderLayout.WEST);
  
        contentPage.add(text,BorderLayout.CENTER);
        JButton btn = new JButton("发送信息");
  
        contentPage.add( btn , BorderLayout.EAST);
        area = new JTextArea(34, 30);
        area.setEditable(false);
        contentPage.add(area, BorderLayout.SOUTH);
        
        btn.addActionListener((e)->{
            // System.out.println("发送按钮被点击\n"+e);
            te = text.getText();
            this.check("服务端");
        });
    };
    public String getTe(){
      te = text.getText();
      return te;
    }
    public void receive(String s, String name){
      te = s;
      this.check(name);
    }
    public String getTime(){
      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
      String time  = sdf.format(new Date());
      return time;
    }
    private void check(String name){
  
            te = te.length()==0 ?   "没有输入任何内容" : te; 
            // text.setText(time);
            // System.out.println(te);
            total = total>31 ? 0 : total+1;
            cnt = total == 0 ? "" : cnt;
            cnt += "("+ getTime() +")" + name + " ："  + te + "\n";
            area.setText(cnt);
            te = "";
            // 清除文本框内容
            text.setText(te);
    }
  }
  