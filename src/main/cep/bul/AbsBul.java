package main.cep.bul;

public abstract class AbsBul implements BulIml{
    @Override
    public boolean isName() {
        return false;
    }
    @Override
    public void setName(boolean name) {
    }
    @Override
    public boolean isNumber() {
        return false;
    }
    @Override
    public void setNumber(boolean number) {
    }
    @Override
    public boolean isMonth() {
        return false;
    }
    @Override
    public void setMonth(boolean month) {
    }
    @Override
    public boolean isYear() {
        return false;
    }
    @Override
    public void setYear(boolean year) {
    }
    @Override
    public boolean isMoney() {
        return false;
    }
    @Override
    public void setMoney(boolean money) {
    }
    @Override
    public boolean isBulblock() {
        return false;
    }
    @Override
    public void setBulblock(boolean bulblock) {
    }
    @Override
    public boolean isText() {
        return false;
    }
    @Override
    public void setText(boolean text) {
    }
    @Override
    public boolean isPhone() {
        return false;
    }
    @Override
    public void setPhone(boolean phone) {
    }
    @Override
    public boolean isActivPay() {
        return false;
    }
    @Override
    public void setActivPay(boolean activPay) {
    }
    @Override
    public boolean isType() {
        return false;
    }
    @Override
    public void setType(boolean type) {
    }

    @Override
    public boolean isAllprice() {
        return false;
    }
    @Override
    public void setAllprice(boolean allprice) {
    }
    @Override
    public boolean isEndprice() {
        return false;
    }
    @Override
    public void setEndprice(boolean endprice) {
    }
    @Override
    public boolean isMustPay() {
        return false;
    }
    @Override
    public void setMustPay(boolean mustPay) {
    }
    @Override
    public boolean isTimebeetwinpay() {
        return false;
    }
    @Override
    public void setTimebeetwinpay(boolean timebeetwinpay) {
    }
}
