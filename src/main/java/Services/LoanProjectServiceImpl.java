package Services;


import com.example.pidev.Entities.LoanProject;
import com.example.pidev.Repositories.LoanProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class LoanProjectServiceImpl implements Iloan {

    @Autowired
    private LoanProjectRepository loanProjectRepository;


//    @Override
//    public LoanProject addLoan(LoanProject loanproject, Long Idprojet) {
//        LoanProject l= LoanProjectRepository.save(loanproject);
//        return  LoanProjectRepository.save(l);
//
//    }

//    @Override
//    public LoanProject updateproject(LoanProject e) {
//        return loanProjectRepository.save(e);
//    }
    @Override
    public List<LoanProject> getAllLoanProjects() {
        return loanProjectRepository.findAll();
    }

    @Override
    public LoanProject getLoanProjectById(Long id) {
        return loanProjectRepository.findById(id).orElse(null);
    }

//    @Override
//    public LoanProject saveLoanProject(LoanProject loanProject) {
//        return loanProjectRepository.save(loanProject);
//    }
//
//    @Override
//    public void deleteLoanProjectById(Long id) {
//        loanProjectRepository.deleteById(id);
//    }
}




