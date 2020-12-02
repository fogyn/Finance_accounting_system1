package main.cards;

import main.abs.Ars;
import main.cep.block.*;
import main.cep.bul.BulCard;
import main.cep.bul.BulIml;
import main.elementi.Card;
import java.util.ArrayList;
import java.util.Arrays;
import static main.Exit.*;

public class NewCard extends AbsCard {
    private ArrayList<String> fieldscard = new ArrayList<>(Arrays.asList("название карты", "Номер", "Месяц", "Год", "Сумма", "Заблокирована", "Дополнительные данные"));
    private Ars ars;
    public NewCard(Ars ars){
        this.ars = ars;
        menunewcard.add("Выход из приложения - 0");
        menunewcard.add("Выход в главное меню - 9");
        menunewcard.add("Выход в предидущее меню - 8");
        menunewcard.add("Повторить операцию - 7");
    }
    @Override
    public void todo() {
        String type = "новый";
        BulIml bulUpdateCard = new BulCard();
        System.out.println("Создание новой карты");
        CepochkaAll name1 = new CepName();
        CepochkaAll phone1 = new CepPhone();
        CepochkaAll summ1 = new CepSumm();
        CepochkaAll text1 = new CepText();
        Card card = new Card();
        name1.setNext(phone1);
        phone1.setNext(summ1);
        summ1.setNext(text1);
        String s = name1.handle(card,ars,type,"новой карты", bulUpdateCard);
        if(!s.equals("q")){
            System.out.println("---------------------");
            System.out.println("Данные по новой карте");
            card.printCard();
            System.out.println("---------------------");
            String podtverdi = "";
            while (true){
                System.out.println("Подтвердите создание новой карты - да/нет");
                System.out.println("Для выхода введите - q");
                System.out.println("---------------------------------------------------");
                podtverdi = scn2.nextLine();
                if(podtverdi.equals("да") || podtverdi.equals("нет") || podtverdi.equals("q")){
                    if (podtverdi.equals("q") || podtverdi.equals("нет")) {
                        ex = exit_pref.ordinal();
                        eex="q";
                        System.out.println("Создание отменено");
                        break;
                    }
                    else if(podtverdi.equals("да")){
                        var mapall = ars.getMapall();
                        var cards = (ArrayList<Card>)mapall.get("карты");
                        cards.add(card);
                        System.out.println("Карта добавлена в список");
                        break;
                    }
                }
                else{
                    System.out.println("Что то не так. Повторите ответ");
                }
            }
        }
        System.out.println("========================");
        exit(ars);
    }

}
