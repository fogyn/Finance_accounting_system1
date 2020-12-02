package main.cashes;

import main.abs.AbsMain;
import main.abs.Ars;
import main.cep.MethodForCepochka;
import main.cep.bul.BulIml;
import main.elementi.Cash;

import java.util.ArrayList;

import static main.Exit.exit_pref;

public class AbsCash extends AbsMain {
    public String eex = "ok";
    private MethodForCepochka mfc;
    public AbsCash() {
        this.mfc = new MethodForCepochka();
    }
    public Cash choiseCashforUpdate(Ars ars){
        var mapall = ars.getMapall();
        var cashes = (ArrayList<Cash>)mapall.get("cash");
        Cash cash =null;
        if(cashes ==null || cashes.size()<1){
            System.out.println("Список электронных кошельков пуст");
            System.out.println("**********************");
            ex = exit_pref.ordinal();
            eex="q";
        }
        else {
            System.out.println("В списке "+cashes.size()+" эл.кошельков.");
            for(int i = 0; i<cashes.size();i++){
                System.out.println("Номер электронного кошелька №"+i);
                System.out.println("**********************");
                Cash c =  cashes.get(i);
                c.print();
            }
            System.out.println("**********************");
            int number = -1;
            while (true) {
                System.out.println("Введите номер эл.кошелька  из списка");
                System.out.println("Для отказа введите - q");
                System.out.println("---------------------------------------------------");
                String num = scn2.nextLine();
                if (num.equals("q")) {
                    ex = exit_pref.ordinal();
                    eex="q";
                    break;
                }
                if (checInt(num) && Integer.parseInt(num) > -1 && Integer.parseInt(num) < cashes.size() ) {

                    number = Integer.parseInt(num);

                    cash = cashes.get(number);
                    break;

                } else {
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
            }
        }
        return cash;
    }
    public boolean choisePay(String s){
          boolean bul = false;
        bul = mfc.choisePay(s);
        if(mfc.eex.equals("q")){
            ex = exit_pref.ordinal();
            eex = "q";
        }
        return bul;
    }
    public void updateCash(Ars ars, Cash oldcash, Cash upcash, BulIml bulUpdateCash){
        if(bulUpdateCash.isName()){
            oldcash.setName(upcash.getName());
        }
        if(bulUpdateCash.isPhone()){
            oldcash.setPhone(upcash.getPhone());
        }
        if(bulUpdateCash.isMoney()){
            oldcash.setSumma(upcash.getSumma());
        }
        if(bulUpdateCash.isText()){
            oldcash.setText(upcash.getText());
        }
        if(bulUpdateCash.isBulblock()){

            if(oldcash.isBulblock()){
                oldcash.setBulblock(false);
                //запуск , разблокировка
            }
            else{
                oldcash.setBulblock(true);
                // Заморозка . Блокировка
            }
        }
    }
    protected int getNumberHesh(ArrayList<String> ar){
        int number = -1;
        while (true) {
            System.out.println("Введите номер хэш-записи из списка");
            System.out.println("Для отказа введите - q");
            System.out.println("---------------------------------------------------");
            String num = scn2.nextLine();
            if (num.equals("q")) {
                ex = exit_pref.ordinal();
                eex="q";
                break;
            }
            if (checInt(num) && Integer.parseInt(num) > -1 && Integer.parseInt(num) < ar.size() ) {
                number = Integer.parseInt(num);
                break;

            } else {
                System.out.println("Повторите ввод данных. Что-то не так");
            }
        }
        return number;
    }

}
