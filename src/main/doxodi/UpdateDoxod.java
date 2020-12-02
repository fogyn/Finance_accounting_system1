package main.doxodi;

import main.abs.Ars;
import main.cep.block.*;
import main.cep.bul.BulDoxod;
import main.cep.bul.BulIml;
import main.dinamik.StartDoxodThread;
import main.elementi.Doxod;

import static main.Exit.exit_pref;

public class UpdateDoxod extends AbsDoxod{
    private Ars ars;
    public UpdateDoxod(Ars ars){
        this.ars =ars;
    }
    @Override
    public void todo() {
        System.out.println("Редактирование доход");
        System.out.println("========================");
        String updoxod = "редактирование";
        // выбираем doxod для редактирования.
        var choisedoxod  = choiseDoxodforUpdate(ars);
        BulIml bulUpdateDoxod = new BulDoxod();
        if(choisedoxod !=null){
            var bulfreze = choisedoxod.isBulblock();
            System.out.println("Для редактирования вы выбрали: ");
            choisedoxod.print();
            System.out.println("----------------------------");

            CepochkaAll name = new CepNameDoxod();
            CepochkaAll sum = new CepSumm();
            CepochkaAll cardnum = new CepNumCard();
            CepochkaAll time = new CepTime();
            CepochkaAll text = new CepText();
            CepochkaAll freze = new CepBlock();

            Doxod doxod = new Doxod();

            name.setNext(sum);
            sum.setNext(cardnum);
            cardnum.setNext(time);
            time.setNext(text);
            text.setNext(freze);

            String s = name.handle( doxod, ars, updoxod, "дохода", bulUpdateDoxod);
            System.out.println("========================");
//
            if(!s.equals("q")){
                System.out.println("Ваш доход был - ");
                choisedoxod.print();
                System.out.println("========================");
                System.out.println("Ваш доход стал  - ");
                doxod.print();
                System.out.println("========================");
                String podtverdi = "";
                while (true){
                    System.out.println("Подтвердите результат редактирования - да/нет");
                    System.out.println("Для выхода введите - q");
                    System.out.println("---------------------------------------------------");
                    podtverdi = scn2.nextLine();

                    if(podtverdi.equals("да") || podtverdi.equals("нет") || podtverdi.equals("q")){
                        if (podtverdi.equals("q") || podtverdi.equals("нет")) {
                            ex = exit_pref.ordinal();
                            eex="q";
                            System.out.println("Редактирование отменено");
                            break;
                        }
                        else if(podtverdi.equals("да")){

                            updateDoxod(ars, choisedoxod, doxod, bulUpdateDoxod);

                            System.out.println("Доход отредактирован");
                            if(!choisedoxod.isBulblock() && bulfreze){
                                StartDoxodThread startDoxodThread = new StartDoxodThread(ars);
                                startDoxodThread.pusk2(doxod);
                            }

                            break;
                        }
                    }
                    else{
                        System.out.println("Что то не так. Повторите ответ");
                    }
                }
            }
        }
        System.out.println("========================");
        exit(ars);
    }
}
