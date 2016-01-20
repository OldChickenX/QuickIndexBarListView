package com.example.administrator.quickindexbarlistview;

/**
 * Created by Administrator on 2016/1/20.
 */
public class Person implements Comparable<Person> {
    private String name;
    private String pinYin;

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public Person(String name) {
        this.name = name;
        this.pinYin=PinYinUtils.getPinyin(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Person another) {
        return pinYin.compareTo(another.getPinYin());
    }
}
