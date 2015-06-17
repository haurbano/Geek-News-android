package movil.salt.weeknews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import movil.salt.weeknews.fragments.PagerAdapter;
import movil.salt.weeknews.fragments.TitleFragment;
import movil.salt.weeknews.fragments.noticiasGenerales;
import movil.salt.weeknews.fragments.videoJuegosFragment;
import movil.salt.weeknews.net.ejecutarGet;


public class MainActivity extends ActionBarActivity {

    ViewPager pager;
    List<TitleFragment> data;
    PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        data = new ArrayList<>();

        data.add(new noticiasGenerales());
        data.add(new videoJuegosFragment());

        adapter = new PagerAdapter(getSupportFragmentManager(),data);
        pager.setAdapter(adapter);


    }

    public static Context getContext()
    {
        return getContext();
    }
}
