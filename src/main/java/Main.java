import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int max = 0;
        List<FutureTask<Integer>> threads = new ArrayList<>();
        for (int thr = 0; thr < 25; thr++) {
            final Callable callable = new MyCallable();
            final FutureTask<Integer> stringFutureTask = new FutureTask<>(callable);
            Thread thread = new Thread(stringFutureTask);
            thread.start();
            threads.add(stringFutureTask);
        }

        for (FutureTask<Integer> ftask : threads) {
            int fromThread = ftask.get(); // зависаем, ждём когда поток объект которого лежит в thread завершится
            if(fromThread > max) {
                max = fromThread;
            }
        }

        System.out.println("Максимальное значение " + max);
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
