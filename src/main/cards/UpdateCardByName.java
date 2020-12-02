package main.cards;
import main.abs.Ars;
import main.cep.block.*;
import main.cep.bul.BulCard;
import main.cep.bul.BulIml;
import main.elementi.Card;

import java.util.ArrayList;
import java.util.Calendar;

import static main.Exit.exit_pref;

public class UpdateCardByName extends AbsCard {
    private Ars ars;
    public UpdateCardByName(Ars ars) {
        this.ars = ars;
        menunewcard.add("Выход из приложения - 0");
        menunewcard.add("Выход в главное меню - 9");
        menunewcard.add("Выход в предидущее меню - 8");
        menunewcard.add("Повторить операцию - 7");
    }
    @Override
    public void todo() {
        System.out.println("Редактирование карты");
        var mapall = ars.getMapall();
        var cards = (ArrayList<Card>)mapall.get("карты");
        String type = "редактирование";
        BulIml bulUpdateCard = new BulCard();
        System.out.println("Редактирование карты");
        var choisecard  = choiseCardforUpdate(ars);
        if(choisecard!=null){
            CepochkaAll name = new CepName();
            CepochkaAll sum = new CepSumm();
            CepochkaAll phone = new CepPhone();
            CepochkaAll text = new CepText();
            CepochkaAll year = new CepYear();
            CepochkaAll block = new CepBlock();

            Card card = new Card();
            card.setYear(choisecard.getYear());
            name.setNext(sum);
            sum.setNext(phone);
            phone.setNext(text);
            text.setNext(year);
            year.setNext(block);

            String s = name.handle(card,ars,type,"карты", bulUpdateCard);
            if(!s.equals("q")){
                System.out.println("Ваша карта была - ");
                choisecard.printCard();
                System.out.println("========================");
                System.out.println("Ваша карта стал  - ");
                card.printCard();
                System.out.println("========================");
                String podtverdi = "";
                while (true){
                    System.out.println("Подтвердите результат редактирования - да/нет");
                    System.out.println("Для выхода введите - q");
                    System.out.println("---------------------------------------------------");
                    podtverdi = scn2.nextLine();

                    if(podtverdi.equals("да") || podtverdi.equals("нет") || podtverdi.equals("q")){
                        if (podtverdi.equals("q") || podtverdi.equals("нет")) {
                            ex = exit_pref.ordinal();
                            eex="q";
                            System.out.println("Редактирование отменено");
                            break;
                        }
                        else if(podtverdi.equals("да")){

                            updateCard(ars, choisecard, card, bulUpdateCard);

                            System.out.println("Карта отредактирована");
                            break;
                        }
                    }
                    else{
                        System.out.println("Что то не так. Повторите ответ");
                    }
                }
            }
        }
        System.out.println("========================");
        exit(ars);
    }
}
