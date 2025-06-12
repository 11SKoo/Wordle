import jp.ne.stars.hunikki.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class Wordle{
    static String ans;
    public static void main(String[] args){
        int[] att={1};

        JFrame jf=new JFrame("Wordle");
        jf.setSize(1000,1000);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);
        jf.getContentPane().setBackground(Color.white);

        
        word.words();
        ans=word.ans;
        used us=new used(ans);

        MyPanel jp=new MyPanel(500,600,ans);
        jp.setLocation(200, 50);
        KeyPanel keyjp=new KeyPanel();
  
        keyjp.setLocation(0, 700);
        keyjp.tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputS = keyjp.tf.getText();
                String upper = inputS.toUpperCase();

                if(att[0]<=6){
                    if(inputS.length()!=5){
                        keyjp.tx.setText("not 5 Words");
                        keyjp.tf.setText("");
                    }else{
                        if(word.checkW(upper)){
                            keyjp.tx.setText("");
                            us.dic(upper);
                            jp.setChar(upper, att[0]-1);
                            att[0]++;
                            keyjp.tf.setText("");
                            System.out.println("入力された内容: " + inputS);
                        }else{
                            keyjp.tx.setText("not exist");
                            keyjp.tf.setText("");
                        }
                    }    
                }else{
                    keyjp.tx.setForeground(Color.ORANGE);
                    keyjp.tx.setText("ANSWER"+"\""+ans+"\"");
                    keyjp.tx.setForeground(Color.blue);
                }
            }
        });
        keyjp.resb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.wordres();
                att[0]=1;
                keyjp.tx.setText("ANSWER"+"\""+ans+"\"");
                res();
                jp.ANS=ans;



                us.res();
                us.Ans=ans;
                jp.repaint();
                us.repaint();
            }
        });

        jf.add(jp);
        jf.add(keyjp);
        jf.setVisible(true);
                
        

        JDialog subWindow = new JDialog((Frame)null, "key", false);
        subWindow.setSize(410, 300);
        subWindow.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // 閉じられないようにする
        subWindow.setUndecorated(true);
        subWindow.setFocusableWindowState(false);
        subWindow.setAlwaysOnTop(false);
        subWindow.add(us);
        subWindow.setVisible(true);



    }
    public static void res(){
        word.words();
        ans=word.ans;
    }

}