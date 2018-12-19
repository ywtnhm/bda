package com.monetware.bda.model;


public class WordFrequency implements  Comparable{

    private String dateStr;

    private Integer count;

    public String getDateStr(){
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    @Override
    public int compareTo(Object obj) {
        int year = Integer.parseInt(this.getDateStr().split("-")[0]) - Integer.parseInt(((WordFrequency)obj).getDateStr().split("-")[0]);
        int month = Integer.parseInt(this.getDateStr().split("-")[1]) - Integer.parseInt(((WordFrequency)obj).getDateStr().split("-")[1]);
        if (year > 0 || year == 0 && month > 0) {
            return 1;
        } else if (year == 0 && month == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
