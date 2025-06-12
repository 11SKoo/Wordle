package jp.ne.stars.hunikki;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class used extends JPanel {
    public Map<Character, Integer> wordMap;
    public String Ans;
    public char[] keys = {
        'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P',
        'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L',
        'Z', 'X', 'C', 'V', 'B', 'N', 'M'
    };

    public used(String ans) {
        Ans = ans;
        wordMap = new HashMap<>();
        res();

        /* 0: not used
         * 1: gray
         * 2: orange
         * 3: green
         */
    }

    public void res() {
        wordMap.clear();
        for (char c = 'A'; c <= 'Z'; c++) {
            wordMap.put(c, 0);
        }
    }

    public void dic(String u) {
        for (int i = 0; i < u.length(); i++) {
            char c = u.charAt(i);
            if (c == Ans.charAt(i)) {
                wordMap.put(c, 3); // 緑
            } else if (Ans.indexOf(c) != -1) {
                // すでに緑でなければオレンジ
                if (wordMap.get(c) < 2) {
                    wordMap.put(c, 2); // オレンジ
                }
            } else {
                // まだ塗られていない場合だけグレー
                if (wordMap.get(c) == 0) {
                    wordMap.put(c, 1); // グレー
                }
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Arial", Font.BOLD, 16));

        int x = 10, y = 30;
        int keyWidth = 35, keyHeight = 35;
        int spacing = 5;

        for (int i = 0; i < keys.length; i++) {
            int colorCode = wordMap.getOrDefault(keys[i], 0);
            switch (colorCode) {
                case 0 -> g.setColor(Color.white);
                case 1 -> g.setColor(Color.gray);
                case 2 -> g.setColor(Color.orange);
                case 3 -> g.setColor(Color.green);
            }

            g.fillRect(x, y, keyWidth, keyHeight);
            g.setColor(Color.black);
            g.drawRect(x, y, keyWidth, keyHeight);
            g.drawString(String.valueOf(keys[i]), x + 12, y + 25);

            x += keyWidth + spacing;
            if (i == 9 || i == 18) {
                x = 40;
                y += keyHeight + spacing;
            }
        }
    }
}