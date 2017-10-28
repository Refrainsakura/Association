package association;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataRead {

    public static List<String> CSVtoList(String filename) {
        File csv = new File(filename);
        BufferedReader br = null;
        List<String> res = new ArrayList<>();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(csv)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        try {
            List<String> allString = new ArrayList<>();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;
                String[] words = everyLine.split(","); //讲每一行的字符串以逗号隔开
                for (int i = 0; i < words.length; i++) {
                    allString.add(words[i]);
                    res = allString; //将表格中的每个单元格内的字符加入列表
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
