package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.ElementImpl;

public class CepType extends AbsBaseCepochka{
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
        boolean typeold = element.isType();
        boolean typ = false;
        if(type.contains("новый")){
            typ =  getMfc().choisePay("Если платеж с завершением , введите - да\n В противном случае , введите - нет");
        }
        else{
            typ =  getMupdt().choiseBulBlock("Если надо изменить состояние с завершением или без , введите - да\n " +
                    "Если редактирование не требуется, введите - нет, или пустое поле");
            element.setText(element.getText()+"->!"+typeold);
        }
        if(type.contains("новый")){
            if(!getMfc().eex.equals("q")){
                element.setType(typ);
                bul=true;
                System.out.println("отработал по type - "+ type);
            }
        }
        else if(!getMupdt().eex.equals("q")){
            element.setType(typ);
            bulsUp.setType(getMupdt().isBulfield());
            getMupdt().setBulfield(false);
            bul=true;
            System.out.println("отработал по type - "+ type);
        }
        return bul;
    }
}
