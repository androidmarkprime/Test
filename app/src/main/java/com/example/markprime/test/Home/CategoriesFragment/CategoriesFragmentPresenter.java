package com.example.markprime.test.Home.CategoriesFragment;

public class CategoriesFragmentPresenter implements CategoriesFragmentContract.Presenter {

    private CategoriesFragmentContract.View view;

    CategoriesFragmentPresenter(CategoriesFragmentContract.View view){
        this.view = view;
    }

//    @Override
//    public String buildCategoriesUrl(Location location) {
//        String url = "";
//
//        url += Constants.BASE_URL;
//        url += "v1/app/categories";
//        url += "?api_key=" + Constants.API_KEY;
//        if (location != null) {
//            url += "&userlat=" + location.getLatitude();
//            url += "&userlong=" + location.getLongitude();
//        }
//        url += "&accesstoken=" + BaseActivity.prefs.getString(Constants.ACCESS_TOKEN, "");
//        url += "&version=" + BuildConfig.VERSION_CODE;
//        url += "&platform=android";
//
//        return url;
//    }

}
