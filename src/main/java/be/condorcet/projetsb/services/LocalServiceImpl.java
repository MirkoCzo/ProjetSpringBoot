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
public class LocalServiceImpl implements InterfService<Local>{

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
        read(local.getId_local());
        localRepository.save(local);
        return local;
    }

    @Override
    public void delete(Local local) throws Exception {
        localRepository.deleteById(local.getId_local());
    }

    @Override
    public List<Local> all() throws Exception {
        return localRepository.findAll();
    }
}
