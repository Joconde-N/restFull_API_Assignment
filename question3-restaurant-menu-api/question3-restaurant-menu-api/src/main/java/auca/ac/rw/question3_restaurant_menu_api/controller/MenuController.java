package auca.ac.rw.question3_restaurant_menu_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import auca.ac.rw.question3_restaurant_menu_api.model.MenuItem;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private List<MenuItem> menu = new ArrayList<>();
    private Long nextId = 9L;

    public MenuController(){
        menu.add(new MenuItem(1L, "Bruschetta", "Tomato, basil toast", 7500.0, "Appetizer",true ));
        menu.add(new MenuItem(2L, "Spring Rolls", "Crispy veggie wrap", 8500.0, "Appetizer",true ));
        menu.add(new MenuItem(3L, "Grilled Salmon", "Smoky tender fish", 22500.0, "Main Course",true ));
        menu.add(new MenuItem(4L, "Beef Stew", "Rich beef broth", 20000.0, "Main Course",false ));
        menu.add(new MenuItem(5L, "Cheesecake", "Creamy crumb crust ", 10000.0, "Dessert",true ));
        menu.add(new MenuItem(6L, "Tiramisu", "Coffee cocoa layers", 11000.0, "Dessert",false ));
        menu.add(new MenuItem(7L, "Lemonade", "Sweet citrus drink", 5000.0, "Beverage",true ));
        menu.add(new MenuItem(8L, "Iced Tea", "Cool lemon tea", 5000.0, "Beverage",false ));
    }

    @GetMapping
    public List<MenuItem> getAllMenuItems(){
        return menu;
    }

    @GetMapping("/{id}")
    public MenuItem getMenuItem(@PathVariable Long id){
        for(MenuItem item : menu){
            if (item.getId().equals(id)){
                return item;
            }
        }
        return null;
    }

    @GetMapping("/category/{category}")
    public List<MenuItem> getByCategory(@PathVariable String category){
        List<MenuItem> result = new ArrayList<>();
        for(MenuItem item : menu){
            if(item.getCategory().equalsIgnoreCase(category)){
                result.add(item);

            }
        }
        return result;
    }

    @GetMapping("/available")
    public List<MenuItem> getAvailable(@RequestParam boolean available){
        List<MenuItem> result =new ArrayList<>();
        for(MenuItem item : menu){
            if(item.isAvailable() == available){
                result.add(item);
            }
        }
        return result;
    }

    @GetMapping("/search")
    public List<MenuItem> getByName(@RequestParam String name){
        List<MenuItem> result = new ArrayList<>();
        for(MenuItem item : menu){
            if(item.getName().toLowerCase().contains(name.toLowerCase())){
                result.add(item);
            }
        }
        return result;
    }

    @PostMapping
    public MenuItem addMenuItem(@RequestBody MenuItem item){
        item.setId(nextId++);
        menu.add(item);
        return item;
    }

    @PutMapping("/{id}/availability")
    public MenuItem toggleAvailability(@PathVariable Long id){
        for(MenuItem item : menu){
            if(item.getId().equals(id)){
                item.setAvailable(!item.isAvailable());
                return item;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id){
        for (int i =0; i < menu.size(); i++){
            if(menu.get(i).getId().equals(id)){
                menu.remove(i);
                break;
            }
        }
    }    


    
}
