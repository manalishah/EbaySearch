package abc.com.ebaysearch;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import abc.com.ebaysearch.model.Item;

/**
 * Created by manali on 4/17/15.
 */
public class ItemAdapter extends ArrayAdapter<Item> {

    private Context context;
    private List<Item> itemList;

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
            Item item = itemList.get(position);
            TextView tv = (TextView) view.findViewById(R.id.firstLine);
            tv.setText(item.getTitle());
            TextView p = (TextView) view.findViewById(R.id.secondLine);
            String price = item.getConvertedCurrentPrice();
            String shipping = item.getShippingServiceCost();

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


            return view;
        }
        else{
            return view;
        }
    }
}
