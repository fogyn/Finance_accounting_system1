package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.ElementImpl;

public class CepName extends AbsBaseCepochka{
    @Override
    public String handle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp) {
        if(!canHandle(element, ars, type, n, bulsUp)){
            System.out.println("Вышли из name");
            return "q";
        }
        else{
            return super.handle(element, ars, type, n, bulsUp);
        }
    }
    private boolean canHandle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp){
        boolean bul = false;
        String name = "";
        if(type.contains("новый")){
            name =  getMfc().choiseName("Введите имя "+n);
        }
        else{
            name =  getMupdt().choiseName("Отредактируйте имя "+n+" , если требуется");
        }
        if(type.contains("новый") ){
            if(!getMfc().eex.equals("q")){
                element.setName(name);
                bul=true;
                System.out.println("отработал по имени - "+ name);
            }
        }
        else if(!getMupdt().eex.equals("q")){
            element.setName(name);
            bulsUp.setName(getMupdt().isBulfield());
            getMupdt().setBulfield(false);
            bul=true;
            System.out.println("отработал по имени - "+ name);
        }
        return bul;
    }
}
