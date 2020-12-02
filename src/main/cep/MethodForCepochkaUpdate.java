package main.cep;

import main.abs.AbsMain;
import main.abs.Ars;
import main.elementi.Card;
import main.elementi.Doxod;
import main.elementi.ElementImpl;

import java.util.ArrayList;

import static main.Exit.exit_pref;

public class MethodForCepochkaUpdate extends AbsMain {
    public String eex = "ok";
    private boolean bulfield = false;
    private MethodForCepochka mfc;
    public MethodForCepochkaUpdate() {
        this.mfc = new MethodForCepochka();
    }
    public boolean isBulfield() {
        return bulfield;
    }
    public void setBulfield(boolean bulfield) {
        this.bulfield = bulfield;
    }

    public String choiseNameDoxod(Ars ars, String s){
        var mapall = ars.getMapall();
        var doxods = (ArrayList<Doxod>)mapall.get("доходы");
        String name = "";
        ArrayList<String> nameDoxods = new ArrayList<>();
        if(doxods== null || doxods.size()<1){
            System.out.println("Список доходов пуст");
        }
        else {
            for(Doxod doxod:doxods){
                nameDoxods.add(doxod.getName());
            }
            nameDoxods.forEach(System.out::println);
        }
        System.out.println("---------------------------------------------------");
        while (true) {
            System.out.println(s);
            System.out.println("Для отказа введите - q");
            System.out.println("---------------------------------------------------");
            name = scn2.nextLine();
            if (name.isEmpty() || name.isBlank() || name.equals(" ")) {
                name = "0";
                break;
            } else {
                if (name.charAt(0) < 48 || name.length() > 100) {
                    System.out.println("Название покупки не может начинаться с не допустимых символов");
                } else {
                    if (name.equals("q")) {
                        ex = exit_pref.ordinal();
                        eex = "q";
                        break;
                    } else {
                        setBulfield(true);
                        break;
                    }
                }
            }
        }
        return name;
    }
    public String choiseName(String s){
    String name = "";
    while (true) {
        System.out.println(s);
        System.out.println("Для отказа введите - q");
        System.out.println("---------------------------------------------------");
        name = scn2.nextLine();
        if (name.equals("q")) {
            ex = exit_pref.ordinal();
            eex = "q";
            break;
        }
        else if (name.isEmpty() || name.isBlank() || name.equals(" ")) {
            name = "не меняем";
            break;
        } else {
            if (name.charAt(0) < 48 || name.length() > 100) {
                System.out.println("Название кошелька не может начинаться с не допустимых символов. Повторите ввод");
            }
            else {
                setBulfield(true);
                break;
            }
        }
    }
    return name;
    }
    public float choiseMoney(String s){
        float money = 0;
        while (true){
            System.out.println(s);
            System.out.println("Для отказа введите - q");
            System.out.println("---------------------------------------------------");
            String pricess = scn2.nextLine();

            if (pricess.equals("q")) {
                ex = exit_pref.ordinal();
                eex="q";
                break;
            }
            else{
                if(pricess.equals(" ") || pricess.isBlank() || pricess.isEmpty()){
                    money = -1;
                    break;
                }

                else if(checFloat(pricess) && Float.parseFloat(pricess)>=0){
                    money = backFloatPriceReal(pricess);
                    setBulfield(true);
                    break;
                }
                else{
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
            }
        }
        return money;
        }
    public String choisePhone(String s){
        String phone = "";
        while (true) {
            System.out.println(s);
            System.out.println("Для отказа введите - q");
            System.out.println("---------------------------------------------------");
            phone = scn2.nextLine();
            if (phone.equals("q")) {
                ex = exit_pref.ordinal();
                eex = "q";
                break;
            }
            else if (phone.isEmpty() || phone.isBlank() || phone.equals(" ")) {
                phone = "не меняем";
                break;
            } else {
                char[] mas = phone.toCharArray();
                if(mas.length>9 && mas.length<13 && mfc.testmas(mas)){
                    setBulfield(true);
                    break;
                }
                else{
                    System.out.println("Не подходящий формат телефона. Тольк цифры. Повторите ввод.");
                }
            }
        }
        return phone;
    }
    public String choiseText(String s){
        String text = "";
        while (true){
            System.out.println(s);
            System.out.println("Для отказа введите - q");
            System.out.println("---------------------------------------------------");
            text = scn2.nextLine();
            if (text.equals("q")) {
                ex = exit_pref.ordinal();
                eex="q";
                break;
            }
            else if(text.equals(" ") || text.isBlank() || text.isEmpty()){
                text = "не меняем";
                break;
            }
            else if(text.length()>500){
                System.out.println("Сильно много символов. Повторите.");
            }
            else{
                setBulfield(true);
                break;
            }
        }
        return text;
    }
    public int choiseYear(ElementImpl card, String s){
        int newyear = 0;
        while (true){
            System.out.println(s);
            String year  = scn2.nextLine();
            if(year.equals("q")){
                ex = exit_pref.ordinal();
                eex = "q";
                break;
            }
            else if(year.isEmpty() || year.equals("") || year.isBlank()){
                break;
            }
            else{
                if(checInt(year) && Integer.parseInt(year)>card.getYear() && Integer.parseInt(year)<=card.getYear()+5){
                    newyear=Integer.parseInt(year);
                    setBulfield(true);
                    break;
                }
                else{
                    System.out.println("Повторите ввод данных. Что-то не так. Возможно слишком большая прибавка по " +
                            "годам. Допускается не более +5");
                }
            }
        }
        return newyear;
    }
    public boolean choiseBulBlock(String s){
        boolean block = false;
        String buls  ="";
        while (true) {
            System.out.println(s);
            System.out.println("Для отказа введите - q");
            System.out.println("---------------------------------------------------");
            buls = scn2.nextLine();
            if (buls.equals("да") || buls.equals("нет") || buls.equals("q") || buls.equals(" ") || buls.isEmpty() || buls.isBlank()) {
                if (buls.equals("q")) {
                    ex = exit_pref.ordinal();
                    eex = "q";
                    break;
                }
                else if (buls.equals("да")) {
                    block = true;
                    setBulfield(true);
                    break;
                } else {
                    break;
                }
            }
            else {
                System.out.println("Что то не так. Повторите ответ");
            }
        }
        return block;
    }
    //
    public Card choiseCard(Ars ars, String s) {
        var mapall = ars.getMapall();
        Card card = null;
        var cardss = (ArrayList<Card>) mapall.get("карты");
        if (cardss.size() < 1) {

            System.out.println("Откройте карту в разделе 1 главного меню - Карты");
            ex = exit_pref.ordinal();
            eex = "q";
        } else {
            for (int i = 0; i < cardss.size(); i++) {
                if (!cardss.get(i).isBulblock()) {
                    System.out.println("Карта " + (i + 1));
                    cardss.get(i).printCard();
                }
            }
            int number = -1;

            while (true) {
                System.out.println(s);
                System.out.println("Для отказа введите - q");
                System.out.println("---------------------------------------------------");
                String num = scn2.nextLine();
                if (num.equals("q")) {
                    ex = exit_pref.ordinal();
                    eex = "q";
                    break;
                }
                if (num.isEmpty() || num.isBlank() || num.equals(" ")) {
                    break;
                } else {
                    if (checInt(num) && Integer.parseInt(num) > 0 && Integer.parseInt(num) <= cardss.size()) {

                        number = Integer.parseInt(num) - 1;
                        if (!cardss.get(number).isBulblock()) {
                            card = cardss.get(number);
                            setBulfield(true);
                            break;
                        }

                    } else {
                        System.out.println("Повторите ввод данных. Что-то не так");
                    }
                }
            }
        }
        return card;
    }
    public long  choiseTime(String s){
        long time = 0;
        while (true){
            System.out.println(s);
            System.out.println("Для отказа введите - q");
            System.out.println("---------------------------------------------------");
            String tim = scn2.nextLine();
            if(tim.equals("q")){
                ex = exit_pref.ordinal();
                eex = "q";
                break;
            }
            else{
                if(tim.equals(" ") || tim.isBlank() || tim.isEmpty()){
                    break;
                }
                else{
                    if(checInt(tim) && Integer.parseInt(tim)>0 && Integer.parseInt(tim)<11){
                        time=Integer.parseInt(tim);
                        time = time*60000;
                        setBulfield(true);
                        break;
                    }
                    else{
                        System.out.println("Повторите ввод данных. Время для демонстрации не боле 10 минут. Что-то не так");
                    }
                }
            }
        }
        return time;
    }
    public float upChoiseEndPrice(float allprice, String s){
        float endprice = 0;
        while (true){
            System.out.println(s);
            System.out.println("Для отказа введите - q");
            System.out.println("---------------------------------------------------");
            String pricess = scn2.nextLine();
            if (pricess.equals("q")) {
                ex = exit_pref.ordinal();
                eex="q";
                break;
            }
            else{
                if(pricess.equals(" ") || pricess.isBlank() || pricess.isEmpty()){
                    endprice = -1;
                    break;
                }
                else if(checFloat(pricess) && Float.parseFloat(pricess)>0 && Float.parseFloat(pricess)<=allprice){
                    endprice = backFloatPriceReal(pricess);
                    setBulfield(true);
                    break;
                }
                else{
                    System.out.println("Повторите ввод данных. Что-то не так. Возможно остаток больше стоимости");
                }
            }
        }
        return endprice;
    }
}
