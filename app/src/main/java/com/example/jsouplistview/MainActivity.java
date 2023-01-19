package com.example.jsouplistview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Elements news;
    public ArrayList<String> newsList = new ArrayList<>();
    public ArrayList<String> newsListMain = new ArrayList<>();
    private ListView lv;
    private CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.news_list);
        customAdapter = new CustomAdapter(this,newsListMain);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_listview_detail, R.id.headline, newsList);
        ArrayAdapter<String> adapterMain = new ArrayAdapter<String>(this, R.layout.activity_listview_detail, R.id.headline_main, newsListMain);

        new NewThread().execute();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent openLinksIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsList.get(i)));
                startActivity(openLinksIntent);
            }
        });

    }

    public class NewThread extends AsyncTask<String, Void, String> {

        ProgressDialog dialog = new ProgressDialog(MainActivity.this);



        @Override
        protected void onPreExecute(){
            dialog.setMessage("Loading News...");
            dialog.show();

        }

        @Override
        protected String doInBackground(String... arg) {

            Document doc;
            try {
                doc = Jsoup.connect("https://varzesh3.com").get();
                news = doc.select("li.text-type>a");
                newsList.clear();
                for (Element news : news) {
                    newsList.add(news.attr("href"));
                    newsListMain.add(news.text());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();

            lv.setAdapter(customAdapter);

        }

    }

}