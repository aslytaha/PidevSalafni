package Controllers;

import Services.LoanProjectServiceImpl;
import com.example.pidev.Entities.LoanProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanProjectController {

     @Autowired
     private LoanProjectServiceImpl loanProjectService;

    @GetMapping("/")
    public List<LoanProject> getAllLoanProjects() {
        return loanProjectService.getAllLoanProjects();
    }
//
//    @GetMapping("/{id}")
//    public LoanProject getLoanProjectById(@PathVariable Long id) {
//        return loanProjectService.getLoanProjectById(id);
//    }
//
//    @PostMapping("/")
//    public LoanProject saveLoanProject(@RequestBody LoanProject loanProject) {
//        return loanProjectService.saveLoanProject(loanProject);
//    }
}

