package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.ElementImpl;

public class CepPhone extends AbsBaseCepochka{
    @Override
    public String handle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp) {
        if(!canHandle(element, ars, type, n, bulsUp)){
            System.out.println("Вышли из phone");
            return "q";
        }
        else{
            return super.handle(element, ars, type, n, bulsUp);
        }
    }
    private boolean canHandle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp){
        boolean bul = false;
        String phone = "";
        if(type.contains("новый")){
            phone =  getMfc().choisePhone("Введите номер телефона "+n);
        }
        else{
            phone =  getMupdt().choisePhone("Отредактируйте номер телефона "+n+" , если требуется");
        }
        if(type.contains("новый") ){
            if(!getMfc().eex.equals("q")){
                element.setPhone(phone);
                bul=true;
                System.out.println("отработал по phone - "+ phone);
            }
        }
        else if(!getMupdt().eex.equals("q")){
            element.setPhone(phone);
            bulsUp.setName(getMupdt().isBulfield());
            getMupdt().setBulfield(false);
            bul=true;
            System.out.println("отработал по phone - "+ phone);
        }
        return bul;
    }
}
