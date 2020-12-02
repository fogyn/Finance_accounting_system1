package main.elementi;

import java.io.*;
import java.util.Calendar;
import java.util.Random;

public class Card extends AbsModel implements Externalizable {
    private String name;
    private String number;
    private int month;
    private int year;
    private float money;
    private boolean bulblock;
    private String text;
    private String phone;

    transient private long min = 1000000000000000l;
    transient private long max = 10000000000000000l;
    transient private Random rnd = new Random();
    transient private Calendar calendar;
    transient private Object cardLock;

    public Card(){
        this.number = ""+(long)(Math.random()*(max-min)+min);
        this.calendar=Calendar.getInstance();
        this.month = calendar.get(Calendar.MONTH);
        this.year = calendar.get(Calendar.YEAR)+2;
        this.bulblock = false;
        this.cardLock = new Object();
    }

    public Card(String name,  float money, String text) {
        this.name = name;
        this.number = ""+(long)(Math.random()*(max-min)+min);
        this.calendar=Calendar.getInstance();
        this.month = calendar.get(Calendar.MONTH);
        this.year = calendar.get(Calendar.YEAR)+2;
        this.money=money;
        this.text = text;
        this.bulblock = false;
        this.phone ="нет данных";
        this.cardLock = new Object();
    }
    public Card(String name,  float money, String text, String phone) {
        this.name = name;
        this.number = ""+(long)(Math.random()*(max-min)+min);
        this.calendar=Calendar.getInstance();
        this.month = calendar.get(Calendar.MONTH);
        this.year = calendar.get(Calendar.YEAR)+2;
        this.money=money;
        this.text = text;
        this.bulblock = false;
        this.phone =phone;
        this.cardLock = new Object();
    }



    public Card(String name,   String text) {
        this.name = name;
        this.number = ""+(long)(Math.random()*(max-min)+min);
        this.calendar=Calendar.getInstance();
        this.month = calendar.get(Calendar.MONTH);
        this.year = calendar.get(Calendar.YEAR)+2;
        this.money=0;
        this.text = text;
        this.bulblock = false;
        this.phone ="нет данных";
        this.cardLock = new Object();
    }


    public Card(String name, String number, int month, int year, float money) {
        this.name = name;
        this.number = number;
        this.month = month;
        this.year = year;
        this.money=money;
        this.bulblock=false;
        this.text = "Нет данных";
        this.cardLock = new Object();
    }

    public Card(String name, String number, int month, int year, float money, String text) {
        this.name = name;
        this.number = number;
        this.month = month;
        this.year = year;
        this.money=money;
        this.text = text;
        this.bulblock = false;
        this.cardLock = new Object();
    }

    public Object getCardLock() {
        return cardLock;
    }

    public void setCardLock(Object cardLock) {
        this.cardLock = cardLock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isBulblock() {
        return bulblock;
    }

    public void setBulblock(boolean bulblock) {
        this.bulblock = bulblock;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public float getSumma() {
        return money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSumma(float money) {
        this.money = money;
    }
    public void printCard(){
        System.out.println("имя карты - "+getName());
        System.out.println("номер карты - "+getNumber());
        System.out.println("до какого года действует карта - "+getYear());
        System.out.println("до какого месяца действует карта - "+getMonth());
        System.out.println("остаток на счете - "+ getSumma());
        System.out.println("Заблокирована или нет карта - "+isBulblock());
        System.out.println("Дополнительная информация по карте - "+getText());
        System.out.println("Телефон привязанный к карте - "+getPhone());
        System.out.println("---------------------------");

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        out.writeUTF(this.name);
        out.writeUTF(this.number);
        out.writeInt(this.month);
        out.writeInt(this.year);
        out.writeFloat(this.money);
        out.writeBoolean(this.bulblock);
        out.writeUTF(this.text);
        out.writeUTF(this.phone);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        this.name = in.readUTF();
        this.number = in.readUTF();
        this.month = in.readInt();
        this.year = in.readInt();
        this.money = in.readFloat();
        this.bulblock = in.readBoolean();
        this.text = in.readUTF();
        this.phone = in.readUTF();

        //
        this.cardLock = new Object();
    }
}
