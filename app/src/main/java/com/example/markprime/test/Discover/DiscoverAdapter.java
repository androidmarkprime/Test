//package com.example.markprime.test.Discover;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.drawable.GradientDrawable;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.Html;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.example.markprime.test.Model.DiscoverCarouselObject;
//import com.example.markprime.test.Model.DiscoverObject;
//import com.example.markprime.test.R;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DiscoverAdapter extends RecyclerView.Adapter<ViewHolder>{
//
//    private final int TYPE_CAROUSEL = 0;
//    private final int TYPE_INSPIRE_ME = 1;
//    private final int TYPE_EVENT = 2;
//
//    private final String CAROUSEL = "carousel";
//    private final String INSPIRE_ME = "inspireme";
//    private final String EVENT = "event";
//
//    private List<DiscoverObject> discoverObjectList;
//    private Context context;
//
//    private DiscoverAdapterListener.DiscoverListener discoverListener;
//    private DiscoverAdapterListener.DiscoverCarouselListener carouselListener;
//
//
//    DiscoverAdapter (List<DiscoverObject> discoverObjectList, Context context,
//                     DiscoverAdapterListener.DiscoverListener discoverListener,
//                     DiscoverAdapterListener.DiscoverCarouselListener carouselListener){
//
//        this.discoverObjectList = discoverObjectList;
//        this.context = context;
//        this.discoverListener = discoverListener;
//        this.carouselListener = carouselListener;
//
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
//        LayoutInflater inflater = LayoutInflater.from(context);
//        switch (viewType){
//
//            case TYPE_CAROUSEL:
//
//                return new CarouselViewHolder(inflater.inflate(
//                        R.layout.model_discover_carousel_item, parent, false
//                ));
//
//            case TYPE_INSPIRE_ME:
//
//                return new InspireViewHolder(inflater.inflate(
//                        R.layout.model_inspire_item, parent, false
//                ));
//
//            case TYPE_EVENT:
//
//                return new EventViewHolder(inflater.inflate(
//                        R.layout.model_discover_event_item, parent, false
//                ));
//
//        }
//        return null;
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.setContent(discoverObjectList.get(position), position);
//    }
//
//    @Override
//    public int getItemCount() { return discoverObjectList.size(); }
//
//    @Override
//    public int getItemViewType(int position) {
//        switch (discoverObjectList.get(position).getmType()){
//            case CAROUSEL:
//                return TYPE_CAROUSEL;
//            case INSPIRE_ME:
//                return TYPE_INSPIRE_ME;
//            case EVENT:
//                return TYPE_EVENT;
//        }
//        return super.getItemViewType(position);
//    }
//
//
//
//    class CarouselViewHolder extends ViewHolder{
//        private TextView tv_carousel_title;
//        private RecyclerView re_carousel_items;
//        private Button btn_see_more;
//        private LinearLayout ll_carousel_item;
//
//        private DiscoverCarouselAdapter adapter;
//        private List<DiscoverCarouselObject> carouselObjectList = new ArrayList<>();
//
//        CarouselViewHolder (View itemView){
//            super(itemView);
//
//            tv_carousel_title = itemView.findViewById(R.id.tv_carousel_title);
//            re_carousel_items = itemView.findViewById(R.id.re_carousel_items);
//            btn_see_more = itemView.findViewById(R.id.btn_see_more);
//            ll_carousel_item = itemView.findViewById(R.id.ll_carousel_item);
//
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//            re_carousel_items.setLayoutManager(linearLayoutManager);
//
//        }
//
//        @Override
//        public void setContent(final DiscoverObject content, final int position) {
//            super.setContent(content, position);
//            tv_carousel_title.setText(content.getmTitle());
//
//            if(content.getmInfo() != null) {
//                for (int i = 0; i < content.getmInfo().length(); i++) {
//                    try {
//                        carouselObjectList.add(new DiscoverCarouselObject(content.getmInfo().get(position)));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            adapter = new DiscoverCarouselAdapter(carouselObjectList, context, new DiscoverAdapterListener.DiscoverCarouselListener() {
//                @Override
//                public void carouselItemClicked(DiscoverCarouselObject carouselModel) {
//                    Answers.getInstance().logCustom(new CustomEvent(
//                            "Select from carousel").
//                            putCustomAttribute("Type", getCarouselType()));
//                    carouselListener.carouselItemClicked(carouselModel);
//                }
//            }
//
//            private boolean checkAllLocations(){
//                for(int i = 0; i < carouselObjectList.size(); i++){
//                    if(!carouselObjectList.get(i).getmType().equals("city")){
//                        return false;
//                    }
//                }
//                return true;
//            }
//
//            private boolean checkAllArtists(){
//                for (int i = 0; i < carouselObjectList.size(); i++){
//                    if(!carouselObjectList.get(i).getmType().equals("artist")){
//                        return false;
//                    }
//                }
//                return true;
//            }
//
//            private boolean checkAllGenres(){
//                for (int i = 0; i < carouselObjectList.size(); i++){
//                    if(!carouselObjectList.get(i).getmType().equals("genre")){
//                        return false;
//                    }
//                }
//                return true;
//            }
//
//            private String getCarouselType(){
//                if(checkAllLocations()){
//                    return "city";
//                } else if(checkAllGenres()){
//                    return "genre";
//                } else if(checkAllArtists()){
//                    return "artist";
//                } else {
//                    return "";
//                }
//            }
//
//            private boolean checkShowButton(DiscoverObject content){
//                for(int i = 0; i < carouselObjectList.size(); i++){
//                    if(content.getmInfo().length() < 5){
//                        return true;
//                    }
//                }
//
//                return false;
//            }
//
//
//    }
//
//    class InspireViewHolder extends ViewHolder{
//
//        private ImageView iv_inspire_item;
//        private LinearLayout ll_rl_container;
//        private RelativeLayout rl_inspire_item;
//
//        InspireViewHolder(View itemView) {
//            super(itemView);
//            iv_inspire_item = itemView.findViewById(R.id.iv_inspire_item);
//            ll_rl_container = itemView.findViewById(R.id.ll_rl_container);
//            rl_inspire_item = itemView.findViewById(R.id.rl_inspire_item);
//        }
//
//        @Override
//        public void setContent(final DiscoverObject content, int position) {
//            super.setContent(content, position);
//            ll_rl_container.setAlpha(0.9f);
//            ll_rl_container.bringToFront();
//            try {
//                Picasso.load(content.getImage().getString(Constants.DEFAULT)).into(iv_inspire_item);
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//            rl_inspire_item.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    discoverListener.inspireMeClicked(content.getmFullObject());
//                    Answers.getInstance().logCustom(new CustomEvent("Select Inspire me").
//                            putCustomAttribute("Name", content.getmTitle()));
//                }
//            });
//
//        }
//    }
//
//
//
//    }
//
//    class EventViewHolder extends ViewHolder{
//
//        private LinearLayout ll_container;
//        private ImageView iv_main, iv_save_event;
//        private TextView tv_event_name, tv_event_date, tv_location, tv_chosen_title;
//        private View gradient_view;
//        private RelativeLayout rl_going_to_container;
//
//        EventViewHolder(View itemView) {
//            super(itemView);
//            ll_container = itemView.findViewById(R.id.ll_container);
//            ll_tag_layout = itemView.findViewById(R.id.ll_tag_layout);
//            ll_text = itemView.findViewById(R.id.ll_text);
//
//            iv_main = itemView.findViewById(R.id.iv_main);
//            iv_save_event = itemView.findViewById(R.id.iv_save_event);
//
//            tv_event_name = itemView.findViewById(R.id.tv_event_name);
//            tv_event_date = itemView.findViewById(R.id.tv_event_date);
//            tv_location = itemView.findViewById(R.id.tv_location);
//            tv_chosen_title = itemView.findViewById(R.id.tv_chosen_title);
//
//            gradient_view = itemView.findViewById(R.id.gradient_view);
//            ll_container.bringToFront();
//            rl_going_to_container.bringToFront();
//        }
//
//        @Override
//        public void setContent(final DiscoverObject content, int position) {
//            if(!content.getmTitle().equals("")){
//                tv_chosen_title.setVisibility(View.VISIBLE);
//                tv_chosen_title.setText(content.getmTitle());
//            }
//            super.setContent(content, position);
//            try {
//                Glide.with(context).load(content.getmInfo().getJSONObject(0).
//                        getJSONObject(Constants.IMAGE).getString(Constants.DEFAULT)).into(iv_main);
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//
//            ll_container.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    try {
//                        Answers.getInstance().logCustom(new CustomEvent("Select single event").
//                                putCustomAttribute("ID", content.getmInfo().getJSONObject(0).
//                                        getString(Constants.ID)));
//                        discoverListener.singleEventClicked(content.getmInfo().
//                                getJSONObject(0).getString(Constants.ID));
//                    } catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//            try {
//                if(content.getmInfo().getJSONObject(0).
//                        getString(Constants.HEADER_HEX) != null && !content.getmInfo().getJSONObject(0).
//                        getString(Constants.HEADER_HEX).equals("")){
//
//                    GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
//                            new int[]{Color.TRANSPARENT, alterColor(Color.parseColor(
//                                    content.getmInfo().getJSONObject(0).
//                                            getString(Constants.HEADER_HEX)), 0.2f)});
//                    gd.setCornerRadius(0f);
//
//                    gradient_view.setBackground(gd);
//                    gradient_view.bringToFront();
//                }
//
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//
//            try {
//                tv_event_name.setText(Html.fromHtml(content.getmInfo().getJSONObject(
//                        0).getString(Constants.NAME)));
//                tv_event_date.setText(getFormattedDate(content.getmInfo().
//                        getJSONObject(0).getString(Constants.DATE)));
//                tv_location.setText(String.format("%s, %s", content.getmInfo().getJSONObject(
//                        0).getJSONObject(Constants.VENUE).getString(Constants.NAME), content.getmInfo().getJSONObject(0).getJSONObject("Venue").
//                        getString("Town")));
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private boolean checkConnectionAvailable(){
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null;
//    }
//
//}
