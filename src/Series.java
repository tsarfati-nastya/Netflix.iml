import com.sun.xml.internal.bind.v2.util.EditDistance;

import java.util.Arrays;

public class Series {

    private String nameOfTheSeries;
    private Episode[] episodes;

    public Series(String nameOfTheSeries, Episode[] episodes) {
        this.nameOfTheSeries = nameOfTheSeries;
        this.episodes = episodes;
    }
    public Episode getEpisodeByName(String nameOfEpisode){
            for (int i = 0; i < this.episodes.length; i++){
                if (this.episodes[i].getNameOfEpisode().toLowerCase().equals(nameOfEpisode.toLowerCase())){
                    return this.episodes[i];
                }
            } return null;
        }

    public void printLastEpisodeWatchedByUser(Account accountWatched){
        for (int i = episodes.length -1; i >= 0 ; i--) {
            if (episodes[i].didHeWatch(accountWatched)){
                System.out.println(episodes[i]);
                break;
            }
        }
    }

    public void print(){
        System.out.println(this);
    }

    public String toString(){
        return "the name of the series: " + this.getNameOfTheSeries() + "\nthe episodes are:" +
                "\n" + Arrays.toString(episodes);}

    public Series(String nameOfTheSeries) {
        this.nameOfTheSeries = nameOfTheSeries;
    }

    public String getNameOfTheSeries() {
        return nameOfTheSeries;
    }

    public void setNameOfTheSeries(String nameOfTheSeries) {
        this.nameOfTheSeries = nameOfTheSeries;
    }

    public Episode[] getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Episode[] episodes) {
        this.episodes = episodes;
    }
}

