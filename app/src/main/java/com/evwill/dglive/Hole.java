package com.evwill.dglive;

public class Hole {
    private int mPar;
    private int mLength;
    private String mName;
    private int mOrder;

    Hole(String name, int par) {
        mName = name;
        mPar = par;
    }

    public int getPar() {
        return mPar;
    }

    public void setPar(int par) {
        mPar = par;
    }

    public int getLength() {
        return mLength;
    }

    public void setLength(int length) {
        mLength = length;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getOrder() {
        return mOrder;
    }

    public void setOrder(int order) {
        mOrder = order;
    }
}
