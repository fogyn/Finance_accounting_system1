package main.potentialtrati;

import main.abs.AbsMain;
import main.abs.Ars;
import main.cep.MethodForCepochka;
import main.cep.bul.BulIml;
import main.dinamik.StartThread;
import main.elementi.Card;
import main.elementi.Pottrat;
import main.elementi.Trati;

import java.util.ArrayList;

import static main.Exit.*;

public class AbsPot<T> extends AbsMain {
    public String eex = "ok";
    private MethodForCepochka mfc;
    public AbsPot() {
        mfc = new MethodForCepochka();
    }
    @Override
    public void todo() {
    }

    public Card choiseCard(Ars ars){
        Card card = null;
        card = mfc.choiseCard("Выберите номер карты из списка,  с которой будет производиться оплата.", ars);
        if(mfc.eex.equals("q")){
            ex = exit_pref.ordinal();
            eex = "q";
        }
        return card;
    }

    public float choiseCurrentPriceSrochno(String s, Pottrat pot){
        float current = 0;
        while (true){
            System.out.println(s);
            System.out.println("Для отказа введите - q");
            System.out.println("---------------------------------------------------");
            String pricess = scn2.nextLine();
            if (pricess.equals("q")) {
                ex = exit_pref.ordinal();
                eex="q";
                break;
            }
            else if(checFloat(pricess) && Float.parseFloat(pricess)>0 && Float.parseFloat(pricess)<=pot.getEndprice()){
                    current = backFloatPriceReal(pricess);
                    break;
                }
                else{
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
        }
        return current;
    }

    public Pottrat choisePotTratforUpdate(Ars ars){
        var mapall = ars.getMapall();
        var pottrats = (ArrayList<Pottrat>)mapall.get("pot");
        Pottrat pot =null;
        System.out.println("В списке "+pottrats.size()+" трат.");
        if(pottrats.size()<1){
            System.out.println("Список потенциальных трат пуст");
            System.out.println("**********************");
            ex = exit_pref.ordinal();
            eex="q";
        }
        else {
            for(int i = 0; i<pottrats.size();i++){
                System.out.println("Номер потенциальной покупки №"+i);
                System.out.println("**********************");
                Pottrat p =  pottrats.get(i);
                p.printPotTrat();
            }
            System.out.println("**********************");
            int number = -1;
            while (true) {
                System.out.println("Введите номер потенциальной покупки  из списка");
                System.out.println("Для отказа введите - q");
                System.out.println("---------------------------------------------------");
                String num = scn2.nextLine();
                if (num.equals("q")) {
                    ex = exit_pref.ordinal();
                    eex="q";
                    break;
                }
                if (checInt(num) && Integer.parseInt(num) > -1 && Integer.parseInt(num) < pottrats.size() ) {
                    number = Integer.parseInt(num);
                        pot = pottrats.get(number);
                        break;
                } else {
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
            }
        }
        return pot;
    }
    public Pottrat choisePotTratMustPay(Ars ars){
        var mapall = ars.getMapall();
        var mustPayList = (ArrayList<Pottrat>)mapall.get("траты для оплаты");
        var cards = (ArrayList<Card>)mapall.get("карты");
        Pottrat pot =null;
        System.out.println("В списке "+mustPayList.size()+" трат.");
        if(mustPayList.size()<1){
            System.out.println("Список потенциальных трат пуст");
            System.out.println("**********************");
            ex = exit_pref.ordinal();
            eex="q";
        }
        else {
            for(int i = 0; i<mustPayList.size();i++){
                System.out.println("Номер потенциальной покупки №"+i);
                System.out.println("**********************");
                //pottrats.get(i).printPotTrat(mustPayList.get(i));
                mustPayList.get(i).printPotTrat();
                System.out.println("========================");
                var num = mustPayList.get(i).getNumber();
                //var card = cards.stream().filter(c->c.getNumber().equals(num)).findFirst();
                Card card = null;
                for(Card c:cards){
                    if(c.getNumber().equals(num)){
                        card=c;
                        break;
                    }
                }
                if(card!=null){
                    System.out.println("на карте "+card.getSumma());
                }
                else{
                    System.out.println("Необходимой карты нет");
                }
             }

            System.out.println("**********************");
            int number = -1;
            while (true) {
                System.out.println("Введите номер потенциальной покупки  из списка");
                System.out.println("Для отказа введите - q");
                System.out.println("---------------------------------------------------");
                String num = scn2.nextLine();
                if (num.equals("q")) {
                    ex = exit_pref.ordinal();
                    eex="q";
                    break;
                }
                if (checInt(num) && Integer.parseInt(num) > -1 && Integer.parseInt(num) < mustPayList.size() ) {
                    number = Integer.parseInt(num);
                    pot = mustPayList.get(number);
                    break;
                } else {
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
            }
        }
        return pot;
    }
    protected Pottrat choisePotfromList(ArrayList<Pottrat> pottratsList){
        Pottrat pot =null;
        System.out.println("В списке "+pottratsList.size()+" трат.");
        if(pottratsList.size()<1){
            System.out.println("Список потенциальных трат пуст");
            System.out.println("**********************");
            ex = exit_pref.ordinal();
            eex="q";
        }
        else {
            for(int i = 0; i<pottratsList.size();i++){
                System.out.println("Номер потенциальной покупки №"+i);
                System.out.println("**********************");
                pottratsList.get(i).printPotTrat();
            }
            System.out.println("**********************");
            int number = -1;
            while (true) {
                System.out.println("Введите номер потенциальной покупки  из списка");
                System.out.println("Для отказа введите - q");
                System.out.println("---------------------------------------------------");
                String num = scn2.nextLine();
                if (num.equals("q")) {
                    ex = exit_pref.ordinal();
                    eex="q";
                    break;
                }
                if (checInt(num) && Integer.parseInt(num) > -1 && Integer.parseInt(num) < pottratsList.size() ) {
                    number = Integer.parseInt(num);
                    pot = pottratsList.get(number);
                    break;
                } else {
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
            }
        }
        return pot;
    }
   public void updatePot(Ars ars, Pottrat oldpot, Pottrat uppot, BulIml bulUpdatePot ){
        if(bulUpdatePot.isName()){
            oldpot.setName(uppot.getName());
        }
        if(bulUpdatePot.isNumber()){
            oldpot.setNumber(uppot.getNumber());
        }
        if(bulUpdatePot.isAllprice()){
            oldpot.setAllprice(uppot.getAllprice());
        }
        if(bulUpdatePot.isEndprice()){
            oldpot.setEndprice(uppot.getEndprice());
        }
        if(bulUpdatePot.isMoney()){
            oldpot.setSumma(uppot.getSumma());
        }
        if(bulUpdatePot.isTimebeetwinpay()){
            oldpot.setTimebeetwinpay(uppot.getTimebeetwinpay());
        }
        if(bulUpdatePot.isText()){
            oldpot.setText(uppot.getText());
        }
        if(bulUpdatePot.isActivPay()){
            if(oldpot.isActivPay()){
                oldpot.setActivPay(false);
                //автоплатеж откл
            }
            else{
                oldpot.setActivPay(true);
                // автоплатеж вкл
            }
        }
        if(bulUpdatePot.isType()){
            if(oldpot.isType()){
                oldpot.setType(false);
                //завершение платежа отключить
            }
            else{
                oldpot.setType(true);
                // Заверешение включить
            }
        }
        if(bulUpdatePot.isMustPay()){
            if(oldpot.isMustpay()){
                oldpot.setMustpay(false);
                //плата по платежу еще не наступила
            }
            else{
                oldpot.setMustpay(true);
                // платеж необходимо оплатить
            }
        }
        if(bulUpdatePot.isBulblock()){
            if(oldpot.isBulblock()){
                  oldpot.setBulblock(false);
                  //платеж разморожен (все работает)
            }
            else{
                oldpot.setBulblock(true);
               // Заморозка . Блокировка
            }
        }
    }
    protected boolean testOplati(Pottrat pot, float currentPriceOne, Ars ars){
        boolean bul = true;
        var mapall = ars.getMapall();
        var mustPayList = (ArrayList<Pottrat>)mapall.get("траты для оплаты");
        var pottrats = (ArrayList<Pottrat>)mapall.get("pot");
        var listTrats = (ArrayList<Trati>)mapall.get("траты");
        var cards = (ArrayList<Card>)mapall.get("карты");
            if (pot == null) {
                System.out.println("--------------------------------------------------");
                System.out.println("Потенциальный платеж - Удален. Оплату произвести нельзя.");
                System.out.println("--------------------------------------------------");
                return false;
            }
            //проверка заморозки
            if (pot.isBulblock()) {
                System.out.println("--------------------------------------------------");
                System.out.println("Потенциальный платеж " + pot.getName() + " заморожен. Оплату произвести нельзя. Разморозте платеж");
                System.out.println("--------------------------------------------------");
                return false;
            }//нет карты
            Card card = null;
            for(Card c:cards){
                if(c.getNumber().equals(pot.getNumber())){
                    card=c;
                    break;
                }
            }
            if (card == null) {
                System.out.println("--------------------------------------------------");
                System.out.println("Потенциальный платеж " + pot.getName() + " произвести нельзя. Удалена карта");
                System.out.println("--------------------------------------------------");
                return false;
            }
            // карта блокирована
            if (card.isBulblock()) {
                System.out.println("--------------------------------------------------");
                System.out.println("Потенциальный платеж " + pot.getName() + "  произвести нельзя. Карта блокирована. " +
                        "Разблокируйте карту в меню карт");
                System.out.println("--------------------------------------------------");
                return false;
            }
            // нехватка денег на карте
            if (pot.isType()) {
                if(currentPriceOne!=0){
                    if (card.getSumma() < currentPriceOne) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("Потенциальный платеж " + pot.getName() + "  произвести нельзя. " +
                                "Сумма на карте меньше необходимой для совершения платежа. " +
                                "Пополните карту в меню карт или смените карту");
                        System.out.println("--------------------------------------------------");
                        return false;
                    }
                }
                else{
                    // остаток
                    if (pot.getEndprice() >= pot.getSumma() && card.getSumma() < pot.getSumma()) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("Потенциальный платеж " + pot.getName() + "  произвести нельзя. " +
                                "Сумма на карте меньше необходимой для совершения платежа. " +
                                "Пополните карту в меню карт или смените карту");
                        System.out.println("--------------------------------------------------");
                        return false;
                    }
                    else if (pot.getEndprice() < pot.getSumma() && card.getSumma() < pot.getEndprice()) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("Потенциальный платеж " + pot.getName() + " произвести нельзя. " +
                                "Сумма на карте меньше необходимой для совершения платежа. " +
                                "Пополните карту в меню карт или смените карту");
                        System.out.println("--------------------------------------------------");
                        return false;
                    }
                }
            }
            else {
                //нет остатка
                if (card.getSumma() < pot.getSumma()) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Потенциальный платеж " + pot.getName() + "  произвести нельзя. " +
                            "Сумма на карте меньше необходимой для совершения платежа. " +
                            "Пополните карту в меню карт или смените карту");
                    System.out.println("--------------------------------------------------");
                    return false;
                }
            }
           return bul;
    }
   protected void chekOplati(Pottrat pot, Ars ars){
        var mapall = ars.getMapall();
        var mustPayList = (ArrayList<Pottrat>)mapall.get("траты для оплаты");
        var pottrats = (ArrayList<Pottrat>)mapall.get("pot");
        var listTrats = (ArrayList<Trati>)mapall.get("траты");
        var cards = (ArrayList<Card>)mapall.get("карты");
            Card card = null;
            for(Card c:cards){
                if(c.getNumber().equals(pot.getNumber())){
                    card=c;
                    break;
                }
            }
            if (pot.isType()) {
                    if ((pot.getEndprice() - pot.getSumma()) > 0) {
                        pot.setEndprice(pot.getEndprice() - pot.getSumma());
                        Trati potTrati = new Trati(pot.getName(), card.getNumber(), "потенциальная",
                                pot.getSumma(), true, pot.getText(), pot.getAllprice(), pot.getEndprice());
                        pot.setMustpay(false);
                        //надо добавить в лист трат
                       synchronized (ars.getLock()) {
                           card.setSumma(card.getSumma() - pot.getSumma());
                           mustPayList.remove(pot);
                            listTrats.add(potTrati);
                        }
                        StartThread startThread = new StartThread(ars);
                        startThread.pusk2(pot);
                        System.out.println("--------------------------------------------------");
                        System.out.println("Потенциальный платеж " + pot.getName() + " прошел успешно !!!");
                        System.out.println("--------------------------------------------------");
                        return;
                    }
                    else {
                        Trati potTrati = new Trati(pot.getName(), card.getNumber(), "потенциальная",
                                pot.getEndprice(), true, pot.getText(), pot.getAllprice(), 0);
                       synchronized (ars.getLock()) {
                            card.setSumma(card.getSumma() - pot.getEndprice());
                            listTrats.add(potTrati);
                            mustPayList.remove(pot);
                            pottrats.remove(pot);
                         }
                        System.out.println("--------------------------------------------------");
                        System.out.println("Потенциальный платеж " + pot.getName() + " прошел успешно !!!");
                        System.out.println("Данный платеж завершен. Из списка потенциальных платежей " + pot.getName() + " удален.");
                        System.out.println("--------------------------------------------------");
                    }
                }
            else{
                Trati potTrati = new Trati(pot.getName(), card.getNumber(), "потенциальная",
                        pot.getSumma(), true, pot.getText());
                pot.setMustpay(false);
                synchronized (ars.getLock()) {
                    for(Card c:cards){
                        if(c.getNumber().equals(card.getNumber())){
                            c.setSumma(c.getSumma()-pot.getSumma());
                            break;
                        }
                    }
                    listTrats.add(potTrati);
                    mustPayList.remove(pot);
                }
                StartThread startThread = new StartThread(ars);
                startThread.pusk2(pot);
                System.out.println("--------------------------------------------------");
                System.out.println("Потенциальный платеж " + pot.getName() + " прошел успешно !!!");
                System.out.println("--------------------------------------------------");
            }
     }
   protected void chekOplatiSrochno(Pottrat pot, float currentPriceOne, Card card, Ars ars){
        var mapall = ars.getMapall();
        var mustPayList = (ArrayList<Pottrat>)mapall.get("траты для оплаты");
        var pottrats = (ArrayList<Pottrat>)mapall.get("pot");
        var listTrats = (ArrayList<Trati>)mapall.get("траты");
                    if ((pot.getEndprice() - currentPriceOne) > 0) {
                        pot.setEndprice(pot.getEndprice() - currentPriceOne);
                        Trati potTrati = new Trati(pot.getName(), card.getNumber(), "потенциальная",
                                currentPriceOne, true, pot.getText(), pot.getAllprice(), pot.getEndprice());
                        //надо добавить в лист трат
                        synchronized (ars.getLock()) {
                            card.setSumma(card.getSumma() - currentPriceOne);
                            listTrats.add(potTrati);
                            if(mustPayList.contains(pot)){
                                pot.setMustpay(false);
                                mustPayList.remove(pot);
                                StartThread startThread = new StartThread(ars);
                                startThread.pusk2(pot);
                            }
                        }
                        System.out.println("--------------------------------------------------");
                        System.out.println("Досрочный Потенциальный платеж " + pot.getName() + " прошел успешно !!!");
                        System.out.println("--------------------------------------------------");
                        return;
                    }
                    else {
                        Trati potTrati = new Trati(pot.getName(), card.getNumber(), "потенциальная",
                                currentPriceOne, true, pot.getText(), pot.getAllprice(), 0);
                        synchronized (ars.getLock()) {
                            card.setSumma(card.getSumma() - currentPriceOne);
                            listTrats.add(potTrati);
                            if(mustPayList.contains(pot)){
                                mustPayList.remove(pot);
                            }
                            pottrats.remove(pot);
                        }
                        System.out.println("--------------------------------------------------");
                        System.out.println("Досрочный Потенциальный платеж " + pot.getName() + " прошел успешно !!!");
                        System.out.println("Данный платеж завершен. Из списка потенциальных платежей " + pot.getName() + " удален.");
                        System.out.println("--------------------------------------------------");
                    }
                }

    protected boolean choisePay(String s){
        boolean bul = false;
        bul = mfc.choisePay(s);
        if(mfc.eex.equals("q")){
            ex = exit_pref.ordinal();
            eex = "q";
        }
        return bul;
    }
}
