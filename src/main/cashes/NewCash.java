package main.cashes;

import main.abs.Ars;

import main.cep.block.*;
import main.cep.bul.BulCash;
import main.cep.bul.BulIml;
import main.elementi.Cash;

import java.util.ArrayList;

import static main.Exit.exit_pref;

public class NewCash extends AbsCash {
    private Ars ars;

    public NewCash(Ars ars){
        this.ars =ars;
    }

    @Override
    public void todo() {
        System.out.println("новый электронный кошелек");
        System.out.println("========================");
        String newcash = "новый";


        BulIml bulUpdateCash = new BulCash();
        Cash cash = new Cash();
        CepochkaAll name = new CepName();
        CepochkaAll phone = new CepPhone();
        CepochkaAll money = new CepSumm();
        CepochkaAll text = new CepText();

        name.setNext(phone);
        phone.setNext(money);
        money.setNext(text);
        String s = name.handle(cash, ars, newcash, "нового кошелька", bulUpdateCash);

        if(!s.equals("q")){
            //System.out.println("Создали каэш");
            cash.print();
            System.out.println("========================");
            String podtverdi = "";
            while (true){
                System.out.println("Подтвердите создание новго электронного кошелька - да/нет");
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
                        var mapall = ars.getMapall();
                        var cashes = (ArrayList<Cash>)mapall.get("cash");
                        cashes.add(cash);
                        System.out.println("Кошелек добавлен в список");
                        break;
                    }

                }
                else{
                    System.out.println("Что то не так. Повторите ответ");
                }
            }
        }

        System.out.println("========================");
        exit(ars);
    }
}
