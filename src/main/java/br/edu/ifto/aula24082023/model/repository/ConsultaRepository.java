package br.edu.ifto.aula24082023.model.repository;
import br.edu.ifto.aula24082023.model.entity.Consulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ConsultaRepository {
    @PersistenceContext
    private EntityManager em;

    public void save (Consulta consulta){
        em.persist(consulta);
    }//save

    public Consulta consulta(Long id){
        return em.find(Consulta.class, id);
    } //consulta

    public List consultas(){
        Query query = em.createQuery("from Consulta");
        return query.getResultList();
    }//consultas

    public Double valorTotal(){
        Query query = em.createQuery("select sum(valor) from Consulta");
        List total;
        total= query.getResultList();
        return (Double) total.get(0);
    }//consultas

    public void remove (Long id){
        Consulta consulta = em.find(Consulta.class, id);
        em.remove(consulta);
    }//remove

    public void update(Consulta consulta){
        em.merge(consulta);
    }//update

}
