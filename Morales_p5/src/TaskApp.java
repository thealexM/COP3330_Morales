class TaskApp extends App {

    @Override

    void createList(){
        TaskList.newList();
    }

    @Override

    int specifyItem(String condition, String action, int length) {

        int selection;

        do {

            print(TaskList.ListInfo(condition));

            print("Which item would you like to " + action + "?\n> ");


            selection = getSelection(in.nextLine(), length);

        } while(selection==0);

        return selection;
    }

    @Override

    void ManageList() {

        String choice = "0";

        while (!choice.equals("8")) {

            int selection;

            print("\n\nList Operation Menu\n" +
                    "-------------------\n\n" +
                    "1) view the list\n" +
                    "2) add a item\n" +
                    "3) edit a item\n" +
                    "4) remove a item\n" +
                    "5) mark a item complete\n" +
                    "6) mark a item incomplete\n" +
                    "7) save the current list\n" +
                    "8) exit to the main menu\n> ");
            choice = in.nextLine();

            if((TaskList.tasks.size()==0)&&!(choice.equals("2")||choice.equals("8"))){

                print("\n list is empty.\n");
            }
            else {

                if (choice.equals("1")) {

                    print(TaskList.ListInfo("All"));

                } else if (choice.equals("2")) {

                    TaskList.addTask("Null", "Null", "9999-09-09", false);

                    editItem(TaskList.tasks.size() - 1);

                } else if (choice.equals("3")) {

                    selection = specifyItem("All", "edit", TaskList.tasks.size());

                    editItem(selection-1);

                } else if (choice.equals("4")) {

                    selection = specifyItem("All", "remove", TaskList.tasks.size());

                    TaskList.removeTask(selection-1);

                } else if (choice.equals("5")) {

                    int[] key = TaskList.getIncompleteKey("Incomplete");

                    if(key.length>0) {

                        selection = specifyItem("Incomplete", "mark as complete", key.length);

                        TaskList.editComplete(key[selection - 1]);

                    } else print("\nNo tasks meeting that criteria were found.");

                } else if (choice.equals("6")) {

                    int[] key = TaskList.getIncompleteKey("Complete");

                    if(key.length>0) {

                        selection = specifyItem("Complete", "mark as incomplete", key.length);

                        TaskList.editComplete(key[selection - 1]);

                    } else print("\nNo tasks meeting that criteria were found.");

                } else if (choice.equals("7")) {

                    print(TaskList.SavePrompt());

                    String FileName;

                    do {

                        print("\nEnter a file name (no extension allowed): ");

                        FileName = in.nextLine() + TaskList.getExtension();

                    } while (!TaskList.ValidateFileName(FileName));

                    TaskList.CreateFile(FileName);

                    TaskList.saveList(TaskList.AmassListInfo(), FileName);

                } else if (!choice.equals("8")) {

                    print("\nThat isn't an option.\n");
                }
            }
        }
        TaskList.newList();
    }


    @Override

    boolean loadList() {

        String FileName;

        do {

            do{

                print("\nEnter file name (no extension allowed): ");

                FileName = in.nextLine() + TaskList.getExtension();

            }while (!TaskList.ValidateFileName(FileName));

        } while (!TaskList.fileExists(FileName));

        if(!TaskList.loadList(FileName)){

            print("\nThe file you specified isn't a valid task list.\n");

            return false;
        }
        return true;
    }

    private void editItem(int index) {

        String title, description, due_date;

        boolean done;

        do {

            print("\nTask title: ");

            title = in.nextLine();

            print("\nTask description: ");

            description = in.nextLine();

            print("\nTask due date (YYYY-MM-DD): ");

            due_date = in.nextLine();

            done = TaskList.ValidateTask(title, due_date);

            if(!done) print("\nYour entry doesn't meet the stated criteria.\n");

        } while (!done);

        TaskList.editTitle(index, title);

        TaskList.editDescription(index, description);

        TaskList.editDue_Date(index, due_date);
    }
}