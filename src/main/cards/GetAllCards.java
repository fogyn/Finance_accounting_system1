package main.cards;


import main.abs.Ars;
import main.elementi.Card;

import java.util.ArrayList;

public class GetAllCards extends AbsCard {

    private Ars ars;

    public GetAllCards(Ars ars){

        this.ars = ars;
        menunewcard.add("Выход из приложения - 0");
        menunewcard.add("Выход в главное меню - 9");
        menunewcard.add("Выход в предидущее меню - 8");
    }
    @Override
    public void todo() {
        System.out.println("Вывод данных по всем картам");
        var mapall = ars.getMapall();
        var cardss = (ArrayList<Card>)mapall.get("карты");

        System.out.println("В списке "+cardss.size()+" карты.");
        if(cardss.size()<1){
            System.out.println("Список карт пуст");
        }
        else {
            cardss.forEach(c->c.printCard());
            }
        //
        exit(ars);

    }

}
