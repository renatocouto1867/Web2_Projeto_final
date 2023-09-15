package br.edu.ifto.aula24082023.model.repository;

import br.edu.ifto.aula24082023.model.entity.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PacienteRepository {
    @PersistenceContext
    private EntityManager em;

    public void save (Paciente paciente){
        em.persist(paciente);
    }//save

    public Paciente paciente (Long id){
        return em.find(Paciente.class, id);
    } //paciente

    public List pacientes (){
        Query query = em.createQuery("from Paciente ");
        return query.getResultList();
    }

    public void remove (Long id){
        Paciente paciente = em.find(Paciente.class, id);
        em.remove(paciente);
    }//remove

    public void update(Paciente paciente){
        em.merge(paciente);
    }//update

}
