package com.perfaware.sterlingtester.models;

public class Order {
    OrderUid orderUid;

    public void setOrderUid(OrderUid orderUid) {
        this.orderUid = orderUid;
    }

    public OrderUid getOrderUid() {
        return this.orderUid;
    }

    public String getOrderNumber() {
        return this.orderUid.prefix + this.orderUid.start;
    }

    public boolean hasNext() {
        if (this.orderUid.called) {
            if (Integer.parseInt(this.orderUid.start) + 1 <= Integer.parseInt(this.orderUid.end)) {
                int numberOfDigits = this.orderUid.start.length();
                this.orderUid.start = String.format("%0" + numberOfDigits + "d", (Integer.parseInt(this.orderUid.start) + 1));
                return true;
            } else return false;
        } else {
            this.orderUid.called = true;
            return true;
        }
    }

    public static class OrderUid {
        String prefix;
        String start;
        String end;
        boolean called;

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

    }
}
