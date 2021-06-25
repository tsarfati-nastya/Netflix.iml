public class Account {
  private String userName;
  private String accountOpeningDate;
  private String endOfSubscription;
  private String password;
  private Series [] series;

  public void printLastSeriesWatched(){
      if (series.length > 0){
          for (int i = 0; i < series.length; i++) {
              System.out.println("the series name: " + series[i].getNameOfTheSeries());
              System.out.println("your last episode watched: ");
           this.series[i].printLastEpisodeWatchedByUser(this);
          }
      }
      else System.out.println("no series watched yet!");
  }

  public void print(){
      System.out.println(this);
  }

  public String toString(){
      return ("account details are: " + "\nthe user name is: " + this.getUserName() + "the account opening date is: " + this.getAccountOpeningDate()
      + "\nthe end of subscription is: " + this.getEndOfSubscription() );
  }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.accountOpeningDate = "30.01.2021";
        this.endOfSubscription = "31.01.2025";
        this.series = new Series[0];
    }

    public boolean checkIfSeriesWatched (Series seriesToChack){
        for (int i = 0; i < this.series.length; i++) {
            if (this.series[i].getNameOfTheSeries().equals(seriesToChack.getNameOfTheSeries())){
            return true;
            }
        }return false;
    }

    public void addSeries(Series seriesToAdd){
        if (!this.checkIfSeriesWatched(seriesToAdd)){

        Series[] array = new Series[this.series.length + 1];
        for (int i = 0; i < series.length; i++) {
            array[i] = series[i];
        }
        array[this.series.length] = seriesToAdd;
        this.series = array;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountOpeningDate() {
        return accountOpeningDate;
    }

    public void setAccountOpeningDate(String accountOpeningDate) {
        this.accountOpeningDate = accountOpeningDate;
    }

    public String getEndOfSubscription() {
        return endOfSubscription;
    }

    public void setEndOfSubscription(String endOfSubscription) {
        this.endOfSubscription = endOfSubscription;
    }
}
