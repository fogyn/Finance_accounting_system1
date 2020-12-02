package main.potentialtrati;

import main.abs.Ars;
import main.cep.block.*;
import main.cep.bul.BulIml;
import main.cep.bul.BulPot;
import main.dinamik.StartThread;
import main.elementi.Pottrat;
import java.util.ArrayList;
import static main.Exit.exit_pref;

public class UpdatePotList<T> extends AbsPot{
    private Ars ars;
    public UpdatePotList(Ars ars) {
        this.ars =ars;
    }
    @Override
    public void todo() {
        System.out.println("Вы планируете отредактировать данные по потенциальной покупке");
        System.out.println("В редактирование входит 11 позиций. Если редактировать позицию не требуется, нажмите enter");
        System.out.println("-----------------");
        //
        boolean inlist = false;
        boolean fr = false;
        var mapall = ars.getMapall();
        var mustPayList = (ArrayList<Pottrat>)mapall.get("траты для оплаты");

        var pottrat = choisePotTratforUpdate(ars);
        if(pottrat!=null) {
            if (!eex.equals("q")) {
                pottrat.printPotTrat();
                if (pottrat.isBulblock()) {
                    fr = true;
                }
                if (mustPayList.contains(pottrat)) {
                    inlist = true;
                }
            }
            //
            String uppot = "редактирование";
            BulIml bulUpdatePot = new BulPot();
            Pottrat pot = new Pottrat();
            pot.setType(pottrat.isType());

            CepochkaAll name = new CepName();
            CepochkaAll type = new CepType();
            CepochkaAll allprice = new CepAllPriceUpdate();
            CepochkaAll endprice = new CepEndPrice();
            CepochkaAll activPay = new CepActivPay();
            CepochkaAll currentPrice = new CepSumm();
            CepochkaAll cardnum = new CepNumCard();
            CepochkaAll time = new CepTime();
            CepochkaAll text = new CepText();
            CepochkaAll mustPay = new CepMustPay();
            CepochkaAll frez = new CepBlock();

            name.setNext(type);
            type.setNext(allprice);
            allprice.setNext(endprice);
            endprice.setNext(activPay);
            activPay.setNext(currentPrice);
            currentPrice.setNext(cardnum);
            cardnum.setNext(time);
            time.setNext(text);
            text.setNext(mustPay);
            mustPay.setNext(frez);

            String s = name.handle(pot, ars, uppot, "потенциального платежа", bulUpdatePot);
            if(!s.equals("q")){
                System.out.println("Ваша потенциальная покупка была - ");
                pottrat.printPotTrat();
                System.out.println("========================");
                System.out.println("Ваша потенциальная покупка стал  - ");
                pot.printPotTrat();
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

                            updatePot(ars, pottrat, pot, bulUpdatePot);

                            if(pottrat.isMustpay() && !inlist){
                                mustPayList.add(pottrat);
                            }
                            else if(!pottrat.isMustpay() && inlist && !pottrat.isBulblock()){
                                mustPayList.remove(pottrat);
                                StartThread startThread = new StartThread(ars);
                                startThread.pusk2(pottrat);
                            }
                            //активация замороженного потока
                            else if(!pottrat.isMustpay() && fr && !pottrat.isBulblock()){
                                StartThread startThread = new StartThread(ars);
                                startThread.pusk2(pottrat);
                            }
                            System.out.println("Данные отредактированы");
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
