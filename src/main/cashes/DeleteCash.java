package main.cashes;

import main.abs.Ars;
import main.elementi.Cash;
import main.elementi.Doxod;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static main.Exit.exit_pref;

public class DeleteCash extends AbsCash{
    private Ars ars;
    private Map<String, String> menudel = new LinkedHashMap<>();

    public DeleteCash(Ars ars){

        menudel.put("1", " Удалить все электронные кошельки");
        menudel.put("2", " Удалить выборочный электронный кошелек");
        menudel.put("q", "Отмена");this.ars =ars;
    }

    @Override
    public void todo() {
        System.out.println("Удаление электронного кошелек");
        System.out.println("========================");
        String number = "";

        while (true) {
            System.out.println("Введите команду,  согласно меню");
            System.out.println("----------------------------------------");
            for (String s : menudel.keySet()) {
                System.out.println(s + "." + menudel.get(s));
            }
            System.out.println("----------------------------------------");

            number = scn2.nextLine();
            switch (number) {
                case "1":
                    System.out.println("выполняем удаление всех");
                    dellAll();
                    break;
                case "2":
                    System.out.println("выполняем удаление отдельного элемента");
                    System.out.println("----------------------------------------");
                    delByNumber();
                    break;
            }
            if (number.equals("q") || number.equals("1") || number.equals("2")) {
                break;
            } else {
                System.out.println("Введено не корректное значение. Повторите");
            }
        }

        System.out.println("========================");

        exit(ars);
    }
    private void dellAll() {
        var mapall = ars.getMapall();
        System.out.println("----------------------------------------");
        var cashes = (ArrayList<Cash>)mapall.get("cash");
        if(cashes == null || cashes.size()<1){
            System.out.println("Список электронных кошельков пуст");
            System.out.println("**********************");

        }
        else{
            for (Cash d : cashes) {
                d.print();
            }
            System.out.println("----------------------------------------");
            boolean bul = false;
            bul = choisePay("Подтвердите удаление всех - да");
            if (!eex.equals("q") && bul) {
                //System.out.println(bul);
                cashes.removeAll(cashes);
                System.out.println("Список электронных кошельков очищен.");
            }
        }


    }
    private void delByNumber(){
        var mapall = ars.getMapall();
        var cash = choiseCashforUpdate(ars);
        boolean bul = false;
        if(!eex.equals("q") ){
            bul = choisePay("Подтвердите удаление - да");

        }
        if(!eex.equals("q") && bul){
            var cashes = (ArrayList<Cash>)mapall.get("cash");
            cashes.remove(cash);
            System.out.println("Электронный кошелек удален.");
        }

    }
}
