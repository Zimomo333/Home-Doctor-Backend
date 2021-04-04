package com.hk.server.Listener;

import com.hk.server.utils.FileSave;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @program: gg2020
 * @description: session监听器
 * @author: 头微凉
 * @create: 2020-10-07 15:00
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    /**
     * Description: 主要用于文章没有提交的session清理，把没有提交的文章图片删了
     *
     * @param se:触发事件
     * @return void
     * @author 头微凉
     * 创建时间: 2020/10/7
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("清除session");
        HttpSession session = se.getSession();
        String articleId = (String) session.getAttribute("articleId");
        if (articleId != null)
            FileSave.CleanPicture("article/" + articleId, null);
    }
}