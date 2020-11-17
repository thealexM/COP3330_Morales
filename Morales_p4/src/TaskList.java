import java.io.*;
import java.util.ArrayList;

public class TaskList {
   
    protected static ArrayList<TaskItem> List = new ArrayList<>();
   
    protected static String extension = ".txt";

    protected static void addTask(String title, String description, String due_date){
       
        List.add(new TaskItem(title,description,due_date, false));
    }

    protected static String PrintableList(){
       
        String Tasks = "\n\n\nCurrent Tasks\n-------------\n\n", completion;
       
        for(int index = 0; index < List.size();index ++) {
          
            if(List.get(index).complete) completion = "Complete  ";
            
            else completion = "Incomplete";
            Tasks += (index + 1) + ") " + completion + " [" + List.get(index).due_date + "] "
                    + List.get(index).title + ": " + List.get(index).description + "\n";
        }
        return Tasks;
    }
    protected static void ClearTaskList() {
       
        List.clear();
    }
    protected static int[]    GenerateCompletionKey(boolean complete) {
        
        int AmountValid = 0;
        
        for (int index = 0; index < List.size(); index++) {
            
            if (List.get(index).complete == complete) AmountValid++;
        }
        int[] key = new int[AmountValid];
       
        for (int index = 0, position = 0; index < List.size(); index++) {
            
            if (List.get(index).complete == complete) {
               
                key[position] = index;
                
                position++;
            }
        }
        return key;
    }
    protected static String PartialTasks(boolean complete){
        
        String Prefix = "", Tasks = " Completed Tasks\n----------------------\n\n";
        
        int position = 1;
        
        for(int index = 0; index < List.size();index ++) {
           
            if(List.get(index).complete == complete) {
               
                Tasks += (position) + ") [" + List.get(index).due_date + "] "
                        + List.get(index).title + ": " + List.get(index).description + "\n";
                position++;
            }
        }
        if(position == 1){
           
            return "\n\nThere are no " + Prefix + "completed tasks.";
        }
        if(!complete) Prefix = "in";
        
        return Tasks + "\n\nSelect a task to mark as " + Prefix + "completed: ";
    }
    protected static boolean ValidateTask(String title, String due_date) {
       
        boolean validDate = TaskItem.Due_DateReport(TaskItem.Due_DateHandler(due_date));
      
        boolean validTitle = TaskItem.TitleHandler(title);
       
        return validDate && validTitle;
    }   
    protected static void editTask(String title, String description, String due_date, int index){
     
        List.get(index).title = title;
       
        List.get(index).description = description;
       
        List.get(index).due_date = due_date;
    }
    protected static void removeTask(int index){
        
        List.remove(index);
    }

    protected static void editCompletionStatus(int index, boolean complete){
      
        List.get(index).complete = complete;
    }

    protected static boolean ReadProtection(String FileName){
        try {
            
            new BufferedReader(new FileReader(FileName));
      
        } catch (IOException e) {
           
            System.out.println("\nThe file could not be found.\n");
           
            return false;
        }
        System.out.println("\nThe file was found.\n");
       
        return true;
    }
    protected static BufferedReader ReadFile(String FileName){
        
        BufferedReader input = null;
        
        try { input = new BufferedReader(new FileReader(FileName));
        } catch (IOException e) { System.out.println("\nThe file could not be found.\n"); }
        
        if(input!=null) System.out.println("\nThe list was found.\n");
        
        return input;
    }
    protected static boolean LoadFile(BufferedReader input){
        
        int tally = 0, validity;
        
        String placeholder = "", title = "#null#", description = "#null#", due_date = "#null#";
        do {
           
            try { if ((placeholder = input.readLine()) == null) break;
            } catch (IOException e) { e.printStackTrace(); }
          
            if((tally + 4) % 4 == 0) title = placeholder;
           
            if((tally + 3) % 4 == 0) description = placeholder;
            
            if((tally + 2) % 4 == 0) due_date = placeholder;
            
            if((tally + 1) % 4 == 0) {
               
                validity = TaskItem.Due_DateHandler(due_date);
               
                if(validity % 3 == 0 || validity % 5 == 0 || validity % 7 == 0 || validity % 11 == 0 ||
                        title.length() == 0) { return false; } 
                
                TaskItem Task = new TaskItem(title, description, due_date, Boolean.parseBoolean(placeholder));
                
                List.add(Task); }
            tally++;
        } while(true);
        return true;
    }
    protected static void PrintSavePrompt() {
       
        System.out.print("\n\nYou chose to save your progress.\n" +
                "After saving, you can close the task List with 8, or continue modifying it.\n");
    }
    protected static void   CreateFile(String FileName){
       
        File file = new File(FileName);
       
        try {
           
            if (file.createNewFile()) {
                
                System.out.println("\nThe file was created.\n");
           
            } else{ System.out.println("\nA file of that name already exists, it will be overwritten.\n"); }
       
        } catch (IOException e) { e.printStackTrace(); }
    }
    protected static boolean   ValidateFileName(String input){
       
        String[] criminals = { "/", "\n", "\r", "\t", "\0", "\f", "`", "?", "*", "<", ">", "|", ":", String.valueOf((char)34)};
        
        if(input.length() == 0) {
            
            System.out.print("\nThe file name needs at least one character.\n");
            
            return false;
        }
        for(int index = 0; index < criminals.length; index++) {
           
            if (input.contains(criminals[index])){
                
                System.out.print("\nThe file name contains illegal characters.\n");
                
                return false;
            }
        }
        return true;
    }
    protected static String   AmassListInfo() {
        
        String ListInfo = "";                                           
        
        for(int index = 0; index < List.size(); index++){
           
            ListInfo += List.get(index).AmassTaskInfo();
        }
        return ListInfo;
    }
    protected static void     SaveTaskList(String info, String FileName) {
       
        try {
            
            FileWriter writer = new FileWriter(FileName);
           
            writer.write(info);
            
            writer.close();
      
        } catch (IOException e) {
         
            e.printStackTrace();
        }
    }
}
