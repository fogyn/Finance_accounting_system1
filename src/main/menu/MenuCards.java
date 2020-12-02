package main.menu;

import main.abs.AbsMain;
import main.abs.Ars;
import main.cards.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static main.Exit.*;

public class MenuCards extends AbsMain {
    private LinkedHashMap<String, AbsMain> menu = new LinkedHashMap<>();
    private int colmenu = 2;
    private Ars ars;
    public MenuCards(Ars ars){
        this.ars = ars;
        menu.put("Новая карта", new NewCard(ars));
        menu.put("Удаление карты", new DellCard(ars));
        menu.put("Данные по всем картам", new GetAllCards(ars));
        menu.put("Редактирование данных карты", new UpdateCardByName(ars));
        menu.put("Пополнение карты", new PopolnitCard(ars));
        menu.put("Перевод с карты", new PerevodWithCard(ars));
    }

    @Override
    public void todo() {
        start(menu, colmenu, 0);

    }
}
