package main.elementi;

import java.io.*;

public class Doxod extends AbsModel implements Externalizable {
    private  String name;
    private String number;
    private float money;
    private String text;
    private long timebeetwinpay;
    private boolean bulblock;
    //

    private transient Object lockerDoxod;

    public Doxod(){
        this.lockerDoxod = new Object();
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

    public float getSumma() {
        return money;
    }

    public void setSumma(float money) {
        this.money = money;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTimebeetwinpay() {
        return timebeetwinpay;
    }

    public void setTimebeetwinpay(long timebeetwinpay) {
        this.timebeetwinpay = timebeetwinpay;
    }

    public boolean isBulblock() {
        return bulblock;
    }

    public void setBulblock(boolean bulblock) {
        this.bulblock = bulblock;
    }

    public Object getSelfLocker() {
        return lockerDoxod;
    }

    public void setSelfLocker(Object selfLocker) {
        this.lockerDoxod = selfLocker;
    }
    public void print(){
        System.out.println(" Имя потенциального дохода- "+ getName());

        System.out.println(" Размер суммы поступления - "+ getSumma());
        System.out.println(" Заморожено или нет поступление дохода - "+ isBulblock());
        System.out.println("Дополнительная информация - "+getText());
        System.out.println("Время между поступлением дохода - "+getTimebeetwinpay() + " мс");
        System.out.println(" Номер карты на которую будет зачислен платеж "+ getNumber());
        System.out.println("---------------------------");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        out.writeUTF(this.name);
        out.writeUTF(this.number);
        out.writeFloat(this.money);
        out.writeUTF(this.text);
        out.writeLong(this.timebeetwinpay);
        out.writeBoolean(this.bulblock);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.number = in.readUTF();
        this.money = in.readFloat();
        this.text = in.readUTF();
        this.timebeetwinpay = in.readLong();
        this.bulblock = in.readBoolean();
        //
        this.lockerDoxod = new Object();
    }
}

