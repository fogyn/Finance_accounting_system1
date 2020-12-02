package main.doxodi;

import main.abs.Ars;
import main.cep.block.*;
import main.cep.bul.BulDoxod;
import main.cep.bul.BulIml;
import main.dinamik.StartDoxodThread;
import main.elementi.Doxod;


import java.util.ArrayList;

import static main.Exit.exit_pref;

public class NewDoxod extends AbsDoxod{

    private Ars ars;

    public NewDoxod(Ars ars){
        this.ars =ars;
    }

    @Override
    public void todo() {
        System.out.println("новый доход");
        System.out.println("========================");
        String newdoxod = "новый";

        CepochkaAll name = new CepNameDoxod();
        CepochkaAll sum = new CepSumm();
        CepochkaAll cardnum = new CepNumCard();
        CepochkaAll time = new CepTime();
        CepochkaAll text = new CepText();
        CepochkaAll freze = new CepBlock();

        BulIml bulUpdateDoxod = new BulDoxod();
        Doxod doxod = new Doxod();

        name.setNext(sum);
        sum.setNext(cardnum);
        cardnum.setNext(time);
        time.setNext(text);
        text.setNext(freze);

        String s = name.handle( doxod, ars, newdoxod, "потенциального дохода", bulUpdateDoxod);
        System.out.println("========================");

        if(!s.equals("q")){
            System.out.println("Ваш доход");
            doxod.print();
            System.out.println("========================");
            String podtverdi = "";
            while (true){
                System.out.println("Подтвердите создание новго дохода - да/нет");
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
                        var listDoxods = (ArrayList<Doxod>)mapall.get("доходы");
                        listDoxods.add(doxod);
                        System.out.println("Доход добавлен в список");
                        // Запуск нового потока
                        StartDoxodThread startDoxodThread = new StartDoxodThread(ars);
                        startDoxodThread.pusk2(doxod);

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
