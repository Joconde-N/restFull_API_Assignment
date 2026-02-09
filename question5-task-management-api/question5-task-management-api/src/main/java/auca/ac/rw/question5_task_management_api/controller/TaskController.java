package auca.ac.rw.question5_task_management_api.controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import auca.ac.rw.question5_task_management_api.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long nextId = 4L;

    public TaskController(){
        tasks.add(new Task(1L, "Finish assignment", "Complete all endpoints", false, "HIGH", "2026-02-10"));
        tasks.add(new Task(2L, "Test in Postman", "Take screenshots or export collection", false, "MEDIUM", "2026-02-09"));
        tasks.add(new Task(3L, "Push to GitLab", "Create branch and push", false, "LOW", "2026-02-08"));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId){
        for(Task t : tasks){
            if(t.getTaskId().equals(taskId)){
                return ResponseEntity.ok(t);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/status")
    public ResponseEntity<List<Task>> getByStatus(@RequestParam boolean completed){
        List<Task> result = new ArrayList<>();
        for (Task t : tasks){
            if (t.isCompleted() == completed){
                result.add(t);
            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getByPriority(@PathVariable String priority){
        if (!priority.equalsIgnoreCase("LOW")
                && !priority.equalsIgnoreCase("MEDIUM")
                && !priority.equalsIgnoreCase("HIGH")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<Task> result = new ArrayList<>();
        for (Task t : tasks){
            if (t.getPriority() != null && t.getPriority().equalsIgnoreCase(priority)){
                result.add(t);
            }
        }
        return ResponseEntity.ok(result);
    }

    

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        if (!task.getPriority().equalsIgnoreCase("LOW")
                && !task.getPriority().equalsIgnoreCase("MEDIUM")
                && !task.getPriority().equalsIgnoreCase("HIGH")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (task.getDueDate() == null || !task.getDueDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        task.setTaskId(nextId++);
        task.setCompleted(false);
        tasks.add(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(task);

    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updated){
        if (!updated.getPriority().equalsIgnoreCase("LOW")
                && !updated.getPriority().equalsIgnoreCase("MEDIUM")
                && !updated.getPriority().equalsIgnoreCase("HIGH")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (updated.getDueDate() == null || !updated.getDueDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                t.setTitle(updated.getTitle());
                t.setDescription(updated.getDescription());
                t.setPriority(updated.getPriority());
                t.setDueDate(updated.getDueDate());
                t.setCompleted(updated.isCompleted());
                return ResponseEntity.ok(t);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markCompleted(@PathVariable Long taskId) {
        for (Task t : tasks) {
            if (t.getTaskId().equals(taskId)) {
                t.setCompleted(true);
                return ResponseEntity.ok(t);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId().equals(taskId)) {
                tasks.remove(i);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    
}
