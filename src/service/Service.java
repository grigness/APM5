package service;

import Domain.MealPlanner;
import repository.Repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public void add(MealPlanner activity) throws SQLException {
        this.repo.add(activity);
    }

    public List<MealPlanner> getActivities(){
        return repo.getActivities();
    }

//    public ArrayList<MealPlanner> getFilteredActivitiesByValue(int value) {
//        return(ArrayList<MealPlanner>) repo.getActivities().stream()
//                .filter(activity ->
//                        (activity.getCookingTime()< value))
//                .collect(Collectors.toList());
//    }

    public List<MealPlanner> getSorted(){

        return this.repo.getActivities().stream()
                .sorted(Comparator.comparing(MealPlanner::getName))
                .collect(Collectors.toList());
    }

//    public ArrayList<MealPlanner> getFilteredByOrganism(Integer source) {
//        return (ArrayList<MealPlanner>) repo.getActivities().stream()
//                .filter(r -> Objects.equals(r.getCookingTime(),source))
//                .collect(Collectors.toList());
//    }

//    public List<MealPlanner> getSortedByTime(Integer time){
//        return this.repo.getActivities().stream()
//                .sorted(Comparator.comparingInt()
//                .collect(Collectors.toList());
//    }

    public ArrayList<MealPlanner> getFilteredActivitiesByValue(int value) {
        return(ArrayList<MealPlanner>) repo.getActivities().stream()
                .filter(activity ->
                        (activity.getCookingTime()<= value))
                .collect(Collectors.toList());
    }



    public Service(Repository repo) {
        this.repo = repo;
    }
}
