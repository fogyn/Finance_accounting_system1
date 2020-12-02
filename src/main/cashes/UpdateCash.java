package main.cashes;

import main.abs.Ars;

import main.cep.block.*;
import main.cep.bul.BulCash;
import main.cep.bul.BulIml;
import main.elementi.Cash;

import static main.Exit.exit_pref;

public class UpdateCash extends AbsCash{

    private Ars ars;

    public UpdateCash(Ars ars){
        this.ars =ars;
    }

    @Override
    public void todo() {
        System.out.println("редактирование электронного кошелька");
        System.out.println("========================");
        String updatecash = "редактирование";

        var choisecash  = choiseCashforUpdate(ars);
        if(choisecash!=null){
            //var bulfreze = choisecash.isBulblock();
            System.out.println("Для редактирования вы выбрали: ");
            choisecash.print();
            System.out.println("----------------------------");

            BulIml bulUpdateCash = new BulCash();
            Cash cash = new Cash();
            CepochkaAll name = new CepName();
            CepochkaAll phone = new CepPhone();
            CepochkaAll money = new CepSumm();
            CepochkaAll text = new CepText();
            CepochkaAll bulBlock = new CepBlock();

            name.setNext(phone);
            phone.setNext(money);
            money.setNext(text);
            text.setNext(bulBlock);
            String s = name.handle(cash, ars, updatecash , " электронного кошелька", bulUpdateCash);

            //
            if(!s.equals("q")){
                System.out.println("Ваш эл.кошелек был - ");
                choisecash.print();
                System.out.println("========================");
                System.out.println("Ваш эл.кошелек стал  - ");
                cash.print();
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

                            updateCash(ars, choisecash, cash, bulUpdateCash);

                            System.out.println("Кошелек отредактирован");
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
