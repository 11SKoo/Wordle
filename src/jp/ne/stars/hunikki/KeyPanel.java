package jp.ne.stars.hunikki;

import javax.swing.*;
import java.awt.*;


public class KeyPanel extends JPanel {
    public TextField tf;
    public Label tx;
    public Button resb;

    public KeyPanel(){
        this.setLayout(null);
        this.setSize(1000, 300);
        this.setBackground(Color.lightGray);

        Label lb=new Label("Type 5 words");
        lb.setBounds(350,5,500,50);
        lb.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(lb);

        tf = new TextField(1);
        tf.setFont(new Font("Arial", Font.BOLD, 24));
        tf.setBounds(350,70,150,50);
        this.add(tf);

        tx = new Label("");
        tx.setForeground(Color.blue);
        tx.setFont(new Font("Arial", Font.BOLD, 24));
        tx.setBounds(350,130,250,50);
        this.add(tx);

        resb =new Button("RESET");
        resb.setBounds(700,70,150,50);
        resb.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(resb);
    }
}
