import java.util.Random;

class HumanChoice{
    int selected_door;
    boolean changed_choice;

    HumanChoice(int selected_door,boolean changed_choice){
        this.selected_door = selected_door;
        this.changed_choice = changed_choice;
    }
}

class DoorExperiment {
    private int count_doors;
    private int numbers_attempts;
    private Random random;
    private HumanChoice human;
    
    DoorExperiment(int count_door,int numbers_attempts,boolean changed_choice){
        this.count_doors = count_door;
        this.numbers_attempts = numbers_attempts;
        this.count_doors = count_door;
        this.random = new Random();
        this.human = new HumanChoice(random.nextInt(count_door), changed_choice);
    }
    
    public int run(){
        int count_wins = 0;
        for (int i = 0; i < numbers_attempts; i++) {
            int door_win = random.nextInt(count_doors);     
            human.selected_door = random.nextInt(count_doors);                                    
            int open_door = random.nextInt(count_doors);
            while (open_door == door_win || open_door == human.selected_door) open_door = random.nextInt(count_doors);
            if (human.changed_choice) {           
                if (human.selected_door == door_win) count_wins++;
                continue;
            } else {
                int changed_door = random.nextInt(count_doors);
                while (changed_door == open_door || changed_door == human.selected_door) changed_door = random.nextInt(count_doors);
                human.selected_door = changed_door;
                if (human.selected_door == door_win) count_wins++;
            }
        }
        return count_wins;
    }
}

public class MontyHall_Paradox {
    public static void main(String[] args) {
        int count_door = 3;
        int numbers_attempts = 100;

        DoorExperiment exp_change = new DoorExperiment(count_door, numbers_attempts, false);     
        int result_exp_with_change = exp_change.run();
        System.out.println("Угаданная дверь c учетом изменением выбора: " + result_exp_with_change);    // Кол-во верно выбранных дверей у человека, изменяющего выбор 

        DoorExperiment exp_nochange = new DoorExperiment(count_door, numbers_attempts, true);
        int result_exp_without_change = exp_nochange.run();
        System.out.println("Угаданная дверь без изменения выбора: " + result_exp_without_change); // Кол-во верно выбранных дверей у человека, не изменяющего выбор
    }
}
