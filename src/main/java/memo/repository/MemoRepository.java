package memo.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import memo.entity.Memo;

public class MemoRepository {

	public static Memo findById(Long id, EntityManager em) {
		return em.find(Memo.class, id);
	}

	public static List<Memo> findAll(EntityManager em) {
		return em.createQuery("SELECT m FROM Memo m", Memo.class).getResultList();
	}
	
	public static List<Memo> findRecentMemos(EntityManager em) {
        return em.createQuery("SELECT m FROM Memo m ORDER BY m.createdDate DESC", Memo.class)
                 .getResultList();
    }
	
	public static List<Memo> findOldestMemos(EntityManager em) {
        return em.createQuery("SELECT m FROM Memo m ORDER BY m.createdDate ASC", Memo.class)
                 .getResultList();
    }

	public static void saveMemo(Memo memo, EntityManager em) {
		em.persist(memo);
	}

	public static void updateMemo(Long memoId, String newComment, EntityManager em) {
		Memo memo = findById(memoId, em);
		if (memo != null) {
			memo.updateComment(newComment);
			em.merge(memo);
		}
	}

	public static void deleteMemo(Long id, EntityManager em) {
        Memo memo = findById(id, em);
        if (memo != null) {
            em.remove(memo);
        }
    }
}
