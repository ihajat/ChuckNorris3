package com.example.iqbalhajat.chucknorris3.presenters;

import com.example.iqbalhajat.chucknorris3.models.Joke;
import com.example.iqbalhajat.chucknorris3.apis.JokeService;
import com.example.iqbalhajat.chucknorris3.models.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;

import static junit.framework.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class NeverEndingListActivityPresenterTest {

    /**
     * TDD: Test 1: test Web Service API Call returns 20 Jokes ONLY
     */
    @Test
    public void testWebServiceAPICallReturns20Jokes() {

        Call<Response<List<Joke>>> call = JokeService.Companion.create().getJokesNonReactive();

        //Magic is here at .execute() instead of .enqueue(), which is synchronous
        retrofit2.Response<Response<List<Joke>>> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Response<List<Joke>> authResponse = response.body();
        assertTrue(response.isSuccessful() && authResponse.getValue().size()==20);

    }




}
