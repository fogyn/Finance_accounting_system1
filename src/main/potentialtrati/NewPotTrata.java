package main.potentialtrati;

import main.abs.Ars;
import main.cep.block.*;
import main.cep.bul.BulIml;
import main.cep.bul.BulPot;
import main.dinamik.StartThread;
import main.elementi.Pottrat;


import java.util.ArrayList;
import java.util.LinkedHashMap;

import static main.Exit.exit_pref;

public class NewPotTrata extends AbsPot{
    private Ars ars;
    public NewPotTrata(Ars ars){
        this.ars = ars;
    }
    @Override
    public void todo() {
        var mapall = ars.getMapall();
        var pottrats = (ArrayList<Pottrat>)mapall.get("pot");
                System.out.println("Вы создаете новую потенциальную покупку");
        System.out.println("-----------------");
        String newpot = "новый";
        BulIml bulUpdatePot = new BulPot();
        Pottrat pot = new Pottrat();

        CepochkaAll name = new CepName();
        CepochkaAll type = new CepType();
        CepochkaAll allprice = new CepAllPrice();
        CepochkaAll activPay = new CepActivPay();
        CepochkaAll sum = new CepSumm();
        CepochkaAll cardnum = new CepNumCard();
        CepochkaAll time = new CepTime();
        CepochkaAll text = new CepText();

        name.setNext(type);
        type.setNext(allprice);
        allprice.setNext(activPay);
        activPay.setNext(sum);
        sum.setNext(cardnum);
        cardnum.setNext(time);
        time.setNext(text);

    String s = name.handle(pot, ars, newpot, "потенциальной покупки", bulUpdatePot);
        if(!s.equals("q")){
            pot.printPotTrat();
            System.out.println("========================");
            String podtverdi = "";
            while (true){
                System.out.println("Подтвердите создание нового потенциального платежа - да/нет");
                System.out.println("Для выхода введите - q");
                System.out.println("---------------------------------------------------");
                podtverdi = scn2.nextLine();
                if(podtverdi.equals("да") || podtverdi.equals("нет") || podtverdi.equals("q")){
                    if (podtverdi.equals("q") || podtverdi.equals("нет")) {
                        ex = exit_pref.ordinal();
                        eex="q";
                        System.out.println("Создание отменено");
                        break;
                    }
                    else if(podtverdi.equals("да")){
                        pottrats.add(pot);
                        StartThread startThread = new StartThread(ars);
                        startThread.pusk2(pot);
                        System.out.println("Потенциальный платеж добавлен в список");
                        break;
                    }
                }
                else{
                    System.out.println("Что то не так. Повторите ответ");
                }
            }
        }
        exit(ars);
    }
}

