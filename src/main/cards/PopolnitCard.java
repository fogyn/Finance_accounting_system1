package main.cards;
import main.abs.Ars;
import main.elementi.Card;

import java.util.ArrayList;

import static main.Exit.exit_pref;

public class PopolnitCard extends AbsCard {
    private Ars ars;

    public PopolnitCard(Ars ars) {

        this.ars = ars;
        menunewcard.add("Выход из приложения - 0");
        menunewcard.add("Выход в главное меню - 9");
        menunewcard.add("Выход в предидущее меню - 8");
        menunewcard.add("Повторить операцию - 7");
    }
    @Override
    public void todo() {
        System.out.println("Пополнение карты");
        var mapall = ars.getMapall();
        var cardss = (ArrayList<Card>)mapall.get("карты");
        if(cardss.size()<1){
            System.out.println("Список карт пуст");
        }
        else {
            //cardss.forEach(c->c.printCard(c));
            for (int i = 0; i < cardss.size(); i++) {
                if(!cardss.get(i).isBulblock()){
                    System.out.println("Карта " + (i + 1));
                    cardss.get(i).printCard();
                }

            }
            int number = -1;

            while (true) {

                System.out.println("Введите номер карты из списка, для пополнения");
                System.out.println("Для отказа введите - q");
                System.out.println("---------------------------------------------------");
                String num = scn2.nextLine();
                if (num.equals("q")) {
                    ex = exit_pref.ordinal();
                    break;
                }
                if (checInt(num) && Integer.parseInt(num) > 0 && Integer.parseInt(num) <= cardss.size()  ) {
                                                                                                        //&& !cardss.get(Integer.parseInt(num)).isBulblock()
                    number = Integer.parseInt(num) - 1;
                    break;
                } else {
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
            }
            if(number!=-1){
                while (true){
                    System.out.println("Вы выбрали карту - "+cardss.get(number).getName());
                    System.out.println("Сумма на карте сейчас - "+cardss.get(number).getSumma());
                    System.out.println("Введите сумму для пополнения карты, целым числом");
                    System.out.println("---------------------------------------------------");
                    String sum = scn2.nextLine();
                    if (checInt(sum) && Integer.parseInt(sum) > 0 && Integer.parseInt(sum) <= Integer.MAX_VALUE) {
                        int summa = Integer.parseInt(sum);
                        cardss.get(number).setSumma(cardss.get(number).getSumma()+summa);
                        break;
                    } else {
                        System.out.println("Повторите ввод данных. Что-то не так");
                    }
                }
                //writeFile(getPath(), cardss);
                System.out.println("Пополнение карты прошло успешно");
                System.out.println("---------------------------------------------------");

            }
            exit(ars);
        }
    }
}
