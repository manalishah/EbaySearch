package abc.com.ebaysearch;

import abc.com.ebaysearch.model.Item;
import abc.com.ebaysearch.parser.ItemJSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.net.*;
import android.widget.Toast;


import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;


public class ResultActivity extends ListActivity {

    private List<Item> itemList;
    private Boolean noResults = Boolean.FALSE;
    private TextView result;
    private String resultString;
    private TextView resultFor;
    List<MyTask> tasks;
    ProgressBar pb;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        pb = (ProgressBar)findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        result = (TextView)findViewById(R.id.result);
        resultFor = (TextView)findViewById(R.id.resultFor);
        Intent i = getIntent();
        String keywords = i.getStringExtra(MainActivity.KEYWORD);
        String min = i.getStringExtra(MainActivity.MIN);
        String max = i.getStringExtra(MainActivity.MAX);
        String sort = i.getStringExtra(MainActivity.SORT);
        tasks = new ArrayList<>();
        String url = "http://default-environment-d94dmawcvp.elasticbeanstalk.com/index.php?keywords=";
        url += keywords;
        resultString = "Results for " + keywords;
        if(!min.matches("")){
            url += "&MinPrice=" + min;

        }

        if(!max.matches("")){
            url += "&MaxPrice=" + max;

        }
        url += "&sortOrder=";
        if(sort.matches("Best Match")){
            url += "BestMatch";
        }
        else if(sort.matches("Price: highest first")){
            url += "CurrentPriceHighest";
        }
        else if(sort.matches("Price + Shipping: highest first")){
            url += "PricePlusShippingHighest";
        }
        else{
            url += "PricePlusShippingLowest";
        }

        url += "&paginationInput=5&pageNo=1";



        if (isOnline()) {
            requestData(url);
        } else {
//                        Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    private void updateDisplay() {
        if(noResults){
            result.setText("NO RESULTS FOUND !");

        }else {
            resultFor.setText(resultString);
            ItemAdapter adapter = new ItemAdapter(this, R.layout.item_list, itemList);
            setListAdapter(adapter);
        }
    }

    private class MyTask extends AsyncTask<String, String, List<Item>> {

        @Override
        protected void onPreExecute() {
            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);

        }

        @Override
        protected List<Item> doInBackground(String... params) {

            String content = HttpManager.getData(params[0]);
            try {
                JSONObject resultJSON = new JSONObject(content);
                String ack = resultJSON.getString("ack");

                if(ack.matches("Success")){
                    itemList = ItemJSONParser.parseFeed(content);

                    for(Item item: itemList){

                        try{
                            String imageURL = item.getGalleryURL();
                            InputStream in = (InputStream)new URL(imageURL).getContent();
                            Bitmap bitmap = BitmapFactory.decodeStream(in);
                            item.setBitmap(bitmap);
                            in.close();

                        }catch(Exception e){
                            e.printStackTrace();

                        }
                        finally {

                        }
                    }
                    updateDisplay();
                }else{
                    noResults = Boolean.TRUE;
                    updateDisplay();

                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return itemList;
        }

        @Override
        protected void onPostExecute(List<Item> result) {
            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }

//            if (result == null) {
//                Toast.makeText(ResultActivity.this, "Web service not available", Toast.LENGTH_LONG).show();
//                return;
//            }

            itemList = result;
            updateDisplay();

        }

        @Override
        protected void onProgressUpdate(String... values) {
        }

    }
}
