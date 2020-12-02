package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.ElementImpl;

public class CepAllPriceUpdate extends AbsBaseCepochka{
    @Override
    public String handle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp) {
        if(!canHandle(element, ars, type, n, bulsUp)){
            System.out.println("Вышли из allpriceUp");
            return "q";
        }
        else{
            return super.handle(element, ars, type, n, bulsUp);
        }
    }
    private boolean canHandle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp){
        boolean bul = false;
        float allprice = 0;
        boolean oldtype = false;
        if(element.getText().contains("->!true")){
            oldtype = true;
        }
        if(!oldtype && element.isType() || oldtype && !element.isType()){
            allprice =  getMupdt().choiseMoney("Отредактируйте полную стоимость покупки");
        }
        else if(oldtype && element.isType()){
            bulsUp.setAllprice(true);
            allprice = 0;
        }
        else{
            allprice = 0;
        }
        if(!getMupdt().eex.equals("q")){
            element.setAllprice(allprice);
            bulsUp.setAllprice(getMupdt().isBulfield());
            getMupdt().setBulfield(false);
            bul=true;
            System.out.println("отработал по allpriceUp - "+ allprice);
        }
        return bul;
    }
}
