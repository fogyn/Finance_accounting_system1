package main.trati;

import main.abs.AbsMain;
import main.abs.Ars;
import main.cep.MethodForCepochka;
import main.elementi.Card;
import main.elementi.Cash;


import java.util.ArrayList;

import static main.Exit.exit_pref;


public class AbsTrati extends AbsMain {
    public String eex = "ok";
    private MethodForCepochka mfc;
    public AbsTrati(){
        mfc = new MethodForCepochka();
    }

    public Card choiseCard(float price, Ars ars){
        Card card = null;
        var mapall = ars.getMapall();
        var cardss = (ArrayList<Card>)mapall.get("карты");
        if(cardss.size()<1){

            System.out.println("Откройте карту в разделе 1 главного меню - Карты");
            ex = exit_pref.ordinal();
            eex="q";
        }
        else {
            for (int i = 0; i < cardss.size(); i++) {
                if (!cardss.get(i).isBulblock()) {
                    System.out.println("Карта " + (i + 1));
                    cardss.get(i).printCard();
                }
            }
            int number = -1;
            while (true) {
                System.out.println("Введите номер карты из списка, для оплаты");
                System.out.println("Для отказа введите - q");
                System.out.println("---------------------------------------------------");
                String num = scn2.nextLine();
                if (num.equals("q")) {
                    ex = exit_pref.ordinal();
                    eex="q";
                    break;
                }
                if (checInt(num) && Integer.parseInt(num) > 0 && Integer.parseInt(num) <= cardss.size() ) {

                    number = Integer.parseInt(num) - 1;
                    if(!cardss.get(number).isBulblock()){
                        // проверка суммы
                        if(cardss.get(number).getSumma()>price){
                            //подтверждаем что сумма на карте больше стоимости покупки
                            card = cardss.get(number);
                            break;
                        }
                        else{
                            System.out.println("Сумма на карте меньше стоимости покупки. Вам надо выбрать другую карту или " +
                                    "пополнить эту карту в разделе Карты и вернуться для осуществления покупки");
                        }

                    }
                    else{
                        System.out.println("Карта заблокирована. Выберите другую");
                    }

                } else {
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
            }
        }

        return card;
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
    protected Cash choiseCash(float price, Ars ars){
        Cash cash = null;
        var mapall = ars.getMapall();
        var cashes = (ArrayList<Cash>)mapall.get("cash");
        if(cashes.size()<1){
            System.out.println("Создайте электронный кошелек в разделе 2 главного меню - Кошельки");
            ex = exit_pref.ordinal();
            eex="q";
        }
        else {
            for (int i = 0; i < cashes.size(); i++) {
                if (!cashes.get(i).isBulblock()) {
                    System.out.println("Кошелек № " + (i + 1));
                    System.out.println("----------------------");
                    cashes.get(i).print();
                }
            }
            int number = -1;
            while (true) {
                System.out.println("Введите номер кошелька из списка, для оплаты");
                System.out.println("Для отказа введите - q");
                System.out.println("---------------------------------------------------");
                String num = scn2.nextLine();
                if (num.equals("q")) {
                    ex = exit_pref.ordinal();
                    eex="q";
                    break;
                }
                if (checInt(num) && Integer.parseInt(num) > 0 && Integer.parseInt(num) <= cashes.size() ) {
                    number = Integer.parseInt(num) - 1;
                    if(!cashes.get(number).isBulblock()){
                        // проверка суммы
                        if(cashes.get(number).getSumma()>price){
                            //подтверждаем что сумма на карте больше стоимости покупки
                            cash = cashes.get(number);
                            break;
                        }
                        else{
                            System.out.println("Сумма на кошельке меньше стоимости покупки. Вам надо выбрать другой кошелек или карту или " +
                                    "пополнить этот кошелек в разделе Кошельки и вернуться для осуществления покупки");
                        }
                    }
                    else{
                        System.out.println("Операции с кошельком заблокированы. Выберите другой");
                    }
                } else {
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
            }
        }

        return cash;
    }
    public String choiseCardOrCash(float price, Ars ars){
        String s="";
        while (true){
            System.out.println("Выберите для оплаты карты 1 или кошельки 2");
            System.out.println("Для отказа введите - q");
            System.out.println("---------------------------------------------------");
            String num = scn2.nextLine();
            if (num.equals("q")) {
                ex = exit_pref.ordinal();
                eex="q";
                s="q";
                break;
            }
            else if(num.equals("1")){
               Card card = choiseCard(price,ars);
               if(!eex.equals("q")){
                   s = card.getNumber();
                   break;
               }
            }
            else if(num.equals("2")){
                Cash cash = choiseCash(price,ars);
                if(!eex.equals("q")){
                    s = cash.getNumber();
                    break;
                }
            }
            else{
                System.out.println("Повторите ввод данных. Что-то не так");
            }
        }
        return s;
    }

}
