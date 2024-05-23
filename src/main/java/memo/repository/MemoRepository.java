package memo.repository;

import java.util.List;

import common.util.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import memo.entity.Memo;

public class MemoRepository {

	public static Memo findById(Long id) {
		EntityManager em = DbUtil.getEntityManager();
		try {
			return em.find(Memo.class, id);
		} finally {
			em.close();
		}
	}

	public static List<Memo> findAll() {
		EntityManager em = DbUtil.getEntityManager();
		try {
			return em.createQuery("SELECT m FROM Memo m", Memo.class).getResultList();
		} finally {
			em.close();
		}
	}

	public static boolean saveMemo(Memo memo) {
		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();			
			em.persist(memo);			
			tx.commit();		
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return false;
	}

	public static boolean updateMemo(Long memoId, String newComment) {
        EntityManager em = DbUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Memo memo = em.find(Memo.class, memoId);
            if (memo != null) {
                memo.updateComment(newComment);
                em.merge(memo);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
        	tx.rollback();
        	e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

	public static boolean deleteMemo(Long id) {
		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Memo memo = em.find(Memo.class, id);
			if (memo != null) {
				em.remove(memo);				
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return false;
	}
}
