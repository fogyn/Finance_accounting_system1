package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.ElementImpl;

public class CepEndPrice extends AbsBaseCepochka{
    @Override
    public String handle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp) {
        if(!canHandle(element, ars, type, n, bulsUp)){
            System.out.println("Вышли из endprice");
            return "q";
        }
        else{
            return super.handle(element, ars, type, n, bulsUp);
        }
    }
    private boolean canHandle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp){
        boolean bul = false;
        float endprice = 0;
        boolean oldtype = false;
        if(element.getText().contains("->!true")){
            oldtype = true;
        }
        if(!oldtype && element.isType() || oldtype && !element.isType()){
            System.out.println(element.getAllprice());
            endprice =  getMupdt().upChoiseEndPrice(element.getAllprice(), "Отредактируйте остаток. Остаток не может быть 0, и больше полной стоимости");
        }
        else if(oldtype && element.isType()){
            endprice = 0;
            bulsUp.setEndprice(true);
        }
        else{
            endprice = 0;
        }
        if(!getMupdt().eex.equals("q")){
            element.setEndprice(endprice);
            bulsUp.setEndprice(getMupdt().isBulfield());
            getMupdt().setBulfield(false);
            bul=true;
            System.out.println("отработал по endprice - "+ endprice);
            String [] mas = element.getText().split("->!");
            element.setText(mas[0]);
        }
        return bul;
    }
}
