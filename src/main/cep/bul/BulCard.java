package main.cep.bul;

public class BulCard extends AbsBul{
    private boolean name;
    private boolean number;
    private boolean month;
    private boolean year;
    private boolean money;
    private boolean bulblock;
    private boolean text;
    private boolean phone;

    public BulCard() {
        this.name = false;
        this.number = false;
        this.month = false;
        this.year = false;
        this.money = false;
        this.bulblock = false;
        this.text = false;
        this.phone = false;
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
    public boolean isMonth() {
        return month;
    }
    public void setMonth(boolean month) {
        this.month = month;
    }
    public boolean isYear() {
        return year;
    }
    public void setYear(boolean year) {
        this.year = year;
    }
    public boolean isMoney() {
        return money;
    }
    public void setMoney(boolean money) {
        this.money = money;
    }
    public boolean isBulblock() {
        return bulblock;
    }
    public void setBulblock(boolean bulblock) {
        this.bulblock = bulblock;
    }
    public boolean isText() {
        return text;
    }
    public void setText(boolean text) {
        this.text = text;
    }
    public boolean isPhone() {
        return phone;
    }
    public void setPhone(boolean phone) {
        this.phone = phone;
    }
}
