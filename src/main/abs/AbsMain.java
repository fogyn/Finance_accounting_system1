package main.abs;

import main.elementi.AbsModel;
import main.elementi.Pottrat;

import java.util.*;

import static main.Exit.*;
import static main.Exit.exit_all;

public class AbsMain implements ToDoMain{
    public String eex = "ok";
    protected int ex;
    protected Scanner scn2 = new Scanner(System.in);
    protected ArrayList<String> pathFile = new ArrayList<String>();
    public ArrayList<String> arexits2 = new ArrayList<>(Arrays.asList("Выход из приложения - 0","Выход в главное меню - 9",
            "Выход в предидущее меню - 8","Повторить операцию - 7"));
    public ArrayList<String> stringMenu = new ArrayList<>(Arrays.asList("Вы в меню карт","Вы в меню электронных кошельков",
            "Вы в меню потенциальных доходов","Вы в меню потенциальных (повторяющихся) трат", "Вы в меню текущих трат" ));
    public AbsMain(){ }
    public void todo(){};
    protected void printMenu(ArrayList<String> menus){
        for(int i=0;i<menus.size();i++){
            System.out.println((i+1)+". "+menus.get(i));
        }
    }
    public boolean checInt(String s){
        boolean bul=true;
        try{
            Integer.parseInt(s);
        }catch (Exception e){
            bul=false;
        }
        return bul;
    }
    public boolean checFloat(String s){
        boolean bul=true;
        try{
            Float.parseFloat(s);
        }catch (Exception e){
            bul=false;
        }
        return bul;
    }
    public float backFloatPriceReal(String s){
        float realPrice = Float.parseFloat(s);
        realPrice = (float) ((int)(realPrice*100))/100;
        return realPrice;
    }
    public int getEx() {
        return ex;
    }
    public void setEx(int ex) {
        this.ex = ex;
    }
    public void printExit(ArrayList<String> ar){
        for(String s:ar){
            System.out.println(s);
        }
    }
    public void printexit2(ArrayList<String> arex2, int col){
        for(int i=0;i<col;i++){
            System.out.println(arex2.get(i));
        }
    }
    public boolean chekExit(int num, int colmenu){
        boolean bul =false;
        switch (colmenu){
            case 1:
                switch (num){
                    case 0:
                        ex=exit_all.ordinal();
                        bul=true;
                        break;
                }
                break;
            case 2:
                switch (num){
                    case 0:
                        ex=exit_all.ordinal();
                        bul=true;
                        break;
                    case 9:
                        ex=exit_main.ordinal();
                        bul=true;
                        break;
                }
                break;
            case 3:
                switch (num){
                    case 0:
                        ex=exit_all.ordinal();
                        bul=true;
                        break;
                    case 9:
                        ex=exit_main.ordinal();
                        bul=true;
                        break;
                    case 8:
                        ex=exit_pref.ordinal();
                        bul=true;
                        break;
                }
                break;
            case 4:
                switch (num){
                    case 0:
                        ex=exit_all.ordinal();
                        bul=true;
                        break;
                    case 9:
                        ex=exit_main.ordinal();
                        bul=true;
                        break;
                    case 8:
                        ex=exit_pref.ordinal();
                        bul=true;
                        break;
                    case 7:
                        ex=exit_repeat.ordinal();
                        bul=true;
                        break;
                }
                break;
        }
        return bul;
    }
    public void exit(Ars ars){
        var mapall = ars.getMapall();
        var mustPayList = (ArrayList<Pottrat>) mapall.get("траты для оплаты");

        if(mustPayList.size()!=0){

            System.out.println("Спиок срочных платежей");
            printMustPayList2( (ArrayList<Pottrat>) mapall.get("траты для оплаты"));

        }
        while(true){
            printExit(arexits2);
            String s = scn2.nextLine();
            if(checInt(s)){
                int i = Integer.parseInt(s);
                if(i==7){
                    ex=exit_repeat.ordinal();
                    break;
                }
                if(i==8){
                    ex=exit_pref.ordinal();
                    break;
                }
                if(i==9){
                    ex=exit_main.ordinal();
                    break;
                }
                if(i==0){
                    ex=exit_all.ordinal();
                    break;
                }

                else{
                    System.out.println("Введено не соответствующее число. Повторите");
                }
            }
            else{
                System.out.println("Введено не соответствующее число. Повторите");
            }
        }
    }
    public void start(LinkedHashMap<String, AbsMain> menu, int colmenu, int numMenu){
        ArrayList<String> menus = new ArrayList<>(menu.keySet());
        ArrayList<AbsMain> ops = new ArrayList<>(menu.values());

        while (true){
            System.out.println(stringMenu.get(numMenu));
            System.out.println("Введите номер подраздела, числом");
            printMenu(menus);
            //printExit(arex);
            printexit2(arexits2, colmenu);
            System.out.println("---------------");
            String s = scn2.nextLine();
            if(checInt(s)){
                int i = Integer.parseInt(s);
                if(chekExit(i, colmenu)){
                    break;
                }
                if(i<1 || i>menus.size()){
                    System.out.println("Такого подраздела нет. Повторите");
                }
                else{
                    while(true){
                        ops.get(i-1).todo();
                        ex = ops.get(i-1).getEx();
                        if(ex!=exit_repeat.ordinal()){
                            break;
                        }
                    }
                    if(ex==exit_all.ordinal()){
                        break;
                    }
                    if(ex==exit_main.ordinal()){
                        break;
                    }
                }
            }
            else{
                System.out.println("Введено не соответствующее число. Повторите");
            }
        }
    }
    public void printMustPayList2(ArrayList<Pottrat> mustpay){
        mustpay.forEach(c->{c.printPotTrat2();
            System.out.println("===========================");});
    }

}
