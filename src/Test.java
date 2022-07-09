import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) throws IOException {
//        LocalTime start = LocalTime.now();
        File folderInput_3 = new File("./input_3");
        Map<String, Integer> map = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (File file : folderInput_3.listFiles()) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
            for (String line = ""; line != null; line = bufferedReader.readLine()) {
                stringBuilder.append(line);
            }
        }
        StringTokenizer stringTokenizer = new StringTokenizer(stringBuilder.toString(), " .,!=+-");
        while (stringTokenizer.hasMoreTokens()) {
            map.merge(stringTokenizer.nextToken(), 1, (oldValue, value) -> oldValue + value);
        }
        List<Map.Entry<String, Integer>> listOfFrequency = new ArrayList<>(map.entrySet());
        listOfFrequency.sort((entry1, entry2) -> {
            return entry1.getValue() - entry2.getValue();
        });
        try {
            PrintWriter printWriter = new PrintWriter("./output_3.txt", StandardCharsets.UTF_8);
            printWriter.println("10 tu xuat hien it nhat");
            for (int i = 0; i < 10; i++) {
                Map.Entry<String, Integer> entry = listOfFrequency.get(i);
                printWriter.println(entry.getKey() + " xuat hien: " + entry.getValue());
            }
            printWriter.println("10 tu xuat hien nhieu nhat");
            for (int i = listOfFrequency.size() - 1; i >= listOfFrequency.size() - 10; i--) {
                Map.Entry<String, Integer> entry = listOfFrequency.get(i);
                printWriter.println(entry.getKey() + " xuat hien: " + entry.getValue());
            }
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        LocalTime end = LocalTime.now();
//        System.out.println(ChronoUnit.NANOS.between(start, end));
    }
}
