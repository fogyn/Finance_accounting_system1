package main.trati;

import main.abs.Ars;
import main.cep.block.*;
import main.cep.bul.BulTrati;
import main.elementi.Card;
import main.elementi.Cash;
import main.elementi.Trati;


import java.util.ArrayList;


public class NewTrati<T> extends AbsTrati{

    private Ars ars;
    private ArrayList<String> choisecardcash;

    public NewTrati(Ars ars){
        choisecardcash = new ArrayList<>();

        choisecardcash.add("Если оплату делаете картой, выберите - 1");
        choisecardcash.add("Если оплату делаете кошельком, выберите - 2");
        choisecardcash.add("Для отмены операции, введите - q");
        this.ars = ars;

    }

    @Override
    public void todo() {
        var mapall = ars.getMapall();
        var trats = (ArrayList<Trati>)mapall.get("траты");
        var cards = (ArrayList<Card>)mapall.get("карты");
        var cashes = (ArrayList<Cash>)mapall.get("cash");
        System.out.println("Вы делаете новую покупку");
        System.out.println("-----------------------");
        //
        CepochkaAll name1 = new CepName();
        CepochkaAll number1 = new CepNumCardOrCash();
        CepochkaAll summ1 = new CepSumm();
        CepochkaAll text1 = new CepText();

        Trati trati = new Trati();
        BulTrati blt = new BulTrati();
        name1.setNext(summ1);
        summ1.setNext(number1);
        number1.setNext(text1);

        String s = name1.handle(trati,ars, "новый", "текущей покупки", blt);

        Boolean activPay = false;
        if(!s.equals("q")){
            System.out.println("Ваша текущая покупка");
            trati.printTrati();
            System.out.println("---------------------------------");
            activPay = choisePay("Подтвердите оплату покупи y/n - да/нет");
        }
        if(!eex.equals("q") && activPay){
            trati.setPayOk(true);
            if(trati.getNumberCard().charAt(0)<97){
                for(Card c:cards){
                    if(c.getNumber().equals(trati.getNumberCard())){
                        c.setSumma(c.getSumma()-trati.getSumma());
                        break;
                    }
                }
            }
            else{
                for(Cash c:cashes){
                    if(c.getNumber().equals(trati.getNumberCard())){
                        c.setSumma(c.getSumma()-trati.getSumma());
                        c.addHashList(trati.getName(), trati.getData(),trati.getSumma());
                        break;
                    }
                }
            }
            trats.add(trati);
            System.out.println("Покупка совершена. Поздравляем");
        }
        exit(ars);

    }

}
