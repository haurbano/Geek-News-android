package movil.salt.weeknews.net;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import movil.salt.weeknews.MainActivity;
import movil.salt.weeknews.fragments.noticiasGenerales;
import movil.salt.weeknews.models.Noticia;

/**
 * Created by pc on 09/06/2015.
 */
public class ejecutarGet extends AsyncTask{


    String respuestaString;
    static final  String uriRSS = "http://pipes.yahoo.com/pipes/pipe.run?_id=ed4a7895e7d48bae9c8c73820ad6f70a&_render=rss";
    static final String uriJSON ="https://pipes.yahoo.com/pipes/pipe.run?_id=ed4a7895e7d48bae9c8c73820ad6f70a&_render=json";


    public ejecutarGet() {
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(uriJSON);

            HttpResponse response = client.execute(httpGet);
            respuestaString = EntityUtils.toString(response.getEntity(),"UTF-8");
        }
        catch (Exception c)
        {
            Log.d("flag-error","Error en la ejecucion del get");
            respuestaString = null;
            Toast.makeText(MainActivity.getContext(),"Debes conectarte a internet",Toast.LENGTH_SHORT).show();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        noticiasGenerales noticiasEnviar = new noticiasGenerales();
        ArrayList<Noticia> noticias = new ArrayList<>();

        //region Json parser
        try {
            JSONObject object = new JSONObject(respuestaString);
            JSONObject value = object.getJSONObject("value");
            JSONArray noticiasJson = value.getJSONArray("items");
            for (int i =0;i<noticiasJson.length();i++)
            {
                Noticia noticia = new Noticia();
                JSONObject noti = noticiasJson.getJSONObject(i);
                noticia.setTitle(noti.getString("title"));
                noticia.setLink(noti.getString("link"));
                noticia.setContenido(noti.getString("description"));

                noticias.add(noticia);
            }

            noticiasEnviar.setNoticias(noticias);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //endregion


        super.onPostExecute(o);
    }
}
