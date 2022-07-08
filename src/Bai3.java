import javax.swing.plaf.ComponentUI;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

public class Bai3 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(6);
        File folderInput_3 = new File("./input_3");
        Map<String, Integer> map = new ConcurrentHashMap<>();
        List<CompletableFuture<Void>> listOfFuture = new ArrayList<>();

        for (File file : folderInput_3.listFiles()) {
            listOfFuture.add(CompletableFuture.runAsync(() -> {
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bufferedReader = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
                    for (String line = ""; line != null; line = bufferedReader.readLine()) {
                        stringBuilder.append(line);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                StringTokenizer stringTokenizer = new StringTokenizer(stringBuilder.toString(), " .,!=+-");
                while (stringTokenizer.hasMoreTokens()) {
                    map.merge(stringTokenizer.nextToken(), 1, (oldValue, value) -> oldValue + value);
                }
            }, threadPool));
        }

        CompletableFuture<Void> finalTask = CompletableFuture.allOf(listOfFuture.toArray(new CompletableFuture[0]));
        finalTask.thenAccept(res -> {
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
            System.out.println(listOfFrequency.get(listOfFrequency.size() - 1).getValue());
        });
        threadPool.shutdown();
    }
}
