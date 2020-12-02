package main.cards;

import main.abs.Ars;
import main.elementi.Card;

import java.util.ArrayList;

import static main.Exit.exit_pref;

public class PerevodWithCard extends AbsCard {
    private int kto = 1;
    private int kogo = 0;
    private Ars ars;

    public PerevodWithCard(Ars ars) {

        this.ars = ars;
        menunewcard.add("Выход из приложения - 0");
        menunewcard.add("Выход в главное меню - 9");
        menunewcard.add("Выход в предидущее меню - 8");
        menunewcard.add("Повторить операцию - 7");
    }


    @Override
    public void todo() {
        System.out.println("Перевод с карты");
        var mapall = ars.getMapall();
        var cardss = (ArrayList<Card>)mapall.get("карты");
        //System.out.println(cardss.size());
        boolean bul =false;
        int k;
        int who;
        if(cardss.size()<1){
            System.out.println("Список карт пуст");
        }
        else {
             k = choiseCard(cardss, kogo, -1);
             if(k!=-1){
                 who = choiseCard(cardss, kto, k);
                 if(who!=-1){
                     while (true) {
                         System.out.println("Введите сумму для пополнения карты, целым числом");
                         System.out.println("Сейчас сумма на карте списания = " + cardss.get(who).getSumma());
                         System.out.println("---------------------------------------------------");
                         String sum = scn2.nextLine();
                         if(sum.equals("q")){
                             ex=exit_pref.ordinal();
                             break;
                         }
                         if (checInt(sum) && Integer.parseInt(sum) > 0 && Integer.parseInt(sum) <= cardss.get(who).getSumma()) {
                             int summa = Integer.parseInt(sum);
                             cardss.get(k).setSumma(cardss.get(k).getSumma() + summa);
                             cardss.get(who).setSumma(cardss.get(who).getSumma() - summa);
                             break;
                         } else {
                             System.out.println("Повторите ввод данных. Что-то не так");
                         }
                     }

                     //writeFile(getPath(), cardss);
                     System.out.println("Пополнение карты прошло успешно");
                     System.out.println("---------------------------------------------------");
                 }

             }
            exit(ars);

        }


    }


}

