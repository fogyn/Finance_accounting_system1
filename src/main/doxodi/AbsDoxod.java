package main.doxodi;

import main.abs.AbsMain;
import main.abs.Ars;
import main.cep.MethodForCepochka;
import main.cep.MethodForCepochkaUpdate;
import main.cep.bul.BulIml;
import main.elementi.Card;
import main.elementi.Doxod;

import java.util.ArrayList;

import static main.Exit.exit_pref;

public class AbsDoxod extends AbsMain {
    public String eex = "ok";
    private MethodForCepochka mfc;
    private MethodForCepochkaUpdate mupdt;

    public AbsDoxod(){mfc = new MethodForCepochka();
        mupdt = new MethodForCepochkaUpdate();}

    public boolean choisePay(String s){
        boolean bul = false;
        bul = mfc.choisePay(s);
        if(mfc.eex.equals("q")){
            ex = exit_pref.ordinal();
            eex = "q";
        }
        return bul;
    }

    public void updateDoxod(Ars ars, Doxod oldDoxod, Doxod updoxod, BulIml bulUpdateDoxod){
        if(bulUpdateDoxod.isName()){
            oldDoxod.setName(updoxod.getName());
        }
        if(bulUpdateDoxod.isMoney()){
            oldDoxod.setSumma(updoxod.getSumma());
        }
        if(bulUpdateDoxod.isNumber()){
            oldDoxod.setNumber(updoxod.getNumber());
        }
        if(bulUpdateDoxod.isTimebeetwinpay()){
            oldDoxod.setTimebeetwinpay(updoxod.getTimebeetwinpay());
        }
        if(bulUpdateDoxod.isText()){
            oldDoxod.setText(updoxod.getText());
        }
        if(bulUpdateDoxod.isBulblock()){
            if(oldDoxod.isBulblock()){
                oldDoxod.setBulblock(false);
                //запуск потока, разблокировка
            }
            else{
                oldDoxod.setBulblock(true);
                // Заморозка дохода. Блокировка потока
            }
        }
    }

    public Doxod choiseDoxodforUpdate(Ars ars){
        var mapall = ars.getMapall();
        var doxods = (ArrayList<Doxod>)mapall.get("доходы");
        Doxod doxod =null;

        if(doxods ==null || doxods.size()<1){
            System.out.println("Список потенциальных доходов пуст");
            System.out.println("**********************");
            ex = exit_pref.ordinal();
            eex="q";
        }
        else {
            System.out.println("В списке "+doxods.size()+" доходов.");
            for(int i = 0; i<doxods.size();i++){
                System.out.println("Номер потенциального дохода №"+i);
                System.out.println("**********************");
                Doxod d =  doxods.get(i);
                d.print();
            }
            System.out.println("**********************");
            int number = -1;
            while (true) {
                System.out.println("Введите номер потенциального дохода  из списка");
                System.out.println("Для отказа введите - q");
                System.out.println("---------------------------------------------------");
                String num = scn2.nextLine();
                if (num.equals("q")) {
                    ex = exit_pref.ordinal();
                    eex="q";
                    break;
                }
                if (checInt(num) && Integer.parseInt(num) > -1 && Integer.parseInt(num) < doxods.size() ) {
                    number = Integer.parseInt(num);
                    doxod = doxods.get(number);
                    break;
                } else {
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
            }
        }
        return doxod;
    }
}
