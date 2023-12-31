package be.condorcet.projetsb.services;

import be.condorcet.projetsb.modele.Infos;
import be.condorcet.projetsb.modele.InfosKey;
import be.condorcet.projetsb.repositories.InfosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InfosServiceImpl implements InterfInfosService{

    @Autowired
    private InfosRepository infosRepository;
    @Override
    public Infos create(Infos infos) throws Exception {
        infosRepository.save(infos);
        return infos;
    }

    @Override
    public Infos read(Integer id) throws Exception {
        return null;
    }

    @Override
    public Infos read(InfosKey id) throws Exception {
        Optional<Infos> oi = infosRepository.findById(id);
        return oi.get();
    }

    @Override
    public List<Infos> findAllByNhIsGreaterThanEqual(int nh) {
        return infosRepository.findAllByNhIsGreaterThanEqual(nh);
    }

    @Override
    public List<Infos> findAllByIdIdformateur(int idformateur) {
        return infosRepository.findAllByIdIdformateur(idformateur);
    }

    @Override
    public List<Infos> findAllByIdIdsessioncours(int idsessioncours) {
        return infosRepository.findAllByIdIdsessioncours(idsessioncours);
    }

    @Override
    public Infos update(Infos infos) throws Exception {
        read(infos.getId());
        infosRepository.save(infos);
        return infos;
    }

    @Override
    public void delete(Infos infos) throws Exception {
        infosRepository.delete(infos);
    }

    @Override
    public List<Infos> all() throws Exception {
        return infosRepository.findAll();
    }
}
