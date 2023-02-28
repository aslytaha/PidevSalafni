package Services;

import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Entities.LoanProject;

import java.util.List;

public interface Iloan {
    LoanProject addLoan(LoanProject loanproject, Long Idprojet);

    LoanProject updateproject(LoanProject e);

    List<LoanProject> getAllLoanProjects();

    LoanProject getLoanProjectById(Long id);

    LoanProject saveLoanProject(LoanProject loanProject);

    void deleteLoanProjectById(Long id);

    DetailsLoans getDetailsLoansById(Integer id);

    DetailsLoans saveDetailsLoans(DetailsLoans detailsLoans);

    void deleteDetailsLoansById(Integer id);

    DetailsLoans updateDetails(DetailsLoans d);
}
