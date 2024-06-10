import java.util.ArrayList;

public class Table {
    ArrayList<TableSeat> TableSeats;
    String TableNO;
    int SeatCount;
    Boolean Available;
    Order tablOrder;

    public Table(String inNO, int SeatCount,String tableOrder){
        this.TableSeats = new ArrayList<TableSeat>();
        this.Available= true;
        this.TableNO = inNO;
        this.SeatCount = SeatCount;
        this.tablOrder = new Order(tableOrder);
    }
    public int getSeatCount(){
        return this.SeatCount;
    }

    public String getTableNo(){
        return this.TableNO;
    }
    public Boolean isAvailable(){
        return this.Available;
    }
    public void setSeatCount(int newIn){
        this.SeatCount=newIn;
    }
    public void setAvailable(boolean newA){
        this.Available = newA;
    }
    public void setTableNO(String newNO){
        this.TableNO = newNO;
    }
}
