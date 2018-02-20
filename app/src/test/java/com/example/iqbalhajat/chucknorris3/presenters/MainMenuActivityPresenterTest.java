package com.example.iqbalhajat.chucknorris3.presenters;

import com.example.iqbalhajat.chucknorris3.models.Joke;
import com.example.iqbalhajat.chucknorris3.views.MainActivityView;
import com.example.iqbalhajat.chucknorris3.models.Model;
import com.example.iqbalhajat.chucknorris3.repositories.Repository;

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
public class MainMenuActivityPresenterTest {

    private static final String funny_joke = "Norris never goes to the dentist because his teeth are unbreakable. His enemies never go to the dentist because they have no teeth.";

    @Mock
    MainActivityView view;
    @Mock
    Repository repository;

    MainPresenter presenter;
    Joke randomJoke;

    Model.Result mResult;
    MockWebServer mMockWebServer;
    TestObserver<Model.Result> mSubscriber;

    @Before
    public void setUp() throws Exception {

        presenter = new MainPresenter(view);

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

        mResult = new Result(new Value(3,funny_joke));
        mMockWebServer = new MockWebServer();
        mSubscriber = new TestObserver<>();
    }

    @After
    public void tearDown() throws Exception {


    }

    /**
     * TDD: Test 1: Check that on a successful retrieval of a joke, the view with a joke is displayed
     */
    @Test
    public void shouldDisplayChuckNorrisJoke() throws Exception {

        //given
        Observable<Result> observable =repository.getObservable("Chuck","Norris");

        Mockito.when(observable).thenReturn(Observable.just( new Result(new Value(12,funny_joke))));

        presenter.getJoke("Chuck","Norris");

        //then
        Mockito.verify(view).displayJoke(funny_joke);
    }

    /**
     * TDD: Test 2: Check that on a non-successful retrieval of a joke, an error is displayed
     */
    @Test
    public void shouldDisplayErrorIfFailsToGetJoke() throws Exception {

        //given
        Observable<Result> observable =repository.getObservable("Chuck","Norris");

        Mockito.when(observable).thenReturn(Observable.error(new Exception("test")));

        presenter.getJoke("Chuck","Norris");

        //then
        Mockito.verify(view).displayError();
    }

    /**
     * TDD: Test 3: test Observable Behaviour
     */
    @Test
    public void testObservableBehaviour() throws Exception {
        Mockito.when(repository.getObservable("Chuck","Norris")).thenReturn(
                Observable.just( new Result(new Value(12,funny_joke))
                ));

        TestObserver<Model.Result> testObserver = repository.getObservable("Chuck","Norris")
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
