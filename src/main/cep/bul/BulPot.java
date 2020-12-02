package main.cep.bul;

public class BulPot extends AbsBul{

    private boolean name;
    private boolean number;
    private boolean activPay;
    private boolean type;
    private boolean allprice;
    private boolean endprice;
    private boolean money;
    private boolean text;
    private boolean mustPay;
    private boolean timebeetwinpay;
    private boolean bulBlock;
    //

    public BulPot() {
        this.name = false;
        this.number = false;
        this.activPay = false;
        this.type = false;
        this.allprice = false;
        this.endprice = false;
        this.money = false;
        this.text = false;
        this.mustPay = false;
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

    public boolean isActivPay() {
        return activPay;
    }

    public void setActivPay(boolean activPay) {
        this.activPay = activPay;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean isAllprice() {
        return allprice;
    }

    public void setAllprice(boolean allprice) {
        this.allprice = allprice;
    }

    public boolean isEndprice() {
        return endprice;
    }

    public void setEndprice(boolean endprice) {
        this.endprice = endprice;
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

    public boolean isMustPay() {
        return mustPay;
    }

    public void setMustPay(boolean mustPay) {
        this.mustPay = mustPay;
    }

    public boolean isTimebeetwinpay() {
        return timebeetwinpay;
    }

    public void setTimebeetwinpay(boolean timebeetwinpay) {
        this.timebeetwinpay = timebeetwinpay;
    }

    public boolean isBulblock() {
        return bulBlock;
    }

    public void setBulblock(boolean bulBlock) {
        this.bulBlock = bulBlock;
    }
}
