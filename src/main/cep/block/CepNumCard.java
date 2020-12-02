package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.Card;
import main.elementi.ElementImpl;

public class CepNumCard extends AbsBaseCepochka{
    @Override
    public String handle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp) {
        if(!canHandle(element, ars, type, n, bulsUp)){
            System.out.println("Вышли из карты");
            return "q";
        }
        else{
            return super.handle(element, ars, type, n, bulsUp);
        }
    }
    private boolean canHandle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp){
        boolean bul = false;
        Card card = null;
        if(type.contains("новый")){
            //
            card =  getMfc().choiseCard("Выберите карту из списка", ars);
        }
        else{
            card =  getMupdt().choiseCard(ars, "Отредактируйте карту из списка");
        }

        if(type.contains("новый")){
            if(!getMfc().eex.equals("q")){
                if(card==null){
                    element.setNumber("0");
                    System.out.println("отработал по карте - Редактирования нет");
                }
                else{
                    element.setNumber(card.getNumber());
                    System.out.println("отработал по карте - "+ card.getName());
                }
                bul=true;
            }
        }
        else if(!getMupdt().eex.equals("q")){
            if(card==null){
                element.setNumber("0");
                System.out.println("отработал по карте - Редактирования нет");
            }
            else{
                element.setNumber(card.getNumber());
                bulsUp.setNumber(getMupdt().isBulfield());
                getMupdt().setBulfield(false);
                System.out.println("отработал по номеру - "+ card.getName());
            }
            bul=true;
        }
        return bul;
    }
}
