package jp.ne.stars.hunikki;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class word {
    public static String[] data;
    public static String ans;

public static void words() {
    System.out.println("Current Directory: " + System.getProperty("user.dir"));

    // JAR内のリソースを取得
    InputStream in = word.class.getClassLoader().getResourceAsStream("words.txt");

    if (in == null) {
        System.out.println("Error: words.txt が見つかりません！（JAR内）");
        return;
    }

    String fileContent = readFile("words.txt"); // まずは String で受け取る
    data = fileContent.split("\n"); // 改行で分割して String[] に変換

    if (data.length == 0) {
        System.out.println("Error: words.txt の内容が空です！");
        return;
    }

    Random rand = new Random();
    int ra = rand.nextInt(data.length); // 0 〜 data.length - 1 の乱数
    ans = data[ra];
    //System.out.println("選ばれた単語: " + ans);
}

    /*public static String[] readFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        return lines.toArray(new String[0]); // 配列に変換して返す
    }*/

    public static boolean checkW(String w){
        boolean exist=false;
        for (String line : data) {
            if(line.equals(w))exist=true;
        }
        return exist;
    }
    
public static String readFile(String fileName) {
    StringBuilder data = new StringBuilder();

    try (InputStream in = word.class.getClassLoader().getResourceAsStream(fileName);
         BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

        if (in == null) {
            System.out.println("Error: " + fileName + " が見つかりません！（JAR内）");
            return "";
        }

        String line;
        while ((line = reader.readLine()) != null) {
            data.append(line).append("\n");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return data.toString();
}
}