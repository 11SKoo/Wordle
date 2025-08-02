import javax.swing.*;
import java.awt.*;

public class Wordle{
    public static void main(String[] args){
        JFrame jf=new JFrame("WORDLE");
        jf.setSize(660,500);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setResizable(false);

        MainPanel mp=new MainPanel();
        jf.add(mp);
        
        mp.setLayout(null);



        JButton button = new JButton("RESET");
        button.setBounds(420, 200, 150, 40);
        button.setFont(new Font("Meiryo", Font.PLAIN, 16));         // 好きなフォントに変更
        button.setForeground(Color.GRAY);                           // 文字色をグレーに
        button.setBackground(new Color(0, 0, 0, 0));                 // 背景透明
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // グレーの枠線
        button.setFocusPainted(false);                              // フォーカス時の枠を非表示
        button.setContentAreaFilled(false);                         // 背景の塗り潰しなし
        // イベント処理
        button.addActionListener(e -> mp.Reset());
        
        
        
        mp.add(button);

        jf.setVisible(true);
        // ...existing code...
    }
}