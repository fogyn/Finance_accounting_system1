package main.elementi;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;


public class Cash extends AbsModel implements Externalizable {
    private String name;
    private String number;
    private String data;
    private float money;
    private boolean bulblock;
    private String text;
    private String phone;
    private ArrayList<String> hashList;

    transient private long min = 1000000000000000l;
    transient private long max = 10000000000000000l;
    transient private Random rnd = new Random();
    transient private Calendar calendar;
    transient private Object lockCash;

    public Cash(){
        hashList = new ArrayList<>();
        this.number = todoCod()+(long)(Math.random()*(max-min)+min);
        //this.number = todoCod();
        this.calendar= GregorianCalendar.getInstance();
        this.data = ""+calendar.getTime();
        this.bulblock = false;
        this.lockCash = new Object();
    }
    public Cash(String name,   String phone) {
        hashList = new ArrayList<>();
        this.name = name;
        this.number = todoCod()+(long)(Math.random()*(max-min)+min);
        //this.number = todoCod();
        this.calendar=GregorianCalendar.getInstance();
        this.data = ""+calendar.getTime();
        this.money=0;
        this.text = "нет данных";
        this.bulblock = false;
        this.phone =phone;
        this.lockCash = new Object();
    }

    public Cash(String name,  float money, String phone) {
        hashList = new ArrayList<>();
        this.name = name;
        this.number = todoCod()+(long)(Math.random()*(max-min)+min);
        //this.number = todoCod();
        this.calendar=GregorianCalendar.getInstance();
        this.data = ""+calendar.getTime();
        this.money=money;
        this.text = "нет данных";
        this.bulblock = false;
        this.phone =phone;
        this.lockCash = new Object();
    }

    public Cash(String name,  float money, String text, String phone) {
        hashList = new ArrayList<>();
        this.name = name;
        this.number = todoCod()+(long)(Math.random()*(max-min)+min);
        //this.number = todoCod();
        this.calendar=GregorianCalendar.getInstance();
        this.data = ""+calendar.getTime();
        this.money=money;
        this.text = text;
        this.bulblock = false;
        this.phone =phone;
        this.lockCash = new Object();
    }

    private String todoCod(){
        String s = "";
        int length  =rnd.nextInt(3)+2;
        for(int i=0; i<length;i++){
            s=s+(char)(rnd.nextInt(25)+97);
        }

        return s;
    }

    public Object getLockCash() {
        return lockCash;
    }

    public void setLockCash(Object lockCash) {
        this.lockCash = lockCash;
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

    public String getData() {
        return data;
    }

    public ArrayList<String> getHashList() {
        return hashList;
    }

    public void setHashList(ArrayList<String> hashList) {
        this.hashList = hashList;
    }

    public float getSumma() {
        return money;
    }

    public void setSumma(float money) {
        this.money = money;
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

    public String getPhone() {
        return phone;
    }
    public void printHashList(){
        this.hashList.forEach(c->{
            System.out.println(c);
            System.out.println("-----------------");
        });
    }
    public void printHashList2(){
        for(int i=0;i<this.hashList.size();i++){
            System.out.println("хэш №"+i+". - "+hashList.get(i));
            System.out.println("-------------------------------------");
        }
    }

    public void addHashList(String name, String data, float price){
        String pass = todoPass(name, data, price, this.number);
        String encodedString = Base64.getEncoder().encodeToString(pass.getBytes());
        //System.out.println("Закодированная строка "+encodedString);
        this.hashList.add(encodedString);
    }
    public void removeHashList(int num){
        this.hashList.remove(num);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void print(){
        System.out.println(" Имя кошелька- "+getName());
        System.out.println(" Размер суммы  - "+ getSumma());
        System.out.println(" Блокированы операции или нет - "+isBulblock());
        System.out.println("Дополнительная информация - "+getText());
        System.out.println(" Номер кошелька  - "+getNumber());
        System.out.println(" Телефон - "+getPhone());
        System.out.println("Дата создания - "+getData());
        System.out.println("---------------------------");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        out.writeUTF(this.name);
        out.writeUTF(this.number);
        out.writeUTF(this.data);
        out.writeFloat(this.money);
        out.writeBoolean(this.bulblock);
        out.writeUTF(this.text);
        out.writeUTF(this.phone);
        out.writeObject(this.hashList);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        this.name = in.readUTF();
        this.number = in.readUTF();
        this.data = in.readUTF();
        this.money = in.readFloat();
        this.bulblock = in.readBoolean();
        this.text = in.readUTF();
        this.phone = in.readUTF();
        this.hashList = (ArrayList<String>) in.readObject();
        //
        this.lockCash = new Object();
    }
    private String todoPass(String name, String data, float price, String numCash){
        String s = name+"!!!!!"+data+"!!!!!"+price+"!!!!!"+numCash;
        s = s.replace(" ", "@)(@");
        return s;
    }
    public void printDecoder(int num){
        System.out.println("==========================");
        printDecoder1(decoderHash(num));
        System.out.println("==========================");
    }
    private ArrayList<String> decoderHash(int num){
        ArrayList<String> list = new ArrayList<>();
        list.add("Название : ");
        list.add("Дата : ");
        list.add("Стоимость : ");
        list.add("Номер кошелька : ");
        String s = this.hashList.get(num);
        byte[] decodedBytes = Base64.getDecoder().decode(s);
        String decodedString = new String(decodedBytes);

        List<String> dt = Arrays.asList(decodedString.split("!!!!!"));
        for(int i=0;i<list.size();i++){
            String decoder = dt.get(i).replace("@)(@", " ");
            String ss = list.get(i)+decoder;
            list.set(i, ss);
        }
        return list;
    }
    private void printDecoder1(ArrayList<String> list){
        for(String s: list){
            System.out.println(s);
            System.out.println("===================");
        }
    }
}
