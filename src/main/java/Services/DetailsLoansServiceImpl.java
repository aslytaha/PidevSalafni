package Services;


import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Repositories.DetailsLoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class DetailsLoansServiceImpl implements Iloan{

    @Autowired
    private DetailsLoansRepository detailsLoansRepository;


    public List<DetailsLoans> getAllDetailsLoans() {
        return detailsLoansRepository.findAll();
    }

    @Override
    public DetailsLoans getDetailsLoansById(Integer id) {
        return detailsLoansRepository.findById(id).orElse(null);
    }

    @Override
    public DetailsLoans saveDetailsLoans(DetailsLoans detailsLoans) {
        return detailsLoansRepository.save(detailsLoans);
    }

    @Override
    public void deleteDetailsLoansById(Integer id) {
        detailsLoansRepository.deleteById(id);
    }
    @Override
    public DetailsLoans updateDetails(DetailsLoans d) {
        return detailsLoansRepository.save(d);
    }























}
