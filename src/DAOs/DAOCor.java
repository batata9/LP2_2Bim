package DAOs;


import Entidades.Cor;
import daos.DAOGenerico;
import static daos.DAOGenerico.em;
import java.util.ArrayList;
import java.util.List;

public class DAOCor extends DAOGenerico<Cor> {

    public DAOCor() {
        super(Cor.class);
    }

    public int autoIdCor() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idCor) FROM Cor e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Cor> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Cor e WHERE e.nomeCor LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Cor> listById(int id) {
        return em.createQuery("SELECT e FROM Cor e WHERE e.idCor = :id").setParameter("id", id).getResultList();
    }

    public List<Cor> listInOrderNome() {
        return em.createQuery("SELECT e FROM Cor e ORDER BY e.nomeCor").getResultList();
    }

    public List<Cor> listInOrderId() {
        return em.createQuery("SELECT e FROM Cor e ORDER BY e.idCor").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Cor> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdCor() + "-" + lf.get(i).getNome());
        }
        return ls;
    }
}
