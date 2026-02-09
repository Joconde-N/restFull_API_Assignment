package auca.ac.rw.question6_user_profile_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.question6_user_profile_api.model.ApiResponse;
import auca.ac.rw.question6_user_profile_api.model.UserProfile;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> users = new ArrayList<>();
    private Long nextId = 4L;

    public UserProfileController(){
        users.add(new UserProfile(1L, "JocP", "joc@gmail.com", "Joconde Ngoga", 22, "Rwanda", "Student", true));
        users.add(new UserProfile(2L, "aline", "aline@gmail.com", "Aline Karlie", 19, "Rwanda", "CS lover", true));
        users.add(new UserProfile(3L, "kevinIsh", "kevin@gmail.com", "Kevin Ishimwe", 25, "Kenya", "Backend dev", false));

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "All user profiles", users)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> getById(@PathVariable Long id) {
        for (UserProfile u : users) {
            if (u.getUserId().equals(id)) {
                return ResponseEntity.ok(
                        new ApiResponse<>(true, "User found", u)
                );
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> create(@RequestBody UserProfile user) {
        user.setUserId(nextId++);
        users.add(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User profile created successfully", user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> update(
            @PathVariable Long id,
            @RequestBody UserProfile updated
    ) {
        for (UserProfile u : users) {
            if (u.getUserId().equals(id)) {
                u.setUsername(updated.getUsername());
                u.setEmail(updated.getEmail());
                u.setFullName(updated.getFullName());
                u.setAge(updated.getAge());
                u.setCountry(updated.getCountry());
                u.setBio(updated.getBio());
                u.setActive(updated.isActive());

                return ResponseEntity.ok(
                        new ApiResponse<>(true, "User updated successfully", u)
                );
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(id)) {
                users.remove(i);
                return ResponseEntity.ok(
                        new ApiResponse<>(true, "User deleted successfully", null)
                );
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<UserProfile>>> search(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge
    ) {
        List<UserProfile> result = new ArrayList<>();

        for (UserProfile u : users) {
            boolean match = true;

            if (username != null && !u.getUsername().toLowerCase().contains(username.toLowerCase())) {
                match = false;
            }

            if (country != null && !u.getCountry().equalsIgnoreCase(country)) {
                match = false;
            }

            if (minAge != null && u.getAge() < minAge) {
                match = false;
            }

            if (maxAge != null && u.getAge() > maxAge) {
                match = false;
            }

            if (match) {
                result.add(u);
            }
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Search results", result)
        );
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activate(@PathVariable Long id) {
        for (UserProfile u : users) {
            if (u.getUserId().equals(id)) {
                u.setActive(true);
                return ResponseEntity.ok(
                        new ApiResponse<>(true, "User activated", u)
                );
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivate(@PathVariable Long id) {
        for (UserProfile u : users) {
            if (u.getUserId().equals(id)) {
                u.setActive(false);
                return ResponseEntity.ok(
                        new ApiResponse<>(true, "User deactivated", u)
                );
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }
    
}
