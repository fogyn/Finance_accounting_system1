package main.dinamik;

import main.elementi.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class ThreadDoxod<T> implements Runnable{
    private Doxod doxod;
    private ArrayList<T> listTrats;
    //private Card card;
    private Object lock;
    private Object lockUpdate;
    private Map<String, ArrayList<T>> mapall;


    public ThreadDoxod(Doxod doxod, Map<String, ArrayList<T>> mapall, Object lock){
        this.mapall = mapall;
        this.doxod = doxod;
        this.listTrats = (ArrayList<T>) mapall.get("траты");


        this.lock = lock;
        this.lockUpdate = doxod.getSelfLocker();

    }
    @Override
    public void run() {
        Thread current = Thread.currentThread();

        while (!current.isInterrupted()) {

            try {
                Thread.sleep(doxod.getTimebeetwinpay());
            } catch (InterruptedException e) {
                current.interrupt();
            }
            Card card = null;

            var cards = (ArrayList<Card>) mapall.get("карты");
           // System.out.println("номер заданной карты - "+doxod.getCardnum());
            for(Card c:cards){
               // System.out.println(c.getNumber());
                if(c.getNumber().equals(doxod.getNumber())){
                    card=c;
                    break;
                }
            }

            var num = (int) cards.stream().filter(c -> c.isBulblock()).count();
            //проснулся
//            System.out.println("имя карты на которую зачислять - "+card.getName());
            if (doxod != null) {
                if (doxod.isBulblock()) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Потенциальный доход " + doxod.getName() + " заморожен.  Разморозте доход");
                    System.out.println("--------------------------------------------------");
                    break;
                }
                else if (card == null) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Потенциальный доход " + doxod.getName() + " Переведен на другую карту. Карта указанная для перевода удалена");

                    if (cards.size() < 1 || num == cards.size()) {
                        todoCard();
                    }
                    else {
                          choiseCard(cards);
                    }

                    System.out.println("--------------------------------------------------");

                }
                else if (card.isBulblock()) {
                    System.out.println("--------------------------------------------------");
                    System.out.println("Потенциальный доход " + doxod.getName() + " Переведен на другую карту. Карта указанная для перевода заблокирована");

                    //Card newcard2 = null;
                    if (num<cards.size()) {
                        choiseCard(cards);
                        System.out.println("--------------------------------------------------");
                    }
                    else{
                        todoCard();
                    }

                }
                else {

                    Trati prixod = new Trati(doxod.getName(), card.getNumber(), "доход", doxod.getSumma(), true, doxod.getText());
                    synchronized (lock) {
                        card.setSumma(card.getSumma() + doxod.getSumma());
                        listTrats.add((T) prixod);
                    }
                    System.out.println("Деньги зачислены. Зачисление прошло успешно");
                    System.out.println("--------------------------------------------------");
                }

            }
            else {
                System.out.println("Доход удален");
                break;
            }
        }

    }
    private void todoCard(){
        Random rnd = new Random();
        Card newCard1 = new Card("Банковская карта "+rnd.nextInt(), "Создана в связи отсутствием других действующих карт");
        var cards = (ArrayList<Card>)mapall.get("карты");

        newCard1.setSumma(newCard1.getSumma()+doxod.getSumma());
        Trati prixod = new Trati(doxod.getName(), newCard1.getNumber(), "доход", doxod.getSumma(), true, doxod.getText());
        synchronized (lock){
            listTrats.add((T) prixod);
            doxod.setNumber(newCard1.getNumber());
        }
        cards.add(newCard1);

        System.out.println("Деньги зачислены на новую карту "+newCard1.getName());
    }

    private void choiseCard(ArrayList<Card> cards){
        Card newcard = null;
        for (Card c : cards) {
            if (!c.isBulblock()) {
                newcard = c;
                break;

            }
        }
        Trati prixod = new Trati(doxod.getName(), newcard.getNumber(), "доход", doxod.getSumma(), true, doxod.getText());
        synchronized (lock) {
            newcard.setSumma(newcard.getSumma() + doxod.getSumma());
            listTrats.add((T) prixod);
            doxod.setNumber(newcard.getNumber());
        }
        System.out.println("Деньги зачислены zна новую карту " + newcard.getName());
    }

}
