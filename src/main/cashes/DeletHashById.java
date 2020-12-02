package main.cashes;

import main.abs.Ars;
import main.elementi.Card;
import main.elementi.Cash;

import java.util.ArrayList;

import static main.Exit.exit_pref;

public class DeletHashById extends AbsCash{
    private Ars ars;

    public DeletHashById(Ars ars){
        this.ars =ars;
    }

    @Override
    public void todo() {
        System.out.println("Удалить хэшкод по номеру");
        System.out.println("========================");

        var mapall = ars.getMapall();
        var cash = choiseCashforUpdate(ars);
        if(cash!=null){
            System.out.println("Выводим все данные по хэшключам");
            System.out.println("-------------------------------");
            if(cash.getHashList().size()<1 || cash.getHashList()==null){
                System.out.println("Хэш записей сейчас нет. Удаление не возможно");
            }
            else{
                cash.printHashList2();
                int i = getNumberHesh(cash.getHashList());
                if(i!=-1){
                    System.out.println("для удаления выбран - "+cash.getHashList().get(i));
                    System.out.println("---------------------------------");
                    boolean bul = choisePay("Подтвердите удаление y/n - да/нет");
                    if(!eex.equals("q") && bul){
                        cash.removeHashList(i);
                        System.out.println("Удаление хэш записи прошло успешно");
                    }
                }
            }

        }
        System.out.println("========================");
        exit(ars);
    }

}
