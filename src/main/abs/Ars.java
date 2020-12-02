package main.abs;

import main.elementi.*;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


//Делаем синглтон
public class Ars<T> implements Serializable{
    private Map<String, String> mapPath;
    private Map<String, ArrayList<T>> mapall;
    private Lock lock;
    public Ars(Map<String, String> mapPath){
        this.mapPath = mapPath;
        this.mapall = new LinkedHashMap<>();
        getArsInit(mapPath);
        this.lock = new Lock();
    }
    private void getArsInit(Map<String, String> mapPath){
        for(String label:mapPath.keySet()){
            switch (label){
                case "карты":

                    var listCards = (ArrayList<Card>) readFile(mapPath.get(label));
                    mapall.put("карты", (ArrayList<T>) listCards);
                    break;
                case "траты":
                    var listTrats = (ArrayList<Trati>)readFile(mapPath.get(label));
                    mapall.put("траты",(ArrayList<T>)listTrats);
                    break;
                case "pot":

                    var listPottrats = (ArrayList<Pottrat>) readFile(mapPath.get(label));

                    mapall.put("pot", (ArrayList<T>) listPottrats);

                     var arMustPay = (ArrayList<Pottrat>) listPottrats.stream().filter(c -> !c.isBulblock() && c.isMustpay()).collect(Collectors.toList());
                    mapall.put("траты для оплаты", (ArrayList<T>) arMustPay);
                    break;
                case "доходы":
                    var listDoxods = (ArrayList<Doxod>) readFile(mapPath.get(label));

                    mapall.put("доходы", (ArrayList<T>) listDoxods);
                    var armustDoxod  =(ArrayList<Doxod>)listDoxods.stream().filter(c->!c.isBulblock()).collect(Collectors.toList());
                    mapall.put("доходы в работе", (ArrayList<T>)armustDoxod);
                    break;
                case "cash":
                    var listCash = (ArrayList<Cash>) readFile(mapPath.get(label));
                    mapall.put("cash", (ArrayList<T>) listCash);

                    break;
                }
        }
    }
    public void writeFile(String path, ArrayList<T> ar){
        try(ObjectOutputStream ous = new ObjectOutputStream(new  FileOutputStream(path))){
            ous.writeObject(ar);
        }catch (Exception e){
            e.printStackTrace();
        }
   }
    public void writeFileAll(){
        writeFile(mapPath.get("карты"),  mapall.get("карты"));
        writeFile(mapPath.get("траты"),  mapall.get("траты"));
        writeFile(mapPath.get("pot"), mapall.get("pot"));
        writeFile(mapPath.get("доходы"), mapall.get("доходы"));
        writeFile(mapPath.get("cash"), mapall.get("cash"));

    }
    private ArrayList<T> readFile(String path){
        ArrayList<T> ar = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
            ar = (ArrayList<T>) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ar;
    }
    public Map<String, ArrayList<T>> getMapall() {
        return mapall;
    }
    public void setMapall(Map<String, ArrayList<T>> mapall) {
        this.mapall = mapall;
    }
    public Object getLock(){
        return this.lock.getLocker();
    }

}
