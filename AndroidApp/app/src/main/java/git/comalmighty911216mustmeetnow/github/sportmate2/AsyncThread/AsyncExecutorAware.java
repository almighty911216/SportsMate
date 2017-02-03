package git.comalmighty911216mustmeetnow.github.sportmate2.AsyncThread;

/**
 * Created by almig on 2017-02-03.
 */
public interface AsyncExecutorAware<T> {
    public void setAsyncExecutor(AsyncExecutor<T> asyncExecutor);
}
