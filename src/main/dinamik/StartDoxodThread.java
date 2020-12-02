package main.dinamik;

import main.abs.Ars;
import main.elementi.Doxod;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StartDoxodThread {

    private Ars ars;
    private Object lock;

    public StartDoxodThread(Ars ars){
        //super(ars);
        this.ars = ars;
        this.lock = ars.getLock();

    }

    public void pusk2(Doxod dox){
        ThreadDoxod startThread = new ThreadDoxod(dox, ars.getMapall(), lock);
        Thread thread = new Thread(startThread);
        thread.setDaemon(true);
        thread.start();
    }


    public void puskListDoxods(ArrayList<Doxod> listDoxods){
        var ar = listDoxods.stream().filter(c->!c.isBulblock()).collect(Collectors.toList());
        System.out.println("Запущено "+ar.size()+" потоков потенциальных доходов");
        ar.forEach(c->pusk2(c));
    }
}
