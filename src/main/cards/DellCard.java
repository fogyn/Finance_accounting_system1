package main.cards;


import main.abs.Ars;
import main.elementi.Card;

import java.util.ArrayList;

public class DellCard extends AbsCard {
    private Ars ars;

    public DellCard(Ars ars) {

        this.ars = ars;
        menunewcard.add("Выход из приложения - 0");
        menunewcard.add("Выход в главное меню - 9");
        menunewcard.add("Выход в предидущее меню - 8");
        menunewcard.add("Повторить операцию - 7");
    }

    @Override
    public void todo() {
        System.out.println("Удаление карты");
        var mapall = ars.getMapall();
        var cardss = (ArrayList<Card>)mapall.get("карты");

        boolean bul =false;
        if(cardss.size()<1){
            System.out.println("Список карт пуст");
        }
        else {
            for(int i =0;i<cardss.size();i++){
                System.out.println("Карта "+(i+1));
                cardss.get(i).printCard();
            }
            int number=-1;

            while (true){

                System.out.println("Введите номер карты из списка, для удаления");
                System.out.println("Для отказа введите - q");
                String num  = scn2.nextLine();

                if(checInt(num) && Integer.parseInt(num)>0 && Integer.parseInt(num)<=cardss.size()){
                    number=Integer.parseInt(num)-1;
                    break;
                }
                else{
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
            }
            if(number!=-1){
                if(cardss.size()==1){
                    System.out.println("Будет произведено обналичивание остатка, так как карт, на которые можно было бы сделать перевод");
                    cardss.remove(number);
                }
                else{
                    int where = choiseCard(cardss, 2, number);
                    if(where==-2){
                        cardss.remove(number);
                        System.out.println("Заберите остаток.");
                    }
                    else{
                        cardss.get(where).setSumma(cardss.get(where).getSumma()+cardss.get(number).getSumma());
                        cardss.remove(number);
                    }
                }

                //writeFile(getPath(), cardss);
                System.out.println("Карта удалена");
            }

        }
        exit(ars);

    }
}
