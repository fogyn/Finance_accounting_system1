package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.ElementImpl;

public class CepAllPrice extends AbsBaseCepochka{
    @Override
    public String handle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp) {
        if(!canHandle(element, ars, type, n, bulsUp)){
            System.out.println("Вышли из type");
            return "q";
        }
        else{
            return super.handle(element, ars, type, n, bulsUp);
        }
    }
    private boolean canHandle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp){
        boolean bul = false;
        float allprice = 0;
        if(type.contains("новый") && element.isType()){
            allprice =  getMfc().choiseMoney("Введите полную стоимость покупки");
        }
        if(!getMfc().eex.equals("q") && element.isType()){
            element.setAllprice(allprice);
            element.setEndprice(allprice);
            bul=true;
            System.out.println("отработал c завершением по allprice - "+ allprice);
        }
        else if(!getMfc().eex.equals("q") && !element.isType()){
            element.setAllprice(0);
            element.setEndprice(0);
            bul=true;
            System.out.println("отработал без завершения по allprice.");
        }
        return bul;
    }
}
