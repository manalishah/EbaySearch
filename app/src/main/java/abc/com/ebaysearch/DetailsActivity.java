package abc.com.ebaysearch;
import abc.com.ebaysearch.model.Item;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by manali on 4/18/15.
 */
public class DetailsActivity extends Activity {

    private static final String TAG = "manalisMessage";
    List<MyTask> tasks;
    ProgressBar pb;
    private Item item;
    TextView n1;
    TextView n2;
    TextView n3;
    TextView n4;
    TextView n5;
    TextView n6;
    TextView n7;
    TextView n8;
    TextView v1;
    TextView v2;
    TextView v3;
    TextView v4;
    ImageView v5;
    TextView v6;
    ImageView v7;
    ImageView v8;
    LinearLayout l4;
    LinearLayout l5;
    LinearLayout l6;
    LinearLayout l7;
    LinearLayout l8;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    String description;

    TextView Title;
    TextView Price;
    TextView Location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();


        ImageView f = (ImageView)findViewById(R.id.F);
        f.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                login();
            }
        });

        Intent intent = getIntent();
        item = new Item();
        description = "";

        item.setTitle(intent.getStringExtra("Title"));
        item.setViewItemURL(intent.getStringExtra("ViewItemURL"));
        item.setGalleryURL(intent.getStringExtra("GalleryURL"));
        item.setPictureURLSuperSize(intent.getStringExtra("PictureURLSuperSize"));
        item.setConvertedCurrentPrice(intent.getStringExtra("ConvertedCurrentPrice"));
        item.setShippingServiceCost(intent.getStringExtra("ShippingServiceCost"));
        item.setConditionDisplayName(intent.getStringExtra("ConditionDisplayName"));

        item.setListingType(intent.getStringExtra("ListingType"));
        item.setLocation(intent.getStringExtra("Location"));
        item.setCategoryName(intent.getStringExtra("CategoryName"));
        item.setTopRatedListing(intent.getStringExtra("TopRatedListing"));
        item.setSellerUserName(intent.getStringExtra("SellerUserName"));
        item.setFeedbackScore(intent.getStringExtra("FeedbackScore"));
        item.setPositiveFeedbackPercent(intent.getStringExtra("PositiveFeedbackPercent"));
        item.setFeedbackRatingStar(intent.getStringExtra("FeedbackRatingStar"));
        item.setTopRatedSeller(intent.getStringExtra("TopRatedSeller"));
        item.setSellerStoreName(intent.getStringExtra("SellerStoreName"));
        item.setSellerStoreURL(intent.getStringExtra("SellerStoreURL"));
        item.setShippingType(intent.getStringExtra("ShippingType"));
        item.setShipToLocations(intent.getStringExtra("ShipToLocations"));
        item.setExpeditedShipping(intent.getStringExtra("ExpeditedShipping"));
        item.setOneDayShippingAvailable(intent.getStringExtra("OneDayShippingAvailable"));
        item.setReturnsAccepted(intent.getStringExtra("ReturnsAccepted"));
        item.setHandlingTime(intent.getStringExtra("HandlingTime"));




        Title = (TextView)findViewById(R.id.title);
        Price = (TextView)findViewById(R.id.price);
        Location = (TextView)findViewById(R.id.location);
        n1 = (TextView)findViewById(R.id.name1);
        v1 = (TextView)findViewById(R.id.value1);
        n2 = (TextView)findViewById(R.id.name2);
        v2 = (TextView)findViewById(R.id.value2);
        n3 = (TextView)findViewById(R.id.name3);
        v3 = (TextView)findViewById(R.id.value3);
        n4 = (TextView)findViewById(R.id.name4);
        v4 = (TextView)findViewById(R.id.value4);
        n5 = (TextView)findViewById(R.id.name5);
        v5 = (ImageView)findViewById(R.id.value5);
        n6 = (TextView)findViewById(R.id.name6);
        v6 = (TextView)findViewById(R.id.value6);
        n7 = (TextView)findViewById(R.id.name7);
        v7 = (ImageView)findViewById(R.id.value7);
        n8 = (TextView)findViewById(R.id.name8);
        v8 = (ImageView)findViewById(R.id.value8);

        l4 = (LinearLayout)findViewById(R.id.l4);
        l5 = (LinearLayout)findViewById(R.id.l5);
        l6 = (LinearLayout)findViewById(R.id.l6);
        l7 = (LinearLayout)findViewById(R.id.l7);
        l8 = (LinearLayout)findViewById(R.id.l8);


        final Button basicInfo = (Button)findViewById(R.id.basicInfo);
        final Button sellerInfo = (Button)findViewById(R.id.sellerInfo);
        final Button shippingInfo = (Button)findViewById(R.id.shippingInfo);
        final Button buyNow = (Button)findViewById(R.id.buyNow);
        final ImageView topRated = (ImageView)findViewById(R.id.topRated);

        if(item.getTopRatedListing() == "false"){
            topRated.setVisibility(View.INVISIBLE);
        }

        basicInfo.setBackgroundColor(Color.BLUE);
        sellerInfo.setBackgroundColor(Color.WHITE);
        shippingInfo.setBackgroundColor(Color.WHITE);

        n4.setVisibility(View.GONE);
        n5.setVisibility(View.GONE);
        n6.setVisibility(View.GONE);
        v7.setVisibility(View.GONE);
        v8.setVisibility(View.GONE);
        v4.setVisibility(View.GONE);
        v5.setVisibility(View.GONE);
        v6.setVisibility(View.GONE);
        v7.setVisibility(View.GONE);
        v8.setVisibility(View.GONE);
        l4.setVisibility(View.GONE);
        l5.setVisibility(View.GONE);
        l6.setVisibility(View.GONE);
        l7.setVisibility(View.GONE);
        l8.setVisibility(View.GONE);



        basicInfo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                n1.setText("Category Name");
                v1.setText(item.getCategoryName());
                n2.setText("Condition");
                v2.setText(item.getConditionDisplayName());
                n3.setText("Buying Format");
                v3.setText(item.getListingType());

                basicInfo.setBackgroundColor(Color.BLUE);
                sellerInfo.setBackgroundColor(Color.WHITE);
                shippingInfo.setBackgroundColor(Color.WHITE);

                n4.setVisibility(View.GONE);
                n5.setVisibility(View.GONE);
                n6.setVisibility(View.GONE);
                v7.setVisibility(View.GONE);
                v8.setVisibility(View.GONE);
                v4.setVisibility(View.GONE);
                v5.setVisibility(View.GONE);
                v6.setVisibility(View.GONE);
                v7.setVisibility(View.GONE);
                v8.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
                l6.setVisibility(View.GONE);
                l7.setVisibility(View.GONE);
                l8.setVisibility(View.GONE);


            }
        });

        sellerInfo.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                n1.setText("User Name");
                v1.setText(item.getSellerUserName());
                n2.setText("Feedback Score");
                v2.setText(item.getFeedbackScore());
                n3.setText("Positive Feedback");
                v3.setText(item.getPositiveFeedbackPercent());

                n4.setText("Feedback Rating");
                v4.setText(item.getFeedbackRatingStar());
                n5.setText("Top Rated");
                if(item.getTopRatedSeller() == "true"){
                    v5.setImageResource(R.drawable.yes);
                }
                n6.setText("Store");
                v6.setText(item.getSellerStoreName());

                basicInfo.setBackgroundColor(Color.WHITE);
                sellerInfo.setBackgroundColor(Color.BLUE);
                shippingInfo.setBackgroundColor(Color.WHITE);

                n4.setVisibility(View.VISIBLE);
                n5.setVisibility(View.VISIBLE);
                n6.setVisibility(View.VISIBLE);
                v4.setVisibility(View.VISIBLE);
                v5.setVisibility(View.VISIBLE);
                v6.setVisibility(View.VISIBLE);
                l4.setVisibility(View.VISIBLE);
                l5.setVisibility(View.VISIBLE);
                l6.setVisibility(View.VISIBLE);

                n7.setVisibility(View.GONE);
                n8.setVisibility(View.GONE);

                v7.setVisibility(View.GONE);
                v8.setVisibility(View.GONE);

                l7.setVisibility(View.GONE);
                l8.setVisibility(View.GONE);

            }
        });

        shippingInfo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                n1.setText("Shipping Type");
                v1.setText(item.getShippingType());
                n2.setText("Handling Time");
                v2.setText(item.getHandlingTime());
                n3.setText("Shipping Locations");
                v3.setText(item.getShipToLocations());

                n5.setText("Expedited Shipping");
                if(item.getExpeditedShipping() == "true") {
                    v5.setImageResource(R.drawable.yes);
                }
                n7.setText("One Day Shipping");
                if(item.getOneDayShippingAvailable() == "true") {
                    v7.setImageResource(R.drawable.yes);
                }


                n8.setText("Returns Accepted");
                if(item.getReturnsAccepted() == "true") {
                    v8.setImageResource(R.drawable.yes);
                }

                basicInfo.setBackgroundColor(Color.WHITE);
                sellerInfo.setBackgroundColor(Color.WHITE);
                shippingInfo.setBackgroundColor(Color.BLUE);

                n4.setVisibility(View.GONE);
                n5.setVisibility(View.VISIBLE);
                n6.setVisibility(View.GONE);
                n7.setVisibility(View.VISIBLE);
                n8.setVisibility(View.VISIBLE);
                v4.setVisibility(View.GONE);
                v5.setVisibility(View.VISIBLE);
                v6.setVisibility(View.GONE);
                v7.setVisibility(View.VISIBLE);
                v8.setVisibility(View.VISIBLE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.VISIBLE);
                l6.setVisibility(View.GONE);
                l7.setVisibility(View.VISIBLE);
                l8.setVisibility(View.VISIBLE);

            }
        });





        String photo = item.getPictureURLSuperSize();
        item.setGalleryURL(photo);
        if(isOnline()){
            requestPhoto(photo);
        }

        buyNow.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleLink = item.getViewItemURL();
                openWebURL(titleLink);
            }

            public void openWebURL(String inURL) {
                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(inURL));
                startActivity(browse);
            }
        });
    }



    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
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

    private void requestPhoto(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    private void updateDisplay() {


        ImageView image = (ImageView)findViewById(R.id.imageView);
        image.setImageBitmap(item.getBitmap());

        Title.setText(item.getTitle());

        String cost = item.getShippingServiceCost();
        String p ="Price: $" + item.getConvertedCurrentPrice() + " ";
        if(cost.matches("0.0")){
            p +="(FREE Shipping)";
        }
        else{
            p += "(+ $" + cost + " Shipping)";
        }
        Price.setText(p);
        Location.setText(item.getLocation());

        n1.setText("Category Name");
        v1.setText(item.getCategoryName());
        n2.setText("Condition");
        v2.setText(item.getConditionDisplayName());
        n3.setText("Buying Format");
        v3.setText(item.getListingType());

        description = p + " " + item.getLocation();


    }

    public void login() {



        shareDialog = new ShareDialog(this);
        // this part is optional



        String desc = description;
        String title=item.getTitle();
        String link = item.getViewItemURL();
        String url=item.getGalleryURL();



        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle(title)
                    .setContentDescription(desc)
                    .setContentUrl(Uri.parse(link))
                    .build();

            shareDialog.show(linkContent);
        }

        Log.i(TAG, "afngersharig");
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(getApplicationContext(), "Facebook Post Successful", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Success");


            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Facebook Post Cancelled", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "cancel");
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getApplicationContext(), "Facebook Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "error");


            }

        });

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {



                try{
                    String imageURL = item.getGalleryURL();
                    InputStream in = (InputStream)new URL(imageURL).getContent();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    item.setBitmap(bitmap);
                    in.close();

                }catch(Exception e){
                    e.printStackTrace();

                }
            return "";

        }

        @Override
        protected void onPostExecute(String result) {

            updateDisplay();

        }

        @Override
        protected void onProgressUpdate(String... values) {
        }

    }
}
