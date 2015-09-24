package com.nkosi.buildingmanteinancefront.model;

import java.io.Serializable;
import java.util.List;


/**
 * @author Nkosy Description office space occupied by a tenant Date 23/04/2015
 */

public class OfficeSpace implements Serializable {

    private long officeSpace_id;
    private int officeSpace_no;
    private List<Facility> facilities;

    private OfficeSpace() {
    }

    public OfficeSpace(Builder builder) {
        officeSpace_id = builder.officeSpace_id;
        officeSpace_no = builder.officeSpace_no;
        facilities = builder.facilities;
    }

    public static class Builder {

        private long officeSpace_id;
        private int officeSpace_no;
        private List<Facility> facilities;

        public Builder(int officeSpace_no) {
            this.officeSpace_no = officeSpace_no;
        }

        public Builder officeSpace_id(long value) {
            this.officeSpace_id = value;
            return this;
        }

        public Builder officeSpace_no(int value) {
            this.officeSpace_no = value;
            return this;
        }

        public Builder facilities(List<Facility> value) {
            this.facilities = value;
            return this;
        }

        public Builder copy(OfficeSpace value) {
            this.officeSpace_id = value.officeSpace_id;
            this.officeSpace_no = value.officeSpace_no;
            this.facilities = value.facilities;
            return this;
        }

        public OfficeSpace build() {
            return new OfficeSpace(this);
        }
    }

    public long getOfficeSpace_id() {
        return officeSpace_id;
    }

    public int getOfficeSpace_no() {
        return officeSpace_no;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfficeSpace that = (OfficeSpace) o;

        if (officeSpace_id != that.officeSpace_id) return false;
        if (officeSpace_no != that.officeSpace_no) return false;
        return !(facilities != null ? !facilities.equals(that.facilities) : that.facilities != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (officeSpace_id ^ (officeSpace_id >>> 32));
        result = 31 * result + officeSpace_no;
        result = 31 * result + (facilities != null ? facilities.hashCode() : 0);
        return result;
    }
}
