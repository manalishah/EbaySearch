package abc.com.ebaysearch.parser;

import abc.com.ebaysearch.model.Item;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manali on 4/17/15.
 */
public class ItemJSONParser {

    public static List<Item> parseFeed(String content) {

        try {
            JSONObject object = new JSONObject(content);
            List<Item> itemList = new ArrayList<>();

            String ack = object.getString("ack");
            if(ack.matches("Success")) {
                int resultCount = Integer.parseInt(object.getString("resultCount"));
                if (resultCount >= 5) {
                    resultCount = 5;
                }

                for (int i = 0; i < resultCount; i++) {

                    String itemX = "item" + i;
                    Item item = new Item();
                    JSONObject itemObject = new JSONObject(object.getString(itemX));
                    item.setId(i+"");

                    //basicInfo
                    JSONObject basicInfo = new JSONObject(itemObject.getString("basicInfo"));
                    item.setTitle(basicInfo.getString("title"));
                    item.setViewItemURL(basicInfo.getString("viewItemURL"));
                    item.setGalleryURL(basicInfo.getString("galleryURL"));
                    item.setPictureURLSuperSize(basicInfo.getString("pictureURLSuperSize"));
                    item.setConvertedCurrentPrice(basicInfo.getString("convertedCurrentPrice"));
                    item.setShippingServiceCost(basicInfo.getString("shippingServiceCost"));
                    item.setConditionDisplayName(basicInfo.getString("conditionDisplayName"));
                    item.setListingType(basicInfo.getString("listingType"));
                    item.setLocation(basicInfo.getString("location"));
                    item.setCategoryName(basicInfo.getString("categoryName"));
                    item.setTopRatedListing(basicInfo.getString("topRatedListing"));


                    //sellerInfo
                    JSONObject sellerInfo = new JSONObject(itemObject.getString("sellerInfo"));
                    item.setSellerUserName(sellerInfo.getString("sellerUserName"));
                    item.setFeedbackScore(sellerInfo.getString("feedbackScore"));
                    item.setPositiveFeedbackPercent(sellerInfo.getString("positiveFeedbackPercent"));
                    item.setFeedbackRatingStar(sellerInfo.getString("feedbackRatingStar"));
                    item.setTopRatedListing(sellerInfo.getString("topRatedSeller"));
                    item.setSellerStoreName(sellerInfo.getString("sellerStoreName"));
                    item.setSellerStoreURL(sellerInfo.getString("sellerStoreURL"));

                    //shippingInfo
                    JSONObject shippingInfo = new JSONObject(itemObject.getString("shippingInfo"));
                    item.setShippingType(shippingInfo.getString("shippingType"));
                    item.setShipToLocations(shippingInfo.getString("shipToLocations"));
                    item.setExpeditedShipping(shippingInfo.getString("expeditedShipping"));
                    item.setOneDayShippingAvailable(shippingInfo.getString("oneDayShippingAvailable"));
                    item.setReturnsAccepted(shippingInfo.getString("returnsAccepted"));
                    item.setHandlingTime(shippingInfo.getString("handlingTime"));

                    itemList.add(item);
                }
                return itemList;
            }
            else{
                return null;
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }
}
