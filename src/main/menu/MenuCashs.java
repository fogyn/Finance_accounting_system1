package main.menu;

import main.abs.AbsMain;
import main.abs.Ars;
import main.cashes.*;
import main.doxodi.AllDoxod;
import main.doxodi.DeleteDoxod;
import main.doxodi.NewDoxod;
import main.doxodi.UpdateDoxod;

import java.util.LinkedHashMap;

public class MenuCashs extends AbsMain {
    private Ars ars;
    private LinkedHashMap<String, AbsMain> menu = new LinkedHashMap<>();
    private int colmenu = 2;

    public MenuCashs(Ars ars){
        this.ars = ars;
        menu.put("Новый электронный кошелек", new NewCash(ars));
        menu.put("Удаление электронного кошелька", new DeleteCash(ars));
        menu.put("Редактирование данных по электронному кошельку", new UpdateCash(ars));
        menu.put("Вывести данные по всем кошелькам", new GetAllCash(ars));
        menu.put("Вывести все данные по хэшсписку", new AllHash(ars));
        menu.put("Удалить выбранный хэш по номеру", new DeletHashById(ars));
        menu.put("Декодировать выбранный хэш по номеру", new DecoderHashById(ars));


    }
    @Override
    public void todo() {
        start(menu, colmenu, 1);
    }
}
