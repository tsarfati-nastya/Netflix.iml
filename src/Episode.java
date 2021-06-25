public class Episode {
    private String nameOfEpisode;
    private String summary;
    private String dateOfAiring;
    private Account[] alreadyWatched;

    public void print () {
        System.out.println(this);
    }

    public String toString (){
        return ("chapter details are: " + this.getNameOfEpisode() + "\n" + this.getDateOfAiring() + "\n" + this.getSummary());
    }

    public boolean didHeWatch(Account account){
        for (int i = 0; i < alreadyWatched.length ; i++) {
            if (alreadyWatched[i].getUserName().equals(account.getUserName())){
                return true;
            }
        }return false;
    }

    public void addAccount(Account accountToAdd){
            Account[] array = new Account[this.alreadyWatched.length + 1];
            for (int i = 0; i < alreadyWatched.length; i++) {
                array[i] = alreadyWatched[i];
            }
            array[this.alreadyWatched.length] = accountToAdd;
            this.alreadyWatched = array;
    }

    public Episode(String nameOfChapter, String summary, String dateOfAiring) {
        this.nameOfEpisode = nameOfChapter;
        this.summary = summary;
        this.dateOfAiring = dateOfAiring;
        this.alreadyWatched = new Account[0];
    }

    public String getNameOfEpisode() {
        return nameOfEpisode;
    }

    public void setNameOfEpisode(String nameOfEpisode) {
        this.nameOfEpisode = nameOfEpisode;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDateOfAiring() {
        return dateOfAiring;
    }

    public void setDateOfAiring(String dateOfAiring) {
        this.dateOfAiring = dateOfAiring;
    }
}
