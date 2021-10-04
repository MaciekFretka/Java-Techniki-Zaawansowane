package src;

import java.sql.Date;

public class Due {
    private Date date;
    private int amount;

    public int getROUTER_ID() {
        return ROUTER_ID;
    }

    public void setROUTER_ID(int ROUTER_ID) {
        this.ROUTER_ID = ROUTER_ID;
    }

    private int ROUTER_ID;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public Due(Date date, int amount, int ROUTER_ID) {
        this.date = date;
        this.amount = amount;
        this.ROUTER_ID = ROUTER_ID;
    }
}
