package main.cep.bul;

public class BulDoxod extends AbsBul{

    private boolean name;
    private boolean number;
    private boolean money;
    private boolean text;
    private boolean timebeetwinpay;
    private boolean bulBlock;

    public BulDoxod(){
        this.name = false;
        this.number = false;
        this.money = false;
        this.text = false;
        this.timebeetwinpay = false;
        this.bulBlock = false;
    }

    public boolean isName() {
        return name;
    }

    public void setName(boolean name) {
        this.name = name;
    }

    public boolean isNumber() {
        return number;
    }

    public void setNumber(boolean number) {
        this.number = number;
    }

    public boolean isMoney() {
        return money;
    }

    public void setMoney(boolean money) {
        this.money = money;
    }

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }

    public boolean isTimebeetwinpay() {
        return timebeetwinpay;
    }

    public void setTimebeetwinpay(boolean timebeetwinpay) {
        this.timebeetwinpay = timebeetwinpay;
    }

    public boolean isBulBlock() {
        return bulBlock;
    }

    public void setBulBlock(boolean bulBlock) {
        this.bulBlock = bulBlock;
    }
}
