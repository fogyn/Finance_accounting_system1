package main.cep;

import main.abs.AbsMain;
import main.abs.Ars;
import main.elementi.Card;
import main.elementi.Doxod;

import java.util.ArrayList;

import static main.Exit.exit_pref;

public class MethodForCepochka extends AbsMain {
    public String eex = "ok";

    public boolean testmas(char[] mas){
    boolean bul =true;
    for(char c:mas){
        if(c<48 || c>57){
            bul=false;
            break;
        }
    }
    return bul;
}
//
    public String choiseName(String s){
        String name = "";
        while (true) {
            System.out.println(s);
            System.out.println("Для отказа введите - q");
            System.out.println("---------------------------------------------------");
            name = scn2.nextLine();
            if (name.isEmpty() || name.isBlank() || name.charAt(0) < 48 || name.length() > 100) {
                System.out.println("Название не может быть пустым, или начинаться с символа пробел");
            }
            else {
                if (name.equals("q")) {
                    ex = exit_pref.ordinal();
                    eex = "q";
                    break;
                }
                else {
                    break;
                }
            }
        }
        return name;
    }
    // имя дохода, отдельно так как должно отличаться
    public String choiseNameDoxod(String s, Ars ars){
        String name = "";
        var mapall = ars.getMapall();
        var doxods = (ArrayList<Doxod>)mapall.get("доходы");
        ArrayList<String> nameDoxods = new ArrayList<>();
            if(doxods== null || doxods.size()<1){
                System.out.println("Список доходов пуст");
            }
            else {
                System.out.println("наименование существующих доходов");
                System.out.println("---------------------------------------------------");
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
                if (name.isEmpty() || name.isBlank() || name.charAt(0) < 48 || name.length() > 100) {
                    System.out.println("Название покупки не может быть пустым, или начинаться с символа пробел");
                } else {
                    if (name.equals("q")) {
                        ex = exit_pref.ordinal();
                        eex = "q";
                        break;
                    } else {
                        if (nameDoxods.contains(name)) {
                            System.out.println("Название дохода, должно отличаться от того что уже есть в списке.");
                        }
                        else {
                            break;
                        }
                    }
                }
            }
        return name;
    }
    // сумма на счету
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
                if(s.contains("текущей покупки") && checFloat(pricess) && Float.parseFloat(pricess)>0){
                    money = backFloatPriceReal(pricess);
                    break;
                }
                else if(checFloat(pricess) && Float.parseFloat(pricess)>=0){
                    money = backFloatPriceReal(pricess);
                    break;
                }
                else{
                    System.out.println("Повторите ввод данных. Что-то не так");
                }
            }
        }
        return money;
    }
    //создание телефона
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
            else if(phone.isEmpty() || phone.isBlank() || phone.equals(" ") ){
                if(!s.contains("к кошельку")){
                    phone = "не данных";
                    break;
                }
                else{
                    System.out.println("Не подходящий формат телефона. Только цифры. Номер состоит от 10 до 12 цифр. Повторите ввод.");
                }

            }
            else {
                char[] mas = phone.toCharArray();
                if(mas.length>9 && mas.length<13 && testmas(mas)){
                    break;
                }
                else{
                    System.out.println("Не подходящий формат телефона. Только цифры. Номер состоит от 10 до 12 цифр. Повторите ввод.");
                }
            }
        }
        return phone;
    }
    //заполнение дополнительной информации
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
                text  = "нет данных";
                break;
            }
            else if(text.length()>500){
                System.out.println("Сильно много символов. Повторите.");
            }
            else{
                break;
            }
        }
        return text;
    }
    //подтверждение
    public boolean choisePay(String s){
        String buls  ="";
        boolean bul = false;
        while (true){
            System.out.println(s);
            System.out.println("Для отказа введите - q");
            System.out.println("---------------------------------------------------");
            buls = scn2.nextLine();
            if(buls.equals("да") || buls.equals("нет") || buls.equals("q")){
                    if (buls.equals("q")) {
                        ex = exit_pref.ordinal();
                        eex="q";
                        break;
                    }
                    else if(buls.equals("да")){
                        bul=true;
                        break;
                    }
                    else{
                        break;
                    }
                }
                else{
                    System.out.println("Что то не так. Повторите ответ");
                }
            }
        return bul;
    }
    // taimer
    public long choiseTime(String s){
        long time = -1;
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
                if(checInt(tim) && Integer.parseInt(tim)>0 && Integer.parseInt(tim)<11){
                    time=Integer.parseInt(tim);
                    time = time*60000;
                    break;
                }
                else{
                    System.out.println("Повторите ввод данных. Время для демонстрации не боле 10 минут. Что-то не так");
                }
            }
        }
        return time;
    }
    //выбор карты
    public Card choiseCard(String s, Ars ars){
        var mapall = ars.getMapall();
        Card card = null;
        var cardss = (ArrayList<Card>)mapall.get("карты");
        if(cardss.size()<1){
            System.out.println("Откройте карту в разделе 1 главного меню - Карты");
            ex = exit_pref.ordinal();
            eex="q";
        }
        else {
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
                    if (checInt(num) && Integer.parseInt(num) > 0 && Integer.parseInt(num) <= cardss.size()) {
                        number = Integer.parseInt(num) - 1;
                        if (!cardss.get(number).isBulblock()) {
                            card = cardss.get(number);
                            break;
                        }
                        }
                    else {
                        System.out.println("Повторите ввод данных. Что-то не так");
                    }
                }
        }
        return card;
    }
}
