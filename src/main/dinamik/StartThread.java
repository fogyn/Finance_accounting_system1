package main.dinamik;

import main.abs.AbsMain;
import main.abs.Ars;
import main.elementi.Pottrat;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class StartThread<T> extends AbsMain {
    Map<String, ArrayList<T>> mapall;

    private Ars ars;
    private Object lock;

    public StartThread(Ars ars){
        //super(ars);
        this.ars = ars;
        this.mapall = ars.getMapall();
        this.lock = ars.getLock();
        //this.lock = new Object();
    }


    public void pusk2(Pottrat pot){
        ThreadTrat startThread = new ThreadTrat(pot, mapall, lock);
        Thread thread = new Thread(startThread);
        thread.setDaemon(true);
        thread.start();
    }


    public void puskListPotTr(ArrayList<Pottrat> listPottrats){
       var ar = listPottrats.stream().filter(c->!c.isBulblock() && !c.isMustpay()).collect(Collectors.toList());
        System.out.println("Запущено "+ar.size()+" потоков потенциальных трат");
        ar.forEach(c->pusk2(c));
    }
}
