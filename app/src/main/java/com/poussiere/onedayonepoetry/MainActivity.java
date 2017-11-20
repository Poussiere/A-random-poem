package com.poussiere.onedayonepoetry;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.poussiere.onedayonepoetry.PoetryRequest.BASE_URL;

public class MainActivity extends AppCompatActivity {

    public static final String LIFECYCLE_CALLBACKS_KEY = "callbacks";
    private CompositeDisposable mCompositeDisposable;
    private ArrayList<Poetry> mPoetryArrayList;
    private TextView poetryLines;
    private Random random = new Random();
    private int randomNumber;
    private String savedPoem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCompositeDisposable = new CompositeDisposable();
        poetryLines = (TextView) findViewById(R.id.poetry_lines);


        if (savedInstanceState != null && savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_KEY)) {
            savedPoem = savedInstanceState.getString(LIFECYCLE_CALLBACKS_KEY);
            poetryLines.setText(savedPoem);


        }
        PoetryRequest poetryRequest = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PoetryRequest.class);

        mCompositeDisposable.add(poetryRequest.register()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));


    }

    private void handleResponse(List<Poetry> list) {

        mPoetryArrayList = new ArrayList<>(list);
        if (savedPoem == null) {
            displayRandomPoem();
        }


    }

    private void displayRandomPoem() {
        if (mPoetryArrayList != null) {
            randomNumber = random.nextInt(mPoetryArrayList.size());
            Poetry mPoetry = mPoetryArrayList.get(randomNumber);
            String lines = mPoetry.getAllStrings();
            poetryLines.setText(lines);
        }
    }

    private void handleError(Throwable error) {

        Toast.makeText(this, "Error while loading your poetry", Toast.LENGTH_LONG).show();
        Log.e("main", "" + error.getLocalizedMessage());
    }


    public void onFabClick(View v) {
        displayRandomPoem();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

    @Override
    protected void onSaveInstanceState(Bundle b) {
        super.onSaveInstanceState(b);
        String savedPoem = poetryLines.getText().toString();
        if (!savedPoem.equals("")) {
            b.putString(LIFECYCLE_CALLBACKS_KEY, savedPoem);
        }
    }


}
