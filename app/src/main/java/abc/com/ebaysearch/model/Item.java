package abc.com.ebaysearch.model;

import android.graphics.Bitmap;

/**
 * Created by manali on 4/17/15.
 */
public class Item {



    String object;
    // basicInfo
    private String id;
    private String title;
    private String viewItemURL;
    private String galleryURL;
    private String pictureURLSuperSize;
    private String convertedCurrentPrice;
    private String shippingServiceCost;
    private String conditionDisplayName;
    private String listingType;
    private String location;
    private String categoryName;
    private String topRatedListing;

    //sellerInfo
    private String sellerUserName;
    private String feedbackScore;
    private String positiveFeedbackPercent;
    private String feedbackRatingStar;
    private String topRatedSeller;
    private String sellerStoreName;
    private String sellerStoreURL;

    //shippingInfo
    private String shippingType;
    private String shipToLocations;
    private String expeditedShipping;
    private String oneDayShippingAvailable;
    private String returnsAccepted;
    private String handlingTime;
    private Bitmap bitmap;
    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewItemURL() {
        return viewItemURL;
    }

    public void setViewItemURL(String viewItemURL) {
        this.viewItemURL = viewItemURL;
    }

    public String getGalleryURL() {
        return galleryURL;
    }

    public void setGalleryURL(String galleryURL) {
        this.galleryURL = galleryURL;
    }

    public String getPictureURLSuperSize() {
        return pictureURLSuperSize;
    }

    public void setPictureURLSuperSize(String pictureURLSuperSize) {
        this.pictureURLSuperSize = pictureURLSuperSize;
    }

    public String getConvertedCurrentPrice() {
        return convertedCurrentPrice;
    }

    public void setConvertedCurrentPrice(String convertedCurrentPrice) {
        this.convertedCurrentPrice = convertedCurrentPrice;
    }

    public String getShippingServiceCost() {
        return shippingServiceCost;
    }

    public void setShippingServiceCost(String shippingServiceCost) {
        this.shippingServiceCost = shippingServiceCost;
    }

    public String getConditionDisplayName() {
        return conditionDisplayName;
    }

    public void setConditionDisplayName(String conditionDisplayName) {
        this.conditionDisplayName = conditionDisplayName;
    }

    public String getListingType() {
        return listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTopRatedListing() {
        return topRatedListing;
    }

    public void setTopRatedListing(String topRatedListing) {
        this.topRatedListing = topRatedListing;
    }

    public String getSellerUserName() {
        return sellerUserName;
    }

    public void setSellerUserName(String sellerUserName) {
        this.sellerUserName = sellerUserName;
    }

    public String getFeedbackScore() {
        return feedbackScore;
    }

    public void setFeedbackScore(String feedbackScore) {
        this.feedbackScore = feedbackScore;
    }

    public String getPositiveFeedbackPercent() {
        return positiveFeedbackPercent;
    }

    public void setPositiveFeedbackPercent(String positiveFeedbackPercent) {
        this.positiveFeedbackPercent = positiveFeedbackPercent;
    }

    public String getFeedbackRatingStar() {
        return feedbackRatingStar;
    }

    public void setFeedbackRatingStar(String feedbackRatingStar) {
        this.feedbackRatingStar = feedbackRatingStar;
    }

    public String getTopRatedSeller() {
        return topRatedSeller;
    }

    public void setTopRatedSeller(String topRatedSeller) {
        this.topRatedSeller = topRatedSeller;
    }

    public String getSellerStoreName() {
        return sellerStoreName;
    }

    public void setSellerStoreName(String sellerStoreName) {
        this.sellerStoreName = sellerStoreName;
    }

    public String getSellerStoreURL() {
        return sellerStoreURL;
    }

    public void setSellerStoreURL(String sellerStoreURL) {
        this.sellerStoreURL = sellerStoreURL;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getShipToLocations() {
        return shipToLocations;
    }

    public void setShipToLocations(String shipToLocations) {
        this.shipToLocations = shipToLocations;
    }

    public String getExpeditedShipping() {
        return expeditedShipping;
    }

    public void setExpeditedShipping(String expeditedShipping) {
        this.expeditedShipping = expeditedShipping;
    }

    public String getOneDayShippingAvailable() {
        return oneDayShippingAvailable;
    }

    public void setOneDayShippingAvailable(String oneDayShippingAvailable) {
        this.oneDayShippingAvailable = oneDayShippingAvailable;
    }

    public String getReturnsAccepted() {
        return returnsAccepted;
    }

    public void setReturnsAccepted(String returnsAccepted) {
        this.returnsAccepted = returnsAccepted;
    }

    public String getHandlingTime() {
        return handlingTime;
    }

    public void setHandlingTime(String handlingTime) {
        this.handlingTime = handlingTime;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
