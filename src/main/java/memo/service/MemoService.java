package memo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import bookmark.entity.Bookmark;
import bookmark.repository.BookmarkRepository;
import common.util.DbUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import memo.dto.MemoDTO;
import memo.entity.Memo;
import memo.repository.MemoRepository;

public class MemoService {

	public static List<MemoDTO> findMemosByBookmarkId(Long bookmarkId) {
		EntityManager em = DbUtil.getEntityManager();
		List<Memo> memos = null;
		List<MemoDTO> memoDtos = null;

		try {
			memos = MemoRepository.findMemosByBookmarkId(bookmarkId, em);
			if (memos.isEmpty()) {
				throw new IllegalArgumentException("메모 조회 실패: 해당 ID의 북마크에 메모가 없습니다.");
			}
			memoDtos = memos.stream().map(memo -> memo.toDTO()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new IllegalArgumentException("메모 조회 실패: " + e.getMessage());
		} finally {
			em.close();
		}

		return memoDtos;
	}

	public static void addMemo(Long bookmarkId, String memoContent) {
		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Bookmark bookmark = BookmarkRepository.findById(bookmarkId, em);
			MemoRepository.saveMemo(
					Memo.builder().comment(memoContent).createdDate(LocalDateTime.now()).bookmark(bookmark).build(),
					em);
			tx.commit();
		} catch (Exception e) {
			throw new IllegalArgumentException("메모 추가 실패: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	public static void updateMemo(Long memoId, String newComment) {
		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Memo findMemo = MemoRepository.findById(memoId, em);
			findMemo.updateComment(newComment);
			MemoRepository.updateMemo(findMemo, em);
			tx.commit();
		} catch (NoResultException e) {
			tx.rollback();
			throw new IllegalArgumentException("메모 조회 실패: 해당 ID의 북마크에 메모가 없습니다.");
		} catch (Exception e) {
			throw new IllegalArgumentException("메모 수정 실패: " + e.getMessage());
		}

		finally {
			em.close();
		}
	}

	public static void deleteMemo(Long memoId) {
		EntityManager em = DbUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Memo memo = MemoRepository.findById(memoId, em);			
			MemoRepository.deleteMemo(memo, em);
			tx.commit();
		} catch (NoResultException e) {
			tx.rollback();
			throw new IllegalArgumentException("메모 조회 실패: 해당 ID의 북마크에 메모가 없습니다.");
		} catch (Exception e) {
			throw new IllegalArgumentException("메모 삭제 실패: " + e.getMessage());
		} finally {
			em.close();
		}
	}
}
