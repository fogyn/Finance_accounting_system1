package main.menu;

import main.abs.AbsMain;
import main.abs.Ars;
import main.dinamik.StartDoxodThread;
import main.dinamik.StartThread;
import main.elementi.Doxod;
import main.elementi.Pottrat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import static main.Exit.exit_all;

public class MainMenu<T> extends AbsMain{
    private LinkedHashMap<String, AbsMain> map = new LinkedHashMap<>();
    private Scanner scn = new Scanner(System.in);
    private Ars ars;

    public MainMenu(Ars ars){

        this.ars = ars;
        map.put("Операции по картам", new MenuCards(ars));
        map.put("Операции по кошелькам", new MenuCashs(ars));
        map.put("Операции по потенциальным доходам", new MenuPotentialDoxod(ars));
        map.put("Операции по потенциальным тратам", new MenuPotencialTrati(ars));
        map.put("Операции по текущим тратам", new MenuTrat(ars));




        System.out.println("Приложение  - Система учета финансов");
    }


    public void start(){
        var mapall = ars.getMapall();
        ArrayList<String> menus = new ArrayList<>(map.keySet());
        ArrayList<AbsMain> mainmenu = new ArrayList<>(map.values());

        StartThread startThread = new StartThread(ars);
        startThread.puskListPotTr((ArrayList<Pottrat>) mapall.get("pot"));

        StartDoxodThread startDoxodThread = new StartDoxodThread(ars);
        startDoxodThread.puskListDoxods((ArrayList<Doxod>) mapall.get("доходы"));


        while(true){
            var mustPayList = (ArrayList<Pottrat>) mapall.get("траты для оплаты");
            if(mustPayList.size()!=0){
                System.out.println("Спиок срочных платежей");
                printMustPayList2( (ArrayList<Pottrat>) mapall.get("траты для оплаты"));
                System.out.println("========================================");
            }
            else{

                System.out.println("Срочных платежей нет");
                System.out.println("===================================");
            }
            System.out.println("Вы вошли в главное меню");
            printMenu(menus);
            printexit2(arexits2, 1);
            System.out.println("---------------");
            System.out.println("Введите номер подраздела, числом");
            String s = scn.nextLine();
            if(checInt(s)){
                int i = Integer.parseInt(s);
                if(chekExit(i, 1)){
                    ars.writeFileAll();
                    break;
                }
                if(i<1 || i>menus.size()){
                    System.out.println("Такого подраздела нет. Повторите");
                }
                else{
                    mainmenu.get(i-1).todo();
                    ex = mainmenu.get(i-1).getEx();
                    if(ex==exit_all.ordinal()){
                        ars.writeFileAll();
                        break;
                    }
                }

            }
            else{
                System.out.println("Введено не соответствующее число. Повторите");
            }

        }

    }

    @Override
    public void todo() {

    }


}
