package com.hk.server.controller;

import com.hk.server.dto.AllArticleDTO;
import com.hk.server.dto.AllAuditDTO;
import com.hk.server.entity.Healthyabout;
import com.hk.server.entity.Healthyabout1;
import com.hk.server.interceptor.TokenInterceptor;
import com.hk.server.service.ArticleService;
import com.hk.server.utils.FileSave;
import com.hk.server.utils.ResponseUtil;
import com.hk.server.utils.TokenUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @program: gg2020
 * @description: 管理员端有关文章的接口
 * @author: 头微凉
 * @create: 2020-09-25 09:59
 */
@RestController
@RequestMapping("admin/article")
@Api("有关管理员文章模块的CRUD接口")
public class AboutArticle {
    @Autowired
    ArticleService articleService;

    @PostMapping("uploadPicture")
    @ApiOperation("富文本上传图片，返回所上传图片的url地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "file", name = "file", value = "富文本上传过来的图片", required = true, dataType = "MultipartFile"),
            @ApiImplicitParam(paramType = "long", name = "id", value = "文章id，可选，有id则为修改文章上传的图片，没有为添加文章上传的图片", required = false, dataType = "Long")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "okk", response = ResponseUtil.class),
            @ApiResponse(code = 460, message = "上传失败")
    })
    public ResponseUtil uploadPicture(@RequestParam(value = "file", required = true) MultipartFile file,
                                      @RequestParam(value = "id", required = false) Long id, HttpSession session, HttpServletRequest request) {
        String articleId = null;
        if (id != null)
            articleId = String.valueOf(id);
        else
            articleId = (String) session.getAttribute("articleId");

        //如果没有articleId的话就创建，并设置过期时间为12小时
        if (articleId == null) {
            String token = request.getHeader("token");
            String account = TokenUtil.getAccount(token);
            String sessionValue = account + new Date().getTime() + (int) Math.random() * 1000;
            session.setAttribute("articleId", sessionValue);
            articleId = sessionValue;
            session.setMaxInactiveInterval(60 * 60 * 12);
        }

        String path = "article/";
        String parent = articleId + "/";
        path += parent;
        //一个文本编辑最多有效3个小时，3个小时内不保存就消失，所以模3小时的毫秒数再加随机个位数保证文件名唯一
        String fileName = String.valueOf(new Date().getTime() % 10800000 + (int) Math.random() * 10);
        if (!FileSave.saveFile(file, path + fileName + ".jpg"))
            return new ResponseUtil(460, "上传失败", null);
        return new ResponseUtil(200, "okk", "article/" + parent + fileName + ".jpg");
    }

    @PostMapping("submitArticle")
    @ApiOperation("富文本文章提交接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "String[]", name = "pictureNames", value = "富文本包含的图片名，注意是图片名，不是路径，为了性能考虑，后端返给前段图片URL后，最终前端提交前需要先正则表达式筛选出所有的图片名称，如果没有可以不传", required = false, dataType = "String[]"),
            @ApiImplicitParam(paramType = "String", name = "content", value = "富文本文章内容", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "String", name = "type", value = "文章类型", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "String", name = "title", value = "文章标题", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "文章提交成功", response = ResponseUtil.class),
            @ApiResponse(code = 460, message = "文章提交失败", response = ResponseUtil.class)
    })
    public ResponseUtil submitArticle(@RequestParam(value = "pictureNames", required = false) String[] pictureNames,
                                      @RequestParam(value = "content", required = true) String content,
                                      @RequestParam(value = "type", required = true) String type,
                                      @RequestParam(value = "title", required = true) String title,
                                      HttpServletRequest request, HttpSession session) {
        String token = request.getHeader("token");
        String adminId = TokenUtil.getAccount(token);
        //如果图片名称数组为空，说明是纯文本，没有图片，直接插入就行,让id自增就行
        String articleId = (String) session.getAttribute("articleId");
        if (pictureNames == null) {
            //调用FileSave工具类方法清除没在富文本中的文件，节约空间。
            if (articleId != null)
                FileSave.CleanPicture("article/" + articleId, null);
            Healthyabout article = new Healthyabout(null, content, type, Long.valueOf(adminId), null, title);
            article.setTime(String.valueOf(new Date().getTime()));
            System.out.println(article);
            if (articleService.insertOne(article) <= 0)
                return new ResponseUtil(460, "文章插入失败", null);
        }
        //如果图片名称数组非空，则肯定存在预先定义的session文章id
        else {
            HashSet<String> names = new HashSet<>();
            session.removeAttribute("articleId");
            for (int i = 0; i < pictureNames.length; i++) {
                names.add(pictureNames[i]);
            }
            //调用FileSave工具类方法清除没在富文本中的文件，节约空间。
            FileSave.CleanPicture("article/" + articleId, names);
            Healthyabout article = new Healthyabout(Long.valueOf(articleId), content, type, Long.valueOf(adminId), null, title);
            article.setTime(String.valueOf(new Date().getTime()));
            if (articleService.insertOne(article) <= 0)
                return new ResponseUtil(460, "文章插入失败", null);
        }
        return new ResponseUtil(200, "文章提交成功", null);
    }

    @PostMapping("update")
    @ApiOperation("修改文章接口，与添加文章接口使用类同")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "String[]", name = "pictures", value = "富文本包含的图片名，注意是图片名，不是路径，为了性能考虑，后端返给前段图片URL后，最终前端提交前需要先正则表达式筛选出所有的图片名称，如果没有可以不传", required = false, dataType = "String[]"),
            @ApiImplicitParam(paramType = "String", name = "content", value = "富文本文章内容", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "String", name = "type", value = "文章类型", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "Long", name = "type", value = "文章id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "String", name = "title", value = "文章标题", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "文章修改成功", response = ResponseUtil.class),
            @ApiResponse(code = 460, message = "文章修改失败", response = ResponseUtil.class)
    })
    public ResponseUtil update(@RequestParam(value = "pictureNames", required = false) String[] pictureNames,
                               @RequestParam(value = "content", required = true) String content,
                               @RequestParam(value = "type", required = true) String type,
                               @RequestParam(value = "title", required = true) String title,
                               @RequestParam(value = "id", required = true) Long id,
                               HttpServletRequest request) {

        //获取管理员账号
        String token = request.getHeader("token");
        String account = TokenUtil.getAccount(token);

        //清理文件夹，防止磁盘空间不足
        HashSet<String> names = new HashSet<>();
        System.out.println(pictureNames);
        if (pictureNames != null)
            for (int i = 0; i < pictureNames.length; i++) {
                names.add(pictureNames[i]);
            }
        //调用FileSave工具类方法清除没在富文本中的文件，节约空间。
        FileSave.CleanPicture("article/" + id, names);
        Healthyabout healthyabout = new Healthyabout(id, content, type, Long.valueOf(account), null, title);
        if (articleService.updateOne(healthyabout) <= 0)
            return new ResponseUtil(460, "文章修改失败", null);

        return new ResponseUtil(200, "文章修改成功", null);
    }

    @GetMapping("findAll")
    @ApiOperation(value = "查询当前属于当前管理员的所有文章")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "int", name = "curPage", value = "当前页数(默认为1)", required = false, dataType = "int"),
            @ApiImplicitParam(paramType = "int", name = "pageSize", value = "页面大小(默认为10)", required = false, dataType = "int")
    })
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "查询成功", response = AllArticleDTO.class),
            }
    )
    public AllArticleDTO findAllArticles(@RequestParam(value = "curPage", defaultValue = "1") int curPage,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         @RequestParam Map<String, Object> map,
                                         HttpServletRequest request) {
        //先获取token，用token获取当前管理员账号
        String token = request.getHeader("token");
        String account = TokenUtil.getAccount(token);
        map.put("adminid", Integer.valueOf(account));
        map.put("start", (curPage - 1) * pageSize);
        map.put("end", pageSize);
        List<Healthyabout1> list = articleService.findAll(map);
        return new AllArticleDTO(200, "查询成功", list);
    }

    @GetMapping("delete")
    @ApiOperation(value = "删除属于当前管理员的一篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "long", name = "id", value = "要删除的文章id", required = true, dataType = "long")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功", response = ResponseUtil.class),
            @ApiResponse(code = 460, message = "删除失败", response = ResponseUtil.class)
    })
    public ResponseUtil delete(@RequestParam(value = "id", required = true) long id,
                               HttpServletRequest request) {
        //先获取管理员账号
        String token = request.getHeader("token");
        String account = TokenUtil.getAccount(token);
        if (articleService.deleteOne(id, Integer.valueOf(account)) <= 0)
            return new ResponseUtil(460, "删除失败", null);
        return new ResponseUtil(200, "删除成功", null);
    }
}