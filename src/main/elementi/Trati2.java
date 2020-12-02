package main.elementi;

import main.trati.AbsTrati;

import java.util.Calendar;

public class Trati2 extends AbsTrati {
    private float allprice;
    private float ostatokprice;
    private String name;
    private String numberCard;
    private String type;
    private float summa;
    private String text;
    private String data;
    private boolean payOk;

    public Trati2(){
        this.type = "текущая";
        this.allprice = 0;
        this.ostatokprice = 0;
        this.data = ""+ Calendar.getInstance().getTime();
        this.payOk = false;
    }
    public Trati2(String name, String numberCard, String type, float summa, boolean payOk, String text, float allprice, float ostatokprice){
        this.name = name;
        this.numberCard = numberCard;
        this.type = type;
        this.summa = summa;
        this.payOk = payOk;
        this.data = ""+Calendar.getInstance().getTime();
        this.text = text;
        this.allprice = allprice;
        this.ostatokprice = ostatokprice;
    }

    public Trati2(String name, String numberCard, String type, float summa, boolean payOk) {
        this.name = name;
        this.numberCard = numberCard;
        this.type = type;
        this.summa = summa;
        this.payOk = payOk;
        this.data = ""+Calendar.getInstance().getTime();
        this.allprice = 0;
        this.ostatokprice = 0;
    }

    public Trati2(String name, String numberCard, String type, float summa, boolean payOk, String text) {
        this.name = name;
        this.numberCard = numberCard;
        this.type = type;
        this.summa = summa;
        this.text = text;
        this.payOk = payOk;
        this.data = ""+Calendar.getInstance().getTime();
        this.allprice = 0;
        this.ostatokprice = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getSumma() {
        return summa;
    }

    public void setSumma(float summa) {
        this.summa = summa;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isPayOk() {
        return payOk;
    }

    public void setPayOk(boolean payOk) {
        this.payOk = payOk;
    }

    public String getData() {
        return data;
    }

    public float getAllprice() {
        return allprice;
    }

    public void setAllprice(float allprice) {
        this.allprice = allprice;
    }

    public float getOstatokprice() {
        return ostatokprice;
    }

    public void setOstatokprice(float ostatokprice) {
        this.ostatokprice = ostatokprice;
    }

    public void printTrati(){
        System.out.println("Покупка - "+getName());
        if(getNumberCard().charAt(0)<97){
            System.out.println("номер карты - "+getNumberCard());
        }
        else{
            System.out.println("номер кошелька - "+getNumberCard());
        }
        System.out.println("Тип покупки (текущая/плановая) - "+getType());
        System.out.println("Сумма покупки - "+getSumma());
        System.out.println("Дополнительная информация- "+getText());
        System.out.println("Дата покупки - "+getData());
        System.out.println("Покупка оплачена/в ожидании - "+isPayOk());

        System.out.println("---------------------------");

    }
    public void printPotTrati() {
        System.out.println("Покупка - "+getName());
        System.out.println(" карты - "+getNumberCard());
        System.out.println("Тип покупки (текущая/плановая) - "+getType());
        System.out.println("Дополнительная информация- "+getText());
        System.out.println("Дата покупки - "+getData());
        System.out.println("Покупка оплачена/в ожидании - "+isPayOk());
        System.out.println("Полная стоимость- "+getAllprice());
        System.out.println("Остаток от стоимости - "+getOstatokprice());
        System.out.println("Текущая сумма оплаты - "+getSumma());
        System.out.println("---------------------------");

    }
    public void printPrixod(){
        System.out.println("Покупка - "+getName());
        if(getNumberCard().charAt(0)<97){
            System.out.println("номер карты - "+getNumberCard());
        }
        else{
            System.out.println("номер кошелька - "+getNumberCard());
        }
        System.out.println("Тип поступившей суммы (текущая/плановая) - "+getType());
        System.out.println("Сумма поступления - "+getSumma());
        System.out.println("Дополнительная информация- "+getText());
        System.out.println("Дата поступления - "+getData());
        //System.out.println("Покупка оплачена/в ожидании - "+isPayOk());
        System.out.println("---------------------------");

    }
}
