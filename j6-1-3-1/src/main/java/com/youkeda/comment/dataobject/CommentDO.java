package main.java.com.youkeda.comment.dataobject;

import java.time.LocalDateTime;

public class CommentDO {
    //  自增主键
    private int id;
    //  关联外部内容的主键
    private String ref_id;
    //  关联用户表主键
    private String user_id;
    //  评论内容
    private String content;
    //  父评论ID
    private int parent_id;
    //  创建时间
    private LocalDateTime gmt_created;
    //  修改时间
    private LocalDateTime gmt_modified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRef_id() {
        return ref_id;
    }

    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public LocalDateTime getGmt_created() {
        return gmt_created;
    }

    public void setGmt_created(LocalDateTime gmt_created) {
        this.gmt_created = gmt_created;
    }

    public LocalDateTime getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(LocalDateTime gmt_modified) {
        this.gmt_modified = gmt_modified;
    }
}
