import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import java.awt.Color;

public class Words {
    String ans="";
    String[] array;
    Words(){
            try {
            // JARファイルのあるディレクトリを取得
            Path jarDir = Paths.get(System.getProperty("user.dir"));

            // 相対パスでファイルを指定（例: data/config.txt）
            Path filePath = jarDir.resolve("sample.txt");
            // すべての行を読み込み List に格納
            List<String> lines = Files.readAllLines(filePath);
            // List → 配列へ変換
            array = lines.toArray(new String[0]);
            // ...existing code...

        } catch (IOException e) {
            // ...existing code...
        }
        Reset();
        // ...existing code...

    }
    void Reset(){
        ans= array[new java.util.Random().nextInt(array.length)];
    }

    Color checkC(char ch,int index){
        if(ch==ans.charAt(index)){
            return Color.GREEN;
        }else if(ans.indexOf(ch) >=0){
            return Color.ORANGE;
        }
        return Color.gray;
    }
    boolean WExist(String str){
        return Arrays.asList(array).contains(str);
    }
}
