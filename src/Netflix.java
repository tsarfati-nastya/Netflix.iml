import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Netflix {
    public static Account[] users = new Account[0];
    public static Series[] series = {
            new Series("Friends", new Episode[]{
                    new Episode("WE were on a break!", "Rachel and Ross are fighting about Rosse's kiss with another women",
                            "1.1.1998"),
                    new Episode("The wedding", "Monica's and Chandler's wedding night, the day Rachel finds out she's pregnant!!",
                            "30.6.2001"),
                    new Episode("The Reunion!!", "Friends reunion after 18 years from the las episode!",
                            "27.5.2021")}),
            new Series("masterChef", new Episode[]{
                    new Episode("The auditions", "the auditions for master chef - welcoming the new competitors"
                            , "1.2.2018"),
                    new Episode("The sorting stage", "all the new competitors are competing for there place in the group"
                            , "14.4.2018"),
                    new Episode("The finals!", "only 3 competitors left - who will be the big winner of Master chef 2018?"
                            , "26.8.2018")}),
            new Series("moneyHeist", new Episode[]{
                    new Episode("The breaking day!", "the robbers break into the largest bank in the country!!"
                            , "20.8.2017"),
                    new Episode("Hostage situation", "the police tries to get into the bank without hurting the hostages"
                            , "13.9.2017"),
                    new Episode("The get away", "the robbers complet the money transfer and manage to escepe without the police noticing!"
                            , "22.12.2017")})
    };

    public static void main(String[] args) {
        printLogIn();
    }

    public static void printLogIn() {
        Scanner scanner = new Scanner(System.in);
        int userOption;
        System.out.println("Welcome to Netflix!! \npleas choose on of the following options: \n1) open new account \n2) log in");
        try {
            userOption = scanner.nextInt();
            if (userOption == 1) {
                Account newAccount = createAccount();
                menu(newAccount);
            } else if (userOption == 2) {
                System.out.println("enter your user name: ");
                String userName = scanner.next();
                System.out.println("enter your password: ");
                String password = scanner.next();
                int userIndex = checkUserIndex(userName);
                if (userIndex != -1 && users[userIndex].getPassword().equals(password)) {
                    menu(users[userIndex]);
                } else {
                    System.out.println("users does not exists!!");
                    printLogIn();
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("the argument is illegal");
            printLogIn();
        }
    }

    public static void menu(Account account) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        try {
            do {
                System.out.println("1 -> show all series" + "\n2 -> show all series you have started to watch"
                        + "\n3 -> show account details" + "\n4 -> choose series to watch" + "\n5 -> log out");
                userChoice = scanner.nextInt();
                switch (userChoice) {
                    case options.SHOW_ALL_SERIES:
                        printSeries();
                        break;
                    case options.SERIES_STARTED_WATCHING:
                        account.printLastSeriesWatched();
                        break;
                    case options.ACCOUNT_DETAILS:
                        account.print();
                        break;
                    case options.CHOOSE_SERIES_TO_WATCH:
                        chooseSeriesToWatch(account);
                        break;
                    case options.LOG_OUT:
                        printLogIn();
                        break;
                }
            } while (userChoice != 999);
        } catch (InputMismatchException e) {
            System.out.println("illegal input!!");
            menu(account);
        }
    }

    public static Series getSeriesByName(String seriesName) {
        for (int i = 0; i < series.length; i++) {
            if (series[i].getNameOfTheSeries().toLowerCase().equals(seriesName.toLowerCase())) {
                return series[i];
            }
        }
        return null;
    }

    public static void chooseSeriesToWatch(Account userWatching) {
        System.out.println("enter the series name: ");
        Scanner scanner = new Scanner(System.in);
        String seriesName = scanner.nextLine();
        Series selectedSeries = getSeriesByName(seriesName);
        if (selectedSeries != null) {
            System.out.println(Arrays.toString(selectedSeries.getEpisodes()));
            if (userWatching.checkIfSeriesWatched(selectedSeries)) {
                System.out.println("your last episode watched is: ");
                selectedSeries.printLastEpisodeWatchedByUser(userWatching);
            }
            System.out.println("enter the episode's name: ");
            String episodeChosen = scanner.nextLine();
            Episode selectedEpisode = selectedSeries.getEpisodeByName(episodeChosen);
            if (selectedEpisode != null) {
                userWatching.addSeries(selectedSeries);
                selectedEpisode.addAccount(userWatching);
                System.out.println("watching the episode");
            } else System.out.println("there is no episode!!");
        } else {
            System.out.println("the series doesn't exists!!");
            menu(userWatching);
        }
    }


    public static void printSeries() {
        for (int i = 0; i < series.length; i++) {
            System.out.println(series[i]);
        }
    }

    public static Account createAccount() {
        Scanner scanner = new Scanner(System.in);
        String userName;
        String userPassword;
        do {
            System.out.println("enter your new user's name: ");
            userName = scanner.next();
        } while (checkUserIndex(userName) != -1);
        do {
            System.out.println("enter password with minimum 6 chars, at least 1 English letter and numbers: ");
            userPassword = scanner.next();
        } while (!strongPassword(userPassword));

        Account newAccount = new Account(userName, userPassword);
        addUser(newAccount);
        return newAccount;
    }

    public static void addUser(Account userToAdd) {
        Account[] biggerArray = new Account[users.length + 1];
        for (int i = 0; i < users.length; i++) {
            biggerArray[i] = users[i];
        }
        biggerArray[users.length] = userToAdd;
        users = biggerArray;
    }

    //returns user index, if exists - returns index, if don't - (-1)
    public static int checkUserIndex(String userName) {
        for (int i = 0; i < users.length; i++) {
            if (userName.equals(users[i].getUserName())) {
                return i;
            }
        }
        return -1;
    }

    public static boolean strongPassword(String password) {
        if (password.length() >= 6) {
            boolean containsNumber = false;
            boolean containsLetter = false;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isDigit(password.charAt(i))) {
                    containsNumber = true;
                } else if (Character.isAlphabetic(password.charAt(i))) {
                    containsLetter = true;
                }
            }
            return (containsNumber && containsLetter);
        } else return false;
    }

}
