package net.sonia.ejemplookhttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {
    private TextView txtJson;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtJson = (TextView) findViewById(R.id.txtJson);

        Hilo hilo = new Hilo();
        hilo.execute();

    }


    OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String runWithParameters(String url, String... params) throws IOException {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("param1", params[0]);
        urlBuilder.addQueryParameter("param2", params[1]);


        String urlParams = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(urlParams)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String runWithHeader(String url) throws IOException {

        Request request = new Request.Builder()
                .header("Authorization", "your token")
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public String runWithHeaderParams(String url, String... params) throws IOException {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("param1", params[0]);
        urlBuilder.addQueryParameter("param2", params[1]);

        String urlParams = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .header("Authorization", "your token")
                .url(urlParams)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
/*
    public String postLibro(String isbn, String url){
        RequestBody body = RequestBody.create(JSON, json)
                .add("isbn", isbn)
                .build();

        Request request = new Request.Builder()
                .url(url).post(body)
                .build();

        Response response = client.newCall(request).execute();
    }
*/
    public class Hilo extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {

            String json="";
            try {
                json=run("http://www.smgformacion.net/libros");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            ResultBean resultBean = ResultBean.fromJson(json);
            if(resultBean.getResultado()==0) {
                txtJson.setText(resultBean.getLibros().get(0).toJson());
            }else{
                txtJson.setText("Incorrecto");
            }

        }
    }
}
