package main.potentialtrati;

import main.abs.Ars;
import main.elementi.Pottrat;

import java.util.ArrayList;

public class GetAllPotList extends AbsPot {
    private Ars ars;

    public GetAllPotList(Ars ars) {

        this.ars = ars;
    }

    @Override
    public void todo() {

        var mapall = ars.getMapall();
        var pottrats = (ArrayList<Pottrat>)mapall.get("pot");
        System.out.println("Вы просматриваете все потенциальные покупки");
        System.out.println("-----------------");


        System.out.println("В списке "+pottrats.size()+" трат.");
        if(pottrats.size()<1){
            System.out.println("Список потенциальных трат пуст");
            System.out.println("**********************");
        }
        else {
            for(Pottrat p:(ArrayList<Pottrat>)pottrats){
                p.printPotTrat();
            }
        }

        //
        exit(ars);
    }
}
