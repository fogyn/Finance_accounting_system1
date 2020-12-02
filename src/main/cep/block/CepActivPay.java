package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.ElementImpl;

public class CepActivPay extends AbsBaseCepochka{
    @Override
    public String handle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp) {
        if(!canHandle(element, ars, type, n, bulsUp)){
            System.out.println("Вышли из activPay");
            return "q";
        }
        else{
            return super.handle(element, ars, type, n, bulsUp);
        }
    }
    private boolean canHandle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp){
        boolean bul = false;
        boolean activPay = false;
        if(type.contains("новый")){
            activPay =  getMfc().choisePay("Если платеж осуществляем автоматически, введите - да\n В противном случае , введите - нет");
        }
        else{
            activPay = getMupdt().choiseBulBlock("Если надо изменить состояние способа оплаты (платеж автоматический или ручной) ," +
                    " введите - да\n Если редактирование не требуется, введите - нет, или пустое поле");
        }
        if(type.contains("новый")){
            if(!getMfc().eex.equals("q")){
                element.setActivPay(activPay);
                bul=true;
                System.out.println("отработал по activePay - "+ activPay);
            }
        }
        else if(!getMupdt().eex.equals("q")){
            element.setActivPay(activPay);
            bulsUp.setActivPay(getMupdt().isBulfield());
            getMupdt().setBulfield(false);
            bul=true;
            System.out.println("отработал по activePay - "+ activPay);
        }
        return bul;
    }
}
