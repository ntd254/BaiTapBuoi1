import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Bai2 {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./input_2/01.txt", StandardCharsets.UTF_8));
        for (String line = ""; line != null; line = bufferedReader.readLine()) {
            stringBuilder.append(line);
        }
        StringTokenizer stringTokenizer = new StringTokenizer(stringBuilder.toString(), ".,!=+- ");
        while (stringTokenizer.hasMoreTokens()) {
            map.merge(stringTokenizer.nextToken(), 1, (oldValue, value) -> oldValue + value);
        }
        PrintWriter printWriter = new PrintWriter("./output_2.txt", StandardCharsets.UTF_8);
        for (String key : map.keySet()) {
            String text = "So lan xuat hien cua " + '"' + key + '"' + ": " + map.get(key);
            printWriter.println(text);
        }
        List<Map.Entry<String, Integer>> listOfFrequency = new ArrayList<>(map.entrySet());
        listOfFrequency.sort((entry1, entry2) -> {
            return entry1.getValue() - entry2.getValue();
        });
        System.out.println(listOfFrequency.get(listOfFrequency.size()-1).getKey() + " " + listOfFrequency.get(listOfFrequency.size()-1).getValue());
        printWriter.flush();
        printWriter.close();
    }
}
