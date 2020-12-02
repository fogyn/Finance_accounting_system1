package main.trati;

import main.abs.Ars;
import main.elementi.Trati;

import java.util.ArrayList;

import static main.Exit.exit_pref;

public class GetAllTrati<T> extends AbsTrati{
    private ArrayList<String> ar = new ArrayList<>();
    private Ars ars;
    public GetAllTrati(Ars ars){

        this.ars = ars;
        ar.add("Весь список трат");
        ar.add("Список текущих трат");
        ar.add("Список потенциальных трат");
        ar.add("Список доходов");
    }

    @Override
    public void todo() {
        System.out.println("Вы вызвали для просмотра список трат");
        int number = -1;
        var mapall = ars.getMapall();
        System.out.println("-----------------------");
        var tratis = (ArrayList<Trati>)mapall.get("траты");

        if(tratis.size()<1){
            System.out.println("Список трат пуст");
        }
        else {

            while (true){

                printMenu(ar);
                System.out.println("Выберите номер операции");
                System.out.println("Для отказа введите - q");
                System.out.println("-----------------------");
                String num = scn2.nextLine();
                if (num.equals("q")) {
                    ex = exit_pref.ordinal();
                    break;
                }
                if (checInt(num) && Integer.parseInt(num) > 0 && Integer.parseInt(num) <= ar.size()) {
                    number = Integer.parseInt(num) - 1;
                    break;
                } else {
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
            }
            int count = 0;
            switch (number){
                case 0:
                    //tratis.forEach(c->c.printTrati(c));
                    for(int i=0;i<tratis.size();i++){
                        Trati t = tratis.get(i);
                        if(t.getType().contains("потенциальная")){
                            t.printPotTrati();
                            count++;
                        }
                        else if(t.getType().equals("доход")){
                            t.printPrixod();
                            count++;
                        }
                        else{

                            t.printTrati();
                            count++;
                        }
                    }
                    System.out.println("------------------------------");
                    System.out.println("Полный список трат = "+count);
                    System.out.println("------------------------------");
                    break;
                case 1:


                    for(int i=0;i<tratis.size();i++){
                        Trati t = tratis.get(i);
                        if(t.getType().contains("потенциальная")){
                            continue;
                        }
                        else{
                            t.printTrati();
                            count++;
                        }
                    }
                    System.out.println("------------------------------");
                    System.out.println("Список текущих трат = "+count);
                    System.out.println("------------------------------");
                    break;
                case 2:

                    for(int i=0;i<tratis.size();i++){
                        Trati pt = tratis.get(i);
                        if(pt.getType().contains("потенциальная")){
                            pt.printPotTrati();
                            count++;
                        }
                        else{
                            continue;
                        }
                    }
                    System.out.println("------------------------------");
                    System.out.println("Список потенциальных трат = "+count);
                    System.out.println("------------------------------");
                    break;
                case 3:
                    for(int i=0;i<tratis.size();i++){
                        Trati t = tratis.get(i);
                        if(t.getType().equals("доход")){
                            t.printPrixod();
                            count++;
                        }

                    }
                    System.out.println("------------------------------");
                    System.out.println("Список доходов состотит из = "+count + " записей.");
                    System.out.println("------------------------------");

                    break;
            }
        }
        if(number!=-1){
            exit(ars);
        }
        else{
            ex=exit_pref.ordinal();
        }

    }
}
