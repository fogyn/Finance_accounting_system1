package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.ElementImpl;

public class CepBlock extends AbsBaseCepochka{

    @Override
    public String handle(ElementImpl element, Ars ars, String type, String n, BulIml bulsUp) {
        if(!canHandle(element, ars, type, n, bulsUp)){
            System.out.println("Вышли из block");
            return "q";
        }
        else{
            return super.handle(element, ars, type, n, bulsUp);
        }
    }

    private boolean canHandle(ElementImpl element, Ars ars, String type, String n, BulIml bulUpdateCard){
        boolean bul = false;
        boolean block = getMupdt().choiseBulBlock("Если надо изменить состояние блокировки "+n+" , введите - да\n " +
                "Если редактирование не требуется, введите - нет, или пустое поле");

        if(!getMupdt().eex.equals("q")){
            element.setBulblock(block);
            bulUpdateCard.setBulblock(getMupdt().isBulfield());
            getMupdt().setBulfield(false);
            bul=true;
            System.out.println("отработал по block - "+ block);
        }
        return bul;
    }
}
