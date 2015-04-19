package abc.com.ebaysearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import abc.com.ebaysearch.model.Item;

/**
 * Created by manali on 4/17/15.
 */
public class ItemAdapter extends ArrayAdapter<Item> {

    private Context context;
    private List<Item> itemList;
    private static final String TAG = "manalisMessage";


    public ItemAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.itemList = objects;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_list, parent, false);

        //Display flower name in the TextView widget
        if( itemList != null) {
            final Item item = itemList.get(position);
            TextView tv = (TextView) view.findViewById(R.id.firstLine);
            tv.setText(item.getTitle());
            TextView p = (TextView) view.findViewById(R.id.secondLine);
            String price = item.getConvertedCurrentPrice();
            String shipping = item.getShippingServiceCost();
            tv.setTag(position);
            String finalPrice = "Price: $" + price + " ";
            if (shipping.matches("0.0")) {
                shipping = "(FREE Shipping)";
            } else {
                shipping = "(+ $" + shipping + " Shipping)";
            }

            finalPrice += shipping;
            p.setText(finalPrice);
            p.setGravity(Gravity.LEFT);

//        //display Image
            ImageView image = (ImageView) view.findViewById(R.id.imageView1);
            image.setImageBitmap(item.getBitmap());
            image.setTag(position);

            image.setOnClickListener(new ImageView.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int p = Integer.parseInt(v.getTag().toString());
                    Item x = itemList.get(p);
                    String url = x.getViewItemURL();
                    openWebURL(url);

                }
                public void openWebURL(String inURL) {
                    Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(inURL));
                    context.startActivity(browse);
                }
            });


            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String p = v.getTag().toString();
                    Log.i(TAG, p);
                    Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                    intent.putExtra("Id", p);
                    Item i = itemList.get(Integer.parseInt(p));
                    intent.putExtra("Title", i.getTitle());
                    intent.putExtra("ViewItemURL", i.getViewItemURL());
                    intent.putExtra("GalleryURL", i.getGalleryURL());
                    intent.putExtra("PictureURLSuperSize",i.getPictureURLSuperSize());
                    intent.putExtra("ConvertedCurrentPrice", i.getConvertedCurrentPrice());
                    intent.putExtra("ShippingServiceCost", i.getShippingServiceCost());
                    intent.putExtra("ConditionDisplayName", i.getConditionDisplayName());
                    intent.putExtra("ListingType", i.getListingType());
                    intent.putExtra("Location", i.getLocation());
                    intent.putExtra("CategoryName", i.getCategoryName());
                    intent.putExtra("TopRatedListing", i.getTopRatedListing());
                    intent.putExtra("SellerUserName", i.getSellerUserName());
                    intent.putExtra("FeedbackScore", i.getFeedbackScore());
                    intent.putExtra("PositiveFeedbackPercent", i.getPositiveFeedbackPercent());
                    intent.putExtra("FeedbackRatingStar", i.getFeedbackRatingStar());
                    intent.putExtra("TopRatedSeller", i.getTopRatedSeller());
                    intent.putExtra("SellerStoreName", i.getSellerStoreName());
                    intent.putExtra("SellerStoreURL", i.getSellerStoreURL());
                    intent.putExtra("ShippingType", i.getShippingType());
                    intent.putExtra("ShipToLocations", i.getShipToLocations());
                    intent.putExtra("ExpeditedShipping", i.getExpeditedShipping());
                    intent.putExtra("OneDayShippingAvailable", i.getOneDayShippingAvailable());
                    intent.putExtra("ReturnsAccepted", i.getReturnsAccepted());
                    intent.putExtra("HandlingTime", i.getHandlingTime());



                    context.startActivity(intent);


                }
            });


            return view;
        }
        else{
            return view;
        }
    }
}
