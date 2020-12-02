package main.menu;

import main.abs.AbsMain;
import main.abs.Ars;
import main.doxodi.AllDoxod;
import main.doxodi.DeleteDoxod;
import main.doxodi.NewDoxod;
import main.doxodi.UpdateDoxod;
import main.potentialtrati.*;

import java.util.LinkedHashMap;

public class MenuPotentialDoxod extends AbsMain {
    private Ars ars;
    private LinkedHashMap<String, AbsMain> menu = new LinkedHashMap<>();
    private int colmenu = 2;

    public MenuPotentialDoxod(Ars ars){
        this.ars = ars;
        menu.put("Новый потенциальный доход", new NewDoxod(ars));
        menu.put("Удаление потенциального дохода", new DeleteDoxod(ars));
        menu.put("Редактирование данных по потенциальному доходу", new UpdateDoxod(ars));
        menu.put("Вывести все данные по списку потенциальных доходов", new AllDoxod(ars ));


    }
    @Override
    public void todo() {
        start(menu, colmenu, 2);
    }
}
