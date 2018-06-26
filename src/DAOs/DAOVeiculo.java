package DAOs;


import Entidades.Veiculo;
import daos.DAOGenerico;
import static daos.DAOGenerico.em;
import java.util.ArrayList;
import java.util.List;

public class DAOVeiculo extends DAOGenerico<Veiculo> {

    public DAOVeiculo() {
        super(Veiculo.class);
    }

    public int autoIdVeiculo() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idVeiculo) FROM Veiculo e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Veiculo> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Veiculo e WHERE e.nomeVeiculo LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Veiculo> listById(int id) {
        return em.createQuery("SELECT e FROM Veiculo e WHERE e.idVeiculo = :id").setParameter("id", id).getResultList();
    }

    public List<Veiculo> listInOrderNome() {
        return em.createQuery("SELECT e FROM Veiculo e ORDER BY e.nomeVeiculo").getResultList();
    }

    public List<Veiculo> listInOrderId() {
        return em.createQuery("SELECT e FROM Veiculo e ORDER BY e.idVeiculo").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Veiculo> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdVeiculo() + "-" + lf.get(i).getPlacaVeiculo());
        }
        return ls;
    }
}
