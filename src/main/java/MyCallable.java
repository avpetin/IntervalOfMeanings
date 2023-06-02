import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    @Override
    public Object call() {
        String text = Main.generateText("aab", 30_000);
        long startTs = System.currentTimeMillis(); // start time
        int maxSize = 0;
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < text.length(); j++) {
                if (i >= j) {
                    continue;
                }
                boolean bFound = false;
                for (int k = i; k < j; k++) {
                    if (text.charAt(k) == 'b') {
                        bFound = true;
                        break;
                    }
                }
                if (!bFound && maxSize < j - i) {
                    maxSize = j - i;
                }
            }
        }
        System.out.println(text.substring(0, 100) + " -> " + maxSize);
        long endTs = System.currentTimeMillis(); // end time
        System.out.println("Time: " + (endTs - startTs) + "ms");
        return maxSize;
    }
}
