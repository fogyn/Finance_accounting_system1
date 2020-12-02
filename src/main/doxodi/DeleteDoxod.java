package main.doxodi;

import main.abs.Ars;
import main.elementi.Doxod;
import main.elementi.Pottrat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DeleteDoxod extends AbsDoxod {
    private Map<String, String> menudel = new LinkedHashMap<>();
    private Ars ars;

    public DeleteDoxod(Ars ars) {
        menudel.put("1", " Удалить все потенциальные доходы");
        menudel.put("2", " Удалить выборочный потенциальный доход");
        menudel.put("q", "Отмена");
        this.ars = ars;
    }

    @Override
    public void todo() {
        System.out.println("Удаление  дохода");
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
        //
        exit(ars);
    }

    private void dellAll() {
        var mapall = ars.getMapall();
        System.out.println("----------------------------------------");
        for (Doxod d : (ArrayList<Doxod>) mapall.get("доходы")) {
            d.print();
        }
        System.out.println("----------------------------------------");
        boolean bul = false;
        bul = choisePay("Подтвердите удаление всех - да");
        if (!eex.equals("q") && bul) {
            //System.out.println(bul);
            var mustDoxodList = (ArrayList<Doxod>)mapall.get("доходы в работе");
            mustDoxodList.removeAll(mustDoxodList);
            var doxodList = (ArrayList<Doxod>) mapall.get("доходы");
            doxodList.removeAll(doxodList);
            System.out.println("Список потенциальных доходов очищен.");
        }
    }
    private void delByNumber(){
        var mapall = ars.getMapall();
        var doxod = choiseDoxodforUpdate(ars);
        boolean bul = false;
        if(!eex.equals("q") ){
            bul = choisePay("Подтвердите удаление - да");

        }
        if(!eex.equals("q") && bul){

            var mustDoxodList = (ArrayList<Doxod>)mapall.get("доходы в работе");
            if(mustDoxodList.contains(doxod)){
                mustDoxodList.remove(doxod);
            }
            var doxods = (ArrayList<Doxod>)mapall.get("доходы");
            doxods.remove(doxod);
            System.out.println("Потенциальный доход удалена.");
        }

    }


}
