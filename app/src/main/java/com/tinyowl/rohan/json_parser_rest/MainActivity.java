package com.tinyowl.rohan.json_parser_rest;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    @Bind(R.id.get_object_button)
    Button mGetObject;
    @Bind(R.id.display_object_view)
    TextView mDisplayObject;

    //List<String> stringList;
    List<Contributor> mContributorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
/*
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://ip.jsontext.com")
                .build();

        IpObject ipObject = restAdapter.create(IpObject.class);
        stringList = ipObject.stringList();
*/

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setErrorHandler(new RetrofitErrorHandler())
                .build();

        GitHubService gitHubService = restAdapter.create(GitHubService.class);

        Callback<List<Contributor>> callback = new Callback<List<Contributor>>() {


            @Override
            public void success(List<Contributor> contributors, Response response) {
                if (contributors != null) {
                    mContributorList = contributors;
                }
                else
                    mContributorList.add(0, new Contributor());

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("RETROFIT ERROR", "GETTING THE STUFF FAILURE");
            }
        };

        try {
            gitHubService.contributors("square", "okhttp", callback);
        } catch (RetrofitError e) {
            //Log.e("ERROR ", ((e.getSuccessType()).toString()));
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.get_object_button)
    public void sendRequest() {

        //mDisplayObject.setText(stringList.get(0));
        String result = "";

        for (Contributor contributor : mContributorList) {

            result = result + "Login : " + contributor.login + " Contribution : " + Integer.toString(contributor.contributions) + "\n";
        }

        mDisplayObject.setText(result);
    }
}
