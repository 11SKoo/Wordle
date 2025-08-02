import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

//main panel
public class MainPanel extends JPanel implements KeyListener{
    // 定数
    private static final int ROWS = 6;
    private static final int COLS = 5;
    private static final int CELL_SIZE = 60;
    private static final int CELL_MARGIN = 65;
    private static final int BOARD_WIDTH = 500;
    private static final int CHAR_Y_ADJUST = -5; // 文字のY座標微調整

StringBuilder sb;
String[] wordlist = new String[6];
int nowline = 0;
Words ws;

String message="";


    MainPanel(){
        //setBounds(0,0,100,100);
        sb = new StringBuilder("");
        String str = sb.toString();
        
        ws=new Words();
        
        
        for (int i = 0; i < wordlist.length; i++) {
            wordlist[i] = "";
        }
        setFocusable(true); 
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        g.setFont(new Font("SansSerif", Font.BOLD, 55));
        FontMetrics fm = g.getFontMetrics();
        //int offsetX = (BOARD_WIDTH - CELL_MARGIN * COLS) / 2;
        int offsetX =10;
        
        for(int i = 0; i < ROWS; i++) {
            for(int e = 0; e < COLS; e++) {
                
                char ch = ' ';
                if (wordlist[i].length() > e) {
                    ch = wordlist[i].charAt(e);
                }


                int rectX = e * CELL_MARGIN + offsetX;
                int rectY = i * CELL_MARGIN;
                
                g.drawRect(rectX, rectY, CELL_SIZE, CELL_SIZE);

                if(nowline>i){
                    g.setColor(ws.checkC(ch,e));
                    g.fillRect(rectX, rectY, CELL_SIZE, CELL_SIZE);
                }
                
                int charWidth = fm.charWidth(ch);
                int charHeight = fm.getAscent();
                int x = rectX + (CELL_SIZE - charWidth) / 2;
                int y = rectY + (CELL_SIZE + charHeight) / 2 + CHAR_Y_ADJUST;
                
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(ch), x, y);
                g.setColor(Color.GRAY);
            }
        }

        g.setColor(Color.DARK_GRAY);
        g.drawRect(CELL_MARGIN*5+10,0,310-2 , 450);
        g.setFont(new Font("SansSerif", Font.BOLD, 28));
        g.drawString(message, 20, 500-60);

        //keyboard
        String[] key={"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"};
        g.setFont(new Font("SansSerif", Font.BOLD, 28));
        for(int i=0;i<key.length;i++){
            for(int e=0;e<key[i].length();e++){
                g.drawRect(4+e*30+CELL_MARGIN*5+10+i*10,10+i*30,29,29);
                
                    g.setColor(CharIn(key[i].charAt(e)));
                    g.fillRect(4+e*30+CELL_MARGIN*5+10+i*10,10+i*30,29,29);
                
                g.setColor(Color.DARK_GRAY);
                g.drawString(String.valueOf(key[i].charAt(e)),4+e*30+CELL_MARGIN*5+10+i*10+3,10+i*30+25);
                g.setColor(Color.gray);
            }
        }
    }

    public Color CharIn(char c){
        if(nowline-1<0)return new Color(0, 0, 0, 0);
        for(int i=nowline-1;i>=0;i--){
            if(wordlist[i].indexOf(c)>=0){
                return ws.checkC(c, wordlist[i].indexOf(c));
            }
        }
        return new Color(0, 0, 0, 0);
    }
    public void Reset(){
        message = "ANSWER was *"+ws.ans+"*";   // メッセージクリア
        sb.setLength(0); // 入力欄クリア
        ws.Reset();      // Words状態リセット
        nowline = 0;     // 行番号リセット
        for (int i = 0; i < wordlist.length; i++) {
            wordlist[i] = "";
        }
        repaint();       // 画面更新
        setFocusable(true);
        requestFocusInWindow();
    }
//key
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        }else if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(nowline<6) 
                if(sb.length()<5){
                    message="Not enough letters";
                }else{
                    if(ws.WExist(sb.toString())){
                        message="";
                        nowline++;
                        sb.setLength(0);
                    }else{
                        message="Not in word list";
                    }
                }
            else{
                message="ANSWER is *"+ws.ans+"*";
            }
            repaint();
        }else{
            char c = e.getKeyChar();
            if ((Character.isLetter(c))&&(sb.length()<5)){ // アルファベットのみ追加
                sb.append(Character.toUpperCase(c));
            }
        }
        if(nowline<=5){
        wordlist[nowline]=sb.toString();
        }
        repaint();
    }



    @Override
    public void keyReleased(KeyEvent e) {
    
    }
}