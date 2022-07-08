import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Bai2 {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./input_2/01.txt", StandardCharsets.UTF_8));
        for (String line = ""; line != null; line = bufferedReader.readLine()) {
            stringBuilder.append(line).append("\n");
        }
        StringTokenizer stringTokenizer = new StringTokenizer(stringBuilder.toString(), ".,!=+-");
        while (stringTokenizer.hasMoreTokens()) {
            map.merge(stringTokenizer.nextToken(), 1, (oldValue, value) -> oldValue + value);
        }
        PrintWriter printWriter = new PrintWriter("./input_2/output.txt", StandardCharsets.UTF_8);
        for (String key : map.keySet()) {
            String text = "So lan xuat hien cua " + '"' + key + '"' + ": " + map.get(key);
            printWriter.println(text);
        }
        printWriter.flush();
        printWriter.close();
    }
}
