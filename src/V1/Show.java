import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.*;
 

class myFrame extends JFrame{
    public String cnt = "";
    public int total = 0;
    public myFrame(String title){
        super(title);
        // 内容面板
        Container contentPage = getContentPane();
        // 往内容面板添加控件
        contentPage.setLayout(new BorderLayout());
        JTextField text = new JTextField(20);
        contentPage.add(new JLabel("请输入要发送的内容"), BorderLayout.WEST);

        contentPage.add(text,BorderLayout.CENTER);
        JButton btn = new JButton("发送信息");

        contentPage.add( btn , BorderLayout.EAST);
        JTextArea area = new JTextArea(34, 30);
        area.setEditable(false);
        contentPage.add(area, BorderLayout.SOUTH);

        /**
         * 对事件按钮进行监听
         * 1. 创建监听器对象
         * 2. 把监听器注册给按钮
         */

         // 使用匿名内部类优化
        // ActionListener sl = new ActionListener(){

        btn.addActionListener((e)->{
            // System.out.println("发送按钮被点击\n"+e);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String time = sdf.format(new Date());
            
            String te = text.getText();
            te = te.length()==0 ?   "没有输入任何内容" : te; 
            // text.setText(time);
            // System.out.println(te);
            total = total>31 ? 0 : total+1;
            cnt = total == 0 ? "" : cnt;
            cnt += "("+ time +")" + " 客户端："  + te + "\n";
            area.setText(cnt);
            te = "";
            // 清除文本框内容
            text.setText(te);
        });
    }
}



public class Show{
    public static void main(String []args){
        myFrame f = new myFrame("聊天室客户端界面");

        // 设置布局管理器
        // f.setLayout(new BorderLayout());
        
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

    }
}