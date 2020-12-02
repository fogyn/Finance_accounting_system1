package main.cep.block;

import main.abs.Ars;
import main.cep.bul.BulIml;
import main.elementi.ElementImpl;

public interface CepochkaAll {
    void setNext(CepochkaAll cep);
    String handle(ElementImpl el, Ars ars, String type, String n, BulIml bul);
}
