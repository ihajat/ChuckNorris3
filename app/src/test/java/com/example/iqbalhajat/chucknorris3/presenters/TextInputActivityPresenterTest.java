package com.example.iqbalhajat.chucknorris3.presenters;

import com.example.iqbalhajat.chucknorris3.models.Joke;
import com.example.iqbalhajat.chucknorris3.repositories.Repository;
import com.example.iqbalhajat.chucknorris3.views.TextInputActivityView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockWebServer;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;

import static com.example.iqbalhajat.chucknorris3.models.Model.Result;
import static com.example.iqbalhajat.chucknorris3.models.Model.Value;


@RunWith(MockitoJUnitRunner.class)
public class TextInputActivityPresenterTest {

    @Mock
    TextInputActivityView view;
    @Mock
    Repository repository;

    TextInputActivityPresenter presenter;
    Joke randomJoke;

    Result mResult;
    MockWebServer mMockWebServer;
    TestObserver<Result> mSubscriber;

    @Before
    public void setUp() throws Exception {

        presenter = new TextInputActivityPresenter(view); // Schedulers.trampoline()


        // Override RxJava schedulers
        RxJavaHooks.setOnIOScheduler(new Func1<Scheduler, Scheduler>() {
            @Override
            public Scheduler call(Scheduler scheduler) {
                return rx.schedulers.Schedulers.immediate();
            }
        });

        RxJavaHooks.setOnComputationScheduler(new Func1<Scheduler, Scheduler>() {
            @Override
            public Scheduler call(Scheduler scheduler) {
                return rx.schedulers.Schedulers.immediate();
            }
        });

        RxJavaHooks.setOnNewThreadScheduler(new Func1<Scheduler, Scheduler>() {
            @Override
            public Scheduler call(Scheduler scheduler) {
                return rx.schedulers.Schedulers.immediate();
            }
        });

        // Override RxAndroid schedulers
        final RxAndroidPlugins rxAndroidPlugins = RxAndroidPlugins.getInstance();
        rxAndroidPlugins.registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return rx.schedulers.Schedulers.immediate();
            }
        });

        randomJoke = new Joke();
        mResult = new Result(new Value(3,"Chuck is good"));
        mMockWebServer = new MockWebServer();
        mSubscriber = new TestObserver<>();
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void shouldGetChuckNorrisJoke2() throws Exception {
        Mockito.when(repository.getObservable("Chuck","Norris")).thenReturn(
                Observable.just( new Result(new Value(12,"Chuck is good"))
                ));

        TestObserver<Result> testObserver = repository.getObservable("Chuck","Norris")
                .test();
        testObserver.awaitTerminalEvent();

        testObserver
                .assertNoErrors()
                .assertValue(l -> assertIfChuckMissing(l.getValue().getJoke()));

    }

    public static boolean assertIfChuckMissing(String joke){
        System.out.println(joke);
        return (joke).contains("Chuck");
    }


}
