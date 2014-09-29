package com.appspot.wwwwbrownbag;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {
    /* ブラウザからのGETリクエストを処理する */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /* /WEB-INF/index.jspを呼び出す */
        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/WEB-INF/index.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /* 入力項目を取得する */
        String when = req.getParameter("when");
        String where = req.getParameter("where");
        String who = req.getParameter("who");
        String what = req.getParameter("what");
        String sentence = SentenceHelper.makeSentence(when, where, who, what);
        FlickrHelper helper = FlickrHelper.newHelper(when);
        String pageUrl = "";
        String imageUrl = "";
        /* Flickr APIに接続できたときだけ、写真の情報を登録する */
        if (helper != null) {
            pageUrl = helper.getPageUrl();
            imageUrl = helper.getImageUrl();
        }
        req.setAttribute("sentence", sentence);
        req.setAttribute("imageUrl1", imageUrl);
        req.setAttribute("pageUrl1", pageUrl);
        
        helper = FlickrHelper.newHelper(where);
        /* Flickr APIに接続できたときだけ、写真の情報を登録する */
        if (helper != null) {
            pageUrl = helper.getPageUrl();
            imageUrl = helper.getImageUrl();
        }
        req.setAttribute("imageUrl2", imageUrl);
        req.setAttribute("pageUrl2", pageUrl);

        helper = FlickrHelper.newHelper(who);
        /* Flickr APIに接続できたときだけ、写真の情報を登録する */
        if (helper != null) {
            pageUrl = helper.getPageUrl();
            imageUrl = helper.getImageUrl();
        }
        req.setAttribute("imageUrl3", imageUrl);
        req.setAttribute("pageUrl3", pageUrl);
        
        helper = FlickrHelper.newHelper(what);
        /* Flickr APIに接続できたときだけ、写真の情報を登録する */
        if (helper != null) {
            pageUrl = helper.getPageUrl();
            imageUrl = helper.getImageUrl();
        }
        req.setAttribute("imageUrl4", imageUrl);
        req.setAttribute("pageUrl4", pageUrl);
        
        /* 文章を保存して、JSPを呼び出す */
        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/WEB-INF/index_post.jsp");
        rd.forward(req, resp);
    }
}
