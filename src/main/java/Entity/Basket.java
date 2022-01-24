package Entity;

public class Basket {
    private String username;
    private Integer idTicket;
    private String filmName;
    private Integer number;
    private Integer priceAll;

    public Basket(String username,Integer idTicket,String filmName,Integer number,Integer priceAll){
        this.username = username;
        this.idTicket = idTicket;
        this.filmName = filmName;
        this.number = number;
        this.priceAll = priceAll;
    }

    public String getUsername() {
        return username;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public String getFilmName() {
        return filmName;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getPriceAll() {
        return priceAll;
    }
}
