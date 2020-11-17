import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void
    
        newTaskItem() {
        
        TaskItem Task = new TaskItem("title","description","YYYY-MM-DD", false);
       
        assertEquals("title",Task.title);
       
        assertEquals("description",Task.description);
        
        assertEquals("YYYY-MM-DD",Task.due_date);
       
        assertFalse(Task.complete);
    }   
    @Test
    public void
   inputTaskItemInvalidDueDate() {
       
        assertEquals(210,TaskItem.Due_DateHandler("1776-02*300"));
       
        assertFalse(TaskItem.Due_DateReport(210));
    }   
    @Test
    public void
    inputTaskItemInvalidDueDateProtectionTriggered() {
        
        assertEquals(11,TaskItem.Due_DateHandler("0"));
       
        assertFalse(TaskItem.Due_DateReport(11));
    }   
    @Test public void
    inputTaskItemValidDueDate() {
       
        assertEquals(1,TaskItem.Due_DateHandler("9999-12-25"));
    }   
    @Test public void
    invalidDueDateReport() {
        assertFalse(TaskItem.Due_DateReport(15));
    }  
    @Test public void
    validDueDateReport() {
        assertTrue(TaskItem.Due_DateReport(1));
    }   
    @Test public void
    inputTaskItemInvalidTitle() {
        assertFalse(TaskItem.TitleHandler(""));
    }   
    @Test public void
    inputTaskItemValidTitle() {
        assertTrue(TaskItem.TitleHandler("Title"));
    }  
    @Test public void
    isolateUnitInvalid() {
        assertEquals(0,TaskItem.IsolateUnit(0,3,"Not-A-Date"));
    }   
    @Test public void
    isolateUnitValid() {
        assertEquals(0,TaskItem.IsolateUnit(2020,3,"2020-10-20"));
    }   
    @Test public void
    amassTaskInfoTest() {
       
        TaskList.ClearTaskList();
       
        TaskList.addTask("title","description","YYYY-MM-DD");
        
        String expectedString = "title\ndescription\nYYYY-MM-DD\nfalse\n";
      
        assertEquals(expectedString,TaskList.List.get(0).AmassTaskInfo());
    }   
    @Test public void
    addingTaskItem() {
       
        TaskList.ClearTaskList();
        
        int before = TaskList.List.size();
       
        TaskList.addTask("title","description","YYYY-MM-DD");
        
        int after = TaskList.List.size();
        
        assertEquals(before + 1, after);
    }   
    @Test public void
    printableFullListTest() {
       
        TaskList.ClearTaskList();
        
        TaskList.addTask("title","description","YYYY-MM-DD");
        
        String expectedString = "\n\n\n\n\nCurrent Tasks\n-------------\n\n1) Incomplete [YYYY-MM-DD] title: description\n";
        
        assertEquals(expectedString,TaskList.PrintableList());
    }   
    @Test public void
    clearingListTest() {
        
        TaskList.ClearTaskList();
        
        TaskList.addTask("title","description","YYYY-MM-DD");
       
        TaskList.ClearTaskList();
        
        assertEquals(0, TaskList.List.size());
    }   //Verifies task list clearing.
    @Test public void
    completingIndexKeyValidation() {
       
        TaskList.ClearTaskList();
        
        TaskList.addTask("title","description","YYYY-MM-DD");
       
        TaskList.editCompletionStatus(0,true);
        
        TaskList.addTask("title","description","YYYY-MM-DD");
       
        TaskList.addTask("title","description","YYYY-MM-DD");
        
        TaskList.addTask("title","description","YYYY-MM-DD");
        
        TaskList.editCompletionStatus(3,true);
        
        int[] key = TaskList.GenerateCompletionKey(true);
        
        assertEquals(0,key[0]);
        
        assertEquals(3,key[1]);
    }   
    @Test public void
    printablePartialListTest() {
       
        TaskList.ClearTaskList();
        
        TaskList.addTask("title","description","YYYY-MM-DD");
        
        TaskList.editCompletionStatus(0,true);
        
        TaskList.addTask("title","description","YYYY-MM-DD");
        
        String expectedString = "Current Complete Tasks\n----------------------\n" +
                "\n1) [YYYY-MM-DD] title: description\n\n\nSelect a task to mark complete: ";
        
        assertEquals(expectedString,TaskList.PartialTasks(true));
    }   
    @Test public void
    printablePartialListEmptyTest() {
       
        TaskList.ClearTaskList();
       
        TaskList.addTask("title","description","YYYY-MM-DD");
        
        TaskList.addTask("title","description","YYYY-MM-DD");
       
        String expectedString = "\n\nThere are no complete tasks.";
        
        assertEquals(expectedString,TaskList.PartialTasks(true));
    }  
    @Test public void
    validateTasksTestValid() {
        assertTrue(TaskList.ValidateTask("title", "2050-12-29"));
    }  
    @Test public void
    validateTasksTestInvalid() {
        assertFalse(TaskList.ValidateTask("", "2050-12-29"));
    }   
    @Test public void
    editingTaskTest() {
       
        TaskList.ClearTaskList();
       
        TaskList.addTask("title","description","YYYY-MM-DD");
        
        TaskList.editTask("NewTitle", "NewDescription", "2050-12-29",0);
        
        String expectedString = "NewTitle\nNewDescription\n2050-12-29\nfalse\n";
        
        assertEquals(expectedString,TaskList.List.get(0).AmassTaskInfo());
    }  
    @Test public void
    removingTaskTest() {
       
        TaskList.ClearTaskList();
        
        TaskList.addTask("title","description","YYYY-MM-DD");
        
        TaskList.removeTask(0);
        
        assertEquals(0,TaskList.List.size());
    }   
    @Test public void
    completingTaskItemChangesStatus() {
       
        TaskList.ClearTaskList();
       
        TaskList.addTask("title","description","YYYY-MM-DD");
       
        TaskList.editCompletionStatus(0,true);
        
        assertTrue(TaskList.List.get(0).complete);
    } 
    @Test public void
    invalidReadProtectionTest() {
        String FileName = "\\\2.2.2.2ThisIsNotPossible";
        assertFalse(TaskList.ReadProtection(FileName));
    }   
    @Test public void
    validReadProtectionTest() {
       
        String FileName = "Test.txt";
       
        TaskList.CreateFile(FileName);
       
        assertTrue(TaskList.ReadProtection(FileName));
    }   
    @Test public void
    validReadFile() {
        
        String FileName = "Test.txt";
       
        TaskList.ClearTaskList();
       
        TaskList.addTask("title","description","YYYY-MM-DD");
       
        TaskList.CreateFile(FileName);
       
        assertNotNull(TaskList.ReadFile(FileName));
    }  
    @Test public void
    invalidReadFile() {
       
        String FileName = "Test.txt";
      
        File file = new File(FileName);
        
        file.delete();
       
        assertNull(TaskList.ReadFile(FileName));
    }   
    @Test public void
    loadFileTestValid() {
       
        String FileName = "Test.txt";
       
        TaskList.ClearTaskList();
       
        TaskList.addTask("title","description","2050-12-15");
        
        TaskList.CreateFile(FileName);
       
        TaskList.SaveTaskList(TaskList.AmassListInfo(), FileName);
        
        TaskList.ClearTaskList();
        
        assertTrue(TaskList.LoadFile(TaskList.ReadFile(FileName)));
    }   
    @Test public void
    loadFileTestInvalid() {
        
        String FileName = "Test.txt";
       
        TaskList.ClearTaskList();
       
        TaskList.addTask("","description","2000-12-15");
       
        TaskList.CreateFile(FileName);
       
        TaskList.SaveTaskList(TaskList.AmassListInfo(), FileName);
        
        TaskList.ClearTaskList();
        
        assertFalse(TaskList.LoadFile(TaskList.ReadFile(FileName)));
    }   

    @Test public void
    createFileTest() {
       
        String FileName = "Test.txt";
       
        TaskList.ClearTaskList();
      
        TaskList.addTask("title","description","2050-12-15");
        
        TaskList.CreateFile(FileName);
        
        assertTrue(TaskList.LoadFile(TaskList.ReadFile(FileName)));
    }  
    @Test public void
    inputInvalidFileName() {
        assertFalse(TaskList.ValidateFileName("Test/!.txt"));
    }  
    @Test public void
    inputValidFileName() {
        assertTrue(TaskList.ValidateFileName("Valid Title.txt"));
    }   
    @Test public void
    amassListInfoTest() {
       
        TaskList.ClearTaskList();
        
        TaskList.addTask("title","description","YYYY-MM-DD");
        
        TaskList.addTask("title","description","YYYY-MM-DD");
        
        String expectedString = "title\ndescription\nYYYY-MM-DD\nfalse\n" +
                "title\ndescription\nYYYY-MM-DD\nfalse\n";
        
        assertEquals(expectedString,TaskList.AmassListInfo());
    }   
    @Test public void
    saveFileTest() {
       
        TaskList.ClearTaskList();
       
        TaskList.addTask("title","description","2090-09-02");
       
        TaskList.addTask("title","description","3030-01-06");
        
        String expectedString = "title\ndescription\n2090-09-02\nfalse\n" +
                "title\ndescription\n3030-01-06\nfalse\n";
        
        String FileName = "Test.txt";
        
        TaskList.CreateFile(FileName);
        
        TaskList.SaveTaskList(TaskList.AmassListInfo(), FileName);
        
        TaskList.ClearTaskList();
        
        TaskList.LoadFile(TaskList.ReadFile(FileName));
       
        assertEquals(expectedString,TaskList.AmassListInfo());
    }   
    @Test public void
    inputIsValidMenuSelection() {
        assertEquals(1,App.MenuHandler(1,2));
    }   
    @Test public void
    inputIsInvalidMenuSelection() {
        assertEquals(-1,App.MenuHandler(7,2));
    }   
    @Test public void
    inputIsNotNumber() {
        assertEquals(0,App.StringToInt("1A"));
    }  
    @Test public void
    inputIsNumber() {
        assertEquals(7,App.StringToInt("000000007"));
    }   
}
