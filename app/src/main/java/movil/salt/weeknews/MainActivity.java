package movil.salt.weeknews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import movil.salt.weeknews.fragments.PagerAdapter;
import movil.salt.weeknews.fragments.TitleFragment;
import movil.salt.weeknews.fragments.noticiasGenerales;
import movil.salt.weeknews.fragments.UsuariosFragment;


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
        data.add(new UsuariosFragment());

        adapter = new PagerAdapter(getSupportFragmentManager(),data);
        pager.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        SharedPreferences preferences;
        preferences = getSharedPreferences(LoginActivity.SHARED_NAME,LoginActivity.MODE_PRIVATE);

        Log.d("flag-preferense-cerrarSesion-antes",preferences.getString(LoginActivity.KEY_SESSION,"defecto"));
        SharedPreferences.Editor edit = preferences.edit();


        edit.putString(LoginActivity.KEY_SESSION,"null");
        edit.commit();

        Log.d("flag-preferense-cerrarSesion-despues",preferences.getString(LoginActivity.KEY_SESSION,"defecto"));

        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public static Context getContext()
    {

        return getContext();
    }
}
