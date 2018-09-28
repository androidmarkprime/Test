package com.example.markprime.test.Checkout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.markprime.test.FragmentInteractionListener;
import com.example.markprime.test.Model.DeliveryObject;
import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.Model.OptOutObject;
import com.example.markprime.test.Model.PaymentObject;
import com.example.markprime.test.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CheckoutFragment
        extends Fragment
        implements View.OnClickListener, DeliveryAdapterListener,
        PaymentAdapterListener, OptOutAdapterListener{

    private Context context;
    private FragmentInteractionListener fragmentInteractionListener;
    private LinearLayout checkout_ll, ll_extras_container,
            ll_card_conatiner, ll_payment_container, ll_gpay_container,
            ll_extras_1_container, ll_extras_2_container, pay_button_ll, ll_cd_container, ll_rp_container,
            ll_card_input_conatiner, ll_comms_preferences,ll_delivery_options, ll_optOut;
    private RecyclerView re_delivery, re_summary, re_opt_out_choices;
    private ImageView iv_gpay_icon, iv_card_icon;
    private TextView tv_delivery_info, tv_payment_info, tv_gpay_text, tv_card_text, tv_extras_text,
            tv_extras_1, tv_extras_1_price, tv_extras_2, tv_extras_2_price, tv_summary_info, tv_comms_title,
            tv_opt_out, tv_comms_detail, tv_pay_amount, tv_total_total;
    private CheckBox cb_extras_1, cb_extras_2;
    private Button btn_extras_1_info, btn_extras_2_info;
    private LinearLayoutManager llm_del, llm_sum, llm_opt;
    private DeliveryAdapter deliveryAdapter;
    private OptOutAdapter optOutAdapter;
    private PaymentAdapter paymentAdapter;

    private static final String EVENT_OBJECT = "event_object";
    private EventObject eventObject;

    private boolean refundProtectionEnabled = false, charityDonationEnabled = false;
    private boolean payWithCard = false, payWithGoogle = false;
    private boolean RapidScan = false, Posted = false, Collect = false;
    private boolean personalised = false, targetted = false;
    private double totalPrice = 0.00;

    private boolean deliveryDetails = false;
    private boolean optOutDetails = false;
    private boolean paymentDetails = false;


    private List<PaymentObject> paymentObject = new ArrayList<>();
    private List<DeliveryObject> deliveryObject = new ArrayList<>();

    public CheckoutFragment() {
        // Required empty public constructor
    }

    public static CheckoutFragment newInstance(JSONObject eventDetails){
        CheckoutFragment fragment = new CheckoutFragment();
        Bundle args = new Bundle();
        args.putString(EVENT_OBJECT, eventDetails.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);


        setupCheckoutViews(view);


            List<DeliveryObject> deliveryObjects = deliveryObject;
            if (deliveryDetails == false) {
                deliveryObjects.add(new DeliveryObject(ContextCompat.getDrawable(context, R.mipmap.icn_rapid_scan),
                        "RapidScan", "Tickets in your mobile app.", "0.50"));
                deliveryObjects.add(new DeliveryObject(ContextCompat.getDrawable(context, R.mipmap.icn_posted_tickets),
                        "Posted", "Tickets delivered to your door.", "3.99"));
                deliveryObjects.add(new DeliveryObject(ContextCompat.getDrawable(context, R.mipmap.icn_collect_tickets),
                        "Collect", "Collect your tickets on arrival at the venue.", "1.00"));
            deliveryDetails = true;
            }

            List<OptOutObject> optOutObjects = new ArrayList<>();
            if (optOutDetails == false) {
                optOutObjects.add(
                        new OptOutObject("I'd prefer NOT to receive a weekly personalised events guide"));
                optOutObjects.add(
                        new OptOutObject("I'd prefer NOT to receive a targetted event information"));

                optOutDetails = true;
            }

            List<PaymentObject> paymentObjects = paymentObject;
            if(paymentDetails == false) {
                paymentObjects.add(
                        new PaymentObject("2 x", "Standard Entry Tickets", "50.00"));
            paymentDetails = true;
            }


        setAdapters(deliveryObjects, optOutObjects, paymentObjects);

        setupButtons(view);
        setupCheckBoxes(view);
        setupIV(view);
        setupTV(view);
        setupLL(view);
        onClick(view);

       return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        fragmentInteractionListener = (FragmentInteractionListener) context;

        if (getArguments() != null) {
            try {
                eventObject = new EventObject(new JSONObject(getArguments().getString(EVENT_OBJECT)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void setupCheckoutViews (View view){
        re_delivery = view.findViewById(R.id.re_delivery);
        LinearLayoutManager llm_del = new LinearLayoutManager(context);
        llm_del.setOrientation(LinearLayoutManager.HORIZONTAL);
        re_delivery.setLayoutManager(llm_del);


        re_opt_out_choices = view.findViewById(R.id.re_opt_out_choices);
        LinearLayoutManager llm_opt = new LinearLayoutManager(context);
        llm_opt.setOrientation(LinearLayoutManager.VERTICAL);
        re_opt_out_choices.setLayoutManager(llm_opt);


        re_summary = view.findViewById(R.id.re_summary);
        LinearLayoutManager llm_sum = new LinearLayoutManager(context);
        llm_sum.setOrientation(LinearLayoutManager.VERTICAL);
        re_summary.setLayoutManager(llm_sum);


    }

    private void setAdapters(List<DeliveryObject> deliveryObjects, List<OptOutObject> optOutObjects
            , List<PaymentObject> paymentObjects){
        deliveryAdapter = new DeliveryAdapter(context, deliveryObjects, this);
        re_delivery.setAdapter(deliveryAdapter);

        optOutAdapter = new OptOutAdapter(context, optOutObjects, this);
        re_opt_out_choices.setAdapter(optOutAdapter);

        paymentAdapter = new PaymentAdapter(context, paymentObjects, this);
        re_summary.setAdapter(paymentAdapter);

        getTotalAmount();
        paymentAdapter.notifyDataSetChanged();

    }

    private void setupLL(View view){
        checkout_ll = view.findViewById(R.id.checkout_ll);
        ll_extras_container = view.findViewById(R.id.ll_extras_container);
        ll_card_conatiner = view.findViewById(R.id.ll_card_conatiner);
        ll_payment_container = view.findViewById(R.id.ll_payment_container);
        ll_gpay_container = view.findViewById(R.id.ll_gpay_container);
        ll_extras_1_container = view.findViewById(R.id.ll_extras_1_container);
        ll_extras_2_container = view.findViewById(R.id.ll_extras_2_container);
        pay_button_ll = view.findViewById(R.id.pay_button_ll);
        ll_cd_container = view.findViewById(R.id.ll_cd_container);
        ll_rp_container = view.findViewById(R.id.ll_rp_container);
        ll_card_input_conatiner = view.findViewById(R.id.ll_card_input_conatiner);
        ll_comms_preferences = view.findViewById(R.id.ll_comms_preferences);
        ll_optOut = view.findViewById(R.id.ll_optOut);


        ll_cd_container.setOnClickListener(this);
        ll_rp_container.setOnClickListener(this);
        ll_gpay_container.setOnClickListener(this);
        ll_card_conatiner.setOnClickListener(this);
        ll_comms_preferences.setOnClickListener(this);

        pay_button_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalClicked(eventObject);
            }
        });


    }


    private void setupIV (View view) {
        iv_gpay_icon = view.findViewById(R.id.iv_gpay_icon);
        iv_card_icon = view.findViewById(R.id.iv_card_icon);

    }

    private void setupTV(View view){
        tv_delivery_info = view.findViewById(R.id.tv_delivery_info);
        tv_payment_info = view.findViewById(R.id.tv_payment_info);
        tv_gpay_text = view.findViewById(R.id.tv_gpay_text);
        tv_card_text = view.findViewById(R.id.tv_card_text);
        tv_extras_text = view.findViewById(R.id.tv_extras_text);
        tv_extras_1 = view.findViewById(R.id.tv_extras_1);
        tv_extras_1_price = view.findViewById(R.id.tv_extras_1_price);
        tv_extras_2 = view.findViewById(R.id.tv_extras_2);
        tv_extras_2_price = view.findViewById(R.id.tv_extras_2_price);
        tv_summary_info = view.findViewById(R.id.tv_summary_info);
        tv_comms_title = view.findViewById(R.id.tv_comms_title);
        tv_opt_out = view.findViewById(R.id.tv_opt_out);
        tv_comms_detail = view.findViewById(R.id.tv_comms_detail);
        tv_pay_amount = view.findViewById(R.id.tv_pay_amount);
        tv_total_total=view.findViewById(R.id.tv_total_total);

        tv_opt_out.setOnClickListener(this);
        tv_comms_detail.setOnClickListener(this);
        tv_comms_title.setOnClickListener(this);

        setTotalPrice();

    }

    private void setupButtons(View view){
        btn_extras_1_info = view.findViewById(R.id.btn_extras_1_info);
        btn_extras_2_info = view.findViewById(R.id.btn_extras_2_info);

        btn_extras_1_info.setOnClickListener(this);
        btn_extras_2_info.setOnClickListener(this);
    }

    private void setupCheckBoxes (View view){
        cb_extras_1 = view.findViewById(R.id.cb_extras_1);
        cb_extras_2 = view.findViewById(R.id.cb_extras_2);

        cb_extras_1.setOnClickListener(this);
        cb_extras_2.setOnClickListener(this);


    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.cb_extras_1: charityDonationClicked(); break;
            case R.id.cb_extras_2: refundProtectionClicked(); break;
//            case R.id.btn_extras_1_info: charityInfoClicked(); break;
            case R.id.btn_extras_2_info: refundInfoClicked(); break;
            case R.id.ll_gpay_container: payByGPayClicked(); break;
            case R.id.ll_card_conatiner: payByCardClicked(); break;
            case R.id.tv_comms_title: commsPreferencesClicked(); break;
            case R.id.tv_comms_detail: termsClicked(); break;
            case R.id.tv_opt_out: privacyClicked(); break;
        }
    }

    private void privacyClicked() {
        Intent privacy = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.skiddle.com/terms/privacy.php"));
        startActivity(privacy);
    }

    private void termsClicked() {
        Intent terms = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.skiddle.com/terms"));
        startActivity(terms);
    }

    private void charityDonationClicked() {

        if(!charityDonationEnabled){
            setCharityEnabledSelected();
            charityDonationEnabled = true;
        } else {
            charityDonationEnabled = false;
            ll_cd_container.setBackground(
                    context.getResources().getDrawable(R.drawable.extras_turq_rounded));
            removeCharityDonationFromSummary();
        }
   }

    private void setCharityEnabledSelected(){
            ll_cd_container.setBackground(
                    context.getResources().getDrawable(R.drawable.background_rounded_grey_dark));
            addCharityDonationToSummary();
    }


    private void charityInfoClicked() {

    }


    public void addCharityDonationToSummary() {
        paymentObject.add(new PaymentObject("    ", "Charity Donation", "1.00"));
        paymentAdapter.notifyDataSetChanged();
        getTotalAmount();
        setSummaryAdapter(paymentObject);
        setTotalPrice();

        Toast.makeText(context, "Thank you for donating to such a worthy cause.", Toast.LENGTH_SHORT).show();

    }

    public void removeCharityDonationFromSummary() {
        removeItemFromList("Charity Donation");
        getTotalAmount();
        setTotalPrice();
   }



    private void refundProtectionClicked() {
        if(!refundProtectionEnabled){
            setRefundEnabledSelected();
            refundProtectionEnabled = true;
        } else {
            refundProtectionEnabled = false;
            ll_rp_container.setBackground(
                    context.getResources().getDrawable(R.drawable.extras_turq_rounded));
            removeRefundProtectionFromSummary();
        }
    }

    private void setRefundEnabledSelected() {
        ll_rp_container.setBackground(
                context.getResources().getDrawable(R.drawable.background_rounded_grey_dark));
        addRefundProtectionToSummary();
    }

    private void addRefundProtectionToSummary() {

        paymentObject.add(new PaymentObject("    ", "Refund Protection", "1.00"));
        getTotalAmount();
        setTotalPrice();
        setSummaryAdapter(paymentObject);

        Toast.makeText(context, "You're covered should the worse happen.", Toast.LENGTH_SHORT).show();
    }

    private void removeRefundProtectionFromSummary() {
        removeItemFromList("Refund Protection");
        getTotalAmount();
        setTotalPrice();
    }

    public void removeItemFromList(String string){
        for(int i = 0; i < paymentObject.size(); i++){
            if(paymentObject.get(i).getTv_ticket_details().equals(string)){
                paymentObject.remove(i);
                paymentAdapter.notifyDataSetChanged();
            }
        }
    }



    private void refundInfoClicked() {



    }

    private void payByGPayClicked() {

        if(!payWithGoogle){
            payWithGoogle = true;
            payWithCard = false;
            ll_gpay_container.setBackgroundColor(getResources().getColor(R.color.Grey_LT));
            ll_card_conatiner.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            ll_card_input_conatiner.setVisibility(View.GONE);

        } else {
            payWithGoogle = false;
            ll_gpay_container.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            resetPaymentContainerToDefault();
        }
    }

    private void payByCardClicked() {

        if(!payWithCard){
            payWithCard = true;
            payWithGoogle = false;
            ll_card_conatiner.setBackgroundColor(getResources().getColor(R.color.Grey_LT));
            ll_card_input_conatiner.setVisibility(View.VISIBLE);
            ll_gpay_container.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            resetPaymentContainerToDefault();
        } else {
            payWithCard = false;
            ll_card_input_conatiner.setVisibility(View.GONE);
            ll_card_conatiner.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            resetPaymentContainerToDefault();
        }
    }

    private void resetPaymentContainerToDefault() {

        tv_pay_amount.setVisibility(View.VISIBLE);
        pay_button_ll.setClickable(false);
    }

    private void commsPreferencesClicked() {
        if(!ll_comms_preferences.isShown()){
            ll_comms_preferences.setVisibility(View.VISIBLE);
        }else{
            ll_comms_preferences.setVisibility(View.GONE);
        }
    }

    public void setSummaryAdapter(List<PaymentObject> paymentObjects){
        paymentAdapter = new PaymentAdapter(context, paymentObjects, this);
        re_summary.setAdapter(paymentAdapter);
    }


    public void deliveryClicked(DeliveryObject deliveryObject) {

        removeItemFromList("RapidScan");
        removeItemFromList("Posted");
        removeItemFromList("Collect");



        if (deliveryObject.getTv_delivery_name().contains("RapidScan")){

            if(!RapidScan) {
                RapidScan = true;

                paymentObject.add(new PaymentObject("    ", "RapidScan", "0.50"));
                updatePaymentdetails();

            } else {

                RapidScan = false;
                re_delivery.findViewById(R.id.ll_delivery_options).setBackground(context.getResources().getDrawable(R.drawable.ticket_deliver_white));
                removeItemFromList("RapidScan");
                updatePaymentdetails();

            }

        }else if (deliveryObject.getTv_delivery_name().contains("Posted")){

            if(!Posted){
                Posted = true;

                paymentObject.add(new PaymentObject("    ", "Posted", "3.99"));
                updatePaymentdetails();

            } else {
                Posted = false;
                re_delivery.findViewById(R.id.ll_delivery_options).setBackground(context.getResources().getDrawable(R.drawable.ticket_deliver_white));
                removeItemFromList("Posted");
                updatePaymentdetails();

            }


        } else if (deliveryObject.getTv_delivery_name().contains("Collect")){
            if(!Collect){
                Collect = true;

                paymentObject.add(new PaymentObject("    ", "Collect", "1.00"));
                updatePaymentdetails();

            } else {
                Collect = false;
                re_delivery.findViewById(R.id.ll_delivery_options).setBackground(context.getResources().getDrawable(R.drawable.ticket_deliver_white));
                removeItemFromList("Collect");
                updatePaymentdetails();

            }

        } else {

            RapidScan = false; Posted = false; Collect = false;

            removeItemFromList("RapidScan"); removeItemFromList("Posted"); removeItemFromList("Collect");
            updatePaymentdetails();
        }

    }

    private void updatePaymentdetails() {
        getTotalAmount();
        setTotalPrice();
        setSummaryAdapter(paymentObject);
    }

    private void setTotalPrice(){

        tv_total_total.setText("£" + totalPrice);
        tv_pay_amount.setText("Total to pay £" + totalPrice);


        }

    public double getTotalAmount(){

        totalPrice = 0.00;

        for(int i = 0; i < paymentObject.size(); i++){
            double totalTicketCost = (Double.parseDouble(paymentObject.get(i).getTv_ticket_subtotal()));
            totalPrice = totalPrice + totalTicketCost;
        }
        return totalPrice;
    }

    public void optOutClicked(OptOutObject optOutObject) {

        if(optOutObject.getTv_opt_out().contains("personalised")){

            if(!personalised){
                personalised = true;

                Toast.makeText(context, "You will no longer receive personalised communications from us",
                        Toast.LENGTH_LONG).show();
            } else {
                personalised = false;

            }
        } else if (optOutObject.getTv_opt_out().contains("targetted")) {

            if(!targetted){
                targetted = true;

                Toast.makeText(context, "You will no longer receive targetted communications from us",
                        Toast.LENGTH_LONG).show();
            } else {
                targetted = false;


            }

        } else {

            personalised = false; targetted = false;

        }

    }


    public void totalClicked(EventObject eventObject) {
        fragmentInteractionListener.openMyTicketsFragment(eventObject.getFullObject());
    }
}
