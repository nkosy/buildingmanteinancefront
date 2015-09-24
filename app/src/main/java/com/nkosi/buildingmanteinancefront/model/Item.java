package com.nkosi.buildingmanteinancefront.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Nkosy Description e.g door, window, blinds, table etc Date 23/04/2015
 */

public class Item implements Serializable {

    private long item_id;
    private String item_name;
    private double cost;
    private Date last_mantained;

    public Item() {
    }

    public Item(Builder builder) {
        item_id = builder.item_id;
        cost = builder.cost;
        item_name = builder.item_name;
        last_mantained = builder.last_mantained;

    }

    public static class Builder {

        private long item_id;
        private String item_name;
        private double cost;
        private Date last_mantained;

        public Builder(String item_name) {
            this.item_name = item_name;
        }

        public Builder item_id(Long value) {
            this.item_id = value;
            return this;
        }

        public Builder item_name(String value) {
            this.item_name = value;
            return this;
        }

        public Builder cost(double value) {
            this.cost = value;
            return this;
        }

        public Builder last_mantained(Date value) {
            this.last_mantained = value;
            return this;
        }

        public Builder copy(Item value) {
            this.item_id = value.item_id;
            this.item_name = value.item_name;
            this.last_mantained = value.last_mantained;
            return this;
        }

        public Item build() {
            return new Item(this);
        }

    }

    public long getItem_id() {
        return item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public double getCost() {
        return cost;
    }

    public Date getLast_mantained() {
        return last_mantained;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (item_id != item.item_id) return false;
        if (Double.compare(item.cost, cost) != 0) return false;
        if (item_name != null ? !item_name.equals(item.item_name) : item.item_name != null)
            return false;
        return !(last_mantained != null ? !last_mantained.equals(item.last_mantained) : item.last_mantained != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (item_id ^ (item_id >>> 32));
        result = 31 * result + (item_name != null ? item_name.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (last_mantained != null ? last_mantained.hashCode() : 0);
        return result;
    }
}
