package br.edu.ifto.aula24082023.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
@Entity
public class Paciente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    private String nome;
    private String telefone;
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultaList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Consulta> getConsultaList() {
        return consultaList;
    }

    public void setConsultaList(List<Consulta> consultaList) {
        this.consultaList = consultaList;
    }

    public double valorTotalConsulta(){
        Double valor =consultaList.stream().mapToDouble(Consulta::getValor).sum();
        if (valor.isNaN()) return 0.0;
        return  valor;
    }

}
