package com.jugarte.gourmet.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.Collections;

public class Gourmet implements Parcelable {

    public Gourmet() {
    }

    private String cardNumber = null;
    private String currentBalance = null;
    private String modificationDate = null;
    private ArrayList<Operation> operations = null;
    private boolean offlineMode;
    private String errorCode = null;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Exclude
    public boolean isOfflineMode() {
        return offlineMode;
    }

    public void setOfflineMode(boolean offlineMode) {
        this.offlineMode = offlineMode;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public ArrayList<Operation> getOperations(String pattern) {
        ArrayList<Operation> copyOperations = new ArrayList<>();
        for (Operation operation : operations) {
            if (operation.getName().toLowerCase().contains(pattern.toLowerCase())) {
                copyOperations.add(operation);
            }
        }
        return copyOperations;
    }

    public void setOperations(ArrayList<Operation> operations) {
        this.operations = operations;
    }

    public void addOperation(Operation operation) {
        if (operations == null) {
            operations = new ArrayList<>();
        }

        this.operations.add(operation);
    }

    @Exclude
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cardNumber);
        dest.writeString(this.currentBalance);
        dest.writeString(this.modificationDate);
        dest.writeByte(this.offlineMode ? (byte) 1 : (byte) 0);
        dest.writeList(this.operations);
        dest.writeString(this.errorCode);
    }

    protected Gourmet(Parcel in) {
        this.cardNumber = in.readString();
        this.currentBalance = in.readString();
        this.modificationDate = in.readString();
        this.offlineMode = in.readByte() != 0;
        this.operations = new ArrayList<>();
        in.readList(this.operations, Operation.class.getClassLoader());
        this.errorCode = in.readString();
    }

    public static final Parcelable.Creator<Gourmet> CREATOR = new Parcelable.Creator<Gourmet>() {
        @Override
        public Gourmet createFromParcel(Parcel source) {
            return new Gourmet(source);
        }

        @Override
        public Gourmet[] newArray(int size) {
            return new Gourmet[size];
        }
    };

    public void orderOperations() {
        Collections.sort(operations, Collections.<Operation>reverseOrder());
    }
}
