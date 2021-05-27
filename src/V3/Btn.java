import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Btn{
    public static void main(String []args){
        JFrame f = new JFrame("事件监听测试");
        f.setBounds(0,0,300,400);
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                f.setVisible(false);
                f.dispose();
                System.exit(0);
            }
        });
        Container page = f.getContentPane();
        page.setLayout(new FlowLayout());
        JButton btn = new JButton("打印");

        btn.addActionListener((e)->{
            System.out.println("使用Lambda表达式优化");
        });
        page.add(btn);
        f.setVisible(true);
    }

    
    

}

class btnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("按钮被点击了----");
        }
    }