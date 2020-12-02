package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.cep.MethodForCepochka;
import main.cep.MethodForCepochkaUpdate;
import main.elementi.ElementImpl;

public abstract class AbsBaseCepochka implements CepochkaAll{
    private CepochkaAll next;
    private MethodForCepochka mfc;
    private MethodForCepochkaUpdate mupdt;

    public AbsBaseCepochka() {
        this.mfc = new MethodForCepochka();
        this.mupdt = new MethodForCepochkaUpdate();
    }

    @Override
    public void setNext(CepochkaAll cep) {
        next = cep;
    }

    @Override
    public String handle(ElementImpl element, Ars ars, String type, String n, BulIml bul) {
        if(next!=null){
            return  next.handle(element, ars, type, n, bul);
        }
        return "ok";
    }

    public MethodForCepochka getMfc() {
        return mfc;
    }

    public MethodForCepochkaUpdate getMupdt() {
        return mupdt;
    }
}
