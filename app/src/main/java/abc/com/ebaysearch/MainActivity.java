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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Spinner;
import java.util.List;

import abc.com.ebaysearch.model.Item;
import abc.com.ebaysearch.parser.ItemJSONParser;


public class MainActivity extends ActionBarActivity {

    public final static String KEYWORD = "com.abc.ebaysearch.keywords";
    public final static String MIN = "com.abc.ebaysearch.min";
    public final static String MAX = "com.abc.ebaysearch.max";
    public final static String SORT = "com.abc.ebaysearch.sort";



    EditText keywords;
    EditText minPrice;
    EditText maxPrice;
    TextView error;
    Button search;
    Spinner mySpinner;

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
                String min = minPrice.getText().toString();
                String max = maxPrice.getText().toString();
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
                    error.setText("Buddy im loading a brand new view!");
                    error.setVisibility(View.VISIBLE);

                    String sortBy = mySpinner.getSelectedItem().toString();


                    //Create an Intent to call a new Activity that Displays the results
                    Intent intent = new Intent(v.getContext(), ResultActivity.class);
                    String message = keywords.getText().toString();
                    intent.putExtra(KEYWORD, message);
                    intent.putExtra(MIN, min);
                    intent.putExtra(MAX, max);
                    intent.putExtra(SORT, sortBy);

                    startActivity(intent);

                    // Make http request




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


}
