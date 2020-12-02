package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.ElementImpl;

public class CepSumm extends AbsBaseCepochka{
    @Override
    public String handle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp) {
        if(!canHandle(element, ars, type, n, bulsUp)){
            System.out.println("Вышли из sum");
            return "q";
        }
        else{
            return super.handle(element, ars, type, n, bulsUp);
        }
    }
    private boolean canHandle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp){
        boolean bul = false;
        float sum = -1;
        if(type.contains("новый")){
            sum =  getMfc().choiseMoney("Введите сумму "+n);
        }
        else{
            sum =  getMupdt().choiseMoney("Отредактируйте сумму "+n+" , если требуется");
        }
        if(type.contains("новый") ){
            if(!getMfc().eex.equals("q")){
                element.setSumma(sum);
                bul=true;
                System.out.println("отработал по sum - "+ sum);
            }
        }
        else if(!getMupdt().eex.equals("q")){
            element.setSumma(sum);
            bulsUp.setMoney(getMupdt().isBulfield());
            getMupdt().setBulfield(false);
            bul=true;
            System.out.println("отработал по sum - "+ sum);
        }
        return bul;
    }
}
