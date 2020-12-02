package main;

import main.abs.Ars;
import main.menu.MainMenu;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Map<String, String> mapPath = new LinkedHashMap<>();
        addMapPath(mapPath);
        chekFilesAll(mapPath);
        Ars ars = new Ars(mapPath);


        System.out.println("Для запуска приложения нажмите 1.");
        System.out.println("После запуска, запустятся следующие операции моделирующие периодически выполняемые платежи и поступление средств ");
        String i = "";
        while (true){
            System.out.println("Введите 1");
            i=sc.nextLine();
            if(checInt(i)){
                int ii = Integer.parseInt(i);
                if(ii==1){
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
        MainMenu mainMenu = new MainMenu(ars);
        mainMenu.start();

    }

    //
    private static boolean checInt(String s){
        boolean bul=true;
        try{
            Integer.parseInt(s);
        }catch (Exception e){
            bul=false;
        }
        return bul;
    }

    private static void chekFilesAll(Map<String, String> mapPath){
       for(String s:mapPath.keySet()){
           chekFile(mapPath.get(s));
       }
    }


    private static void chekFile(String path){
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println("Файл создан");
        }

    }
    private static void addMapPath(Map<String, String> mapPath){
        mapPath.put("карты","src/main/dir/Cards.txt");
        mapPath.put("траты","src/main/dir/Trati.txt");
        mapPath.put("pot","src/main/dir/Pots.txt");
        mapPath.put("доходы","src/main/dir/PotentialDoxod.txt");
        mapPath.put("cash","src/main/dir/Cashes.txt");
    }

}
