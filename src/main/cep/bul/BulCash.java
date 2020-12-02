package main.cep.bul;

public class BulCash extends AbsBul{
    private Boolean name;
    private Boolean money;
    private Boolean bulblock;
    private Boolean text;
    private Boolean phone;

    public BulCash(){
        this.name = false;
        this.money = false;
        this.bulblock = false;
        this.text = false;
        this.phone = false;
    }

    public boolean isName() {
        return name;
    }
    public boolean isMoney() {
        return money;
    }
    public boolean isBulblock() {
        return bulblock;
    }
    public boolean isText() {
        return text;
    }
    public boolean isPhone() {
        return phone;
    }
    public void setName(Boolean name) {
        this.name = name;
    }
    public void setMoney(Boolean money) {
        this.money = money;
    }
    public void setBulblock(Boolean bulblock) {
        this.bulblock = bulblock;
    }
    public void setText(Boolean text) {
        this.text = text;
    }
    public void setPhone(Boolean phone) {
        this.phone = phone;
    }
}
