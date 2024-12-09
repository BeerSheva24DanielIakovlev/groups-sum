package telran.numbers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

public class ThreadsPoolGroupSum extends ThreadsGroupSum {

    public ThreadsPoolGroupSum(int[][] groups) {
        super(groups);
    }

    private final ExecutorService threadPool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    @Override
    protected void startTasks(FutureTask<Long>[] tasks) {
        IntStream.range(0, tasks.length).forEach(i -> {
            tasks[i] = new FutureTask<>(new OneGroupSum(groups[i]));
            threadPool.submit(tasks[i]); 
        });
        threadPool.shutdown();
    }
}
