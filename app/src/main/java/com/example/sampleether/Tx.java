package com.example.sampleether;

public class Tx {

    private String mFrom;

    private String mTo;

    private String mValue;

    public Tx(String from, String to, String value) {
        mFrom = from;
        mTo = to;
        mValue = value;
    }

    public String getFrom() {
        return mFrom;
    }

    public String getTo() {
        return mTo;
    }

    public String getValue() {
        return mValue;
    }
}
