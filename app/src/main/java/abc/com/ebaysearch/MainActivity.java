package abc.com.ebaysearch;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;

import org.json.JSONObject;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import abc.com.ebaysearch.model.Item;


public class MainActivity extends ActionBarActivity {

    public final static String KEYWORD = "com.abc.ebaysearch.keywords";
    public final static String MIN = "com.abc.ebaysearch.min";
    public final static String MAX = "com.abc.ebaysearch.max";
    public final static String SORT = "com.abc.ebaysearch.sort";
    public final static String URL = "com.abc.ebaysearch.url";




    private Boolean noResultsFound = Boolean.FALSE;

    EditText keywords;
    EditText minPrice;
    EditText maxPrice;
    TextView error;
    Button search;
    Spinner mySpinner;
    String min;
    String max, message, sortBy,url;
    List<Item> itemList;

    int error1_flag = 1;
    int error2_flag = 1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        validateInput();
    }

    private void validateInput() {

        keywords = (EditText) findViewById(R.id.keywords);
        minPrice = (EditText) findViewById(R.id.minprice);
        maxPrice = (EditText) findViewById(R.id.maxprice);
        search = (Button) findViewById(R.id.search);
        error = (TextView) findViewById(R.id.error);
        mySpinner=(Spinner) findViewById(R.id.sortBy);





        search.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v){
                String keyWords = keywords.getText().toString();
                 min = minPrice.getText().toString();
                 max = maxPrice.getText().toString();
                TextView error = (TextView) findViewById(R.id.error);
                CharSequence toastString;


                if( keyWords.matches("") ){
                    toastString = "Please enter a keyword";
                    error.setVisibility(TextView.VISIBLE);
                    error.setText(toastString);
                    error1_flag = 0;
                }


                // test max > min
                if (error1_flag == 1){
                    if(!min.matches("") && !max.matches("")){

                        double maxVal = Double.parseDouble(maxPrice.getText().toString());
                        double minVal = Double.parseDouble(minPrice.getText().toString());

                        if(maxVal < minVal){
                            toastString = "Price To must be greater than Price From";
                            error.setVisibility(TextView.VISIBLE);
                            error.setText(toastString);
                            error2_flag = 0;

                        }
                        else{
                            error2_flag = 1;
                        }
                    }
                }

                if( error1_flag == 1 && error2_flag == 1 ){
                    message = URLEncoder.encode(keywords.getText().toString());
                    sortBy = mySpinner.getSelectedItem().toString();

                    url = "http://default-environment-d94dmawcvp.elasticbeanstalk.com/index.php?keywords=";
                    url += message;
                    if(!min.matches("")){
                        url += "&MinPrice=" + min;

                    }
                    if(!max.matches("")){
                        url += "&MaxPrice=" + max;
                    }
                    url += "&sortOrder=";
                    if(sortBy.matches("Best Match")){
                        url += "BestMatch";
                    }
                    else if(sortBy.matches("Price: highest first")){
                        url += "CurrentPriceHighest";
                    }
                    else if(sortBy.matches("Price + Shipping: highest first")){
                        url += "PricePlusShippingHighest";
                    }
                    else{
                        url += "PricePlusShippingLowest";
                    }

                    url += "&paginationInput=5&pageNo=1";


                    if(isOnline()){
                        requestData(url);
                    }





                }else if(error2_flag == 0){
                    toastString = "Price To must be greater than Price From";
                    error.setVisibility(TextView.VISIBLE);
                    error.setText(toastString);

                }


            }
        });

        keywords.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView error = (TextView) findViewById(R.id.error);

                if(error1_flag == 0) {
                    if(!keywords.getText().toString().matches("")) {
                        if (error.getVisibility() == View.VISIBLE) {
                            error.setVisibility(View.INVISIBLE);
                            error1_flag = 1;
                        }
                    }
                }
                else if(keywords.getText().toString().matches("")){
                    CharSequence toastString = "Please enter a keyword";
                    error.setVisibility(TextView.VISIBLE);
                    error.setText(toastString);
                    error1_flag = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        maxPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                min = minPrice.getText().toString();
                max = maxPrice.getText().toString();

                if(!min.matches("") && !max.matches("")){
                    double maxVal = Double.parseDouble(maxPrice.getText().toString());
                    double minVal = Double.parseDouble(minPrice.getText().toString());

                    if(maxVal < minVal){
                        String toastString = "Price To must be greater than Price From";
                        error.setVisibility(TextView.VISIBLE);
                        error.setText(toastString);
                        error2_flag = 0;

                    }
                    else{
                        error2_flag = 1;
                        error.setVisibility(TextView.INVISIBLE);
                    }
                }
                else if (min.matches("")){
                    error2_flag = 1;
                    error.setVisibility(TextView.INVISIBLE);
                }
                else if(max.matches("")){
                    error2_flag = 1;
                    error.setVisibility(TextView.INVISIBLE);
                }
            }


        });


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

        if(noResultsFound){
            error.setText("NO RESULTS FOUND !");
            error.setVisibility(View.VISIBLE);
            error1_flag = 0;

        }else {
          //call Result Activity

            //Create an Intent to call a new Activity that Displays the results
            Intent intent = new Intent(this, ResultActivity.class);

            intent.putExtra(KEYWORD, keywords.getText().toString());
            intent.putExtra(URL, url);

            startActivity(intent);

        }
    }

    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(String... params) {

            String content = HttpManager.getData(params[0]);
            try {
                JSONObject resultJSON = new JSONObject(content);
                String ack = resultJSON.getString("ack");

                if(ack.matches("Success")){
                    noResultsFound = Boolean.FALSE;
//                    updateDisplay();
                }else{
                    noResultsFound = Boolean.TRUE;
//                    updateDisplay();

                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            updateDisplay();

        }

    }



}
