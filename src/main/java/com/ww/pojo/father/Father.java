package com.ww.pojo.father;

public class Father {

    protected Integer fatherId;

    protected String fatherName;

    protected void father(){
        System.out.println("I'm Father class");
    }

    public Father() {
    }

    public Father(Integer fatherId, String fatherName) {
        this.fatherId = fatherId;
        this.fatherName = fatherName;
    }

    @Override
    public String toString() {
        return "Father{" +
                "fatherId=" + fatherId +
                ", fatherName='" + fatherName + '\'' +
                '}';
    }
}