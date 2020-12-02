package main.potentialtrati;

import main.abs.Ars;
import main.elementi.Pottrat;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DeletPotList extends AbsPot{
    private Map<String, String> menudel = new LinkedHashMap<>();
    private Ars ars;

    public DeletPotList(Ars ars) {

        this.ars = ars;
        menudel.put("1", " Удалить все потенциальные платежи");
        menudel.put("2", " Удалить выборочный потенциальный платеж");
        menudel.put("q", "Отмена");
    }

    @Override
    public void todo() {
        System.out.println("Вы удаляете потенциальную покупку");
        System.out.println("-----------------");
        String number = "";

        while(true){
            System.out.println("Введите команду,  согласно меню");
            System.out.println("----------------------------------------");
            for(String s:menudel.keySet()){
                System.out.println(s+"."+menudel.get(s));
            }
            System.out.println("----------------------------------------");

            number = scn2.nextLine();
            switch (number){
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
                if(number.equals("q") || number.equals("1") || number.equals("2")){
                    break;
                }
                else{
                    System.out.println("Введено не корректное значение. Повторите");
                }
        }
        //
        exit(ars);
    }

    private void dellAll(){
        var mapall = ars.getMapall();
        System.out.println("----------------------------------------");
        for(Pottrat p:(ArrayList<Pottrat>)mapall.get("pot")){
            p.printPotTrat();
        }
        System.out.println("----------------------------------------");
        boolean bul = false;
        bul = choisePay("Подтвердите удаление всех - да");
        if(!eex.equals("q") && bul){
            //System.out.println(bul);
            var mustPayList = (ArrayList<Pottrat>)mapall.get("траты для оплаты");
            mustPayList.removeAll(mustPayList);
            var potList = (ArrayList<Pottrat>)mapall.get("pot");
            potList.removeAll(potList);
            System.out.println("Список потенциальных покупок очищен.");
        }
    }
    private void delByNumber(){
        var mapall = ars.getMapall();
        var pottrat = choisePotTratforUpdate(ars);
        boolean bul = false;
        if(!eex.equals("q") ){
            bul = choisePay("Подтвердите удаление - да");
            System.out.println(bul);
        }
        if(!eex.equals("q") && bul){
            System.out.println(bul);
            var mustPayList = (ArrayList<Pottrat>)mapall.get("траты для оплаты");
            if(mustPayList.contains(pottrat)){
                mustPayList.remove(pottrat);
            }
            var potList = (ArrayList<Pottrat>)mapall.get("pot");
            potList.remove(pottrat);
            System.out.println("Потенциальная покупка удалена.");
        }

    }
}
