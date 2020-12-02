package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.ElementImpl;
import main.trati.AbsTrati;

public class CepNumCardOrCash extends AbsBaseCepochka{
    @Override
    public String handle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp) {
        if(!canHandle(element, ars, type, n, bulsUp)){
            System.out.println("Вышли из number");
            return "q";
        }
        else{
            return super.handle(element, ars, type, n, bulsUp);
        }
    }
    private boolean canHandle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp){
        boolean bul = false;
        AbsTrati atrat = new AbsTrati();
        String number = atrat.choiseCardOrCash(element.getSumma(), ars);
        if(!atrat.eex.equals("q")){
            element.setNumberCard(number);
            bul=true;
            System.out.println("отработал по number - "+ number);
        }
        return bul;
    }
}
