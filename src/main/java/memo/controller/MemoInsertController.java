package memo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import memo.dto.MemoDTO;
import memo.service.MemoService;

@WebServlet("/memo/insert")
public class MemoInsertController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String jsonInput = reader.lines().collect(Collectors.joining());

        String memoContent = null;     
        Long bookmarkId = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonInput);
            memoContent = jsonObject.getString("memo");
            bookmarkId = jsonObject.getLong("bookmarkId");           
        } catch (JSONException e) {
            throw new IllegalArgumentException("Invalid JSON input: " + e.getMessage());
        }

        MemoService.addMemo(bookmarkId, memoContent);
        List<MemoDTO> memos = MemoService.findMemosByBookmarkId(bookmarkId);

        JSONArray memosJsonArray = new JSONArray();
        for (MemoDTO memo : memos) {
            JSONObject memoJson = new JSONObject();
            memoJson.put("memoId", memo.getMemoId());
            memoJson.put("comment", memo.getComment());
            memoJson.put("formattedCreatedDate", memo.getFormattedCreatedDate());
            memosJsonArray.put(memoJson);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(memosJsonArray.toString());
    }

}
