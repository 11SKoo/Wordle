package jp.ne.stars.hunikki;

import javax.swing.*;
import java.awt.*;


public class MyPanel extends JPanel {
    String text[]=new String[6];
    int now=-1;
    int Width,Height;
    public String ANS;

    public MyPanel(int width, int height,String ans) {
        Width=width;
        Height=height;
        ANS=ans;
        setPreferredSize(new java.awt.Dimension(width, height));
        setSize(width, height);
        this.setBackground(Color.white);

        text = new String[6];
        for (int i = 0; i < text.length; i++) {
            //text[i] = "□□□□□□"; // 空文字列で初期化
            text[i]="";
        }
    }

    public void setChar(String str, int row) {
        text[row]=str;
        repaint();
    }

    public Color checkC(char c,int i){
    if (i >= ANS.length()) {
        return Color.GRAY; // 安全なデフォルト値
    }
        if(c==ANS.charAt(i)){
            return Color.GREEN;
        }else if(ANS.indexOf(c) != -1){
            return Color.ORANGE;
        }else{
            return Color.gray;
        }
    }
    public void wordres(){
        for (int i = 0; i < text.length; i++) {
            //text[i] = "□□□□□□"; // 空文字列で初期化
            text[i]="";
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        
        for (int i = 0; i < text.length; i++) {
            for (int e = 0; e < text[i].length(); e++) {
                int x = (Width / 5) * e;
                int y = (Height / 6) * i + 50;

                // 四角形を先に描画
                g.setColor(checkC(text[i].charAt(e), e));
                g.fillRect(x+5, y - 45, 90, 90); // 高さを調整して文字が中央に来るように

                // 文字を上に描画
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(text[i].charAt(e)), x + 20, y+20); // Xを少しずらして中央寄りに
            }
        }

    }
}
