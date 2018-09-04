package com.example.markprime.test.Model;

import java.util.ArrayList;
import java.util.List;

public class PaymentObject {

    private String tv_quantity_tickets, tv_ticket_details, tv_ticket_subtotal;


    public PaymentObject(
            String tx_quantity, String tx_details, String tx_subtotal
    ){
        this.tv_quantity_tickets = tx_quantity;
        this.tv_ticket_details = tx_details;
        this.tv_ticket_subtotal = tx_subtotal;
    }



    public String getTv_quantity_tickets() {
        return tv_quantity_tickets;
    }

    public String getTv_ticket_details() {
        return tv_ticket_details;
    }

    public String getTv_ticket_subtotal() {
        return tv_ticket_subtotal;
    }

}


