package main.doxodi;

import main.abs.Ars;
import main.elementi.Doxod;
import main.elementi.Pottrat;

import java.util.ArrayList;

public class AllDoxod extends AbsDoxod{

    private Ars ars;

    public AllDoxod(Ars ars){
        this.ars =ars;
    }

    @Override
    public void todo() {
        System.out.println("Выводим данные по всем доходам");
        System.out.println("========================");
        var mapall = ars.getMapall();
        var doxods = (ArrayList<Doxod>)mapall.get("доходы");
        System.out.println("Вы просматриваете все потенциальные доходы");
        System.out.println("-----------------");



        if(doxods == null || doxods.size()<1){
            System.out.println("Список потенциальных доходов пуст");
            System.out.println("**********************");
        }
        else {
            System.out.println("В списке "+doxods.size()+" доходов.");
            //doxods.forEach(Doxod::print);
            for(Doxod d:(ArrayList<Doxod>)doxods){
                d.print();
                System.out.println("===========================");
            }
        }

        exit(ars);
    }
}
