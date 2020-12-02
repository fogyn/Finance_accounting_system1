package main.cashes;

import main.abs.Ars;

public class AllHash extends AbsCash{
    private Ars ars;

    public AllHash(Ars ars){
        this.ars =ars;
    }

    @Override
    public void todo() {
        System.out.println("Все хэшкоды");
        System.out.println("========================");
        var mapall = ars.getMapall();
        var cash = choiseCashforUpdate(ars);
        if(cash!=null){
            System.out.println("Выводим все данные по хэшключам");
            System.out.println("-------------------------------");
            if(cash.getHashList().size()<1 || cash.getHashList()==null){
                System.out.println("Хэш записей, сейчас нет");
            }
            else{
                cash.printHashList();
            }
        }

        System.out.println("========================");
        exit(ars);
    }
}
