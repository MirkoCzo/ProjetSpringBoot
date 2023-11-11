package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Local;
import be.condorcet.projetsb.repositories.LocalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class LocalServiceImpl implements InterfLocalService {

    @Autowired
    private LocalRepository localRepository;
    @Override
    public Local create(Local local) throws Exception {
        localRepository.save(local);
        return local;
    }

    @Override
    public Local read(Integer id) throws Exception {
        Optional<Local> ol = localRepository.findById(id);
        return ol.get();
    }

    @Override
    public Local update(Local local) throws Exception {
        read(local.getIdlocal());
        localRepository.save(local);
        return local;
    }

    @Override
    public void delete(Local local) throws Exception {
        localRepository.deleteById(local.getIdlocal());
    }

    @Override
    public List<Local> all() throws Exception {
        return localRepository.findAll();
    }

    @Override
    public List<Local> findAllByPlacesGreaterThanEqual(int places) {
        return localRepository.findAllByPlacesGreaterThanEqual(places);
    }

    @Override
    public Local findBySigle(String sigle)
    {
        return localRepository.findBySigle(sigle);
    }
}
