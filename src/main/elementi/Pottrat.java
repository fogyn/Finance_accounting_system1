package main.elementi;
import java.io.*;

public class Pottrat extends AbsModel implements Externalizable {
    private String name;
    private  String number;
    private boolean activPay;
    private boolean type;
    private float allprice;
    private float endprice;
    private float money;
    private String text;
    private boolean mustpay;
    private long timebeetwinpay;
    private boolean bulblock;
    private transient Object lockerPottrat;
    public Pottrat(String name, boolean type, boolean activPay, float allprice, float money, String number, String text, long timebeetwinpay) {
        this.number = number;
        this.name = name;
        this.activPay = activPay;
        this.type = type;
        this.allprice = allprice;
        this.money = money;
        this.text = text;
        this.timebeetwinpay = timebeetwinpay;
        this.endprice = this.allprice;
        this.mustpay = false;
        this.bulblock = false;
        this.lockerPottrat = new Object();
    }
    public Pottrat(){
        this.mustpay = false;
        this.bulblock = false;
        this.lockerPottrat = new Object();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isActivPay() {
        return activPay;
    }
    public void setActivPay(boolean activPay) {
        this.activPay = activPay;
    }
    public float getAllprice() {
        return allprice;
    }
    public void setAllprice(float allprice) {
        this.allprice = allprice;
    }
    public float getEndprice() {
        return endprice;
    }
    public void setEndprice(float endprice) {
        this.endprice = endprice;
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
    public boolean isMustpay() {
        return mustpay;
    }
    public void setMustpay(boolean mustpay) {
        this.mustpay = mustpay;
    }
    public long getTimebeetwinpay() {
        return timebeetwinpay;
    }
    public void setTimebeetwinpay(long timebeetwinpay) {
        this.timebeetwinpay = timebeetwinpay;
    }
    public boolean isType() {
        return type;
    }
    public void setType(boolean type) {
        this.type = type;
    }
    public boolean isBulblock() {
        return bulblock;
    }
    public void setBulblock(boolean bulblock) {
        this.bulblock = bulblock;
    }
    public Object getLocker() {
        return lockerPottrat;
    }
    public void setLocker(Object locker) {
        this.lockerPottrat = locker;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void printPotTrat(){
        System.out.println(" Имя потенциальной покупки- "+getName());
        System.out.println(" Тип платежа (с завершением / без)- "+isType());
        System.out.println(" Полная стоиомсть - "+getAllprice());
        System.out.println(" Остаток по платежу - "+getEndprice());
        System.out.println(" Размер платежа - "+ getSumma());
        System.out.println(" Автоплатеж включен / или нет - "+isActivPay());
        System.out.println(" Заморожен или нет платеж - "+ isBulblock());
        System.out.println("Дополнительная информация - "+getText());
        System.out.println("Надо ли совершать платеж - "+isMustpay());
        System.out.println("Время между платежами - "+getTimebeetwinpay() + " мс");
        System.out.println(" Номер карты с которой будет проведен платеж "+ getNumber());
        System.out.println("---------------------------");
    }
    public void printPotTrat2(){
        System.out.println(" Имя потенциальной покупки- "+getName());
        System.out.println(" Размер платежа - "+ getSumma());
        System.out.println(" Номер карты с которой будет проведен платеж "+ getNumber());
        System.out.println("---------------------------");
    }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(this.name);
        out.writeUTF(this.number);
        out.writeBoolean(this.activPay);
        out.writeBoolean(this.type);
        out.writeFloat(this.allprice);
        out.writeFloat(this.endprice);
        out.writeFloat(this.money);
        out.writeUTF(this.text);
        out.writeBoolean(this.mustpay);
        out.writeLong(this.timebeetwinpay);
        out.writeBoolean(this.bulblock);
    }
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.number = in.readUTF();
        this.activPay = in.readBoolean();
        this.type = in.readBoolean();
        this.allprice = in.readFloat();
        this.endprice = in.readFloat();
        this.money = in.readFloat();
        this.text = in.readUTF();
        this.mustpay = in.readBoolean();
        this.timebeetwinpay = in.readLong();
        this.bulblock = in.readBoolean();
        //
        this.lockerPottrat = new Object();
    }
}
