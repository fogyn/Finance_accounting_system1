package main.menu;

import main.abs.AbsMain;
import main.abs.Ars;
import main.cards.*;
import main.trati.GetAllTrati;
import main.trati.NewTrati;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static main.Exit.*;

public class MenuTrat extends AbsMain {

    private int colmenu = 2;
    private LinkedHashMap<String, AbsMain> menu = new LinkedHashMap<>();
    private Ars ars;
    public MenuTrat(Ars ars){
        //super(ars);
        this.ars = ars;

        menu.put("Новая покупка", new NewTrati(ars));
        menu.put("Показать весь список трат", new GetAllTrati(ars));

    }


    @Override
    public void todo() {
        start(menu, colmenu, 4);

    }
}
