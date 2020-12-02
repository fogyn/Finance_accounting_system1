package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.ElementImpl;

public class CepTime extends AbsBaseCepochka{
    @Override
    public String handle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp) {
        if(!canHandle(element, ars, type, n, bulsUp)){
            System.out.println("Вышли из time");
            return "q";
        }
        else{
            return super.handle(element, ars, type, n, bulsUp);
        }
    }
    private boolean canHandle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp){
        boolean bul = false;
        long time = 0;
        if(type.contains("новый")){
            time =  getMfc().choiseTime("Введите время в минутах для демонстрации работы "+n+". Не более 10 минут");
        }
        else{
            time =  getMupdt().choiseTime("Отредактируйте время в минутах для демонстрации "+n+", если требуется.");
        }

        if(type.contains("новый") ){
            if(!getMfc().eex.equals("q")){
                element.setTimebeetwinpay(time);
                bul=true;
                System.out.println("отработал по времени - "+ time);
            }
        }
        else if(!getMupdt().eex.equals("q")){
            element.setTimebeetwinpay(time);
            bulsUp.setTimebeetwinpay(getMupdt().isBulfield());
            getMupdt().setBulfield(false);
            bul=true;
            System.out.println("отработал по номеру - "+ time);
        }
        return bul;
    }
}
