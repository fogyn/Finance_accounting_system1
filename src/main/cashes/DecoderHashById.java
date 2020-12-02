package main.cashes;

import main.abs.Ars;

public class DecoderHashById extends AbsCash{
    private Ars ars;

    public DecoderHashById(Ars ars){
        this.ars =ars;
    }

    @Override
    public void todo() {
        System.out.println("декодировать хэшкод по номеру");
        System.out.println("========================");
        var mapall = ars.getMapall();
        var cash = choiseCashforUpdate(ars);
        if(cash!=null){
            System.out.println("Выводим все данные по хэшключам");
            System.out.println("-------------------------------");
            if(cash.getHashList().size()<1 || cash.getHashList()==null){
                System.out.println("Хэш записей сейчас нет. Декодирование не возможно");
            }
            else{
                cash.printHashList2();
                int i = getNumberHesh(cash.getHashList());
                if(i!=-1){
                    System.out.println("для декодирования выбран - "+cash.getHashList().get(i));
                    System.out.println("---------------------------------");
                    boolean bul = choisePay("Подтвердите декодирвоание y/n - да/нет");
                    if(!eex.equals("q") && bul){

                        System.out.println("Запись декодирвана");
                        cash.printDecoder(i);
                    }
                }
            }

        }
        System.out.println("========================");
        exit(ars);
    }
}
