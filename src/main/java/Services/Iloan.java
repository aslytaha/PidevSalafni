package Services;

import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.LoanProject;

import java.util.List;

public interface Iloan {

    //    @Override
    //    public LoanProject updateproject(LoanProject e) {
    //        return loanProjectRepository.save(e);
    //    }
    List<LoanProject> getAllLoanProjects();

    LoanProject getLoanProjectById(Long id);
}
