package main.menu;

import main.abs.AbsMain;
import main.abs.Ars;
import main.cards.*;
import main.potentialtrati.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static main.Exit.*;
import static main.Exit.exit_all;

public class MenuPotencialTrati extends AbsMain {

    private LinkedHashMap<String, AbsMain> menu = new LinkedHashMap<>();
    private ArrayList<String> arex = new ArrayList<>();
    private int colmenu = 2;
    private Ars ars;

    public MenuPotencialTrati(Ars ars){
        //super(ars);
        this.ars = ars;

        menu.put("Новая потенциальная покупка", new NewPotTrata(ars));
        menu.put("Удаление потенциальной покупки", new DeletPotList(ars));
        menu.put("Редактирование данных по потенциальной покупки", new UpdatePotList(ars));
        menu.put("Вывести все данные по списку потенциальных покупок", new GetAllPotList(ars));
        menu.put("Оплата потенциальных покупок", new OplatePotList(ars));


    }

    @Override
    public void todo() {
        start(menu, colmenu, 3);


        }



//    public void exit(ArrayList arex) {
//        while(true){
//            printExit(arex);
//            String s = scn2.nextLine();
//            if(checInt(s)){
//                  int i = Integer.parseInt(s);
//
//                if(i==9){
//                    ex=exit_main.ordinal();
//                    break;
//                }
//                if(i==0){
//                    ex=exit_all.ordinal();
//                    break;
//                }
//
//                else{
//                    System.out.println("Введено не соответствующее число. Повторите");
//                }
//            }
//            else{
//                System.out.println("Введено не соответствующее число. Повторите");
//            }
//        }
//    }
    }

