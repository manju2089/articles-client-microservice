package articles.microservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by manju on 28-06-2020.
 */
@Entity
@Table(name = "articles")

public class Article {

    private long articleId;
    private String articleName;


    private long blogId;

    public Article(){

    }

    public Article(String name, long blogId) {
        this.articleName = name;
        this.blogId = blogId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getArticleId() {
        return articleId;
    }
    public void setArticleId(long id) {
        this.articleId = id;
    }

    @Column(name = "articleName", nullable = false)
    public String getArticleName() {
        return articleName;
    }
    public void setArticleName(String name) {
        this.articleName = name;
    }


    @Column(name = "blogId", nullable = false)
    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogid) {
        this.blogId = blogid;
    }

}
