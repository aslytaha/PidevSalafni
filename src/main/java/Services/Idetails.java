package Services;

import com.example.pidev.Entities.DetailsLoans;

public interface Idetails {
    DetailsLoans getDetailsLoansById(Integer id);

    DetailsLoans saveDetailsLoans(DetailsLoans detailsLoans);
}
