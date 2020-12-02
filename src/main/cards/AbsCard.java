package main.cards;

import main.abs.AbsMain;
import main.abs.Ars;
import main.cep.MethodForCepochka;
import main.cep.MethodForCepochkaUpdate;
import main.cep.bul.BulIml;
import main.elementi.Card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.Exit.*;

public class AbsCard extends AbsMain implements Serializable {
    public String eex = "ok";
    private MethodForCepochka mfc;
    private MethodForCepochkaUpdate mupdt;

    public ArrayList<String> menunewcard = new ArrayList<>();
    private List<String> textChoise = Arrays.asList("Введите номер карты из списка, для пополнения",
            "Введите номер карты с которой надо пополнить",
            "Введите номер карты на которую перевести остаток с закрываемой карты");
    public AbsCard(){
        mfc = new MethodForCepochka();
        mupdt = new MethodForCepochkaUpdate();
    }
    @Override
    public void todo() {
    }
    protected int choiseCard(ArrayList<Card> cardss, int chois, int karta){
        //var cardss = (ArrayList<Card>)cardss2.stream().filter(c->!c.isBulblock()).collect(Collectors.toList());
        if(karta==-1){
            for (int i = 0; i < cardss.size(); i++) {
                if(!cardss.get(i).isBulblock()){
                    System.out.println("Карта " + (i + 1));
                    cardss.get(i).printCard();
                }
            }
        }
        else{
            //cardss.remove(karta);
            for (int i = 0; i < cardss.size(); i++) {
                if(!cardss.get(i).isBulblock() && i!=karta){
                    System.out.println("Карта " + (i + 1));
                    cardss.get(i).printCard();
                }
            }
        }
        int number = -1;
        while (true) {
            System.out.println(textChoise.get(chois));
            if(chois ==2){
                System.out.println("Если хотите обналичить сумму, введите - n");
            }
            System.out.println("Для отказа введите - q");
            System.out.println("---------------------------------------------------");
            String num = scn2.nextLine();
            if (num.equals("n")) {
                number = -2;
                break;
            }
            if (num.equals("q")) {
                ex = exit_pref.ordinal();
                break;
            }
            if (checInt(num) && Integer.parseInt(num) > 0 && Integer.parseInt(num) <= cardss.size()) {
                int test = Integer.parseInt(num) - 1;
                if(test!=karta && !cardss.get(test).isBulblock()){
                    //number = Integer.parseInt(num) - 1;
                    number = test;
                    break;
                }
                else{
                    System.out.println("Такая карта не доступна. Повторите");
                }

            } else {
                System.out.println("Повторите ввод данных. Что-то не так");
            }
        }
        return  number;
    }
    public Card choiseCardforUpdate(Ars ars){
        var mapall = ars.getMapall();
        var cards = (ArrayList<Card>)mapall.get("карты");
        Card card =null;
        if(cards ==null || cards.size()<1){
            System.out.println("Список карт пуст");
            System.out.println("**********************");
            ex = exit_pref.ordinal();
            eex="q";
        }
        else {
            System.out.println("В списке "+cards.size()+" карт.");
            for(int i = 0; i<cards.size();i++){
                System.out.println("Номер карты №"+i);
                System.out.println("**********************");
                Card c =  cards.get(i);
                c.printCard();
            }
            System.out.println("**********************");
            int number = -1;
            while (true) {
                System.out.println("Введите номер карты  из списка");
                System.out.println("Для отказа введите - q");
                System.out.println("---------------------------------------------------");
                String num = scn2.nextLine();
                if (num.equals("q")) {
                    ex = exit_pref.ordinal();
                    eex="q";
                    break;
                }
                if (checInt(num) && Integer.parseInt(num) > -1 && Integer.parseInt(num) < cards.size() ) {

                    number = Integer.parseInt(num);

                    card = cards.get(number);
                    break;

                } else {
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
            }
        }
        return card;
    }
    public void updateCard(Ars ars, Card oldcard, Card upcard, BulIml bulUpdateCard){
        if(bulUpdateCard.isName()){
            oldcard.setName(upcard.getName());
        }
        if(bulUpdateCard.isPhone()){
            oldcard.setPhone(upcard.getPhone());
        }
        if(bulUpdateCard.isMoney()){
            oldcard.setSumma(upcard.getSumma());
        }
        if(bulUpdateCard.isText()){
            oldcard.setText(upcard.getText());
        }
        if(bulUpdateCard.isBulblock()){
            if(oldcard.isBulblock()){
                oldcard.setBulblock(false);
                //запуск , разблокировка
            }
            else{
                oldcard.setBulblock(true);
                // Заморозка . Блокировка
            }
        }
        if(bulUpdateCard.isYear()){
            oldcard.setYear(upcard.getYear());
        }
    }
    public boolean choisePay(String s){
        boolean bul = false;
        bul = mfc.choisePay(s);
        if(mfc.eex.equals("q")){
            ex = exit_pref.ordinal();
            eex = "q";
        }
        return bul;
    }
}
