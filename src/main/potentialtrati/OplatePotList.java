package main.potentialtrati;

import main.abs.Ars;
import main.elementi.Card;
import main.elementi.Pottrat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OplatePotList<T> extends AbsPot{

    private Map<String, String> menuoplata = new LinkedHashMap<>();
    private Ars ars;

    public OplatePotList(Ars ars) {

        this.ars = ars;
        menuoplata.put("1", " Оплатить все потенциальные платежи");
        menuoplata.put("2", " Оплатить выборочный потенциальный платеж");
        menuoplata.put("3", " Оплатить досрочно выборочный потенциальный платеж");
        menuoplata.put("q", "Отмена");
    }

    @Override
    public void todo() {
        System.out.println("Вы делаете оплату всех потенциальных покупок");
        System.out.println("-----------------");

        String number = "";
        while(true){
            System.out.println("Введите команду,  согласно меню");
            System.out.println("----------------------------------------");
            for(String s: menuoplata.keySet()){
                System.out.println(s+"."+ menuoplata.get(s));
            }
            System.out.println("----------------------------------------");

            number = scn2.nextLine();
            switch (number){
                case "1":
                    System.out.println("выполняем оплату всех срочных платежей");
                    puyAll();
                    break;
                case "2":
                    System.out.println("выполняем оплату отдельного элемента");
                    System.out.println("----------------------------------------");
                    puyByNumber();

                    break;
                case "3":
                    System.out.println("выполняем досрочный платеж по отдельному элемента");
                    System.out.println("----------------------------------------");
                    puyByNumberSrochno(ars);
                    break;
            }
            if(number.equals("q") || number.equals("1") || number.equals("2") || number.equals("3")){
                break;
            }
            else{
                System.out.println("Введено не корректное значение. Повторите");
            }
        }
        //
        exit(ars);
    }

    private void puyAll(){
        var mapall = ars.getMapall();
        var mustPayList = (ArrayList<Pottrat>)mapall.get("траты для оплаты");
        System.out.println("число срочных платежей = "+mustPayList.size());
        if(mustPayList.size()<1 || mustPayList.isEmpty() || mustPayList== null){
            return;
        }
        boolean bul = choisePay("Подтвердите оплату всех - да");
        if(!eex.equals("q") && bul) {
            int a=0;
            int i = 0;
            while (true){
                System.out.println("Платеж из списка "+a);
                if(testOplati(mustPayList.get(i),0, ars)){
                    chekOplati(mustPayList.get(i), ars);
                }
                else{
                    i++;
                }
                a++;
                if(mustPayList.isEmpty() || i>=mustPayList.size()){
                    break;
                }
            }
        }
        else{
            System.out.println("Отмена операции");
        }
    }
    private void puyByNumber() {
        var pot = choisePotTratMustPay(ars);
        boolean bul = false;
        if (!eex.equals("q")) {
            bul = choisePay("Подтвердите опалту - да");

        }
        if(!eex.equals("q") && bul) {
            if(testOplati(pot,0, ars)) {
                chekOplati(pot, ars);
            }
        }
        else{
            System.out.println("Отмена операции");
        }
    }
    private void puyByNumberSrochno(Ars ars){
        // только для платежей с завершением
        var mapall = ars.getMapall();
        var potr = (ArrayList<Pottrat>)mapall.get("pot");
        System.out.println("размер общего списка пот плтежей "+potr.size());
        if(potr.size()<1 || potr.isEmpty() || potr== null){
            return;
        }
        var allpotsFinish =(ArrayList<Pottrat>)potr.stream().filter(c->c.isType()).collect(Collectors.toList());
        System.out.println("массив с завершениями "+allpotsFinish.size());
        var cards = (ArrayList<Card>)mapall.get("карты");
        var pot = choisePotfromList(allpotsFinish);
        if(pot==null){
            System.out.println("Отмена операции");
            return;
        }
        if(pot.isBulblock()){
            System.out.println("Платеж заблокирован. Разброкируйте в разделе редактирование, или выберите другой ");
            System.out.println("--------------------");
            return;
        }
        Card card = null;
        for(Card c:cards){
            if(c.getNumber().equals(pot.getNumber())){
                card = c;
                break;
            }
        }
        float newcurrentPrice = 0;
        if(!eex.equals("q")){
            newcurrentPrice = choiseCurrentPriceSrochno("Сумма остатка "+pot.getEndprice()+". Введенная сумма не может быть больше остатка.\n" +
                    " Введите необходимую сумму платежа", pot);
        }
        Card newcard = null;
        if(!eex.equals("q")){
            newcard = choiseCard(ars);
        }
        boolean bul = false;
        if (!eex.equals("q")) {
            bul = choisePay("Подтвердите оплату - да");
        }
        if(!eex.equals("q") && bul) {
            pot.setNumber(newcard.getNumber());
                if(testOplati(pot,newcurrentPrice, ars)){
                    chekOplatiSrochno(pot, newcurrentPrice, newcard, ars);
                    //System.out.println("Оплата по досрочному платежу прошла успешно");
                }
            System.out.println("---------------------------------");
            boolean bulcard = false;
            bulcard = choisePay("Подтвердите смену карты - да/нет");
            if(!bulcard && card!=null){
                pot.setNumber(card.getNumber());
            }
            }
        else{
            System.out.println("Отмена операции");
        }
    }
}
