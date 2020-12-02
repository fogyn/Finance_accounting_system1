package main.dinamik;

import main.elementi.Card;
import main.elementi.Pottrat;
import main.elementi.Trati;

import java.util.ArrayList;
import java.util.Map;

public class ThreadTrat<T> implements Runnable{
    private Map<String, ArrayList<T>> mapall;
    private Pottrat pot;
    private ArrayList<Pottrat> arMustPay;
    private ArrayList<Trati> listTrats;
    private ArrayList<Pottrat> listPots;
    private Card card;
    private Object lock;
    private Object lockUpdate;

    public ThreadTrat(Pottrat pot, Map<String, ArrayList<T>> mapall, Object lock){
        this.mapall = mapall;
        this.pot = pot;
        this.arMustPay =(ArrayList<Pottrat>) mapall.get("траты для оплаты");
        this.listTrats = (ArrayList<Trati>) mapall.get("траты");
        this.listPots = (ArrayList<Pottrat>) mapall.get("pot");

//        for(Card c:(ArrayList<Card>) mapall.get("карты")){
//            if(c.getNumber().equals(pot.getNumber())){
//                this.card=c;
//                break;
//            }
//        }
        this.lock = lock;
        this.lockUpdate = pot.getLocker();
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        while(!current.isInterrupted()){

            try {
                Thread.sleep(pot.getTimebeetwinpay());
            } catch (InterruptedException e) {
                current.interrupt();
            }

            //
            if(pot!=null){
                if(pot.isActivPay()){
                    // попробуем выполнить автооплату
                    if(pot.isBulblock()){
                        System.out.println("--------------------------------------------------");
                        System.out.println("Потенциальный платеж " +pot.getName()+" заморожен. Оплату произвести нельзя. Разморозте платеж");
                        System.out.println("--------------------------------------------------");
                        break;
                    }//нет карты
                    this.card = null;
                    for(Card c:(ArrayList<Card>) mapall.get("карты")){
                        if(c.getNumber().equals(pot.getNumber())){
                            this.card=c;
                            break;
                        }
                    }
                    if(card==null){
                        System.out.println("--------------------------------------------------");
                        System.out.println("Потенциальный платеж " +pot.getName()+" переведен в список обязательных платежей. Оплату произвести нельзя. Удалена карта");
                        pot.setMustpay(true);
                        synchronized (lock) {
                            arMustPay.add(pot);
                        }
                        System.out.println("Пришло время платить по платежу "+ pot.getName());
                        System.out.println("--------------------------------------------------");
                        break;
                    }
                    // карта блокирована
                    if(card.isBulblock()){
                        System.out.println("--------------------------------------------------");
                        System.out.println("Потенциальный платеж " +pot.getName()+" переведен в список обязательных платежей. Оплату произвести нельзя. Карта блокирована. " +
                                "Разблокируйте карту в меню карт");
                        pot.setMustpay(true);
                        synchronized (lock) {
                            arMustPay.add(pot);
                        }
                        System.out.println("Пришло время платить по платежу "+ pot.getName());
                        System.out.println("--------------------------------------------------");
                        break;
                    }
                    // нехватка денег на карте
                    if(pot.isType()){
                        // остаток
                        if(pot.getEndprice() >=pot.getSumma() && card.getSumma()<pot.getSumma()){
                            System.out.println("--------------------------------------------------");
                            System.out.println("Потенциальный платеж " +pot.getName()+" переведен в список обязательных платежей. Оплату произвести нельзя. " +
                                    "Сумма на карте меньше необходимой для совершения платежа. " +
                                    "Пополните карту в меню карт или смените карту");
                            pot.setMustpay(true);
                            synchronized (lock) {
                                arMustPay.add(pot);
                            }
                            System.out.println("Пришло время платить по платежу "+ pot.getName());
                            System.out.println("--------------------------------------------------");
                            break;
                        }
                        else if(pot.getEndprice() <pot.getSumma() && card.getSumma()<pot.getEndprice()){
                            System.out.println("--------------------------------------------------");
                            System.out.println("Потенциальный платеж " +pot.getName()+" переведен в список обязательных платежей. Оплату произвести нельзя. " +
                                    "Сумма на карте меньше необходимой для совершения платежа. " +
                                    "Пополните карту в меню карт или смените карту");
                            pot.setMustpay(true);
                            synchronized (lock) {
                                arMustPay.add(pot);
                            }
                            System.out.println("Пришло время платить по платежу "+ pot.getName());
                            System.out.println("--------------------------------------------------");
                            break;
                        }
                        // выполняем тогда автоплатеж
                        else{
                            if((pot.getEndprice()-pot.getSumma())>0){
                                // синхронизация!!!!!
                                synchronized (pot.getLocker()) {
                                    pot.setEndprice(pot.getEndprice() - pot.getSumma());
                                }
                                Trati potTrati = new Trati (pot.getName(), card.getNumber(), "потенциальная",
                                        pot.getSumma(), true, pot.getText(), pot.getAllprice(), pot.getEndprice());
                                //надо добавить в лист трат
                                synchronized (lock){
                                    listTrats.add(potTrati);
                                }
                                //
                                synchronized (lock) {
                                    card.setSumma(card.getSumma() - pot.getSumma());
                                }
                                System.out.println("--------------------------------------------------");

                                System.out.println("Потенциальный платеж "+ pot.getName()+" прошел успешно !!!");
                                System.out.println("--------------------------------------------------");
                            }
                            else{
                                Trati potTrati = new Trati (pot.getName(), card.getNumber(), "потенциальная",
                                        pot.getEndprice(), true, pot.getText(), pot.getAllprice(), 0);
                                //надо добавить в лист трат
                                synchronized (lock){
                                    listTrats.add(potTrati);
                                }
                                //
                                synchronized (lock) {
                                    card.setSumma(card.getSumma() - pot.getEndprice());
                                }
                                System.out.println("--------------------------------------------------");
                                System.out.println("Потенциальный платеж "+ pot.getName()+" прошел успешно !!!");
                                System.out.println("Данный платеж завершен. Из списка потенциальных платежей "+pot.getName()+ " удален.");
                                synchronized (lock) {
                                    listPots.remove(pot);
                                }
                                System.out.println("--------------------------------------------------");
                                break;
                            }
                        }
                    }
                    else{
                        //нет остатка
                        if(card.getSumma()<pot.getSumma()){
                            System.out.println("--------------------------------------------------");
                            System.out.println("Потенциальный платеж " +pot.getName()+" переведен в список обязательных платежей. Оплату произвести нельзя. " +
                                    "Сумма на карте меньше необходимой для совершения платежа. " +
                                    "Пополните карту в меню карт или смените карту");
                            pot.setMustpay(true);
                            synchronized (lock) {
                                arMustPay.add(pot);
                            }
                            System.out.println("Пришло время платить по платежу "+ pot.getName());
                            System.out.println("--------------------------------------------------");
                            break;
                        }
                        // выполняем автоплатеж
                        else{
                            Trati potTrati = new Trati (pot.getName(), card.getNumber(), "потенциальная",
                                    pot.getSumma(), true, pot.getText());
                            //надо добавить в лист трат
                            synchronized (lock) {
                                listTrats.add( potTrati);
                                card.setSumma(card.getSumma() - pot.getSumma());
                            }
                            System.out.println("--------------------------------------------------");
                            System.out.println("Потенциальный платеж "+ pot.getName()+" прошел успешно !!!");
                            System.out.println("--------------------------------------------------");
                        }
                    }
                }
                else{
                    pot.setMustpay(true);
                    synchronized (lock) {
                        arMustPay.add(pot);
                    }
                    System.out.println("--------------------------------------------------");
                    System.out.println("Пришло время платить по платежу "+ pot.getName());
                    System.out.println("--------------------------------------------------");
                    break;
                }
            }
            else{
                // если потенциальный платеж удален совсем
                System.out.println("Платеж удален");
                break;
            }
        }
    }
    public Pottrat getPot() {
        return pot;
    }
    public void setPot(Pottrat pot) {
        this.pot = pot;
    }
}
