package br.edu.ifto.aula24082023.model.repository;

import br.edu.ifto.aula24082023.model.entity.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicoRepository {
    @PersistenceContext
    private EntityManager em;

    public void save (Medico medico){
        em.persist(medico);
    }//save

    public Medico medico (Long id){
        return em.find(Medico.class, id);
    } //medico

    public List medicos (){
        Query query = em.createQuery("from Medico");
        return query.getResultList();
    }

    public void remove (Long id){
        Medico medico = em.find(Medico.class, id);
        em.remove(medico);
    }//remove

    public void update(Medico medico){
        em.merge(medico);
    }//update
}
