package main.cashes;

import main.abs.Ars;
import main.elementi.Cash;
import main.elementi.Doxod;

import java.util.ArrayList;

public class GetAllCash extends AbsCash{
    private Ars ars;

    public GetAllCash(Ars ars){
        this.ars =ars;
    }

    @Override
    public void todo() {


        String getall = "показать все";


        var mapall = ars.getMapall();
        var cashes = (ArrayList<Cash>)mapall.get("cash");
        System.out.println("Выводим данные по всем электронным кошелькам");
        System.out.println("-----------------");



        if(cashes == null || cashes.size()<1){
            System.out.println("Список электронных кошельков пуст");
            System.out.println("**********************");
        }
        else {
            System.out.println("В списке "+cashes.size()+" эл.кошельков.");
            //doxods.forEach(Doxod::print);
            for(Cash cash:(ArrayList<Cash>)cashes){
                cash.print();
                System.out.println("===========================");
            }
        }

        exit(ars);
    }
}
