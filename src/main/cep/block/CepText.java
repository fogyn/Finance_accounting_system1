package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.ElementImpl;

public class CepText extends AbsBaseCepochka{

    @Override
    public String handle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp) {
        if(!canHandle(element, ars, type, n, bulsUp)){
            System.out.println("Вышли из text");
            return "q";
        }
        else{
            return super.handle(element, ars, type, n, bulsUp);
        }
    }
    private boolean canHandle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp){
        boolean bul = false;
        String text = "";
        if(type.contains("новый")){
            text =  getMfc().choiseText("Введите дополнительный текст "+n);
        }
        else{
            text =  getMupdt().choiseText("Отредактируйте дополнительный текст "+n+" , если требуется");
        }

        if(type.contains("новый") ){
            if(!getMfc().eex.equals("q")){
                element.setText(text);
                bul=true;
                System.out.println("отработал по text - "+ text);
            }
        }
        else if(!getMupdt().eex.equals("q")){
            element.setText(text);
            bulsUp.setText(getMupdt().isBulfield());
            getMupdt().setBulfield(false);
            bul=true;
            System.out.println("отработал по text - "+ text);
        }
        return bul;
    }
}
